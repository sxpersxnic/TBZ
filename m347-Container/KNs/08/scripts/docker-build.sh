#!/usr/bin/sh

POSITIVE="\033[32m[+]\033[0m"
VERSION="v2"

cd ../services

cd account
docker build -t sxnic/kn08-account:${VERSION} .
docker push sxnic/kn08-account:${VERSION}
echo "${POSITIVE} Built account image"

cd ../buy-sell
docker build -t sxnic/kn08-buysell:${VERSION} .
docker push sxnic/kn08-buysell:${VERSION}
echo "${POSITIVE} Built buy-sell image"

cd ../send-receive
docker build -t sxnic/kn08-sendreceive:${VERSION} .
docker push sxnic/kn08-sendreceive:${VERSION}
echo "${POSITIVE} Built send-receive image"

cd ../frontend
docker build -t sxnic/kn08-frontend:v1 .
docker push sxnic/kn08-frontend:v1
echo "${POSITIVE} Built frontend image"