package main;

import (
	"fmt"
	"github.com/gorilla/mux"
	"github.com/joho/godotenv"
	"go-restAPI/handlers"
	"log"
	"net/http"
	"os"
);

func main() {
	router := mux.NewRouter()

	err := godotenv.Load()
	if err != nil {
		fmt.Println("Error loading .env file")
		return
	}
	apiPort := 8080

	// User
	router.HandleFunc("/auth/signup", handlers.SignUp).Methods("POST")
	router.HandleFunc("/auth/signin", handlers.SignIn).Methods("POST")

	// Post
	router.HandleFunc("/posts", handlers.CreatePost).Methods("POST")
	router.HandleFunc("/posts", handlers.GetAllPosts).Methods("GET")
	router.HandleFunc("/posts/{id}", handlers.GetPostByID).Methods("GET")
	router.HandleFunc("/posts/{id}", handlers.UpdatePost).Methods("PATCH")
	router.HandleFunc("/posts/{id}", handlers.DeletePost).Methods("DELETE")

	log.Fatal(http.ListenAndServe(":"+apiPort, router))
}
