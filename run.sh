#!/bin/bash

# script to run app in production environment

./mvnw clean package

docker compose up -d

sleep 10

java -Dspring.profiles.active=prod -jar target/demo-0.0.1-SNAPSHOT.jar
