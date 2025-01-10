# Project m320 - Cloud Resource Orchestration API

## Overview

- [Criteria](#criteria)
- [Documentation](#documentation)
    - [Project Overview](#project-overview)
    - [Project Plan](#project-overview)
        - [Dependencies](#dependencies)
        - [Project Setup](#project-setup)
        - [Project Structure](#project-structure)
        - [To Implement](#to-implement)
        - [Core Features](#core-features)
        - [Pre Development UML](#pre-development-uml-class-diagram)
        - [User Stories](#user-stories)
    - [Post Development UML](#post-development-uml)
    - [Sequence Diagram](#sequence-diagram)

## Criteria

- [ ] Pre Development UML - Class Diagram
- [ ] 7 Classes
- [ ] 800 Lines of Code
- [ ] 1 Custom Exception
- [ ] 1 Interface
- [ ] 1 Design Pattern + Description in **README.md**
- [ ] Commented and challenged Pull Request
- [ ] Post Class Diagram compared to Pre Class Diagram
- [ ] Sequence diagram of single Use-Case

---

## Documentation

### Project Overview

This application serves as an GraphQL API for Cloud Resource Orchestration.

### Project Plan

#### Dependencies

1. Frameworks and Libraries:
    - Java Spring Boot
        - Spring Web
        - Spring for GraphQL
        - Spring Data JPA
        - Spring Boot DevTools
        - Spring Configuration Processor
    - Lombok
    - Validation
    - PostgreSQL Driver

2. Build Tool:
    - Gradle

3. Cloud SDKs:
    - AWS SDK for Java
    - Azure SDK for Java
    - Google Cloud SDK for Java

4. Testing Tools:
    - JUnit
    - Mockito
    - H2 Database

#### Project Setup

1. Initialization

    - Use [Spring Initializr](https://start.spring.io)
    - Specifications:
        - **Project:** Gradle - Groovy
        - **Language:** Java
        - **Spring Boot:** 3.4.1
        - **Project Metadata:**
            - **Group:** com.orchestra
            - **Artifact:** api
            - **Name:** api
            - **Description:** API for cloud resource orchestration.
            - **Package name:** com.orchestra.api
        - **Packaging:** Jar
        - **Java:** 23
        - **Dependencies:** [Dependencies](#dependencies)

2. Database Configuration
    - In `application.yml`, configure PostgreSQL
    - Set up schema generation for tables (models).
3. Gradle configuration
4. Environment Management
  - Use profiles for local staging and production environments.

#### Project Structure

```css
src/
├── main/
│   ├── java/com/example/orchestration/
│   │   ├── api/           # GraphQL resolvers
│   │   ├── model/         # Entity classes (e.g., Template, Resource)
│   │   ├── repository/    # Spring Data repositories
│   │   ├── service/       # Core services (business logic)
│   │   ├── connector/     # Cloud provider connectors
│   │   ├── exception/     # Custom exceptions (e.g., ResourceNotFoundException)
│   │   └── config/        # Configuration classes
│   └── resources/
│       ├── application.yml    # Configuration file
│       └── graphql/           # GraphQL schema files
├── test/
│   ├── java/com/example/orchestration/
│   │   ├── api/           # Tests for GraphQL resolvers
│   │   ├── service/       # Tests for services
│   │   ├── connector/     # Tests for cloud connectors
│   └── resources/
│       └── test-data/     # Sample test data
```

#### To Implement

#### Core Features

1. Template Management
    - Create, retrieve and manage resource templates.
    - Validate templates for completeness and correctness
2. Resource Provisioning
    - Provision resources using templates.
    - Support for multiple cloud providers (AWS, Azure, GCP).
3. Resource Lifecycle Management
    - Retrieve resource details.
    - Delete resources across cloud providers.
4. GraphQL API
    - Expose APIs to manage templates and resources using GraphQL
    - Query and mutation support.
5. Error Handling
    - Custom exceptions:
        - ResourceNotFoundException
    - Unified error response structure.
6. Cloud Provider Abstraction
    - Interface for common operations like create, delete and retrieve.
    - Concrete implementations for AWS, Azure and GCP.
7. Database Integration
    - Use PostgreSQL for template storage.
    - Persist resource metadata.

#### Pre Development UML (Class-Diagram)

#### User Stories

1. Template Creation
    - As a developer, I want to create resource templates to define infrastructure requirements.
    - Acceptance Criteria:
        - GraphQL mutation to create a template.
        - Validation of template fields.

2. Retrieve Templates
    - As a developer, I want to view a list of templates to choose infrastructure setups.
    - Acceptance Criteria:
        - GraphQL query to retrieve all templates.
        - Support for filtering by name.

3. Provision Resources
    - As a developer, I want to provision resources using a specific template to deploy infrastructure.
    - Acceptance Criteria:
        - GraphQL mutation to provision resources.
        - Interaction with the appropriate cloud provider.

4. View Resources
    - As a developer, I want to view details of provisioned resources to monitor infrastructure.
    - Acceptance Criteria:
        - GraphQL query to retrieve resource details.
        - Include resource metadata and state.

5. Delete Resources
    - As a developer, I want to delete provisioned resources to manage costs and cleanup.
    - Acceptance Criteria:
        - GraphQL mutation to delete resources.
        - Interaction with cloud provider to ensure deletion.

6. Error Feedback
    - As a developer, I want meaningful error messages to understand issues with my requests.
    - Acceptance Criteria:
        - Unified error response.
        - Custom exceptions for common errors.

7. Template Validation
    - As a developer, I want to validate templates to ensure they are deployable.
    - Acceptance Criteria:
        - Validation during creation.
        - Error responses for invalid templates.

8. Multi-Cloud Support
    - As a developer, I want to provision resources across multiple cloud providers to support hybrid architectures.
    - Acceptance Criteria:
        - Support AWS, Azure and GCP connectors.
        - Abstracted interface for cloud operations.

9. Database Persistence
    - As a developer, I want templates and resource metadata to be persisted for future reference.
    - Acceptance Criteria:
        - PostgreSQL integration.
        - Data persistence for templates and resources.

10. System Health Monitoring
    - As a developer, I want to monitor the system's health to ensure API availability.
    - Acceptance Criteria:
        - Health check endpoint.
        - Logging for errors and critical operations.

### Post Development UML

### Sequence Diagram