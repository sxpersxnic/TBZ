use std::collections::{BTreeMap, BTreeSet};
use crate::domain::event::Event;
use crate::domain::snapshot::{Snapshot, SnapshotEntry};

/// Pure function: compares two filesystem snapshots and derives the minimal
/// set of domain events that explains the difference.
///
/// Move detection heuristic: a file is considered moved when a path disappears
/// and another path appears with the exact same `size_bytes`.  The first
/// matching candidate is chosen; ambiguous cases fall back to Remove + Add.
pub fn diff(before: &Snapshot, after: &Snapshot) -> Vec<Event> {
    let before_map: BTreeMap<_, _> = before
        .entries
        .iter()
        .map(|e| (e.path.clone(), e))
        .collect();

    let after_map: BTreeMap<_, _> = after
        .entries
        .iter()
        .map(|e| (e.path.clone(), e))
        .collect();

    let removed_paths: BTreeSet<_> = before_map
        .keys()
        .filter(|p| !after_map.contains_key(*p))
        .cloned()
        .collect();

    let added_paths: BTreeSet<_> = after_map
        .keys()
        .filter(|p| !before_map.contains_key(*p))
        .cloned()
        .collect();

    // Index added entries by size for move detection
    let added_by_size: BTreeMap<u64, Vec<&SnapshotEntry>> =
        added_paths.iter().fold(BTreeMap::new(), |mut acc, path| {
            let entry = after_map[path];
            acc.entry(entry.size_bytes).or_default().push(entry);
            acc
        });

    let mut matched_added_paths: BTreeSet<_> = BTreeSet::new();
    let mut events: Vec<Event> = Vec::new();

    // Detect moves: removed path + added path with same size
    let moves: Vec<(_, _)> = removed_paths
        .iter()
        .filter_map(|from_path| {
            let from_entry = before_map[from_path];
            let candidates = added_by_size.get(&from_entry.size_bytes)?;
            let to_entry = candidates
                .iter()
                .find(|e| !matched_added_paths.contains(&e.path))?;
            Some((from_path.clone(), (*to_entry).clone()))
        })
        .collect();

    for (from_path, to_entry) in moves {
        matched_added_paths.insert(to_entry.path.clone());
        events.push(Event::FileMoved {
            from: from_path,
            to: to_entry.path.clone(),
            timestamp: to_entry.timestamp,
        });
    }

    // Remaining removals (not part of a move)
    let move_froms: BTreeSet<_> = events
        .iter()
        .filter_map(|e| {
            if let Event::FileMoved { from, .. } = e { Some(from.clone()) } else { None }
        })
        .collect();

    let removals = removed_paths.iter().filter(|p| !move_froms.contains(*p)).map(|path| {
        let entry = before_map[path];
        Event::FileRemoved { path: path.clone(), timestamp: entry.timestamp }
    });

    // Remaining additions (not part of a move)
    let additions = added_paths
        .iter()
        .filter(|p| !matched_added_paths.contains(*p))
        .map(|path| {
            let entry = after_map[path];
            Event::FileAdded {
                path: path.clone(),
                size_bytes: entry.size_bytes,
                timestamp: entry.timestamp,
            }
        });

    events.extend(removals);
    events.extend(additions);

    // Sort by timestamp so the event stream is in causal order
    events.sort_by_key(|e| e.timestamp());

    events
}
