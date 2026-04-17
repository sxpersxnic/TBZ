use crate::domain::state::State;
use crate::scoring::clutter::compute_clutter;
use crate::scoring::density::compute_density;

/// Files scoring at or above this threshold are flagged as high-clutter.
/// Combines size + age + location: a large, old file in Downloads scores exactly 70.
const HIGH_CLUTTER_THRESHOLD: u32 = 70;

/// Directories whose density score meets this threshold are flagged.
/// density = file_count * avg_score / 10; threshold of 30 means e.g.
/// 5 files averaging 60 score → density 30 → flagged.
const HIGH_DENSITY_THRESHOLD: u32 = 30;

/// Files above this count (all >= 500 MB) trigger a bulk-large-files warning.
const LARGE_FILE_BYTES: u64 = 500_000_000; // 500 MB
const LARGE_FILE_COUNT_THRESHOLD: usize = 5;

/// A signal is a named finding produced by a rule evaluation.
#[derive(Debug, Clone, PartialEq, Eq)]
pub enum Signal {
    /// A single file is cluttered (high individual score).
    HighClutter { path: String, score: u32, reason: String },
    /// A directory contains many medium-risk files (high collective score).
    HighDensity { path: String, file_count: u32, score: u32 },
    EmptyFilesystem,
    LargeFileCount { count: usize },
}

/// A rule is a pure function: State → findings.
/// Rules are independent and composable; `evaluate_rules` gathers them all.
pub type Rule = fn(&State) -> Vec<Signal>;

// --- Concrete rules ---

/// Flags every file whose clutter score meets HIGH_CLUTTER_THRESHOLD.
/// `now` is sourced from `state.latest_timestamp` — derived from the event log,
/// never from the system clock.
pub fn rule_high_clutter(state: &State) -> Vec<Signal> {
    let now = state.latest_timestamp;
    compute_clutter(state, now)
        .into_iter()
        .filter(|cs| cs.score >= HIGH_CLUTTER_THRESHOLD)
        .map(|cs| Signal::HighClutter {
            path: cs.path,
            score: cs.score,
            reason: cs.reason,
        })
        .collect()
}

/// Flags directories where the collective density score meets HIGH_DENSITY_THRESHOLD.
/// Density = file_count × avg_file_clutter / normalization.
/// `now` is sourced from `state.latest_timestamp`.
pub fn rule_high_density(state: &State) -> Vec<Signal> {
    let now = state.latest_timestamp;
    let file_scores = compute_clutter(state, now);
    compute_density(&file_scores)
        .into_iter()
        .filter(|fd| fd.density >= HIGH_DENSITY_THRESHOLD)
        .map(|fd| Signal::HighDensity {
            path: fd.path,
            file_count: fd.file_count,
            score: fd.density,
        })
        .collect()
}

/// Flags an empty filesystem (useful for catching load/replay errors).
pub fn rule_empty_filesystem(state: &State) -> Vec<Signal> {
    if state.files.is_empty() {
        vec![Signal::EmptyFilesystem]
    } else {
        vec![]
    }
}

/// Flags when many large files are tracked simultaneously.
pub fn rule_many_large_files(state: &State) -> Vec<Signal> {
    let count = state
        .files
        .values()
        .filter(|m| m.size_bytes >= LARGE_FILE_BYTES)
        .count();
    if count >= LARGE_FILE_COUNT_THRESHOLD {
        vec![Signal::LargeFileCount { count }]
    } else {
        vec![]
    }
}

/// Default rule set used by the CLI.
pub const DEFAULT_RULES: &[Rule] = &[
    rule_high_clutter,
    rule_high_density,
    rule_empty_filesystem,
    rule_many_large_files,
];

/// Evaluates all rules against a state and collects every signal produced.
/// No ordering, no priorities, no short-circuiting — pure aggregation via flat_map.
pub fn evaluate_rules(state: &State, rules: &[Rule]) -> Vec<Signal> {
    rules.iter().flat_map(|rule| rule(state)).collect()
}

/// Backward-compatible alias for `evaluate_rules`.
pub fn apply_rules(rules: &[Rule], state: &State) -> Vec<Signal> {
    evaluate_rules(state, rules)
}
