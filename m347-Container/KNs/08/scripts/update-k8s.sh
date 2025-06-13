#!/usr/bin/sh

cd ${TBZ}/m347-Container/KNs/08

GIST_ID=3c0b53c96a19f464ef17d96c32288377

git clone https://gist.github.com/${GIST_ID}.git

cp ./k8s/README.md ./${GIST_ID}
rm -rf ./k8s
mv ${GIST_ID} k8s