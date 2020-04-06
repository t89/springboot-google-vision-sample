#!/bin/sh

gradle assemble
docker build --build-arg JAR_FILE=build/libs/*.jar -t bath:v1 .

