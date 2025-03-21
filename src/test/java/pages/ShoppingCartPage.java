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

public class ShoppingCartPage {

	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	public ShoppingCartPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Navigate to eCommerce Site
	public boolean navigateToECommerceSite() {
		try {
			driver.get("https://ecommercepractice.letskodeit.com/");
			wait.until(ExpectedConditions.urlContains("ecommercepractice"));
			Reports.generateReport(driver, test, Status.PASS, "Navigated to eCommerce website.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to navigate");
			return false;
		}
	}

	// Click a Button (Shop Now / Checkout)
	public boolean clickButton(String button) {
		try {
			By locator = button.equalsIgnoreCase("Shop Now") ? Locators.shopnowBtn : Locators.checkoutBtn;
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			Reports.generateReport(driver, test, Status.PASS, "Clicked on button: " + button);
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to click button: " + button);
			return false;
		}
	}

	// Select Garment
	public boolean selectGarment() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators.shopnowBtn)).click();
			wait.until(ExpectedConditions.elementToBeClickable(Locators.selectgarmetimg)).click();
			Reports.generateReport(driver, test, Status.PASS, "Garment selected.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to select garment: ");
			return false;
		}
	}

	// Add to Cart
	public boolean addToCart() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators.addtocart)).click();
			Reports.generateReport(driver, test, Status.PASS, "Item added to cart.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to add to cart: ");
			return false;
		}
	}

	// Click on Cart Icon
	public boolean clickOnCartIcon() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators.checkoutcart)).click();
			Reports.generateReport(driver, test, Status.PASS, "Cart opened.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to open cart: ");
			return false;
		}
	}

	// Validate Cart Total
	public boolean validateCartTotal() {
		wait.until(ExpectedConditions.elementToBeClickable(Locators.checkoutpage)).click();
		String expectedPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.addedgarmetprice))
				.getText();
		String actualPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.totalcartvalue))
				.getText();
		boolean isMatching = (expectedPrice == actualPrice);
		if (isMatching) {
			Reports.generateReport(driver, test, Status.PASS, "Cart total validation: " + actualPrice);
			return isMatching;
		} else {
			Reports.generateReport(driver, test, Status.FAIL, "Items are not added to card, Add to Cart Failed ");
			return false;
		}
	}

	// Remove Default Garment
	public boolean removeDefaultGarment() {
		try {
			// Check if remove button is enabled
			WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.removedefaultgarmet));
			if (!removeButton.isEnabled()) {
				Reports.generateReport(driver, test, Status.FAIL, "Remove button is not working!");
				return false;
			}
			// Get cart total before removing the item
			String cartTotalBefore = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.totalcartvalue))
					.getText();
			removeButton.click();
			String cartTotalAfter = driver.findElement(Locators.totalcartvalue).getText();

			// Verify if the cart total changed
			if (cartTotalBefore.equals(cartTotalAfter)) {
				Reports.generateReport(driver, test, Status.FAIL, "Cart total did not change after removal!");
				return false;
			}
			Reports.generateReport(driver, test, Status.PASS, "Cart total updated successfully.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to remove default garment");
			return false;
		}
	}

	// Proceed to Checkout
	public boolean proceedToCheckout() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators.checkoutBtn)).click();
			Reports.generateReport(driver, test, Status.PASS, "Proceeded to checkout.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to proceed to checkout: ");
			return false;
		}
	}

	// Verify Thank You Page
	public boolean verifyThankYouPage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.thankyoupage)).isDisplayed();
	}
}
