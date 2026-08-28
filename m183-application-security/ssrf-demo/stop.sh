#!/bin/bash

# SSRF Demo Stop Script

echo "🛑 Stopping SSRF Demo..."

if ! command -v docker-compose &> /dev/null; then
    COMPOSE_CMD="docker compose"
else
    COMPOSE_CMD="docker-compose"
fi

$COMPOSE_CMD down

echo "✅ All services stopped."
