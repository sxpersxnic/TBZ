# Test Levels

## Levels of Testing

- Unit / Component Testing
- Integration Testing
- System Testing
- Acceptance Testing

---

## Exercise 1

- Which test levels have we encountered so far?
 	- Student 1:
  		- Unit Testing
  		- Integration Testing
  		- Acceptance Testing
 	- Student 2:
  		- Acceptance Testing

- When are tests executed?
  - Student 1:
 	  - During development (Unit Testing)
    		- CI pipelines (Unit/Integration Testing)
  - Student 2:
 	  - Manually after development (Acceptance Testing)
    		- User Acceptance Testing once per month

- Do we have dedicated testing or QA teams?
  - Student 1: No
  - Student 2: No

- How does our testing lifecycle look like?
  - Student 1:
 	  - Client defines requirements
    - Developer implements features and writes Unit Tests
    - CI runs tests on each commit

  - Student 2: We don't have a formal testing lifecycle.

## Exercise 2

### Testing approach

**Definition**:

A testing approach describes the overall philosophy, strategy, or mindset applied when designing and executing tests. It defines _how_ testing is performed at a high level.

**Dependency**:

- Testing approach -> affects testing levels and selection of techniques.

### Testing levels

**Definition**:

Testing levels are the stages at which software is tested during development. Each level focuses on a different granularity.

**Dependency**:

- Testing levels <- influenced by the approach
- Testing levels -> use specific types and techniques

### Testing types, techniques and tactics

**Definition**:

These describe _what_ is being tested and _how_ the _tests_ are designed.

**Dependency**:

These choices depend no:

- The testing approach (e.g., risk-based -> more boundary tests, more exploratory sessions)
- The testing level (unit tests often need white-box techniques, system tests use black-box)

### Summary of dependencies

- Testing approach -> influences testing levels
- Testing levels -> determine context for testing types, techniques, and tactics

- **Approach** sets the philosophy
- **Levels** define the scope
- **Types/Techniques/Tactics** define the concrete execution
