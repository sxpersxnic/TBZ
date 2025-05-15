package main

import (
	"log"
	"net/http"

	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"github.com/sxperlinx/TBZ/m347-Container/KNs/08/buy-sell/api"
	"github.com/sxperlinx/TBZ/m347-Container/KNs/08/buy-sell/env"
	"github.com/sxperlinx/TBZ/m347-Container/KNs/08/buy-sell/config"
)

type Transaction struct {
	ID 			int `json:"id"`
	Amount 	int `json:"amount"`
}

func buyHandler(c *gin.Context) {
	handleTransaction(c, "buy")
}

func sellHandler(c *gin.Context) {
	handleTransaction(c, "sell")
}

func handleTransaction(c *gin.Context, action string) {
	var tx Transaction

	if err := c.BindJSON(&tx); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "invalid json"})
		return
	}

	var success bool

	if action == "buy" {
		success = api.AddCrypto(c, tx.ID, tx.Amount)
	} else {
		success = api.RemoveCrypto(c, tx.ID, tx.Amount)
	}

	if !success {
		return
	}

	log.Printf("Transaction %s successful for user %d with amount %d\n", action, tx.ID, tx.Amount)
}

func main() {
	env.Load()

	router := gin.Default()

	router.Use(cors.New(config.Cors()))
	router.Use(config.Middleware())

	router.POST("/buy", buyHandler)
	router.POST("/sell", sellHandler)

	log.Printf("BuySell service is running at %s\n", env.GetHost())
	router.Run(env.GetHost())
}