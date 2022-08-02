package reporter;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGTestListener extends TestListenerAdapter {

    /*
    This method is invoked each time the test method fails but is within the success percentage mentioned.
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        super.onTestFailure(result);
    }

    /*
    This method is invoked when any test method fails.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
    }

    /*
    This method is invoked when any test method succeeds.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
    }

    /*
    This method is invoked before any test method gets executed.
     */
    @Override
    public void onStart(ITestContext context) {
        super.onStart(context);
    }

    /*
    This method is invoked after all tests methods gets executed.
     */
    @Override
    public void onFinish(ITestContext context) {
        super.onFinish(context);
    }

    /*
    This method is invoked before any test methods are invoked.
     */
    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
    }

    /*
    This method is invoked when each test method is skipped.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);
    }

}