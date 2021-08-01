# backbaseAssignment

## HTML Report

Report is generated with [Extent Report v5](https://www.extentreports.com/docs/versions/5/java/index.html). JUnit Listeners are overridden to 
generate HTML automatically when tests classes extends `BaseTest`.
<br>Report properties can be found in `src/test/resources/report.properties`
<br>Available properties:

| Property Name   | Property Description   |
| --- |---|
|report.path | path where html report is generated |
|report.stackTrace | true enables stackTrace in report / false disables it|
