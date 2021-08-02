package com.backbase.setup.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.backbase.utils.PropertiesReader;
import org.junit.jupiter.api.extension.*;

public class BaseTest implements BeforeAllCallback, BeforeTestExecutionCallback, AfterAllCallback, AfterTestExecutionCallback {
    protected static ExtentReports reports;
    protected static ExtentTest test;
    private PropertiesReader propertiesReader = new PropertiesReader("report.properties");

    /**
     * Overrides beforeAll to automatically create .html report
     *
     * @param context Extension Report test context
     * @throws Exception
     */
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        String filename = System.getProperty("user.dir") + propertiesReader.readProperty("report.path") + context.getDisplayName() + "_Results.html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filename);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
    }

    /**
     * Overrides beforeTestExecution to add new test to report
     *
     * @param context Extension Report test context
     * @throws Exception
     */
    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        test = reports.createTest(context.getDisplayName());
        test.log(Status.INFO, context.getDisplayName() + " - started");

    }

    /**
     * Overrides afterTestExecution to set correct report test output
     *
     * @param context Extension Report test context
     * @throws Exception
     */
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (!context.getExecutionException().isPresent()) {
            test.pass(context.getDisplayName() + " - passed");
        } else {
            test.fail(context.getExecutionException().get().getLocalizedMessage() + generateStackTrace(context));
        }
    }

    /**
     * Override afterAll to complete report after all com.backbase.tests
     *
     * @param context Extension Report test context
     * @throws Exception
     */
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        reports.flush();
    }

    /**
     * Generate stack trace as String correctly displayed in report
     *
     * @param context Extension Report test context
     * @return stack trace as String
     */
    private String generateStackTrace(ExtensionContext context) {
        if (propertiesReader.readProperty("report.stackTrace").toLowerCase().equals("true")) {
            String stackTraceString = "<br /><br />Stack Trace:<br />";
            for (StackTraceElement stackTrace : context.getExecutionException().get().getStackTrace()) {
                stackTraceString = stackTraceString + stackTrace.toString() + "<br />";
            }
            return stackTraceString;
        }
        return "";
    }
}
