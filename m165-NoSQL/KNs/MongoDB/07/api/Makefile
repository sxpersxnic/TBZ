# Makefile for MongoDB Metrics API

.PHONY: help build run test clean deps

# Default target
help:
	@echo "Available commands:"
	@echo "  make deps       - Install Go dependencies"
	@echo "  make build      - Build the Go application"
	@echo "  make run        - Run the API server"
	@echo "  make test       - Run the test script"
	@echo "  make clean      - Clean build artifacts"

# Install dependencies
deps:
	go mod tidy

# Build the application
build: deps
	go build -o metrics-api main.go

# Run the application
run: deps
	go run main.go

# Run tests
test:
	chmod +x test.sh && ./test.sh

# Clean build artifacts
clean:
	rm -f metrics-api
