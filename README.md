# backbaseAssignment

1. [Framework dependencies](#framework-dependencies)
2. [Local Run](#local-run)
3. [BrowserStack run](#browserstack-run)
4. [Selenium Grid run](#selenium-grid-run)
5. [How to run tests with Maven](#how-to-run-tests-with-maven)
6. [HTML Report](#html-report)
7. [Test properties](#test-properties)
8. [Manual tests](#manual-tests)

## Framework dependencies

| Name   |  Description   |
| --- |---|
| JUnit Jupiter | test runner |
| Extent reports | report generator |
| Rest Assured | api testing framework |
| Selenium | ui testing framework |


## Local Run

There are three web browser drivers added to project:
* chrome
* firefox
* edge

To run tests locally you need to update `src/test/resources/driver.properties` file with values:

* driver.platform=<b>local</b>
* driver.browser=

Browsers can be chosen between the ones mentioned above. Notice that added drivers are for Windows. If you run it on other OS make sure to use correct drivers 
and change System Separators in `src/test/resources/driver.properties`.

### Headless

To run browsers in headless mode set `driver.headless` parameter to `true` in file `src/test/resources/driver.properties`

## BrowserStack run

Tests can be run on [BrowserStack](https://www.browserstack.com). To run it you need to update `src/test/resources/driver.properties` file with values: 

* driver.bs.username=
* driver.bs.accesskey=
* driver.platform=<b>bs</b>

BrowserStacks credentials can be obtained after logging to your account in [BrowserStack](https://www.browserstack.com) and find button `ACCESS KEY`.
<br>After test run you can find video of the run at you account in `DASHBOARD` panel.

## Selenium Grid run

To run test in Selenium Grid you need to update `src/test/resources/driver.properties` file with values:

* driver.platform=<b>grid</b>
* driver.selenium.grid.url=

Selenium Grid url can point to external instance url or local instance created with e.g. docker. 

## How to run tests with Maven

To run all tests:
```
mvn clean test -Dsuite=ALL
```

OR

```
mvn clean test
```

To run ui tests:
```
mvn clean test -Dsuite=UI
```

To run api tests:
```
mvn clean test -Dsuite=API
```

## HTML Report

Report is generated with [Extent Report v5](https://www.extentreports.com/docs/versions/5/java/index.html). JUnit Listeners are overridden to 
generate HTML automatically when com.backbase.tests classes extends `BaseTest`.
<br>Report properties can be found in `src/test/resources/report.properties`
<br>Available properties:

| Property Name   | Property Description   |
| --- |---|
|report.path | path where html report is generated |
|report.stackTrace | true enables stackTrace in report / false disables it|

## Test properties

Test properties can be found in `src/test/resources/test.properties`. Notice that it contains credentials. In real project, it would be better to store that
in some kind of credential manager (e.g. [Secrets Manager Credentials Provider](https://plugins.jenkins.io/aws-secrets-manager-credentials-provider/) in Jenkins)

## Manual tests

Manual tests can be found in `src/test/resources/manualtest`. It contains api and ui tests for create user and create/remove article. 