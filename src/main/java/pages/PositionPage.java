package pages;

import objects.JobPosition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import reporter.Reporter;

public class PositionPage extends AbstractPage {
    @FindBy(xpath = "//iframe[@id = 'grnhse_iframe']")
    WebElement iframe;
    @FindBy(xpath = "//form[@id = 'application_form']")
    WebElement applicationForm;
    @FindBy(xpath = "//input[@id = 'first_name']")
    WebElement firstName;
    @FindBy(xpath = "//input[@id = 'last_name']")
    WebElement lastName;
    @FindBy(xpath = "//input[@id = 'email']")
    WebElement email;
    @FindBy(xpath = "//input[@id = 'phone']")
    WebElement phone;


    public PositionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void fillTheJobForm(JobPosition job) {
        firstName.clear();
        firstName.sendKeys(job.getFName());
        Reporter.log("About to assert the first name entered");
        Assert.assertEquals(firstName.getAttribute("value"), job.getFName(), "Expected: " + job.getFName());
        Reporter.log("First Name: " + firstName.getAttribute("value"));

        lastName.clear();
        lastName.sendKeys(job.getLName());
        Reporter.log("About to assert the last name entered");
        Assert.assertEquals(lastName.getAttribute("value"), job.getLName(), "Expected: " + job.getLName());
        Reporter.log("Last Name: " + lastName.getAttribute("value"));

        email.clear();
        email.sendKeys(job.getEmail());
        Reporter.log("About to assert the email entered");
        Assert.assertEquals(email.getAttribute("value"), job.getEmail(), "Expected: " + job.getEmail());
        Reporter.log("Email: " + email.getAttribute("value"));

        phone.clear();
        phone.sendKeys(job.getPhone());
        Reporter.log("About to assert the phone entered");
        Assert.assertEquals(phone.getAttribute("value"), job.getPhone(), "Expected: " + job.getPhone());
        Reporter.log("Phone: " + phone.getAttribute("value"));

        driver.switchTo().defaultContent();
    }

    @Override
    public boolean isOnPage() {
        try {
            getWait(30).until(ExpectedConditions.visibilityOf(iframe));
            driver.switchTo().frame(iframe);
            return applicationForm.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
