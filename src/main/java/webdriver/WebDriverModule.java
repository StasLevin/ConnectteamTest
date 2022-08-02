package webdriver;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverModule {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static WebDriver init() {

        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }

    public static WebDriverWait getWait() {
        return wait;
    }
}