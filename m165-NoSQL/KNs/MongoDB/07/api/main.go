package main

import (
	"context"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"time"

	"github.com/joho/godotenv"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

// Metric represents a single metric data point
type Metric struct {
	ID          primitive.ObjectID `json:"_id,omitempty" bson:"_id,omitempty"`
	Name        string             `json:"name" bson:"name"`
	Value       float64            `json:"value" bson:"value"`
	Unit        string             `json:"unit" bson:"unit"`
	Description string             `json:"description" bson:"description"`
	Timestamp   string             `json:"timestamp" bson:"timestamp"`
	CreatedAt   time.Time          `json:"created_at,omitempty" bson:"created_at,omitempty"`
}

// MetricsResponse represents the response structure
type MetricsResponse struct {
	Status  string   `json:"status"`
	Count   int      `json:"count"`
	Metrics []Metric `json:"metrics"`
}

// MongoDB client and collection
var (
	mongoClient     *mongo.Client
	metricsCollection *mongo.Collection
)

// initMongoDB initializes the MongoDB connection
func initMongoDB() error {
	// Get MongoDB URI from environment variable
	mongoURI := os.Getenv("MONGODB_URI")
	if mongoURI == "" {
		return fmt.Errorf("MONGODB_URI environment variable is required")
	}

	// Set client options
	clientOptions := options.Client().ApplyURI(mongoURI)

	// Connect to MongoDB
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	client, err := mongo.Connect(ctx, clientOptions)
	if err != nil {
		return fmt.Errorf("failed to connect to MongoDB: %v", err)
	}

	// Test the connection
	err = client.Ping(ctx, nil)
	if err != nil {
		return fmt.Errorf("failed to ping MongoDB: %v", err)
	}

	mongoClient = client
	
	// Get database and collection
	database := client.Database("kn07")
	metricsCollection = database.Collection("metrics")

	log.Println("Connected to MongoDB successfully")
	return nil
}

// seedDataFromJSON loads initial data from JSON file if MongoDB collection is empty
func seedDataFromJSON() error {
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	// Check if collection already has data
	count, err := metricsCollection.CountDocuments(ctx, bson.D{})
	if err != nil {
		return fmt.Errorf("error checking collection count: %v", err)
	}

	if count > 0 {
		log.Printf("MongoDB collection already contains %d documents, skipping seed", count)
		return nil
	}

	// Read JSON file
	data, err := ioutil.ReadFile("metrics.json")
	if err != nil {
		log.Printf("Warning: Could not read metrics.json for seeding: %v", err)
		return nil // Not a fatal error
	}

	var metrics []Metric
	err = json.Unmarshal(data, &metrics)
	if err != nil {
		return fmt.Errorf("error parsing JSON: %v", err)
	}

	// Add CreatedAt timestamp to each metric
	now := time.Now()
	for i := range metrics {
		metrics[i].CreatedAt = now
	}

	// Convert to interface slice for MongoDB insertion
	var docs []interface{}
	for _, metric := range metrics {
		docs = append(docs, metric)
	}

	// Insert data into MongoDB
	result, err := metricsCollection.InsertMany(ctx, docs)
	if err != nil {
		return fmt.Errorf("error inserting seed data: %v", err)
	}

	log.Printf("Seeded %d metrics from JSON file into MongoDB", len(result.InsertedIDs))
	return nil
}

// getAllMetrics retrieves all metrics from MongoDB
func getAllMetrics() ([]Metric, error) {
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	// Find all documents
	cursor, err := metricsCollection.Find(ctx, bson.D{})
	if err != nil {
		return nil, fmt.Errorf("error finding metrics: %v", err)
	}
	defer cursor.Close(ctx)

	var metrics []Metric
	err = cursor.All(ctx, &metrics)
	if err != nil {
		return nil, fmt.Errorf("error decoding metrics: %v", err)
	}

	return metrics, nil
}

// createMetric inserts a new metric into MongoDB
func createMetric(metric Metric) (*Metric, error) {
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	metric.CreatedAt = time.Now()
	result, err := metricsCollection.InsertOne(ctx, metric)
	if err != nil {
		return nil, fmt.Errorf("error inserting metric: %v", err)
	}

	metric.ID = result.InsertedID.(primitive.ObjectID)
	return &metric, nil
}

// metricsHandler handles requests to /metrics
func metricsHandler(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	switch r.Method {
	case http.MethodGet:
		handleGetMetrics(w, r)
	case http.MethodPost:
		handleCreateMetric(w, r)
	default:
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
	}
}

// handleGetMetrics handles GET requests to /metrics
func handleGetMetrics(w http.ResponseWriter, r *http.Request) {
	metrics, err := getAllMetrics()
	if err != nil {
		log.Printf("Error getting metrics: %v", err)
		http.Error(w, "Internal server error", http.StatusInternalServerError)
		return
	}

	response := MetricsResponse{
		Status:  "success",
		Count:   len(metrics),
		Metrics: metrics,
	}

	if err := json.NewEncoder(w).Encode(response); err != nil {
		log.Printf("Error encoding response: %v", err)
		http.Error(w, "Internal server error", http.StatusInternalServerError)
	}
}

// handleCreateMetric handles POST requests to /metrics
func handleCreateMetric(w http.ResponseWriter, r *http.Request) {
	var metric Metric
	err := json.NewDecoder(r.Body).Decode(&metric)
	if err != nil {
		http.Error(w, "Invalid JSON", http.StatusBadRequest)
		return
	}

	// Set timestamp if not provided
	if metric.Timestamp == "" {
		metric.Timestamp = time.Now().Format(time.RFC3339)
	}

	createdMetric, err := createMetric(metric)
	if err != nil {
		log.Printf("Error creating metric: %v", err)
		http.Error(w, "Internal server error", http.StatusInternalServerError)
		return
	}

	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode(map[string]interface{}{
		"status": "success",
		"message": "Metric created successfully",
		"metric": createdMetric,
	})
}

// healthHandler provides a health check endpoint
func healthHandler(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	
	// Check MongoDB connection
	ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second)
	defer cancel()
	
	mongoStatus := "healthy"
	if err := mongoClient.Ping(ctx, nil); err != nil {
		mongoStatus = "unhealthy"
	}

	response := map[string]interface{}{
		"status": "healthy",
		"database": mongoStatus,
		"timestamp": time.Now().Format(time.RFC3339),
	}

	json.NewEncoder(w).Encode(response)
}

