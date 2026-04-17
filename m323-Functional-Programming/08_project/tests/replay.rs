use std::path::PathBuf;
use helios::domain::event::Event;
use helios::domain::rules::{Signal, evaluate_rules, rule_high_clutter, rule_high_density, rule_empty_filesystem};
use helios::domain::state::initial_state;
use helios::engine::reducer::reduce;
use helios::engine::replay::replay;
use helios::engine::diff::diff;
use helios::domain::snapshot::{Snapshot, SnapshotEntry};
use helios::scoring::clutter::{age_score, size_score, location_penalty, clutter_score_for_file, compute_clutter};
use helios::scoring::density::{density_score, compute_density};
use helios::engine::replay::replay_until;

fn path(s: &str) -> PathBuf { PathBuf::from(s) }

// --- Reducer unit tests ---

#[test]
fn file_added_appears_in_state() {
    let s0 = initial_state();
    let event = Event::FileAdded { path: path("/home/user/doc.txt"), size_bytes: 1024, timestamp: 100 };
    let s1 = reduce(&s0, &event);

    assert!(s1.files.contains_key(&path("/home/user/doc.txt")));
    assert_eq!(s1.files[&path("/home/user/doc.txt")].size_bytes, 1024);
    assert_eq!(s1.latest_timestamp, 100);
}

#[test]
fn file_removed_disappears_from_state() {
    let s0 = initial_state();
    let add = Event::FileAdded { path: path("/tmp/old.log"), size_bytes: 512, timestamp: 10 };
    let remove = Event::FileRemoved { path: path("/tmp/old.log"), timestamp: 20 };
    let s1 = reduce(&s0, &add);
    let s2 = reduce(&s1, &remove);

    assert!(!s2.files.contains_key(&path("/tmp/old.log")));
    assert_eq!(s2.latest_timestamp, 20);
}

#[test]
fn file_moved_changes_path() {
    let s0 = initial_state();
    let add = Event::FileAdded { path: path("/a/file.txt"), size_bytes: 2048, timestamp: 1 };
    let mv = Event::FileMoved { from: path("/a/file.txt"), to: path("/b/file.txt"), timestamp: 2 };
    let s1 = reduce(&s0, &add);
    let s2 = reduce(&s1, &mv);

    assert!(!s2.files.contains_key(&path("/a/file.txt")));
    assert!(s2.files.contains_key(&path("/b/file.txt")));
    assert_eq!(s2.files[&path("/b/file.txt")].size_bytes, 2048);
}

#[test]
fn move_unknown_file_leaves_state_unchanged() {
    let s0 = initial_state();
    let mv = Event::FileMoved { from: path("/ghost"), to: path("/nowhere"), timestamp: 99 };
    let s1 = reduce(&s0, &mv);
    assert!(s1.files.is_empty());
    assert_eq!(s1.latest_timestamp, 99);
}

// --- Replay tests ---

#[test]
fn replay_empty_events_is_initial_state() {
    let state = replay(&[]);
    assert_eq!(state, initial_state());
}

#[test]
fn replay_is_deterministic() {
    let events = vec![
        Event::FileAdded { path: path("/x"), size_bytes: 1, timestamp: 1 },
        Event::FileAdded { path: path("/y"), size_bytes: 2, timestamp: 2 },
        Event::FileRemoved { path: path("/x"), timestamp: 3 },
    ];
    assert_eq!(replay(&events), replay(&events));
}

#[test]
fn replay_produces_correct_final_state() {
    let events = vec![
        Event::FileAdded { path: path("/a"), size_bytes: 10, timestamp: 1 },
        Event::FileAdded { path: path("/b"), size_bytes: 20, timestamp: 2 },
        Event::FileRemoved { path: path("/a"), timestamp: 3 },
    ];
    let state = replay(&events);
    assert_eq!(state.files.len(), 1);
    assert!(state.files.contains_key(&path("/b")));
    assert_eq!(state.latest_timestamp, 3);
}

// --- Snapshot diff tests ---

