# Use the official Eclipse Temurin 21 runtime as a parent image
FROM eclipse-temurin:21

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file and rename it
COPY target/config-server-*.jar /opt/app/config-server.jar

# Copy the download script into the container
COPY scripts/download-server-jks.sh /usr/local/bin/download-server-jks.sh

# Make the script executable
RUN chmod +x /usr/local/bin/download-server-jks.sh

# Environment variable to pass the Spring profile
ENV SPRING_PROFILES_ACTIVE=local

# Set the command to run your Config Server application, ensuring the config/ keystore location is specified
CMD ["java", "-jar", "/opt/app/config-server.jar"]

