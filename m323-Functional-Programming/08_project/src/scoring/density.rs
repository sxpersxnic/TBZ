use std::collections::BTreeMap;
use std::path::Path;
use crate::scoring::clutter::ClutterScore;

// --- Named constants ---

/// Divisor that scales `file_count * avg_score` down to a comparable range.
const DENSITY_NORMALIZATION: u32 = 10;

/// Minimum number of files a directory must contain to be evaluated.
/// Single files are assessed by the file-level rule, not the density rule.
const MIN_FILES_FOR_DENSITY: u32 = 2;

// --- Data model ---

/// Derived folder-level density metric.  Never stored in State.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct FolderDensity {
    pub path: String,
    pub file_count: u32,
    pub avg_score: u32,
    pub density: u32,
}

// --- Pure functions ---

/// Extracts the immediate parent directory string from a file path.
fn parent_dir(path: &str) -> String {
    Path::new(path)
        .parent()
        .and_then(|p| p.to_str())
        .unwrap_or("/")
        .to_owned()
}

/// Computes the density score for a directory.
/// Formula: `file_count * average_score / DENSITY_NORMALIZATION`.
/// Integer arithmetic throughout — no floating-point.
pub fn density_score(file_count: u32, total_score: u32) -> u32 {
    if file_count == 0 {
        return 0;
    }
    let avg = total_score / file_count;
    file_count * avg / DENSITY_NORMALIZATION
}

/// Groups file-level clutter scores by parent directory and computes
/// folder-level density metrics.  Pure: no IO, no mutation of input.
///
/// Only directories with at least `MIN_FILES_FOR_DENSITY` files are included.
pub fn compute_density(scores: &[ClutterScore]) -> Vec<FolderDensity> {
    // Accumulate (total_score, file_count) per parent directory
    let grouped: BTreeMap<String, (u32, u32)> =
        scores.iter().fold(BTreeMap::new(), |mut acc, cs| {
            let dir = parent_dir(&cs.path);
            let entry = acc.entry(dir).or_insert((0, 0));
            entry.0 += cs.score;
            entry.1 += 1;
            acc
        });

    grouped
        .into_iter()
        .filter(|(_, (_, count))| *count >= MIN_FILES_FOR_DENSITY)
        .map(|(dir, (total, count))| FolderDensity {
            path: dir,
            file_count: count,
            avg_score: total / count,
            density: density_score(count, total),
        })
        .collect()
}
