#!/usr/bin/sh

# Creates shared volume
docker volume create shared_data

# Runs two containers which share the volume 'shared_data'
docker run -dit --name kn05b-1 --mount source=shared_data,target=/data alpine sh
docker run -dit --name kn05b-2 --mount source=shared_data,target=/data alpine sh

# kn05b-1 writes to the shared volume
docker exec kn05b-1 sh -c "echo 'Hello from kn05b-1' > /data/greetings.txt"
echo "[+] Wrote to shared volume from kn05b-1"

# kn05b-2 reads from the shared volume
echo "[+] Reading from shared volume in kn05b-2: "
docker exec kn05b-2 sh -c "cat /data/greetings.txt"

# kn05b-2 wites to the shared volume
docker exec kn05b-2 sh -c "echo 'Hello back from kn05b-2' >> /data/greetings.txt"
echo "[+] Wrote to shared volume from kn05b-2"

# kn05b-1 reads from the shared volume
echo "[+] Reading from shared volume in kn05b-1: "
docker exec kn05b-1 sh -c "cat /data/greetings.txt"