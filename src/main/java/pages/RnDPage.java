package pages;

import objects.JobPosition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reporter.Reporter;

import java.util.List;

public class RnDPage extends AbstractPage {
    @FindAll(@FindBy(xpath = "//a[contains(@class, 'careers-category__cards-listItem')]"))
    List<WebElement> positions;

    public RnDPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectPositionByName(String name) {
        positions.stream()
                .filter(e -> e.getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't find element with text " + name))
                .click();
    }

    @Override
    public boolean isOnPage() {
        return positions.size() > 0;
    }

    public List<String> getAllPositions() {
        return positions.stream()
                .map(WebElement::getText).toList();
    }

    public void applyForJob(String position, JobPosition job) {
        selectPositionByName(position);
        PositionPage page = new PositionPage(driver);

        if(page.isOnPage())
            page.fillTheJobForm(job);
        else
            Reporter.log("There is no open positions here");

        driver.navigate().back();
    }
}
