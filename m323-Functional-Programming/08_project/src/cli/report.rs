use crate::domain::rules::Signal;
use crate::domain::state::State;
use crate::scoring::clutter::ClutterScore;

/// Formats the state summary header.  Pure: takes data, returns a String.
pub fn format_state_summary(state: &State) -> String {
    format!(
        "=== Helios Report ===\nTracked files : {}\nLatest event  : t={}",
        state.files.len(),
        state.latest_timestamp,
    )
}

/// Formats a single Signal as a human-readable line.
pub fn format_signal(signal: &Signal) -> String {
    match signal {
        Signal::HighClutter { path, score, reason } => {
            format!("  [CLUTTER  score={:>3}] {} — {}", score, path, reason)
        }
        Signal::HighDensity { path, file_count, score } => {
            format!("  [DENSITY  score={:>3}] {} ({} files)", score, path, file_count)
        }
        Signal::EmptyFilesystem => {
            "  [WARNING          ] Filesystem is empty — no files tracked.".to_string()
        }
        Signal::LargeFileCount { count } => {
            format!("  [LARGE FILES  {:>3}] {} files above 500 MB detected.", count, count)
        }
    }
}

/// Formats a full textual report from state and derived signals.  Pure.
pub fn format_report(state: &State, signals: &[Signal]) -> String {
    let header = format_state_summary(state);

    let body = if signals.is_empty() {
        "No findings.".to_string()
    } else {
        let lines: Vec<String> = signals.iter().map(format_signal).collect();
        format!("Findings ({}):\n{}", signals.len(), lines.join("\n"))
    };

    format!("{}\n\n{}", header, body)
}

/// Formats a list of raw clutter scores (used when the CLI prints a diff result).
pub fn format_clutter_list(scores: &[ClutterScore]) -> String {
    if scores.is_empty() {
        return "No clutter detected.".to_string();
    }
    let mut sorted = scores.to_vec();
    sorted.sort_by(|a, b| b.score.cmp(&a.score));
    let lines: Vec<String> = sorted
        .iter()
        .map(|cs| format!("  score={:>3}  {} ({})", cs.score, cs.path, cs.reason))
        .collect();
    format!("Clutter report ({} files):\n{}", sorted.len(), lines.join("\n"))
}
