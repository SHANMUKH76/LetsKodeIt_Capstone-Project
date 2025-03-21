package com.demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LetsKodeIt {

	public static void main(String[] args) throws InterruptedException {
		

		WebDriver driver = new ChromeDriver();

		driver.get("https://ecommercepractice.letskodeit.com/shop");
		
		driver.findElement(By.xpath(null));

//		driver.findElement(By.id("name")).sendKeys("asdfkk");
//
//		driver.findElement(By.id("email")).sendKeys("shanmukh963@gmail.com");
//
//		driver.findElement(By.id("password")).sendKeys("shanmukh@123");
//
//		driver.findElement(By.id("password_confirmation")).sendKeys("shanmukh@123");
//		

        // Create an instance of WebDriverWait with a timeout of 10 seconds
        WebDriverWait wait = null ;

        try {
            // Wait for 10 seconds
            Thread.sleep(10000); // 10 seconds in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the Sign Up button after waiting
        driver.findElement(By.xpath("//input[@value='Sign Up']")).click();

//		// Locate the email field
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		WebElement emailField = driver.findElement(By.id("name"));
//		String emailValidationMessage = (String) js.executeScript("return arguments[0].validationMessage;", emailField);
//		if(!emailValidationMessage.isEmpty()) {
//			System.out.println("Validation Message: " + emailValidationMessage);
//
//		}

//		WebElement passwordMismatch = driver.findElement(By.cssSelector(".dynamic-text.help-block"));
//		if (!passwordMismatch.getText().isEmpty()) {
//			System.out.println(passwordMismatch.getText());
//		}
		
		
		WebElement parent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu zl-navbar-rhs-ul']")));
        
        // Wait for the logout link to be visible and clickable
        WebElement logout = parent.findElement(By.cssSelector("div[class='navbar-buttons navbar-mob jqLoginLogout zl-navbar-rhs'] li:nth-child(3)"));
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();

        // Verify logout success (e.g., check for a login button or redirect to the login page)
        wait.until(ExpectedConditions.urlContains("Login")); // Replace with the actual condition
		
	}
//
//	public static void main(String[] args) throws InterruptedException {
//		// Initialize WebDriver
//		WebDriver driver = new ChromeDriver();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		By arrowMenu = By.className("zl-navbar-rhs-img");
//		By logoutLink = By.partialLinkText("Logout");
//
//		// Navigate to the registration page
//		driver.get("https://www.letskodeit.com/register");
//
//		// Fill out the registration form
//		driver.findElement(By.id("name")).sendKeys("asdfkk");
//		driver.findElement(By.id("email")).sendKeys("shanmukh9223@gmail.com");
//		driver.findElement(By.id("password")).sendKeys("shanmukh@123");
//		driver.findElement(By.id("password_confirmation")).sendKeys("shanmukh@123");
//
//		// Wait for 10 seconds (to allow CAPTCHA to be solved manually)
//		System.out.println("Waiting for 10 seconds to solve CAPTCHA...");
//		Thread.sleep(30000); // 10 seconds in milliseconds
//
//		// Click the Sign Up button
//		System.out.println("Clicking the Sign Up button...");
//		driver.findElement(By.xpath("//input[@value='Sign Up']")).click();
//
//		try {
//			wait.until(ExpectedConditions.visibilityOfElementLocated(arrowMenu)).click();
//			wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).click();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
