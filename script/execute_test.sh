#!/bin/bash
cd ..
docker-compose -f redis.yml down && docker-compose -f redis.yml up -d
mvn clean test