package pages;

import il.co.topq.difido.model.Enums;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporter.Reporter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void refreshElements() {
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isOnPage();

    public WebDriverWait getWait(long seconds) {
        return new WebDriverWait(driver, Duration.of(seconds, ChronoUnit.SECONDS));
    }

    public WebDriverWait getWait() {
        return getWait(30);
    }

    public void waitForElementEnabled(final WebElement element) {
        try {
            getWait(60).until((ExpectedCondition<Boolean>) driver ->
                    element.isEnabled());
        } catch (Exception e) {
            Reporter.log("Element " + element + " can't be interacted");
            Reporter.log("Error thrown: " + e.getMessage(), Enums.Status.error);
        }
    }

    public void scrollToElement(WebElement e) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        do {
            js.executeScript("arguments[0].scrollIntoView (true);", e);
        } while (!e.isDisplayed());
    }

    public void setAttribute(WebElement e, String attribute, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        do {
            js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", e, attribute, value);
        } while (!e.getAttribute(attribute).equals(value));
    }
}