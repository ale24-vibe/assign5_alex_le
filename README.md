# Assignment 5 – Unit, Mocking, and Integration Testing

[![SE333_CI](https://github.com/ale24-vibe/assign5_alex_le/actions/workflows/SE333_CI.yml/badge.svg)](https://github.com/ale24-vibe/assign5_alex_le/actions/workflows/SE333_CI.yml)

---

## Project Overview

This project is based on the BarnesAndNoble sample code.  
Its purpose is to demonstrate specification-based, structural-based, unit, integration, and mocking tests in a Java Maven environment using JUnit5 and Mockito, with full CI/CD and artifact uploading via GitHub Actions.

---

## Parts Completed

### Part 1: Project Setup
- Maven and Java codebase initialized
- CI workflow configured: see [SE333_CI.yml](https://github.com/ale24-vibe/assign5_alex_le/actions/workflows/SE333_CI.yml)

### Part 2: Static Analysis & Coverage
- Checkstyle and JaCoCo enabled in Maven build
- Results uploaded as workflow artifacts

### Part 3: Testing
- All specification-based, structural-based, unit, integration, and mock-based tests implemented
- Examples: see `src/test/java/org/example/Amazon/AmazonIntegrationTest.java`

---

## Status

**All workflow actions passed:**
- All tests, builds, style checks, and coverage calculations were successful on the latest run.

---

## How to Run

**Run all tests locally:**
```bash
mvn test
```

**Generate coverage report:**
```bash
mvn jacoco:report
```

---

## Artifact Locations

- **Checkstyle report**: `target/checkstyle-result.xml`
- **Coverage report**: `target/site/jacoco/jacoco.xml`

---

## Author

Alex Le | [GitHub Repo](https://github.com/ale24-vibe/assign5_alex_le)
