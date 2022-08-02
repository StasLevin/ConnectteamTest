package tests;

import objects.Departments;
import objects.JobPosition;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.LandingPage;
import pages.RnDPage;
import reporter.Reporter;

import java.util.List;

public class ConnecteamTest extends tests.AbstractTest {

    public static final String URL = "https://connecteam.com/";

    @DataProvider
    public static Object[][] testDataProvider() {
        return new Object[][] {
                {
                    Departments.RnD
                }
        };
    }

    @Test(dataProvider = "testDataProvider")
    public void applyAllJobsRnDDepartment(Departments testCase) {
        Reporter.step("Open landing page");
        LandingPage landingPage = openLandingPage();

        Reporter.step("Open Careers page");
        CareersPage careersPage =  landingPage.clickCareerLink();

        Reporter.log("About to assert 'Careers page' is visible");
        Assert.assertTrue(careersPage.isOnPage(), "Something went wrong. 'Careers page' expected");

        Reporter.log("Existing departments: " + careersPage.getCareerDepartments());

        Reporter.log("About to asset the " + testCase.getDepartmentName() + " department is on the list");
        Assert.assertTrue(careersPage.getCareerDepartments().contains(testCase.getDepartmentName()),
                testCase.getDepartmentName() + " is not found :(" );

        Reporter.log("Select department: " + testCase.getDepartmentName());
        careersPage.selectDepartmentByName(testCase.getDepartmentName());

        switch (testCase){
            case RnD:
                fillAllRndPage();
            case GnA:
            case MARKETING:
            case CUSTOMER:
            case SUCCESS:
            case PRODUCT:
            case SALES:
                // TODO: implement this
                break;
            default:
                throw new RuntimeException("Something went wrong");
        }
    }

    private void fillAllRndPage() {
        RnDPage page = new RnDPage(getDriver());
        Reporter.log("About to assert 'RnD page' is visible");
        Assert.assertTrue(page.isOnPage(), "Something went wrong. 'RnD page' expected");

        List<String> availablePositions = page.getAllPositions();
        availablePositions
                .forEach(e -> {
                    Reporter.step("Applying for the job: " + e);
                    JobPosition job = createJobPosition(e);
                    page.applyForJob(e, job);
                });
    }

    private JobPosition createJobPosition(String e) {
        String ts = getTimeStamp();

        return JobPosition.builder()
                .fName("First_Name_" + ts)
                .lName("Last_Name_" + ts)
                .email("email_" + ts + "@email.com")
                .phone("phone_" + ts)
                .build();
    }

    private LandingPage openLandingPage() {
        Reporter.log("Open Landing Page");
        navigateToUrl(URL);

        LandingPage page = new LandingPage(getDriver());

        Reporter.log("About to assert 'Landing page' is visible");
        Assert.assertTrue(page.isOnPage(), "Something went wrong. 'Landing page' expected");
        return page;
    }
}
