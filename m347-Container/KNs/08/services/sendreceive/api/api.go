package api

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"slices"

	"github.com/sxpersxnic/send-receive/env"
)

func AddCrypto(userId int, amount int) bool {
	resp, err := http.Post(env.GetAccountServiceURL()+"/AddCrypto?userId="+fmt.Sprint(userId)+"&amount="+fmt.Sprint(amount), "application/json", nil)

	if err != nil || resp.StatusCode != http.StatusOK {
		log.Printf("Error adding crypto for user %d: %v, status %d", userId, err, resp.StatusCode)
		return false
	}
	defer resp.Body.Close()

	return true
}

func RemoveCrypto(userId int, amount int) bool {
	cryptos := getCryptos(userId)

	if cryptos < amount || cryptos <= 0 {
		log.Printf("Insufficient cryptos for user %d: %d available, %d requested", userId, cryptos, amount)
		return false
	}

	resp, err := http.Post(env.GetAccountServiceURL()+"/RemoveCrypto?userId="+fmt.Sprint(userId)+"&amount="+fmt.Sprint(amount), "application/json", nil)

	if err != nil || resp.StatusCode != http.StatusOK {
		log.Printf("Error removing crypto for user %d: %v, status %d", userId, err, resp.StatusCode)
		return false
	}
	defer resp.Body.Close()
	return true
}

func getCryptos(userId int) int {
	resp, err := http.Get(env.GetAccountServiceURL()+"/Cryptos?userId="+fmt.Sprint(userId))

	if err != nil {
		log.Printf("Error fetching cryptos for user %d: %v", userId, err)
		return 0
	}

	var cryptos int

	if err := json.NewDecoder(resp.Body).Decode(&cryptos); err != nil {
		log.Printf("Error decoding response for user %d: %v", userId, err)
		return 0;
	}
	defer resp.Body.Close()

	return cryptos;
}

type Friend struct {
	ID int `json:"id"`
	Name string `json:"name"`
}

func CheckFriends(userId int, friendId int) bool {
	resp, err := http.Get(env.GetAccountServiceURL()+"/Friends?userId="+fmt.Sprint(userId))
	if err != nil {
		log.Printf("Error fetching friends of user %d: %v", userId, err)
		return false
	}
	defer resp.Body.Close()

	var friends []Friend
	if err := json.NewDecoder(resp.Body).Decode(&friends); err != nil {
		log.Printf("Error decoding response for user %d: %v", userId, err)
		return false
	}

	var friendIds []int

	for _, f := range friends {
		friendIds = append(friendIds, f.ID)
	}
	log.Printf("User %d has friends: %v", userId, friendIds)
	return slices.Contains(friendIds, friendId)
}