package main

import (
	"fmt"
	"bytes"
	"encoding/json"
	"log"
	"net/http"
	"os"
	"slices"
)

type Request struct {
	ID 					int `json:"id"`
	ReceiverID 	int `json:"receiverId"`
	Amount 			int `json:"amount"`
}

func sendHandler(w http.ResponseWriter, r *http.Request) {
	var req Request
	if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
		http.Error(w, "invalid json", http.StatusBadRequest)
		return
	}

	accountURL := os.Getenv("ACCOUNT_SERVICE_URL")
	if accountURL == "" {
		log.Fatal("ACCOUNT_SERVICE_URL not set")
	}

	isFriend := checkFriend(accountURL, req.ID, req.ReceiverID)
	if !isFriend {
		http.Error(w, "not a friend", http.StatusForbidden)
		return
	}

	data := map[string]int{"id": req.ID, "amount": req.Amount}
	body, _ := json.Marshal(data)
	http.Post(accountURL+"/RemoveCrypto", "application/json", bytes.NewBuffer(body))

	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(map[string]bool{"success": true})
}

func checkFriend(accountURL string, senderID, receiverID int) bool {
	resp, err := http.Get(accountURL + "/Friends/?" + toStr(senderID))
	if err != nil {
		return false
	}
	var friends []int
	json.NewDecoder(resp.Body).Decode(&friends)
	return slices.Contains(friends, receiverID)
}

func toStr(i int) string {
	return fmt.Sprintf("%d", i)
}

func main() {
	http.HandleFunc("/send", sendHandler)
	log.Println("SendReceive service is running on :8003")
	log.Fatal(http.ListenAndServe(":8003", nil))
}