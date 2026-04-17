use std::fs;
use std::process;

use helios::cli::report::format_report;
use helios::domain::event::Event;
use helios::domain::rules::{evaluate_rules, DEFAULT_RULES};
use helios::domain::snapshot::Snapshot;
use helios::engine::diff::diff;
use helios::engine::replay::{replay, replay_until};

// --- Imperative shell: all IO lives here ---

fn load_snapshot(path: &str) -> Snapshot {
    let raw = fs::read_to_string(path).unwrap_or_else(|e| {
        eprintln!("Error: cannot read snapshot '{}': {}", path, e);
        process::exit(1);
    });
    serde_json::from_str(&raw).unwrap_or_else(|e| {
        eprintln!("Error: cannot parse snapshot '{}': {}", path, e);
        process::exit(1);
    })
}

fn load_event_log(path: &str) -> Vec<Event> {
    let raw = fs::read_to_string(path).unwrap_or_else(|e| {
        eprintln!("Error: cannot read event log '{}': {}", path, e);
        process::exit(1);
    });
    serde_json::from_str(&raw).unwrap_or_else(|e| {
        eprintln!("Error: cannot parse event log '{}': {}", path, e);
        process::exit(1);
    })
}

/// Pure: scans a CLI argument list for `--until <ts>` and returns the timestamp.
fn parse_until(args: &[String]) -> Option<i64> {
    args.windows(2)
        .find(|w| w[0] == "--until")
        .and_then(|w| w[1].parse::<i64>().ok())
}

// --- Subcommand handlers (call pure core, print result) ---

fn run_diff(before_path: &str, after_path: &str) {
    let before = load_snapshot(before_path);
    let after = load_snapshot(after_path);

    let events = diff(&before, &after);

    println!("Snapshot diff: {} event(s) detected", events.len());
    for event in &events {
        println!("  {:?}", event);
    }
    println!();

    let state = replay(&events);
    let signals = evaluate_rules(&state, DEFAULT_RULES);
    println!("{}", format_report(&state, &signals));
}

fn run_replay(log_path: &str, until: Option<i64>) {
    let events = load_event_log(log_path);

    match until {
        Some(cutoff) => println!(
            "Replaying {} event(s) from '{}' (cutoff t≤{})",
            events.iter().filter(|e| e.timestamp() <= cutoff).count(),
            log_path,
            cutoff
        ),
        None => println!("Replaying {} event(s) from '{}'", events.len(), log_path),
    }

    let state = match until {
        Some(cutoff) => replay_until(&events, cutoff),
        None => replay(&events),
    };

    let signals = evaluate_rules(&state, DEFAULT_RULES);
    println!("{}", format_report(&state, &signals));
}

fn print_usage() {
    eprintln!("Helios — deterministic filesystem analysis engine");
    eprintln!();
    eprintln!("Usage:");
    eprintln!("  helios diff <before.json> <after.json>              Diff two snapshots");
    eprintln!("  helios replay <events.json> [--until <timestamp>]   Replay an event log");
}

fn main() {
    let args: Vec<String> = std::env::args().collect();

    match args.as_slice() {
        [_, cmd, before, after, ..] if cmd == "diff" => run_diff(before, after),
        [_, cmd, log, ..] if cmd == "replay" => {
            let until = parse_until(&args);
            run_replay(log, until);
        }
        _ => {
            print_usage();
            process::exit(1);
        }
    }
}
