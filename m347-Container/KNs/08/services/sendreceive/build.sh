#!/usr/bin/sh

sudo rm -rf ./bin
echo "[+]	Cleaned up previous build"

sudo rm -f go.sum
echo "[+]	Removed go.sum"

go mod tidy
echo "[+]	Go modules tidied up"

mkdir -p ./bin
echo "[+]	Created bin directory"

sudo go build -v -o ./bin/app .
echo "[+]	Build complete"

sudo chmod +x ./bin/app
echo "[+]	Executable permissions set"