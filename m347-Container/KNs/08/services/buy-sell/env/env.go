package env

import (
	"fmt"
	"os"
	"log"
	"github.com/joho/godotenv"
)

func GetAccountServiceURL() string {
	accountHost := os.Getenv("ACCOUNT_SERVICE_HOST")
	accountPort := os.Getenv("ACCOUNT_SERVICE_PORT")

	if accountHost == "" {
		log.Println("ACCOUNT_SERVICE_HOST not set, defaulting to localhost")
		accountUrl = "localhost"
	}

	if accountPort == "" {
		log.Println("ACCOUNT_SERVICE_PORT not set, defaulting to 8080")
		accountUrl = "8080"
	}

	accountUrl := fmt.Sprintf("%s:%s", accountHost, accountPort)

	return accountUrl
}

func GetHost() string {
	return fmt.Sprintf("0.0.0.0%s", GetPort())
}

func GetPort() string {
	portNum := os.Getenv("PORT")

	if portNum == "" {
		log.Println("PORT not set, defaulting to 8002")
		portNum = "8002"
	}

	return fmt.Sprintf(":%s", portNum)
}

func Load() {
	if os.Getenv("GIN_MODE") != "release" {
		log.Println("Running in debug mode")
		err := godotenv.Load()
		if err != nil {
			log.Println("Error loading .env file")
		}
	} else {
		log.Println("Running in release mode")
	}
}