fn make_snapshot(entries: Vec<(&str, u64, i64)>) -> Snapshot {
    Snapshot {
        entries: entries
            .into_iter()
            .map(|(p, size, ts)| SnapshotEntry { path: path(p), size_bytes: size, timestamp: ts })
            .collect(),
    }
}

#[test]
fn diff_added_files_are_detected() {
    let before = make_snapshot(vec![]);
    let after = make_snapshot(vec![("/new.txt", 100, 50)]);
    let events = diff(&before, &after);
    assert_eq!(events.len(), 1);
    assert!(matches!(&events[0], Event::FileAdded { path: p, .. } if p == &path("/new.txt")));
}

#[test]
fn diff_removed_files_are_detected() {
    let before = make_snapshot(vec![("/gone.txt", 200, 10)]);
    let after = make_snapshot(vec![]);
    let events = diff(&before, &after);
    assert_eq!(events.len(), 1);
    assert!(matches!(&events[0], Event::FileRemoved { path: p, .. } if *p == path("/gone.txt")));
}

#[test]
fn diff_moved_files_are_detected() {
    let before = make_snapshot(vec![("/old/file.zip", 999, 5)]);
    let after = make_snapshot(vec![("/new/file.zip", 999, 10)]);
    let events = diff(&before, &after);
    assert_eq!(events.len(), 1);
    assert!(matches!(
        &events[0],
        Event::FileMoved { from, to, .. }
        if from == &path("/old/file.zip") && to == &path("/new/file.zip")
    ));
}

#[test]
fn diff_unchanged_files_produce_no_events() {
    let snap = make_snapshot(vec![("/same.txt", 42, 1)]);
    let events = diff(&snap, &snap);
    assert!(events.is_empty());
}

// --- Rule tests ---

#[test]
fn rule_empty_filesystem_fires_on_empty_state() {
    let signals = rule_empty_filesystem(&initial_state());
    assert_eq!(signals, vec![Signal::EmptyFilesystem]);
}

#[test]
fn rule_empty_filesystem_silent_when_files_present() {
    let state = replay(&[Event::FileAdded {
        path: path("/something"),
        size_bytes: 1,
        timestamp: 1,
    }]);
    let signals = rule_empty_filesystem(&state);
    assert!(signals.is_empty());
}

#[test]
fn rule_high_clutter_flags_large_old_file_in_downloads() {
    // File in Downloads, 200 MB, seen 2 years ago (latest_timestamp is 2 years later)
    let two_years = 2 * 365 * 24 * 3600_i64;
    let events = vec![Event::FileAdded {
        path: path("/home/user/Downloads/bigfile.iso"),
        size_bytes: 200_000_000,
        timestamp: 0,
    }];
    // Simulate time passing by adding a dummy event with a later timestamp
    let mut events_with_time = events.clone();
    events_with_time.push(Event::FileAdded {
        path: path("/home/user/marker"),
        size_bytes: 1,
        timestamp: two_years,
    });
    let state = replay(&events_with_time);
    let signals = rule_high_clutter(&state);
    let has_clutter = signals.iter().any(|s| matches!(s, Signal::HighClutter { path, .. } if path.contains("bigfile.iso")));
    assert!(has_clutter, "Expected HighClutter signal for bigfile.iso");
}

#[test]
fn evaluate_rules_combines_all_signals() {
    let rules: &[helios::domain::rules::Rule] = &[rule_empty_filesystem];
    let signals = evaluate_rules(&initial_state(), rules);
    assert!(!signals.is_empty());
}

// ============================================================
// Phase 2 — Scoring unit tests
// Tests read like math: input → expected output, no IO.
// ============================================================

// --- age_score ---

#[test]
fn age_score_zero_when_file_is_recent() {
    let now = 1_000_000;
    let last_seen = 999_999; // 1 second ago
    assert_eq!(age_score(now, last_seen), 0);
}

#[test]
fn age_score_old_when_idle_one_year() {
    let one_year = 365 * 24 * 3600_i64;
    assert_eq!(age_score(one_year, 0), 25);
}

#[test]
fn age_score_very_old_when_idle_three_years() {
    let three_years = 3 * 365 * 24 * 3600_i64;
    assert_eq!(age_score(three_years, 0), 45);
}