// closeDB closes the MongoDB connection
func closeDB() {
	if mongoClient != nil {
		ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
		defer cancel()
		mongoClient.Disconnect(ctx)
	}
}

func main() {
	// Load environment variables from .env file
	if err := godotenv.Load(); err != nil {
		log.Printf("Warning: Error loading .env file: %v", err)
	}

	// Initialize MongoDB connection
	if err := initMongoDB(); err != nil {
		log.Fatalf("Failed to initialize MongoDB: %v", err)
	}
	defer closeDB()

	// Seed data from JSON file if collection is empty
	if err := seedDataFromJSON(); err != nil {
		log.Printf("Warning: Failed to seed data: %v", err)
	}

	// Set up routes
	http.HandleFunc("/metrics", metricsHandler)
	http.HandleFunc("/health", healthHandler)

	// Get port from environment or use default
	port := os.Getenv("PORT")
	if port == "" {
		port = "8080"
	}
	
	// Start server
	log.Printf("Starting server on port %s", port)
	log.Printf("MongoDB URI: %s", os.Getenv("MONGODB_URI"))
	log.Printf("Endpoints:")
	log.Printf("  GET  /metrics - Get all metrics data")
	log.Printf("  POST /metrics - Create a new metric")
	log.Printf("  GET  /health  - Health check (includes MongoDB status)")

	if err := http.ListenAndServe(":"+port, nil); err != nil {
		log.Fatalf("Server failed to start: %v", err)
	}
}
