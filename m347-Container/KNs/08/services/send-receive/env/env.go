package env

import (
	"fmt"
	"os"
	"log"
	"github.com/joho/godotenv"
)

func GetAccountServiceURL() string {
	accountUrl := os.Getenv("ACCOUNT_SERVICE_URL")

	if accountUrl == "" {
		log.Println("ACCOUNT_SERVICE_URL not set, defaulting to http://account:8080")
		accountUrl = "http://account:8080"
	}

	return accountUrl
}

func GetHost() string {
	return fmt.Sprintf("0.0.0.0%s", GetPort())
}

func GetPort() string {
	portNum := os.Getenv("PORT")

	if portNum == "" {
		log.Println("PORT not set, defaulting to 8003")
		portNum = "8003"
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