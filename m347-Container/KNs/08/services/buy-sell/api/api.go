package api

import (
	"fmt"
	"encoding/json"
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/sxperlinx/TBZ/m347-Container/KNs/08/buy-sell/env"
)

func AddCrypto(c *gin.Context, userId int, amount int) bool {
	resp, err := http.Post(env.GetAccountServiceURL()+"/AddCrypto?userId="+fmt.Sprint(userId)+"&amount="+fmt.Sprint(amount), "application/json", nil)

	if err != nil || resp.StatusCode != http.StatusOK {
		http.Error(c.Writer, "transaction failed", http.StatusInternalServerError)
		return false
	}
	defer resp.Body.Close()

	c.IndentedJSON(http.StatusOK, true)
	return true
}

func RemoveCrypto(c *gin.Context, userId int, amount int) bool {
	cryptos := getCryptos(userId)

	log.Printf("User %d has %d cryptos", userId, cryptos)

	if cryptos < amount || cryptos <= 0 {
		c.JSON(http.StatusBadRequest, false)
		return false
	}

	resp, err := http.Post(env.GetAccountServiceURL()+"/RemoveCrypto?userId="+fmt.Sprint(userId)+"&amount="+fmt.Sprint(amount), "application/json", nil)

	if err != nil || resp.StatusCode != http.StatusOK {
		http.Error(c.Writer, "transaction failed", http.StatusInternalServerError)
		return false
	}
	defer resp.Body.Close()

	c.IndentedJSON(http.StatusOK, true)
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