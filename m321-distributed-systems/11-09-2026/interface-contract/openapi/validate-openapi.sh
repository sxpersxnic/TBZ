#!/usr/bin/sh

if [ ! -f openapi.yaml ]; then
  echo "openapi.yaml not found"
  exit 1
fi

if ! command -v npx &> /dev/null; then
  echo "npx could not be found"
  exit 1
fi

npx swagger-cli validate openapi.yaml