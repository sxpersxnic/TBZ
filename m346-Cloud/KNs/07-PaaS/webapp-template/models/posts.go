package models;

type post struct {
	ID      string    `json:"id"`
	UserID	string    `json:"user_id"`
	Title   string `json:"title"`
	Content string `json:"content"`
}

var posts = []post{
	{ID: "1", UserID: "1", Title: "First Post", Content: "This is the content of the first post"},
	{ID: "2", UserID: "1", Title: "Second Post", Content: "This is the content of the second post"},
	{ID: "3", UserID: "2", Title: "Third Post", Content: "This is the content of the third post"},
	{ID: "4", UserID: "2", Title: "Fourth Post", Content: "This is the content of the fourth post"},
}