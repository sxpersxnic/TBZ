use std::collections::BTreeMap;
use std::path::PathBuf;

/// Metadata about a file, derived from events and snapshots.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct FileMetadata {
    pub size_bytes: u64,
    pub last_seen: i64,
}

/// Immutable system state derived entirely from the event log.
/// `latest_timestamp` is the maximum event timestamp seen so far,
/// allowing pure rules to compute file age without touching the clock.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct State {
    pub files: BTreeMap<PathBuf, FileMetadata>,
    pub latest_timestamp: i64,
}

/// Creates an initial empty state with no files and a timestamp of 0.
pub fn initial_state() -> State {
    State {
        files: BTreeMap::new(),
        latest_timestamp: 0,
    }
}
