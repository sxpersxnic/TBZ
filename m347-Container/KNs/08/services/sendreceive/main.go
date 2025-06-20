package main

import (
	"log"
	"net/http"

	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"github.com/sxpersxnic/send-receive/api"
	"github.com/sxpersxnic/send-receive/config"
	"github.com/sxpersxnic/send-receive/env"
)

type Transaction struct {
	ID 			int `json:"id"`
	ReceiverID 	int `json:"receiverId"`
	Amount 	int `json:"amount"`
}

func sendHandler(ctx *gin.Context) {
	var tx Transaction

	if err := ctx.BindJSON(&tx); err != nil {
		log.Printf("Error binding JSON: %v", err)
		ctx.JSON(http.StatusBadRequest, false)
		return
	}

	if !api.CheckFriends(tx.ID, tx.ReceiverID) {
		ctx.JSON(http.StatusBadRequest, false)
		return
	}

	if !api.AddCrypto(tx.ReceiverID, tx.Amount) {
		ctx.JSON(http.StatusInternalServerError, false)
		return
	}

	if !api.RemoveCrypto(tx.ID, tx.Amount) {
		ctx.JSON(http.StatusInternalServerError, false)
		return
	}

	log.Printf("Transaction send successful for user %d to user %d with amount %d\n", tx.ID, tx.ReceiverID, tx.Amount)
	ctx.JSON(http.StatusOK, true)
}

func main() {
	env.Load()

	router := gin.Default()

	router.Use(cors.New(config.Cors()))
	router.Use(config.Middleware())

	router.POST("/send", sendHandler)

log.Printf("Account service URL: %s\n", env.GetAccountServiceURL())
	log.Printf("SendReceive service is running at %s\n", env.GetHost())
	router.Run(env.GetHost())
}