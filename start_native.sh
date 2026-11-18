#!/bin/bash

mvn -f auth-api/pom.xml clean package -DskipTests &
mvn -f data-api/pom.xml clean package -DskipTests &
docker compose up -d --build


echo "Started."

while [ 1 = 1 ]; do
  sleep 1;
done
