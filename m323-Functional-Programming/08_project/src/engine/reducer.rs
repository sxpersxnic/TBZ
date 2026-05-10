use crate::domain::event::Event;
use crate::domain::state::{FileMetadata, State};

/// Pure reducer: given an immutable state and an event, returns the next state.
/// No mutation, no side effects, no IO.
pub fn reduce(state: &State, event: &Event) -> State {
    match event {
        Event::FileAdded { path, size_bytes, timestamp } => {
            let mut files = state.files.clone();
            files.insert(
                path.clone(),
                FileMetadata { size_bytes: *size_bytes, last_seen: *timestamp },
            );
            State {
                files,
                latest_timestamp: state.latest_timestamp.max(*timestamp),
            }
        }

        Event::FileRemoved { path, timestamp } => {
            let mut files = state.files.clone();
            files.remove(path);
            State {
                files,
                latest_timestamp: state.latest_timestamp.max(*timestamp),
            }
        }

        Event::FileMoved { from, to, timestamp } => {
            let mut files = state.files.clone();
            if let Some(meta) = files.remove(from) {
                let updated_meta = FileMetadata { last_seen: *timestamp, ..meta };
                files.insert(to.clone(), updated_meta);
            }
            State {
                files,
                latest_timestamp: state.latest_timestamp.max(*timestamp),
            }
        }
    }
}
