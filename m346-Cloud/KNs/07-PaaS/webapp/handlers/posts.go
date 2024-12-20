package handlers;

import (
	"net/http"
	"github.com/gin-gonic/gin"
	"github.com/username/webapp/models"
)

// getPosts responds with a list of all posts as JSON.
func getPosts(ctx *gin.Context) {
	ctx.IndentedJSON(http.StatusOK, posts)
}