#[test]
fn age_score_never_negative_when_clock_runs_backward() {
    // last_seen in the future relative to now — saturating_sub clamps to 0
    assert_eq!(age_score(0, 9_999_999), 0);
}

// --- size_score ---

#[test]
fn size_score_zero_for_small_file() {
    assert_eq!(size_score(1_024), 0);
}

#[test]
fn size_score_large_for_100mb() {
    assert_eq!(size_score(100_000_000), 25);
}

#[test]
fn size_score_very_large_for_1gb() {
    assert_eq!(size_score(1_000_000_000), 55);
}

#[test]
fn size_score_boundary_just_below_large_is_zero() {
    assert_eq!(size_score(99_999_999), 0);
}

// --- location_penalty ---

#[test]
fn location_penalty_zero_for_deep_project_path() {
    assert_eq!(location_penalty("/home/user/projects/app/src/main.rs"), 0);
}

#[test]
fn location_penalty_applied_in_downloads() {
    assert_eq!(location_penalty("/home/user/Downloads/archive.zip"), 20);
}

#[test]
fn location_penalty_applied_in_tmp() {
    assert_eq!(location_penalty("/tmp/scratch.txt"), 20);
}

#[test]
fn location_penalty_applied_in_desktop() {
    assert_eq!(location_penalty("/Users/alice/Desktop/report.pdf"), 20);
}

// --- clutter_score_for_file ---

#[test]
fn clutter_score_small_recent_file_in_project_is_zero() {
    use helios::domain::state::FileMetadata;
    let meta = FileMetadata { size_bytes: 512, last_seen: 999 };
    assert_eq!(clutter_score_for_file("/home/user/project/code.rs", &meta, 1_000), 0);
}

#[test]
fn clutter_score_large_old_downloads_file_reaches_threshold() {
    use helios::domain::state::FileMetadata;
    let one_year = 365 * 24 * 3600_i64;
    let meta = FileMetadata { size_bytes: 100_000_000, last_seen: 0 };
    // size=25 + age=25 + location=20 = 70
    assert_eq!(clutter_score_for_file("/home/user/Downloads/big.iso", &meta, one_year), 70);
}

#[test]
fn clutter_score_very_large_very_old_file_scores_high() {
    use helios::domain::state::FileMetadata;
    let three_years = 3 * 365 * 24 * 3600_i64;
    let meta = FileMetadata { size_bytes: 2_000_000_000, last_seen: 0 };
    // size=55 + age=45 = 100 (no location penalty for /data)
    assert_eq!(clutter_score_for_file("/data/backup.tar.gz", &meta, three_years), 100);
}

// --- compute_clutter (returns all files, no threshold) ---

#[test]
fn compute_clutter_returns_all_files_including_zero_score() {
    let state = replay(&[Event::FileAdded {
        path: path("/tiny/file.txt"),
        size_bytes: 10,
        timestamp: 100,
    }]);
    let scores = compute_clutter(&state, 101);
    assert_eq!(scores.len(), 1);
    assert_eq!(scores[0].score, 0);
}

#[test]
fn compute_clutter_is_deterministic() {
    let events = vec![
        Event::FileAdded { path: path("/a"), size_bytes: 100_000_000, timestamp: 0 },
        Event::FileAdded { path: path("/b"), size_bytes: 5_000, timestamp: 0 },
    ];
    let state = replay(&events);
    let now = 365 * 24 * 3600_i64;
    assert_eq!(compute_clutter(&state, now), compute_clutter(&state, now));
}

// --- Rule evaluation tests ---

