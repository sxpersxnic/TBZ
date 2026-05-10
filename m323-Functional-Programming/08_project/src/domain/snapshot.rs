use std::path::PathBuf;

/// Represents a snapshot of the file system at a given point in time.
#[derive(Debug, Clone, serde::Deserialize)]
pub struct SnapshotEntry {
    pub path: PathBuf,
    pub size_bytes: u64,
    pub timestamp: i64,
}

/// Represents a snapshot of the file system at a given point in time.
#[derive(Debug, Clone, serde::Deserialize)]
pub struct Snapshot {
    pub entries: Vec<SnapshotEntry>,
}