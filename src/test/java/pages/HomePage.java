package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepo.Locators;
import utils.Reports;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	public HomePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Validate the correct page title
	public boolean validateTitle(String expectedTitle) {
		boolean actResult = true;
		try {
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle, "Title validation failed!");
			Reports.generateReport(driver, test, Status.PASS, "Page title is correct: " + actualTitle);
		} catch (AssertionError e) {
			actResult = false;
			Reports.generateReport(driver, test, Status.FAIL, "Page title mismatch!");
		}
		return actResult;
	}


	// Navigate to Sign-In Page
	public boolean navigateToSignIn() {
	    boolean actResult = true;
	    try {
	        driver.get("https://www.letskodeit.com/login"); // Directly navigate to the login page
	        wait.until(ExpectedConditions.urlContains("/login")); // Ensure the page has loaded
	        Reports.generateReport(driver, test, Status.PASS, "Navigated to Sign-In page via URL.");
	    } catch (Exception e) {
	        actResult = false;
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to navigate to Sign-In page via URL.");
	    }
	    return actResult;
	}
	
	//Helper method for checking Webelements from home page
	public static By pagegetLocatorForElement(String elementText) {
		// TODO Auto-generated method stub
		switch (elementText.toUpperCase()) {
		case "HOME":
			return Locators.home;
		case "ALL COURSES":
			return Locators.allCourses;
		case "INTERVIEW":
			return Locators.interview;
		case "SUPPORT":
			return Locators.support;
		case "BLOG":
			return Locators.blog;
		case "PRACTICE":
			return Locators.practice;
		case "MY COURSES":
			return Locators.myCourses;
		case "COMMUNITY":
			return Locators.community;
		default:
			throw new IllegalArgumentException("Invalid element: " + elementText);
		}
	}

}
