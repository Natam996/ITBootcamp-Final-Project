**Testing Automation Exercise Application**

Automation Exercise is a free demo e-commerce website designed for QA engineers and developers to practice web UI automation, functional testing, and end-to-end user workflows.

This project demonstrates automated testing of key e-commerce functionalities such as user registration, login, product search, cart management, ordering, product reviews, and contact forms. The test suite includes positive scenarios, negative scenarios, expected failures, and validation of business requirements through assertions.

───

**Dependencies**

- Operating System: Windows 10
- IDE: IntelliJ IDEA Community Edition 2026.1
- Java Development Kit (JDK) 21 LTS
- Apache Maven 5.5.1  
- Test Framework: TestNG
- Browser: Google Chrome (required)
- Optional Browsers: Firefox, Microsoft Edge, Safari

  ───

  **Installation**

  Clone the repository using Git:

  https://github.com/Natam996/ITBootcamp-Final-Project.git

  Verify that the following software is installed:

- Java 21 LTS
- Apache Maven 5.5.1
- Google Chrome

  ───

  **Executing Program**

  Run all tests from terminal with:
```
mvn test
```
Run specific class from terminal with:
```
mvn test -Dtest="SignupTest"
```
Run specific xml file from terminal with:
```
mvn clean test -DsuiteXmlFile="testng.xml"

  ───

  **Framework Walkthrough**

  **Packages**

- Base - Contains classes used through the app
- Pages - Contains classes for each page
- Tests - Contains test classes


  **Test Classes**

  The project currently contains the following test cases:

|**Test Class**|**Number of Test Cases**|
| :- | :- |
|SignupTest|5|
|LoginTest|3|
|AddToCartTest|5|
|RemoveFromCartTest|1|
|SearchProductsTest|5|
|OrderProductTest|2|
|ReviewProductTest|2|
|ContactSiteTest|2|

**Total Test Cases:** 25

**Test Coverage**

The automated suite covers:

- User registration
- User login and authentication
- Product search functionality
- Adding products to cart
- Removing products from cart
- Product ordering and checkout flow
- Product review submission
- Contact form functionality

  **Test Types Included**

  The project contains different categories of automated tests:

- Happy Flow Tests
- Positive Test Scenarios
- Negative Test Scenarios
- Validation Tests
- Assertion-Based Failure Scenarios
- Functional End-to-End Tests

  Some test cases are intentionally designed to fail due to unsuccessful assertions in order to demonstrate error detection and reporting capabilities.

  ───

  **Project Files**

- **pom.xml** – Contains all project dependencies and plugins.
- **TestData.xlsx** – External test data source used for Data-Driven Testing (DDT).
- **testng.xml** – Main TestNG suite configuration.
- **.gitignore** – Defines files and folders excluded from version control.

  ───

  **Design Patterns & Technologies**

  This project is built using:

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Data-Driven Testing (DDT)


  ───

  **Naming Convention**

- Classes are written in camel case with first character in upper case
- Methods are written in camel case with first character in lower case
- Class objects are named the same as a class name with lower case for first character
- Test methods are named as test case names


  ───

  **Best Practices**

- Test methods are kept clean and readable.
- Business logic is separated through the Page Object Model pattern.
- Assertions are used to validate expected behavior.
- Reusable methods are centralized in base classes.
- Test data is separated from test logic whenever possible.
- Tests are designed to be independent and maintainable.

  ───

  **Test Execution Results**

  The framework supports:

- Passed test reporting
- Failed test reporting
- Assertion validation
- Detailed stack traces for debugging
- TestNG execution summaries

  ───

  **Future Improvements**

  Potential enhancements for the project include:

- Parallel test execution
- Cross-browser testing support
- CI/CD integration (Jenkins/GitHub Actions)
- Screenshot capture on failure
- Extent Reports integration

  API test coverage expansion

  ───
