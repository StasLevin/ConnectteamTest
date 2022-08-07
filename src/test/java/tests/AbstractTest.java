package tests;

import org.bouncycastle.util.Times;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import reporter.Reporter;
import webdriver.WebDriverModule;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Listeners({il.co.topq.difido.ReportManagerHook.class, reporter.TestNGTestListener.class})
public class AbstractTest {
    private WebDriver driver;

    // TestNG Context
    protected static ITestContext iTestContext;

    // Tests run time counter
    private LocalDateTime testStartTime;

    @BeforeSuite
    public void beforeSuite(ITestContext testContext) {
        iTestContext = testContext;
    }

    @BeforeTest
    void setupTest() {
        driver = WebDriverModule.init();
    }

    @AfterTest
    void tearDown() {
        WebDriverModule.quitDriver();
    }

    @BeforeMethod
    public void beforeMethod() {
        testStartTime = LocalDateTime.now();
        Reporter.startTest(testStartTime);
    }

    @AfterMethod
    public void afterMethod() {
        LocalDateTime testEndTime = LocalDateTime.now();
        long testDurationInSeconds = ChronoUnit.SECONDS.between(testStartTime, testEndTime);
        Reporter.log("Test duration in seconds: " + testDurationInSeconds);
        Reporter.addTestProperty(String.format("durationInSeconds: %s", testDurationInSeconds));
        Reporter.endTest();
    }

    @AfterSuite
    public void afterSuite() {
        try {
            String path = Paths.get(".")
                    .toAbsolutePath()
                    .normalize()
                    .toString()
                    .concat("/test-output/difido/current/index.html");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(path));
        } catch (IOException e) {
            Reporter.log("Failed to open reporter :(");
            e.printStackTrace();
        }
    }

    public void navigateToUrl(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getTimeStamp() {
        return String.valueOf(Timestamp.from(Instant.now()));
    }

}