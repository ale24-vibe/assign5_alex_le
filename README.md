# Assignment 5 – Unit, Mocking, and Integration Testing

## Project Overview

[![SE333_CI](https://github.com/ale24-vibe/assign5_alex_le/actions/workflows/SE333_CI.yml/badge.svg)](https://github.com/ale24-vibe/assign5_alex_le/actions/workflows/SE333_CI.yml)

This project is based on the BarnesAndNoble code provided in the Assignment5_code package. 
The main goal is to practice writing and organizing tests, including both specification-based and structural-based testing approaches, using JUnit in IntelliJ.

## Static Analysis (Checkstyle)

To run locally:
```bash
mvn checkstyle:checkstyle
```
Checkstyle report: `target/checkstyle-result.xml`  
The workflow also uploads this report artifact.

## 🧪 Running Tests & Coverage

To run all tests:
```bash
mvn test
```
To generate a code coverage report:
```bash
mvn jacoco:report
```
Coverage report: `target/site/jacoco/jacoco.xml`  
Uploaded as an artifact by the workflow.

## 📦 Automated CI/CD

Workflow steps performed on each push:
1. Checkout code and set up Java 21
2. Install dependencies
3. Run Checkstyle analysis
4. Upload Checkstyle report
5. Run tests (JUnit5, Mockito)
6. Generate JaCoCo coverage report
7. Upload coverage report

All relevant artifacts are downloadable from the GitHub Actions run.

## Artifact Locations

- **Static Analysis (Checkstyle):**  
  `target/checkstyle-result.xml`

- **Code Coverage (JaCoCo):**  
  `target/site/jacoco/jacoco.xml`

## Author

Alex Le | ale24-vibe

---

_Assignment 5: SE333 – Parts 1 & 2 completed. See workflow in `.github/workflows/SE333_CI.yml`._