#[test]
fn rule_high_clutter_threshold_is_70() {
    // A file scoring exactly 70 must be flagged; one scoring 0 must not.
    let one_year = 365 * 24 * 3600_i64;

    // score = size(25) + age(25) + location(20) = 70  → flagged
    let state_flagged = replay(&[
        Event::FileAdded {
            path: path("/home/user/Downloads/big.iso"),
            size_bytes: 100_000_000,
            timestamp: 0,
        },
        // marker to advance latest_timestamp to one_year
        Event::FileAdded { path: path("/marker"), size_bytes: 1, timestamp: one_year },
    ]);
    let signals = rule_high_clutter(&state_flagged);
    assert!(
        signals.iter().any(|s| matches!(s, Signal::HighClutter { path, .. } if path.contains("big.iso"))),
        "Expected HighClutter for big.iso (score 70)"
    );

    // score = size(0) + age(0) + location(0) = 0  → not flagged
    let state_clean = replay(&[Event::FileAdded {
        path: path("/home/user/projects/app.rs"),
        size_bytes: 1_024,
        timestamp: 0,
    }]);
    assert!(rule_high_clutter(&state_clean).is_empty());
}

#[test]
fn evaluate_rules_is_deterministic_across_replays() {
    let events = vec![
        Event::FileAdded {
            path: path("/home/user/Downloads/dump.zip"),
            size_bytes: 200_000_000,
            timestamp: 0,
        },
        Event::FileAdded { path: path("/marker"), size_bytes: 1, timestamp: 365 * 24 * 3600 },
    ];
    let state1 = replay(&events);
    let state2 = replay(&events);
    use helios::domain::rules::DEFAULT_RULES;
    assert_eq!(
        evaluate_rules(&state1, DEFAULT_RULES),
        evaluate_rules(&state2, DEFAULT_RULES),
    );
}

// ============================================================
// Phase 3 — Density scoring and time-travel replay
// ============================================================

// --- density_score ---

#[test]
fn density_score_zero_when_no_files() {
    assert_eq!(density_score(0, 0), 0);
}

#[test]
fn density_score_5_files_avg_70() {
    // file_count=5, total=350, avg=70, density = 5*70/10 = 35
    assert_eq!(density_score(5, 350), 35);
}

#[test]
fn density_score_3_files_max_score() {
    // file_count=3, total=300 (avg=100), density = 3*100/10 = 30
    assert_eq!(density_score(3, 300), 30);
}

// --- compute_density (folder grouping) ---

#[test]
fn compute_density_groups_by_parent_directory() {
    let one_year = 365 * 24 * 3600_i64;
    // Three files in the same directory
    let events = vec![
        Event::FileAdded { path: path("/data/dir/a.zip"), size_bytes: 100_000_000, timestamp: 0 },
        Event::FileAdded { path: path("/data/dir/b.zip"), size_bytes: 100_000_000, timestamp: 0 },
        Event::FileAdded { path: path("/data/dir/c.zip"), size_bytes: 100_000_000, timestamp: 0 },
        Event::FileAdded { path: path("/other/x.txt"),    size_bytes: 1,            timestamp: 0 },
        Event::FileAdded { path: path("/marker"),         size_bytes: 1,            timestamp: one_year },
    ];
    let state = replay(&events);
    let file_scores = compute_clutter(&state, state.latest_timestamp);
    let densities = compute_density(&file_scores);

    let dir_entry = densities.iter().find(|fd| fd.path == "/data/dir");
    assert!(dir_entry.is_some(), "Expected a density entry for /data/dir");
    let entry = dir_entry.unwrap();
    assert_eq!(entry.file_count, 3);

    // /other has only 1 file — must NOT appear (below MIN_FILES_FOR_DENSITY)
    assert!(!densities.iter().any(|fd| fd.path == "/other"));
}

#[test]
fn compute_density_single_file_directory_excluded() {
    let state = replay(&[Event::FileAdded {
        path: path("/solo/file.txt"),
        size_bytes: 500_000_000,
        timestamp: 0,
    }]);
    let file_scores = compute_clutter(&state, 0);
    let densities = compute_density(&file_scores);
    assert!(densities.is_empty(), "Single-file directory must not appear in density output");
}

#[test]
fn compute_density_is_deterministic() {
    let one_year = 365 * 24 * 3600_i64;
    let events = vec![
        Event::FileAdded { path: path("/dir/a"), size_bytes: 100_000_000, timestamp: 0 },
        Event::FileAdded { path: path("/dir/b"), size_bytes: 100_000_000, timestamp: 0 },
        Event::FileAdded { path: path("/marker"), size_bytes: 1, timestamp: one_year },
    ];
    let state = replay(&events);
    let scores = compute_clutter(&state, state.latest_timestamp);
    assert_eq!(compute_density(&scores), compute_density(&scores));
}

