package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepo.Locators;
import utils.Reports;

public class RegistrationPage {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	// Constructor
	public RegistrationPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Check if Sign-Up page is displayed
	public boolean isSignUpPageDisplayed() {
		return driver.getTitle().contains("Sign Up");
	}

	// Fill Registration Form
	public void enterRegistrationDetails(String firstName, String lastName, String email, String password,
			String confirmPassword) {
		clearFields();
		driver.findElement(Locators.fname).sendKeys(firstName);
		driver.findElement(Locators.lname).sendKeys(lastName);
		driver.findElement(Locators.rEmail).sendKeys(email);
		driver.findElement(Locators.rPassword).sendKeys(password);
		driver.findElement(Locators.rPassConfirmation).sendKeys(confirmPassword);
		Reports.generateReport(driver, test, Status.INFO, "Entered registration details.");
	}

	// Clears all fields before entering data
	private void clearFields() {
		driver.findElement(Locators.fname).clear();
		driver.findElement(Locators.lname).clear();
		driver.findElement(Locators.rEmail).clear();
		driver.findElement(Locators.rPassword).clear();
		driver.findElement(Locators.rPassConfirmation).clear();
	}

	// Submit the form with a manual wait for CAPTCHA input
	public void submitForm() {
	    WebElement submitButton = driver.findElement(Locators.rSubmit);
	    
	    System.out.println("Please complete the CAPTCHA manually on the website...");
	    
	    try {
	        Thread.sleep(5000); // Wait for 30 seconds to allow manual CAPTCHA input
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    submitButton.click();
	    Reports.generateReport(driver, test, Status.INFO, "Submitted the registration form after CAPTCHA input.");
	}


	public boolean isRedirectedToMyCourses() {
	    try {
	        wait.until(ExpectedConditions.titleIs("My Courses"));
	        Reports.generateReport(driver, test, Status.PASS, "User is redirected to My Courses.");
	        return true;
	    } catch (TimeoutException e) {
	        String actualTitle = driver.getTitle(); // Get the actual page title
	        Reports.generateReport(driver, test, Status.FAIL, 
	            "User is NOT redirected to My Courses. Actual page title: " + actualTitle);
	        return false;
	    }
	}

	public String getPasswordMismatchMessage() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement passwordMismatch = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.pwdsDoNotMatch));
	        return passwordMismatch.getText();
	    } catch (TimeoutException e) {
	        return "No validation message found";
	    }
	}
	

	public String getEmailValidationMessage() {
	    WebElement emailField = driver.findElement(Locators.rEmail);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    return (String) js.executeScript("return arguments[0].validationMessage;", emailField);
	}

	public String getFirstNameValidationMessage() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement fnameField = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.fname));
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    return (String) js.executeScript("return arguments[0].validationMessage;", fnameField);
	}

	// Refresh the page to clear previous data and wait until elements are ready
	public void refreshPage() {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.fname));
	}
	
	

}
