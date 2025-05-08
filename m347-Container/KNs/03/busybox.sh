#!/usr/bin/sh

# Approach to create a custom network and run busybox containers with docker commands.
# See the approach using docker-compose at ./compose.yml

echo "[i] Creating network 'tbz'..."
docker network create --driver bridge --subnet 172.18.0.0/16 tbz
echo "[+] Created network 'tbz'"

echo "[i] Starting busybox containers..."

# busybox1 & busybox2 in default bridge
docker run -d --name busybox1 busybox sleep 1d
echo "[+] Started busybox1"

docker run -d --name busybox2 busybox sleep 1d
echo "[+] Started busybox2"

# busybox3 & busybox4 in tbz network
docker run -d --name busybox3 --network tbz busybox sleep 1d
echo "[+] Started busybox3"

docker run -d --name busybox4 --network tbz busybox sleep 1d
echo "[+] Started busybox4"

echo "[+] Started busybox containers"

# Inspect IP-Adresses:
echo "[i] Inspecting IP addresses..."

echo "[+] IP-Addresses:"
docker inspect -f '{{ .Name }}: {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' busybox1 busybox2 busybox3 busybox4