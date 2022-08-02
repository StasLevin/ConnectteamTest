package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CareersPage extends AbstractPage {
    @FindAll(@FindBy(xpath = "//a[contains(@class, 'section-careers__listItem')]"))
    List<WebElement> careersDepartments;

    public CareersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getCareerDepartments() {
        return careersDepartments
                .stream()
                .map(WebElement::getText).toList();
    }

    public void selectDepartmentByName(String name) {
        careersDepartments.stream()
                .filter(e -> e.getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't find element with text " + name))
                .click();
    }

    @Override
    public boolean isOnPage() {
        return careersDepartments.size() > 0;
    }
}
