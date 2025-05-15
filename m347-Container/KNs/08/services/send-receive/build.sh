#!/usr/bin/sh

sudo rm -rf ./bin
echo "::>	[+]	Cleaned up previous build"

sudo go build -v -o ./bin/app .
echo "::>	[+]	Build complete"
sudo chmod +x ./bin/app
echo "::>	[+]	Executable permissions set"