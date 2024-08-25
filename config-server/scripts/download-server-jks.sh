#!/bin/bash

# Check if the environment is production
if [ "$SPRING_PROFILES_ACTIVE" = "prod" ]; then
  # Download the server.jks file from S3 to the specified path
  aws s3 cp s3://brunosantos-config-server/server.jks /app/config/keystore/server.jks
fi

# Start the Config Server with the specified profile
java -jar /app/config-server.jar --spring.config.location=classpath:/config/ --spring.profiles.active=$SPRING_PROFILES_ACTIVE