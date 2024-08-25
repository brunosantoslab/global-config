<p align="center">
  <img src="./assets/config-server-header.png" alt="Config Server">
</p>

## Overview

The `config-server` project is a Spring Cloud Config Server application that provides centralized configuration management for multiple microservices and environments. By using this server, you can manage application configurations in a centralized repository, making it easier to maintain consistency across different environments such as development, QA, UAT, and production. The `config-server` fetches configurations from a Git repository and serves them to client applications on demand.

## Prerequisites

- **Java 17 or later**
- **Maven 3.6 or later**
- **Git**
- **Docker** (for running the application in a container)
- **AWS S3 Bucket** (for storing the `server.jks` keystore and other configuration files in production)
- **AWS IAM Role with appropriate permissions** (for accessing the S3 bucket and other AWS resources in production)
- **Local Keystore File** (`server.jks`) for running the application locally (if not using AWS S3)
- **Environment Variables**: Ensure the environment variables are set up in your environment.

### Generating the Keystore

1. **Generate the Keystore:**

    You will need to generate a `server.jks` keystore file for encrypting properties. Use the following command:

    ```bash
    keytool -genkeypair -alias config-server-key -keyalg RSA -dname "CN=Config Server,OU=Config Server,O=brunosantos.app,L=SaoPaulo,ST=SP,C=BR" -keypass Z#hCX[g%7}_z7pJ1 -keystore server.jks -storepass Z#hCX[g%7}_z7pJ1
    ```

    This will generate a `server.jks` file with the provided password.

### Setting Up Environment Variables

Ensure you have the following environment variables set up:

- **BRUNOSANTOS_KEYSTORE_LOCATION**: The location for the server.jks file.
- **BRUNOSANTOS_KEYSTORE_PASSWORD**: The password for the keystore.
- **BRUNOSANTOS_KEY_SECRET**: The secret used for encrypting and decrypting configuration properties.
- **BRUNOSANTOS_CONFIG_SERVER_USER**: The username for accessing the Config Server.
- **BRUNOSANTOS_CONFIG_SERVER_PASSWORD**: The password for accessing the Config Server.
- **BRUNOSANTOS_GIT_USER**: The username for accessing GitHub.
- **BRUNOSANTOS_GIT_PASSWORD**: The password for accessing GitHub.
- **SPRING_PROFILES_ACTIVE**: Set to `local` for local development.

For running the Config Server with Docker, you'll need a **.env** file with these environment variables placed in the project root.

### Storing the Keystore in AWS S3 (Production)

For production, upload the `server.jks` file to an AWS S3 bucket. Use the IAM role and policy to ensure only the Config Server can access this file.

### Running Locally

For local development, store the `server.jks` file locally and point to it using `application-local.yml` or environment variables.

## Getting Started

1. **Clone the repository:**

    ```bash
    git clone https://github.com/brunosantoslab/global-config.git
    cd global-config/config-server
    ```

2. **Build the project:**

    If you are using Maven, run:

    ```bash
    ./mvnw clean install
    ```

    If you are using Gradle, run:

    ```bash
    ./gradlew build
    ```

3. **Run the Config Server Locally:**

    Start the Config Server using the following command:

    If you are using Maven, run:

    ```bash
    ./mvnw spring-boot:run
    ```

    Or, if you are using Gradle, run:

    ```bash
    ./gradlew bootRun
    ```

    The server will start on the default port `8888`.

## Running the Config Server in Docker

1. **Build the Docker Image:**

    ```bash
    docker build -t config-server .
    ```

2. **Run the Docker Container:**

    ```bash
    docker run --env-file .env -d -p 8888:8888 --env SPRING_PROFILES_ACTIVE=local config-server
    ```

    This command will run the `config-server` container with the `local` profile active, using the environment variables specified in the `.env` file.

3. **Access the Config Server:**

    The Config Server will be accessible at `http://localhost:8888`.

## Client Configuration

To connect a client application to the Config Server, you need to ensure the following:

- **Environment-specific configuration files** must be placed in a dedicated directory for your project within the `global-config` repository. This includes files like `application-dev.yml`, `application-prod.yml`, etc.
- The directory structure for your client application in the `global-config` repository should look like this:

    ```
    global-config/
    ├── project1/
    │   ├── application-dev.yml
    │   ├── application-prod.yml
    └── project2/
        ├── application-dev.yml
        ├── application-prod.yml
    ```

- The client application should include the following settings in its `bootstrap.yml`:

    ```yaml
    spring:
      application:
        name: your-application-name

      cloud:
        config:
          uri: http://localhost:8888
          profile: dev  # or prod, staging, etc.
          label: main   # or the specific branch you want to use
    ```