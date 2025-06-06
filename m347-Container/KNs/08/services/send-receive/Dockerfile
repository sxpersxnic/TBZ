# builder
FROM golang:1.24 AS builder
WORKDIR /usr/src/app

COPY go.mod go.sum ./
RUN go mod download

COPY . .

ENV CGO_ENABLED=0 \
	GOOS=linux \
	GOARCH=amd64

RUN go build -v -o /usr/local/bin/app .

# runtime
FROM alpine:latest AS runtime
WORKDIR /app

COPY --from=builder /usr/local/bin/app .

ARG PORT
ARG ACCOUNT_SERVICE_HOST
ARG ACCOUNT_SERVICE_PORT

ENV	GIN_MODE=release

EXPOSE ${PORT}

ENTRYPOINT ["./app"]