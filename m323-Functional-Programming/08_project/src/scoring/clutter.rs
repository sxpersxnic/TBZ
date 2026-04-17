use std::path::Path;
use crate::domain::state::{FileMetadata, State};

// --- Named scoring constants ---

/// Files larger than this are considered "large" and score extra clutter points.
const SIZE_LARGE_BYTES: u64 = 100_000_000; // 100 MB

/// Files larger than this are considered "very large".
const SIZE_VERY_LARGE_BYTES: u64 = 1_000_000_000; // 1 GB

/// Files unseen for longer than this are considered "old".
const AGE_OLD_SECONDS: i64 = 365 * 24 * 3600; // 1 year

/// Files unseen for longer than this are "very old".
const AGE_VERY_OLD_SECONDS: i64 = 3 * 365 * 24 * 3600; // 3 years

/// Points per scoring dimension.
const SCORE_LARGE_FILE: u32 = 25;
const SCORE_VERY_LARGE_FILE: u32 = 55;
const SCORE_OLD_FILE: u32 = 25;
const SCORE_VERY_OLD_FILE: u32 = 45;
const SCORE_CLUTTER_DIRECTORY: u32 = 20;

/// Well-known directories that accumulate unmanaged files.
const CLUTTER_DIRS: &[&str] = &["Downloads", "Desktop", "tmp", "temp", "Trash", ".Trash"];

// --- Public scoring functions (Phase 2 required signatures) ---

/// Score based solely on how long a file has been idle.
/// `now` and `last_seen` are Unix timestamps (seconds).  Pure.
pub fn age_score(now: i64, last_seen: i64) -> u32 {
    let age_seconds = now.saturating_sub(last_seen);
    if age_seconds >= AGE_VERY_OLD_SECONDS {
        SCORE_VERY_OLD_FILE
    } else if age_seconds >= AGE_OLD_SECONDS {
        SCORE_OLD_FILE
    } else {
        0
    }
}

/// Score based solely on file size (bucketed, no floating-point).  Pure.
pub fn size_score(size_bytes: u64) -> u32 {
    if size_bytes >= SIZE_VERY_LARGE_BYTES {
        SCORE_VERY_LARGE_FILE
    } else if size_bytes >= SIZE_LARGE_BYTES {
        SCORE_LARGE_FILE
    } else {
        0
    }
}

/// Penalty based solely on path location heuristics.  Pure.
pub fn location_penalty(path: &str) -> u32 {
    let is_clutter_dir = Path::new(path).components().any(|c| {
        let s = c.as_os_str().to_string_lossy();
        CLUTTER_DIRS.iter().any(|&dir| s == dir)
    });
    if is_clutter_dir { SCORE_CLUTTER_DIRECTORY } else { 0 }
}

/// Total clutter score for a single file.  Pure.
/// `now` is injected by the caller (never read from the clock here).
pub fn clutter_score_for_file(path: &str, meta: &FileMetadata, now: i64) -> u32 {
    size_score(meta.size_bytes) + age_score(now, meta.last_seen) + location_penalty(path)
}

// --- Explainability ---

fn describe_reasons(path: &str, meta: &FileMetadata, now: i64) -> String {
    let mut reasons: Vec<&str> = Vec::new();
    if meta.size_bytes >= SIZE_VERY_LARGE_BYTES {
        reasons.push("very large file");
    } else if meta.size_bytes >= SIZE_LARGE_BYTES {
        reasons.push("large file");
    }
    let age = now.saturating_sub(meta.last_seen);
    if age >= AGE_VERY_OLD_SECONDS {
        reasons.push("very old");
    } else if age >= AGE_OLD_SECONDS {
        reasons.push("old");
    }
    if location_penalty(path) > 0 {
        reasons.push("in clutter directory");
    }
    if reasons.is_empty() {
        "no significant factors".to_string()
    } else {
        reasons.join(", ")
    }
}

/// A file with its computed clutter score and human-readable reason.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct ClutterScore {
    pub path: String,
    pub score: u32,
    pub reason: String,
}

/// Derives raw clutter scores for every tracked file.  Pure.
/// `now` is injected explicitly — never reads the system clock.
/// Returns ALL files (no threshold applied); callers decide what to flag.
pub fn compute_clutter(state: &State, now: i64) -> Vec<ClutterScore> {
    state
        .files
        .iter()
        .map(|(path, meta)| {
            let p = path.to_string_lossy();
            ClutterScore {
                path: p.clone().into_owned(),
                score: clutter_score_for_file(&p, meta, now),
                reason: describe_reasons(&p, meta, now),
            }
        })
        .collect()
}
