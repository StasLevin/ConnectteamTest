package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractPage {
    @FindBy(xpath = "//a[contains(@class, 'is-arrow')]")
    WebElement getStartedButton;

    @FindBy(xpath = "//a[@href = '/careers/']")
    WebElement careersLink;

    public LandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CareersPage clickCareerLink() {
        scrollToElement(careersLink);
        careersLink.click();
        return new CareersPage(driver);
    }

    @Override
    public boolean isOnPage() {
        return getStartedButton.isDisplayed();
    }
}
