# Global Config

## Overview
The `global-config` repository centralizes configuration management for multiple projects across different environments, such as development, QA, UAT, and production. This repository ensures consistent, secure, and efficient handling of configuration files, organized by project and environment.

**Note:** This repository is designed to support configurations for projects in multiple programming languages, including Java, Python, and others.

## Repository Organization
Here is how you can organize the repository:

```plaintext
global-config/
│
├── config-server/                       # Spring Cloud Config Server project
│   ├── src/
│   ├── pom.xml                          # Maven configuration file (if using Maven)
│   ├── build.gradle                     # Gradle configuration file (if using Gradle)
│   └── ...
│
├── application.yml                      # Global configurations common to all projects and environments
├── application-common.yml               # Configurations shared across all projects
├── project1/
│   ├── application-dev.yml              # Development environment configurations for project1
│   ├── application-test.yml             # Test environment configurations for project1
│   ├── application-prod.yml             # Production environment configurations for project1
│   ├── application-staging.yml          # Staging environment configurations for project1
│   └── secrets/
│       └── secrets-prod.yml.enc         # Encrypted sensitive configurations for production
│
├── project2/
│   ├── application-dev.yml
│   ├── application-test.yml
│   ├── application-prod.yml
│   ├── application-staging.yml
│   └── secrets/
│       └── secrets-prod.yml.enc         # Encrypted sensitive configurations for production
│
└── ...

1. Adding a New Project
To add a new project to the repository:

- Create a new directory under the root for your project (e.g., project3/).
- Add environment-specific configuration files within this directory (e.g., application-dev.yml, application-prod.yml, etc.).
- If needed, create a secrets/ directory inside your project directory for storing encrypted sensitive data.

2. Managing Configuration Files
- Global Configurations: Place any settings that should be consistent across all projects and environments in application.yml.
- Common Configurations: Use application-common.yml for settings shared across multiple projects but not globally.
- Environment-Specific Configurations: Use application-{env}.yml for environment-specific settings (e.g., application-dev.yml for development).
