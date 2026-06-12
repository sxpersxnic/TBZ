#!/bin/bash

# SSRF Demo Quick Start Script

echo "🚀 Starting SSRF Demo..."
echo ""

# Check if docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker first."
    exit 1
fi

# Check if docker-compose is available
if ! command -v docker-compose &> /dev/null; then
    echo "⚠️  docker-compose command not found. Trying 'docker compose'..."
    if ! docker compose version &> /dev/null; then
        echo "❌ Docker Compose is not available."
        exit 1
    fi
    COMPOSE_CMD="docker compose"
else
    COMPOSE_CMD="docker-compose"
fi

echo "📦 Building services..."
$COMPOSE_CMD build

echo ""
echo "🏃 Starting services..."
$COMPOSE_CMD up

