#!/bin/sh


# This script is used to clear the port that is being used by the production server
port_number=$(echo ../application.properties | xargs grep server.port | cut -d'=' -f2)
pid=$(lsof -i :${port_number})

kill -9 ${pid}