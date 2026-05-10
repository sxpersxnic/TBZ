use std::path::PathBuf;

/// Represents a file system event, such as adding, removing, or moving a file.
#[derive(Debug, Clone, PartialEq, Eq, serde::Serialize, serde::Deserialize)]
pub enum Event {
    FileAdded {
        path: PathBuf,
        size_bytes: u64,
        timestamp: i64,
    },
    FileRemoved {
        path: PathBuf,
        timestamp: i64,
    },
    FileMoved {
        from: PathBuf,
        to: PathBuf,
        timestamp: i64,
    },
}

impl Event {
    /// Returns the timestamp of any event variant.  Pure, no IO.
    pub fn timestamp(&self) -> i64 {
        match self {
            Event::FileAdded { timestamp, .. } => *timestamp,
            Event::FileRemoved { timestamp, .. } => *timestamp,
            Event::FileMoved { timestamp, .. } => *timestamp,
        }
    }
}