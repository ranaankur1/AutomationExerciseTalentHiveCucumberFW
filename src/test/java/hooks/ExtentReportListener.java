package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportListener {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReports.html");
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("E2E Test Execution");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
