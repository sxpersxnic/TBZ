package models

type user struct {
	ID       string `json:"id"`
	Username string `json:"username"`
	Password string `json:"password"`
}

var users = []user{
	{ID: "1", Username: "user1", Password: "password1"},
	{ID: "2", Username: "user2", Password: "password2"},
}