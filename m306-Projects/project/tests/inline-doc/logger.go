package main

import "errors"

type Logger struct {
	Level string
}

func NewLogger(level string) (*Logger, error) {
	if level == "" {
		return nil, errors.New("log level cannot be empty")
	}

	if level != "INFO" && level != "WARN" && level != "ERROR" {
		return nil, errors.New("invalid log level: " + level)
	}
	return &Logger{Level: level}, nil
}

func (l *Logger) Info(message string) {
	l.log(message, "INFO")
}

func (l *Logger) Warn(message string) {
	l.log(message, "WARN")
}

func (l *Logger) Error(message string) {
	l.log(message, "ERROR")
}

func (l *Logger) Fatal(message string) {
	l.log(message, "ERROR")
	panic("Fatal error: " + message)
}

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