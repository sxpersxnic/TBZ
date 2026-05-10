use crate::domain::event::Event;
use crate::domain::state::{State, initial_state};
use crate::engine::reducer::reduce;

/// Folds an event log from the initial state, producing the final derived state.
/// Deterministic: the same event sequence always produces the same state.
pub fn replay(events: &[Event]) -> State {
    events.iter().fold(initial_state(), |state, event| reduce(&state, event))
}

/// Replays only events whose timestamp is ≤ `cutoff_ts`.
/// Filtering happens *before* the fold — reducers are never aware of the cutoff.
/// Ordering is preserved; the result is identical to a full replay on the
/// filtered sub-sequence.
pub fn replay_until(events: &[Event], cutoff_ts: i64) -> State {
    events
        .iter()
        .filter(|e| e.timestamp() <= cutoff_ts)
        .fold(initial_state(), |state, event| reduce(&state, event))
}
