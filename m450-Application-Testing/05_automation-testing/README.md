# Automation Testing

## Exercise 1

To test the REST backend of the Spring Boot application automatically, I selected REST Assured, a Java-based testing framework designed specifically for automated REST API testing.
Since the backend is already written in Java/Spring Boot, REST Assured integrates naturally into the project’s Maven structure, requires no additional runtime, and runs directly inside JUnit tests.

### Approach

I created an automated test suite that calls the exposed REST endpoints, asserts the returned HTTP status codes, verifies JSON response bodies, and ensures the API adheres to the expected contract.

### Solution

[Solution](./spring-boot-angular-basic-lw2/src/test/java/ch/tbz/m450/testing/tools/api/APITest.java)

### Result

The backend’s API can now be tested automatically through JUnit using REST Assured.
Running the tests ensures the REST contract is respected before the application is deployed.

⸻

## Exercise 2

For the automated E2E GUI testing of the Angular application, I selected Cypress.

### Reasoning

Cypress is currently the most accessible and developer-friendly end-to-end testing framework for modern front-end frameworks. It runs tests directly in a real browser, provides time-travel debugging, integrates well with CI pipelines, and works flawlessly with Angular applications.

### E2E Test Implementation

[Cypress E2E Test](./spring-boot-angular-basic-lw2/src/main/js/my-app/cypress/e2e/students.cy.ts)

⸻

## Exercise 3

I used Apache JMeter to generate high-traffic load against the Spring Boot backend.
JMeter is widely used for performance and stress testing of REST APIs.

### Exploring JMeter

Key features explored during the exercise:

-	Thread Groups – define how many virtual users send requests
-	HTTP Request Sampler – configure GET/POST requests to the Spring Boot API
-	CSV Data Set Config – optional parameterization for tests
-	Listeners – view results in tables, graphs, and reports
-	Assertions – validate correctness of responses
-	Timers – simulate real-world request delays

### Test Scenario

I created a test plan that simulates 200 virtual users, each repeatedly calling the /students endpoint.

### High-Level Configuration

-	Thread Group:
-	Users = 200
-	Ramp-up = 10 seconds
-	Loop Count = 50
-	Sampler:
-	`GET http://localhost:8081/students`
-	Listener:
-	Vew Results Tree
-	Summary Report

### Observed Results

|Metric	|Result|
|------|-------|
|Total Requests	|10,000|
|Average Response Time	|0ms (local server)|
|Error Rate	|0%|
|Max Response Time	|3ms|
|Throughput	|1003.6 requests/sec|

Short Markdown Summary (deliverable)

### Load Testing Summary (JMeter)

#### Tool Used

Apache JMeter 5.6.3

#### Scenario

A load test was executed against the Spring Boot backend, focusing on the `/students` endpoint.

## Configuration

- 200 virtual users
- Ramp-up: 10 seconds
- Loop Count: 50
- Total requests: 10,000

### Results

- Average response time: 0ms
- Maximum response time: 3ms
- Error rate: 0%
- Throughput: 1003.61 requests/second

### Conclusion

The backend handled the simulated traffic without errors and maintained stable response times.
Based on these results, the Spring Boot service appears suitable for moderate production workloads. But it must be considered that the tests were run locally, and real-world performance may vary based on deployment environment and network conditions. Further testing in a production-like environment is recommended for more accurate performance assessment.
