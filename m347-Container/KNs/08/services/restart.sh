#!/usr/bin/sh

docker compose down
echo "::>	[+]	Container stopped"

docker rmi kn08-account kn08-frontend kn08-buysell kn08-sendreceive mariadb
echo "::>	[+]	Images removed"

docker volume rm kn08_db_data
echo "::>	[+]	Volumes removed"

echo "::>	[+]	Starting containers..."
docker compose up