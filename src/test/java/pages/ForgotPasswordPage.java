package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.Reports;
import objectrepo.Locators;

public class ForgotPasswordPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest test;

    public ForgotPasswordPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //Navigate to Forgot Password Page
    public void navigateToForgotPasswordPage(String url) {
        driver.get(url);
        Reports.generateReport(driver, test, Status.INFO, "Navigated to Forgot Password page: " + url);
    }

    //Enter email in Forgot Password field
    public void enterEmail(String email) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.fEmail));
            driver.findElement(Locators.fEmail).sendKeys(email);
            Reports.generateReport(driver, test, Status.PASS, "Entered email: " + email);
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to enter email: " + e.getMessage());
        }
    }

    //Click on Submit button
    public void submitForgotPasswordRequest() {
        try {
        	driver.findElement(Locators.fsubmit).click();
        	WebElement errorMessageElement = driver.findElement(Locators.errorMessage);
        	String errorMessage = errorMessageElement.getText();
        	System.out.println(errorMessage);
            Reports.generateReport(driver, test, Status.PASS, "Clicked on submit button");
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to click submit: ");
        }
    }

    public String getNotificationMessage() {
        try {
            WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.errorMessage));
            if (err != null) {
                Reports.generateReport(driver, test, Status.PASS, "Error Message is displayed for Incorrect credentials");
                return err.getText();
            }
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "No success or error message displayed");
        }
        return "No message displayed.";
    }

}
