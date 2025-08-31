# AutomationExercise - Cucumber + Java + Selenium (POM)

This framework automates an end-to-end purchase flow at http://automationexercise.com using:

- Java 11, Maven
- Selenium 4, Cucumber 7 (JUnit)
- POM using By locators (no PageFactory)
- All test data comes from the Feature file (Scenario Outline / Examples)
- Explicit waits + Select for dropdowns
- Screenshots on failure (saved to target/screenshots and attached to Cucumber scenario)
- WebDriverManager for driver management

## Run
From project root:
```
mvn clean test
```

Feature file: `src/test/resources/features/AutomationExercise.feature` (Scenario Outline - Examples hold all data)
