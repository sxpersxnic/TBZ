#!/usr/bin/sh

publish() {
	local service_name=$1
	local version=$2
	local prefix=${3:-"kn08-"}

	cd ./$service_name
	docker build -t sxnic/kn08-${service_name}:${version} .
	docker tag sxnic/kn08-${service_name}:${version} sxnic/${service_name}:latest
	docker push sxnic/kn08-${service_name}:${version}
	docker push sxnic/kn08-${service_name}:latest
	cd ..

	echo "\033[32m[+]\033[0m Published kn08-${service_name} version ${version}"
}

publish "buysell" "v4"
publish "sendreceive" "v4"
publish "frontend" "v4"