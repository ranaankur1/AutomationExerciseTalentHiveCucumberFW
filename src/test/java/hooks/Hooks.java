package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Hooks {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    // Initialize ExtentReports once
    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReports.html");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("E2E Test Execution");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Before
    public void setUp(Scenario scenario) {
        // Create ExtentTest for current scenario
        test = extent.createTest(scenario.getName());

        // Chrome Options
        ChromeOptions options = new ChromeOptions();

        // Disable password manager popups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Optional: disable notifications and automation banner
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        // Initialize driver
        driver = new ChromeDriver(options);

        // Maximize and set implicit waits
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Scenario Failed");
            // Optionally, attach screenshot for failure
            // test.addScreenCaptureFromPath(TakeScreenshot());
        } else {
            test.pass("Scenario Passed");
        }

        extent.flush();

        if (driver != null) {
            driver.quit();
        }
    }
}
