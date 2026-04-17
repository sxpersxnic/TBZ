# Helios CLI

Helios is a deterministic filesystem analysis CLI written in Rust. It compares filesystem snapshots, replays event logs, derives a final state, and prints rule-based findings about clutter, density, empty states, and unusually large files.

The project is intentionally structured as a functional core with an imperative shell:

- the CLI handles file loading, argument parsing, and terminal output
- the domain, engine, and scoring layers are pure and deterministic
- business decisions are expressed as data transformations instead of hidden side effects

## What the CLI does

Helios currently exposes two commands:

```bash
helios diff <before.json> <after.json>
helios replay <events.json> [--until <timestamp>]
```

### `diff`

`diff` compares two snapshot files and derives the minimal event stream that explains the change.

Snapshot entries contain a path, file size, and timestamp. Helios turns the difference into domain events such as:

- `FileAdded`
- `FileRemoved`
- `FileMoved`

After deriving events, Helios replays them into a state and evaluates the default rules before printing a report.

### `replay`

`replay` reads a JSON event log and folds it into a final state.

Use `--until <timestamp>` to replay only events whose timestamp is less than or equal to the cutoff. The cutoff is applied before the reducer runs, so the reducer stays unaware of CLI-specific filtering.

## Input formats

### Snapshot JSON

Snapshots are JSON objects with an `entries` array:

```json
{
	"entries": [
		{
			"path": "/home/user/Downloads/big.iso",
			"size_bytes": 200000000,
			"timestamp": 1710000000
		}
	]
}
```

### Event log JSON

Event logs are arrays of domain events:

```json
[
	{
		"FileAdded": {
			"path": "/home/user/Downloads/big.iso",
			"size_bytes": 200000000,
			"timestamp": 1710000000
		}
	}
]
```

## Functional Programming Principles Used Here

Helios applies several functional-programming principles consistently across the codebase.

### 1. Pure functions

Most core functions are pure: they depend only on their inputs and return values without mutating external state.

Examples include:

- `reduce` in `src/engine/reducer.rs`
- `replay` and `replay_until` in `src/engine/replay.rs`
- `diff` in `src/engine/diff.rs`
- scoring functions such as `age_score`, `size_score`, and `compute_clutter`
- rule evaluation in `src/domain/rules.rs`
- report formatting in `src/cli/report.rs`

This makes the system predictable and easy to test.

### 2. Immutability

State is treated as data that is transformed, not mutated in place.

- `State` is cloned and rebuilt in the reducer
- event replay produces a new state from the initial state
- scoring and rule evaluation never write back into the domain model

This keeps each transformation isolated and avoids hidden coupling.

### 3. Referential transparency

Because the core functions are pure, the same input always produces the same output.

That property matters here because Helios is built around deterministic analysis:

- the same event log always replays to the same state
- the same snapshots always produce the same diff
- the same state always produces the same signals

### 4. Explicit data flow

The application passes dependencies as values instead of reading from ambient global state.

Examples:

- `replay_until` receives an explicit cutoff timestamp
- clutter scoring receives `now` as an argument instead of reading the system clock
- rule evaluation receives `State` and a list of rules explicitly

This makes the core easy to reason about and keeps time-dependent behavior testable.

### 5. Separation of pure core and imperative shell

`src/main.rs` is the only place that performs side effects such as:

- reading files from disk
- parsing JSON
- printing output
- exiting the process on error

The rest of the application is organized as a pure pipeline:

snapshot or event data -> transformation -> derived state -> rules -> report

This is a classic functional architecture pattern and is the main design choice in the project.

### 6. Folding over sequences

Event replay is implemented with `fold`, which is a core functional pattern for reducing a list into a single result.

The event log starts from an initial state and is reduced event by event into a final state. This is more declarative than mutating a long-lived structure in place.

### 7. Higher-order functions

Helios uses functions as values in a few places:

- rules are stored in `DEFAULT_RULES` as a slice of function pointers
- `evaluate_rules` maps over the rule list and flattens the resulting signals
- scoring and formatting use iterator adapters such as `map`, `filter`, and `collect`

That keeps the rule engine composable and easy to extend.

### 8. Algebraic data types

Domain events and findings are modeled with enums:

- `Event` captures all supported filesystem changes
- `Signal` captures all supported rule outcomes

This makes invalid states harder to represent and forces exhaustive handling in pattern matches.

### 9. Composition over inheritance

The codebase builds behavior by composing small functions rather than by inheriting from shared base classes.

Examples:

- `diff` derives events from snapshots
- `replay` derives state from events
- `compute_clutter` derives file scores from state
- `compute_density` derives folder-level metrics from file scores
- `evaluate_rules` derives findings from state and a rule set

Each layer stays focused on one transformation.

### 10. Deterministic ordering

The implementation uses ordered maps and sorted event output where stable traversal matters.

That reduces accidental nondeterminism and makes CLI output and tests reproducible.

## Testing

The project includes unit and integration tests that verify the reducer, replay logic, snapshot diffing, scoring functions, and rules.

Run them with:

```bash
cargo test
```

## Project Structure

- `src/main.rs` - imperative CLI entry point
- `src/domain/` - event, snapshot, state, and rule definitions
- `src/engine/` - reducer, replay, and diff logic
- `src/scoring/` - file and folder scoring functions
- `src/cli/` - text report formatting

## Summary

Helios is a small but deliberate example of functional programming in an application context: the core is pure, the state is derived, the rules are composable, and the CLI is kept as a thin shell around deterministic logic.
