#!/usr/bin/env bash

#Build and compile the project
mvn clean install

#Build and tag the image
docker build -t staff-schedule:latest .

# Start containers
docker-compose up
