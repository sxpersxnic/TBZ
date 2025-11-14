package main

import "errors"

// Logger represents a simple logging utility with configurable log levels.
// Valid log levels are INFO, WARN, and ERROR.
type Logger struct {
	Level string
}

// NewLogger creates and returns a new Logger instance with the specified log level.
// Valid log levels are INFO, WARN, and ERROR. Returns an error if the level is empty or invalid.
func NewLogger(level string) (*Logger, error) {
	if level == "" {
		return nil, errors.New("log level cannot be empty")
	}

	if level != "INFO" && level != "WARN" && level != "ERROR" {
		return nil, errors.New("invalid log level: " + level)
	}
	return &Logger{Level: level}, nil
}

// Info logs an informational message with INFO level.
func (l *Logger) Info(message string) {
	l.log(message, "INFO")
}

// Warn logs a warning message with WARN level.
func (l *Logger) Warn(message string) {
	l.log(message, "WARN")
}

// Error logs an error message with ERROR level.
func (l *Logger) Error(message string) {
	l.log(message, "ERROR")
}

// Fatal logs a fatal error message with ERROR level and triggers a panic.
// This should be used for unrecoverable errors that require the program to terminate.
func (l *Logger) Fatal(message string) {
	l.log(message, "ERROR")
	panic("Fatal error: " + message)
}

// log is an internal method that handles the actual logging based on the configured log level.
// Messages are only printed if their severity level is greater than or equal to the logger's level.
func (l *Logger) log(message string, level string) {
	logLevelValue := map[string]int{
		"INFO":  1,
		"WARN":  2,
		"ERROR": 3,
	}

	if logLevelValue[level] >= logLevelValue[l.Level] {
		println("[" + level + "] " + message)
	}

}