// --- rule_high_density ---

#[test]
fn rule_high_density_flags_cluttered_directory() {
    let one_year = 365 * 24 * 3600_i64;
    // 5 large old files in same dir → density = 5 * avg / 10 should exceed threshold
    let events: Vec<_> = (0..5)
        .map(|i| Event::FileAdded {
            path: path(&format!("/Downloads/file{}.iso", i)),
            size_bytes: 100_000_000,  // large
            timestamp: 0,
        })
        .chain(std::iter::once(Event::FileAdded {
            path: path("/marker"),
            size_bytes: 1,
            timestamp: one_year,
        }))
        .collect();

    let state = replay(&events);
    let signals = rule_high_density(&state);

    assert!(
        signals.iter().any(|s| matches!(s, Signal::HighDensity { path, .. } if path == "/Downloads")),
        "Expected HighDensity signal for /Downloads"
    );
}

#[test]
fn rule_high_density_clean_directory_produces_no_signal() {
    // 3 tiny recent files — density will be 0
    let events = vec![
        Event::FileAdded { path: path("/clean/a.txt"), size_bytes: 100, timestamp: 1 },
        Event::FileAdded { path: path("/clean/b.txt"), size_bytes: 100, timestamp: 2 },
        Event::FileAdded { path: path("/clean/c.txt"), size_bytes: 100, timestamp: 3 },
    ];
    let state = replay(&events);
    assert!(rule_high_density(&state).is_empty());
}

// --- replay_until (time-travel) ---

#[test]
fn replay_until_excludes_events_after_cutoff() {
    let events = vec![
        Event::FileAdded { path: path("/early"), size_bytes: 1, timestamp: 10 },
        Event::FileAdded { path: path("/late"),  size_bytes: 1, timestamp: 100 },
    ];
    let state = replay_until(&events, 50);
    assert!(state.files.contains_key(&path("/early")));
    assert!(!state.files.contains_key(&path("/late")));
}

#[test]
fn replay_until_with_max_cutoff_equals_full_replay() {
    let events = vec![
        Event::FileAdded { path: path("/a"), size_bytes: 10, timestamp: 1 },
        Event::FileAdded { path: path("/b"), size_bytes: 20, timestamp: 9_999 },
    ];
    assert_eq!(replay(&events), replay_until(&events, i64::MAX));
}

#[test]
fn replay_until_preserves_event_ordering() {
    // Add then remove within cutoff; file must be absent in result
    let events = vec![
        Event::FileAdded   { path: path("/f"), size_bytes: 1, timestamp: 1 },
        Event::FileRemoved { path: path("/f"), timestamp: 2 },
        Event::FileAdded   { path: path("/f"), size_bytes: 2, timestamp: 100 },
    ];
    let state = replay_until(&events, 50);
    // Events at t=1 and t=2 included; t=100 excluded → file absent
    assert!(!state.files.contains_key(&path("/f")));
}

#[test]
fn replay_until_differs_from_full_replay() {
    let events = vec![
        Event::FileAdded { path: path("/before"), size_bytes: 1, timestamp: 5 },
        Event::FileAdded { path: path("/after"),  size_bytes: 1, timestamp: 50 },
    ];
    let full  = replay(&events);
    let cutoff = replay_until(&events, 10);
    assert_ne!(full, cutoff, "Full and cutoff replay must produce different states");
}

#[test]
fn replay_until_is_deterministic() {
    let events = vec![
        Event::FileAdded { path: path("/x"), size_bytes: 1, timestamp: 1 },
        Event::FileAdded { path: path("/y"), size_bytes: 2, timestamp: 5 },
        Event::FileAdded { path: path("/z"), size_bytes: 3, timestamp: 10 },
    ];
    assert_eq!(replay_until(&events, 5), replay_until(&events, 5));
}
