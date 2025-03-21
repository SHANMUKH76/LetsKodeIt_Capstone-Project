package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepo.Locators;
import utils.Reports;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest test;

	public LoginPage(WebDriver driver, ExtentTest test) {
		if (driver == null) {
			throw new IllegalArgumentException("Driver is NULL in LoginPage! Check Step Definition.");
		}

		this.driver = driver;
		this.test = test;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Perform Login
	public boolean login(String email, String password) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.lEmail)).sendKeys(email);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.lPassword)).sendKeys(password);
			Reports.generateReport(driver, test, Status.PASS, "Login attempted with email: " + email);
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Login failed: " + e.getMessage());
			return false;
		}
	}

	// Retrieve Email Field Error Message
	public String getEmailFieldError() {
		return getErrorMessage(Locators.emailFieldErr);
	}

	// Retrieve Password Field Error Message
	public String getPasswordFieldError() {
		return getErrorMessage(Locators.passwordFieldErr);
	}

	// Retrieve "Incorrect Login Details" Error Message
	public String getIncorrectDetailsError() {
		return getErrorMessage(Locators.incorrectDetailsErr);
	}

	// Retrieve "Valid Email Required" Error Message
	public String getValidEmailError() {
		return getErrorMessage(Locators.validEmailErr);
	}

	// Generic Method to Fetch Error Messages
	private String getErrorMessage(By locator) {
		try {
			WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return errorElement.getText();
		} catch (Exception e) {
			return "No error message found";
		}
	}

	// Check if login page is displayed
	public boolean isLoginPageDisplayed() {
		boolean result = driver.getTitle().contains("Login");
		if (result) {
			Reports.generateReport(driver, test, Status.PASS, "Login page is displayed.");
		} else {

			Reports.generateReport(driver, test, Status.FAIL, "Login page is NOT displayed.");
		}
		return result;
	}

	// Check if signup page is displayed
	public boolean isSignupPageDisplayed() {
		boolean result = driver.getTitle().contains("Register");
		if (result) {
			Reports.generateReport(driver, test, Status.PASS, "SignUP page is displayed.");
		} else {
			Reports.generateReport(driver, test, Status.FAIL, "SignUp page is NOT displayed.");
		}
		return result;
	}

	// Navigate to Sign-Up Page
	public boolean navigateToSignUp() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.signUp)).click();
			Reports.generateReport(driver, test, Status.PASS, "Navigated to Sign-Up page.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to navigate to Sign-Up page.");
			return false;
		}
	}

	public boolean navigateToLogin() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.signIn)).click();
			Reports.generateReport(driver, test, Status.PASS, "Navigated to Log-IN page.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to navigate to Log-In page.");
			return false;
		}

	}

	public boolean clickOnMenuAndLogout() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.arrowDropdown)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.logoutbtn)).click();
			Reports.generateReport(driver, test, Status.PASS, "User logged out successfully.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Unable to click the logout button. Exception: " + e.getMessage());
			return false;
		}

	}
	
	public boolean verifyLogin(String expectedtitle) {
		boolean isOnExpectedPage = driver.getTitle().contains(expectedtitle);
		try {
			if(isOnExpectedPage) {
			Reports.generateReport(driver, test, Status.PASS, "User logged in successfully.");
			}
		}
		catch(Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Creadentials Were Reset, Login Unsuccessfull");
		}
		return isOnExpectedPage;
	}

}