FROM golang:1.24

WORKDIR /usr/src/app

COPY . .
RUN go build -v -o /usr/local/bin/app ./...

CMD ["app"]