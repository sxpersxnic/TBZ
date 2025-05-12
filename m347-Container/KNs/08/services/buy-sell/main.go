package main;

import (
	"bytes"
	"encoding/json"
	"log"
	"net/http"
	"os"
)

type TransactionRequest struct {
	ID 			int `json:"id"`
	Amount 	int `json:"amount"`
}

func buyHandler(w http.ResponseWriter, r *http.Request) {
	handleTransaction(w, r, "buy")
}

func sellHandler(w http.ResponseWriter, r *http.Request) {
	handleTransaction(w, r, "sell")
}

func handleTransaction(w http.ResponseWriter, r *http.Request, action string) {
	var tx TransactionRequest

	if err := json.NewDecoder(r.Body).Decode(&tx); err != nil {
		http.Error(w, "invalid json", http.StatusBadRequest)
		return
	}

	accountUrl := os.Getenv("ACCOUNT_SERVICE_URL")

	if accountUrl == "" {
		log.Fatal("ACCOUNT_SERVICE_URL not set")
	}

	var endpoint string

	if action == "buy" {
		endpoint = "/AddCrypto"
	} else {
		endpoint = "/RemoveCrypto"
	}

	body, _ := json.Marshal(tx)
	resp, err := http.Post(accountUrl+endpoint, "application/json", bytes.NewBuffer(body))

	if err != nil || resp.StatusCode != 200 {
		http.Error(w, "transaction failed", http.StatusInternalServerError)
		return
	}

	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(map[string]bool{"success": true})
}


func main() {
	http.HandleFunc("/buy", buyHandler)
	http.HandleFunc("/sell", sellHandler)

	log.Println("BuySell service is running on :8002")
	log.Fatal(http.ListenAndServe(":8002", nil))
}