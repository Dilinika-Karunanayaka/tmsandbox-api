# TradeMe Sandbox API Test Automation

## Test Framework

### Tools and technologies
> - **RestAssured** - REST API Client 
> - **Cucumber** - BDD test layer 
> - Java - 11
> - Maven - Build and dependency management 
> - JUnit - Assertions  
> - Lombok - Helps to reduce boilerplate code
> - Log4j - Logging framework 
> - Masterthought - Custom test report

### Capabilities
> - Parallel test execution (dynamic or fixed)
> - Run tests against multiple environments based on selected profiles/configs 
> - Multiple test reports - default and third party

## Test Cases 
> - Positive, negative tests, edge cases
> - Security and performance aspects
> - Format, type and arrangement of data expected

## Test Execution
> -  mvn clean test
> -  mvn clean test -Penv.trademe
>  - **Note** - Maven Profile 'env.tmsandbox' is enabled by default

## Test Reports
> - Default Cucumber report - target/cucumber-reports/Cucumber.html
> - Cucumber timeline report - target/cucumber-reports/CucumberTimeline/index.html
> - Masterthought report - target/cucumber-html-reports/overview-features.html
