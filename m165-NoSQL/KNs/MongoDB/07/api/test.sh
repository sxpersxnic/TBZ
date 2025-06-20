#!/bin/bash

# Test script for the MongoDB-integrated Go Metrics API

API_URL="http://localhost:8080"

echo "=== MongoDB Metrics API Test Script ==="
echo

# Test if server is running
echo "1. Testing server health..."
if curl -s "$API_URL/health" > /dev/null; then
    echo "✅ Server is running!"
    echo "Health status:"
    curl -s "$API_URL/health"
    echo
else
    echo "❌ Server is not running. Please start it with: go run main.go"
    echo "Make sure MongoDB is running and connection string is correct in .env"
    exit 1
fi

echo
echo "2. Getting all metrics..."
echo "Raw JSON response:"
curl -s "$API_URL/metrics"
echo
echo

echo "3. Getting metrics count..."
METRICS_COUNT=$(curl -s "$API_URL/metrics" | grep -o '"count":[0-9]*' | cut -d':' -f2)
echo "Total metrics available: $METRICS_COUNT"
echo

echo "4. Creating a new metric..."
NEW_METRIC='{
  "name": "test_metric",
  "value": 123.45,
  "unit": "test_unit",
  "description": "A test metric created via API"
}'

echo "Sending POST request..."
curl -s -X POST \
  -H "Content-Type: application/json" \
  -d "$NEW_METRIC" \
  "$API_URL/metrics"
echo
echo

echo "5. Verifying the new metric was created..."
echo "Updated metrics count:"
UPDATED_COUNT=$(curl -s "$API_URL/metrics" | grep -o '"count":[0-9]*' | cut -d':' -f2)
echo "Total metrics now: $UPDATED_COUNT"
echo

echo "6. Testing invalid endpoint..."
echo "Response for GET /invalid:"
curl -s -w "HTTP Status: %{http_code}\n" "$API_URL/invalid" 2>/dev/null
echo

echo "7. Testing invalid method..."
echo "Response for PUT /metrics:"
curl -s -X PUT -w "HTTP Status: %{http_code}\n" "$API_URL/metrics" 2>/dev/null
echo

echo "8. Testing invalid JSON for POST..."
echo "Response for POST /metrics with invalid JSON:"
curl -s -X POST \
  -H "Content-Type: application/json" \
  -d '{"invalid": json}' \
  -w "HTTP Status: %{http_code}\n" \
  "$API_URL/metrics" 2>/dev/null
echo

echo "=== Test completed ==="
echo
echo "💡 Tips:"
echo "  - View MongoDB data via Mongo Express: http://localhost:8081"
echo "  - Start MongoDB: docker-compose up -d mongodb"
echo "  - Stop MongoDB: docker-compose down"
