package StepDefinition;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectrepo.Locators;
import pages.AllCoursesPage;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PracticePage;
import pages.RegistrationPage;
import pages.ShoppingCartPage;
import utils.Base;

public class LetsKodeItStepDef extends Base {

	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	SoftAssert SA;
	HomePage homePage;
	LoginPage loginPage;
	ForgotPasswordPage forgotPasswordPage;
	RegistrationPage registrationPage;
	AllCoursesPage allcoursespage;
	ShoppingCartPage shoppingcartpage;
	PracticePage practicepage;

	public LetsKodeItStepDef() {
		this.driver = Hooks.driver;
		this.test = Hooks.test;

		if (this.driver == null) {
			throw new RuntimeException("Driver is null in Step Definition! Check Hooks.");
		}
		if (this.test == null) {
			throw new RuntimeException("ExtentTest is null in Step Definition! Check Hooks.");
		}

		this.loginPage = new LoginPage(driver, test);
		this.registrationPage = new RegistrationPage(driver, test);
		this.homePage = new HomePage(driver, test);
		this.forgotPasswordPage = new ForgotPasswordPage(driver, test);
		this.allcoursespage = new AllCoursesPage(driver, test);
		this.shoppingcartpage = new ShoppingCartPage(driver, test);
		this.practicepage = new PracticePage(driver, test);
		this.SA = new SoftAssert();
	}

	// Home Page Verification

	@Given("User navigates to {string}")
	public void userNavigatesTo(String url) {
		driver.get(url);
	}

	@And("Page title should be {string}")
	public void pageTitleShouldBe(String expectedTitle) {
		Assert.assertTrue(homePage.validateTitle(expectedTitle), "Page title validation failed!");
	}

	@And("User should see the following elements:")
	public void userShouldSeeFollowingElements(DataTable dataTable) {
		List<String> elements = dataTable.asList();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		for (String element : elements) {
			By locator = HomePage.pagegetLocatorForElement(element);
			WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Assert.assertTrue(webElement.isDisplayed(), "Element not visible: " + element);
		}
	}

	@Then("User navigates to Sign-In page")
	public void userNavigatesToSignInPage() {
		Assert.assertTrue(homePage.navigateToSignIn(), "Failed to navigate to Sign-In page!");
	}
	
	//======================================================================================

//	// Registration Flow
//
//	@Given("User is on Login page for registration")
//	public void userIsOnLoginPageForRegistration() {
//		boolean displayedornot = loginPage.isLoginPageDisplayed();
//		if (displayedornot) {
//			Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed.");
//		} else {
//			driver.get("https://www.letskodeit.com/login");
//		}
//	}
//
//	@Then("User selects Sign-Up option")
//	public void userSelectsSignUpOption() {
//		loginPage.navigateToSignUp();
//	}
//
//	@Given("User should land on Registration page")
//	public void userShouldLandOnRegistrationPage() {
//		Assert.assertTrue(loginPage.isSignupPageDisplayed(), "Sign-Up page is not displayed.");
//	}
//
//	@When("User provides registration details {string}, {string}, {string}, {string}, {string}")
//	public void userProvidesRegistrationDetails(String firstName, String lastName, String email, String password,
//			String confirmPassword) {
//		registrationPage.refreshPage();
//		registrationPage.enterRegistrationDetails(firstName, lastName, email, password, confirmPassword);
//	}
//
//	@And("User solves Captcha challenge")
//	public void userSolvesCaptchaChallenge() {
//		System.out.println("Captcha bypassed for automation.");
//	}
//
//	@And("User submits registration form")
//	public void userSubmitsRegistrationForm() {
//		registrationPage.submitForm();
//	}
//
//	@Then("User verifies registration outcome {string}")
//	public void userVerifiesRegistrationOutcome(String expectedOutcome) {
//		String actualOutcome = "No validation message found";
//
//		switch (expectedOutcome) {
//		case "My Courses":
//			Assert.assertTrue(registrationPage.isRedirectedToMyCourses(), "User was not redirected to My Courses.");
//			return;
//
//		case "Passwords do not match.":
//			actualOutcome = registrationPage.getPasswordMismatchMessage();
//			break;
//
//		case "Please include an '@' in the email address. 'Shanmukh.com' is missing an '@'.":
//			actualOutcome = registrationPage.getEmailValidationMessage();
//			break;
//
//		case "Please fill out this field.":
//			actualOutcome = registrationPage.getFirstNameValidationMessage();
//			break;
//
//		default:
//			throw new IllegalArgumentException("Invalid outcome: " + expectedOutcome);
//		}
//
//		Assert.assertEquals(expectedOutcome, actualOutcome, "Validation message mismatch!");
//	}

	// ---------------------------------- Logout Scenario
	// ----------------------------------

//	@Then("User logs out if login is successful")
//	public void userLogsOutIfLoginIsSuccessful() {
//		if (driver.getTitle().equals("My Courses")) {
//			Assert.assertTrue(loginPage.clickOnMenuAndLogout(), "Logout failed!");
//			loginPage.navigateToLogin();
//		} else {
//			System.out.println("Login was not successful. Skipping logout.");
//		}
//	}

	// ---------------------------------- Invalid Login Scenario
	// ----------------------------------

	@Given("User arrives on Login page")
	public void userArrivesOnLoginPage() {
		homePage.navigateToSignIn(); // Navigate to the login page
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed!");
	}

	@When("User provides username {string} and password {string}")
	public void userProvidesInvalidCredentials(String email, String password) {
		loginPage.login(email, password);
	}

	@And("User presses Login button")
	public void userPressesLoginButton() {
		driver.findElement(Locators.lsubmit).click();
	}

	@Then("User should receive an error message {string}")
	public void userShouldReceiveAnErrorMessage(String expectedError) {
		String actualError;

		switch (expectedError) {
		case "The email must be a valid email address.":
			actualError = loginPage.getEmailFieldError();
			break;

		case "The password field is required.":
			actualError = loginPage.getPasswordFieldError();
			break;

		case "Incorrect login details":
			actualError = loginPage.getIncorrectDetailsError();
			break;

		case "The email field is required.":
			actualError = loginPage.getEmailFieldError();
			break;

		default:
			actualError = "No error message found";
		}

		Assert.assertEquals(actualError, expectedError, "Error message mismatch!");
	}

	// ---------------------------------- Forgot Password Flow
	// ----------------------------------

	@When("User initiates password reset")
	public void userInitiatesPasswordReset() {
		driver.findElement(Locators.forgotPassword).click();
	}

	@Then("User should land on Forgot Password page")
	public void userShouldLandOnForgotPasswordPage() {
		Assert.assertTrue(driver.getTitle().contains("Password Reset"), "Forgot Password page is not displayed.");
	}

	@When("User inputs {string} for password reset")
	public void userInputsEmailForPasswordReset(String email) {
		forgotPasswordPage.enterEmail(email);
	}

	@Then("User confirms password reset request")
	public void userConfirmsPasswordResetRequest() {
		forgotPasswordPage.submitForgotPasswordRequest();
	}


//	//login with valid credentials
//
//	@Given("User is back on Sign-In page")
//    public void user_is_back_on_sign_in_page() {
//        boolean isNavigated = loginPage.navigateToLogin(); // Navigate using page object method
//        Assert.assertTrue(isNavigated, "Failed to navigate to Sign-In page!");
//        }
//
//    @When("User provide username {string} and password {string}")
//    public void user_provide_username_and_password(String email, String password) {
//        boolean isLoginAttempted = loginPage.login(email, password); // Perform login
//        Assert.assertTrue(isLoginAttempted, "Login attempt failed!");
//    }
//
//    @When("User press Login button")
//    public void user_press_login_button() {
//        driver.findElement(Locators.lsubmit).click();
//        // You can add any extra verification if needed
//    }
//
//    @Then("User should be logged in successfully")
//    public void user_should_be_logged_in_successfully() {
//        // Validate login by checking page redirection or dashboard elements
//        boolean isLoginSuccessful = driver.getTitle().contains("My Courses"); // Assuming title contains "My Courses" after login
//        Assert.assertTrue(isLoginSuccessful, "Login was not successful!");
//    }
//
//    @Then("User should see the {string} page")
//    public void user_should_see_the_page(String expectedPage) {
//        boolean isOnExpectedPage = loginPage.verifyLogin(expectedPage);
//        Assert.assertTrue(isOnExpectedPage, "User is not on the expected page: " + expectedPage);	
//
//    }

	// =====================================================================================================

	// All Courses page verification

	@Given("I am on the All Courses page")
	public void userArrivesOnAllCoursesPage() {
		allcoursespage.loadAllCoursesPage();
		Assert.assertTrue(allcoursespage.isAllCoursesPageDisplayed(), "All Courses page is not displayed!");
	}

	@When("I select {string} from the category dropdown")
	public void userSelectsCategory(String category) {
		Assert.assertTrue(allcoursespage.selectCategory(category), "Failed to select category!");
	}

	@Then("I should see {string} displayed on the page")
	public void userShouldSeeCategoryHeading(String expectedHeading) {
		String actualHeading;

		if (expectedHeading.equals("All Courses")) {
			// For "All" category, check the "All Courses" heading
			actualHeading = allcoursespage.getAllCoursesHeading();
		} else {
			// For specific categories, check the category heading
			actualHeading = allcoursespage.getCategoryHeading();
		}

		System.out.println("Actual Heading: " + actualHeading); // Debugging
		Assert.assertEquals(actualHeading, expectedHeading, "Category heading mismatch!");
	}

	@When("I enter {string} in the search course field and click search")
	public void userSearchesForCourse(String keyword) {
		Assert.assertTrue(allcoursespage.searchCourse(keyword), "Search failed!");
	}

	@Then("I should see a course titled {string} displayed on the page")
	public void userShouldSeeCourseDisplayed(String courseTitle) {
		Assert.assertTrue(allcoursespage.isCourseDisplayed(courseTitle), "Expected course is not displayed!");
	}

	// =========================================================================================================

	// Shopping cart feature

	@Given("User is on the shopping page")
	public void user_is_on_the_shopping_page() {
		boolean result = shoppingcartpage.navigateToECommerceSite();
		Assert.assertTrue(result, "Failed to navigate to shopping page.");
	}

	@When("User adds a garment to the cart")
	public void user_adds_a_garment_to_the_cart() {
		boolean selected = shoppingcartpage.selectGarment();
		Assert.assertTrue(selected, "Failed to select garment.");

		boolean added = shoppingcartpage.addToCart();
		Assert.assertTrue(added, "Failed to add garment to cart.");

		boolean cartOpened = shoppingcartpage.clickOnCartIcon();
		Assert.assertTrue(cartOpened, "Failed to open cart.");
	}

	@Then("Cart total should match the garment price")
	public void cart_total_should_match_the_garment_price() {
		boolean isValid = shoppingcartpage.validateCartTotal();
		SA.assertTrue(isValid, "Cart total mismatch! Failing test.");
	}

	@When("User removes the default garment from the cart")
	public void user_removes_the_default_garment_from_the_cart() {
		boolean removed = shoppingcartpage.removeDefaultGarment();
		SA.assertTrue(removed, "Failed to remove default garment.");
		
	}

	@Then("The item should be removed successfully")
	public void the_item_should_be_removed_successfully() {
		boolean removed = shoppingcartpage.removeDefaultGarment();
		SA.assertTrue(removed, "Item removal verification failed.");
	}

	@When("User clicks on checkout")
	public void user_clicks_on_checkout() {
		boolean result = shoppingcartpage.proceedToCheckout();
		Assert.assertTrue(result, "Failed to proceed to checkout.");
	}

	@Then("User should reach the checkout page")
	public void user_should_reach_the_checkout_page() {
		boolean result = shoppingcartpage.verifyThankYouPage();
		Assert.assertTrue(result, "Checkout Page verification failed!");
	}

	// =================================================================================================================

	// Practice page

	// Navigate to Practice Page
	@Given("I am on the Practice Page")
	public void i_am_on_the_practice_page() {
		Assert.assertTrue(practicepage.navigateToPracticePage(), "Failed to navigate to Practice Page");
	}

	@When("I select and verify the following radio buttons:")
	public void i_select_and_verify_the_following_radio_buttons(DataTable dataTable) {
		List<String> radioButtons = dataTable.asList(String.class);

		for (String selectedButton : radioButtons) {
			// Select the radio button
			Assert.assertTrue(practicepage.selectRadioButton(selectedButton),
					"Failed to select radio button: " + selectedButton);

			// Verify only this button is selected
			for (String button : radioButtons) {
				if (button.equals(selectedButton)) {
					Assert.assertTrue(practicepage.isRadioButtonSelected(button),
							"Radio button not selected: " + button);
				} else {
					Assert.assertFalse(practicepage.isRadioButtonSelected(button),
							"Radio button should not be selected: " + button);
				}
			}
		}
	}

	// Verify selected radio buttons
	@Then("only one radio button should be selected at a time")
	public void only_one_radio_button_should_be_selected() {
		List<String> radioButtons = Arrays.asList("BMW", "Benz", "Honda");
		int selectedCount = 0;
		String selectedButton = "";

		for (String button : radioButtons) {
			if (practicepage.isRadioButtonSelected(button)) {
				selectedCount++;
				selectedButton = button;
			}
		}

		// Only one radio button should be selected
		Assert.assertEquals(selectedCount, 1,
				"More than one radio button is selected! Selected button: " + selectedButton);
	}

	// Select checkboxes
	@When("I select the following checkboxes:")
	public void i_select_the_following_checkboxes(DataTable dataTable) {
		List<String> checkboxes = dataTable.asList(String.class);
		for (String checkbox : checkboxes) {
			Assert.assertTrue(practicepage.selectCheckbox(checkbox), "Failed to select checkbox: " + checkbox);
		}
	}

	// Verify selected checkboxes
	@Then("the following checkboxes should be selected:")
	public void the_following_checkboxes_should_be_selected(DataTable dataTable) {
		List<String> checkboxes = dataTable.asList(String.class);
		for (String checkbox : checkboxes) {
			Assert.assertTrue(practicepage.isCheckboxSelected(checkbox), "Checkbox not selected: " + checkbox);
		}
	}

	// Select dropdown values
	@When("I select the following values from the dropdown:")
	public void i_select_the_following_values_from_the_dropdown(DataTable dataTable) {
		List<String> values = dataTable.asList(String.class);
		for (String value : values) {
			Assert.assertTrue(practicepage.selectFromDropdown(value), "Failed to select dropdown value: " + value);
		}
	}

	// Verify selected dropdown values
	@Then("the following values should be selected in the dropdown:")
	public void the_following_values_should_be_selected_in_the_dropdown(DataTable dataTable) {
		List<String> expectedValues = dataTable.asList(String.class);
		String actualValue = practicepage.getSelectedDropdownValue();
		Assert.assertTrue(expectedValues.contains(actualValue),
				"Dropdown value mismatch. Expected one of: " + expectedValues + ", but got: " + actualValue);
	}

	// Select multiple values from multiple select
	@When("I select the following values from the multiple select:")
	public void i_select_the_following_values_from_the_multiple_select(DataTable dataTable) {
		List<String> values = dataTable.asList(String.class);
		practicepage.selectMultipleValues(values);
	}

	// Verify selected multiple select values
	@Then("the following values should be selected in the multiple select:")
	public void the_following_values_should_be_selected_in_the_multiple_select(DataTable dataTable) {
		List<String> expectedValues = dataTable.asList(String.class);
		List<String> actualValues = practicepage.getSelectedMultipleValues();
		Assert.assertEquals(expectedValues, actualValues, "Multiple select values mismatch");
	}

	// **10. Type in auto-suggest field**
	@When("I type {string} in the auto-suggest field")
	public void i_type_in_the_auto_suggest_field(String text) {
		practicepage.typeInAutoSuggest(text);
	}

	// **11. Verify auto-suggest suggestions**
	@Then("the following suggestions should appear:")
	public void the_following_suggestions_should_appear(DataTable dataTable) {
		List<String> expectedSuggestions = dataTable.asList(String.class);
		List<String> actualSuggestions = practicepage.getAutoSuggestOptions();
		Assert.assertEquals(expectedSuggestions, actualSuggestions, "Auto-suggest options mismatch");
	}

	// Select from auto-suggest suggestions
	@When("I select {string} from the suggestions")
	public void i_select_from_the_suggestions(String suggestion) {
		practicepage.selectAutoSuggestOption(suggestion);
	}

	// **13. Verify input field contains selected suggestion**
	@Then("the input field should contain {string}")
	public void the_input_field_should_contain(String expectedText) {
		String actualText = practicepage.getAutoSuggestFieldValue();
		Assert.assertEquals(expectedText, actualText, "Auto-suggest field value mismatch");
	}

	// **14. New Window button**
	@When("I click the {string} button")
	public void i_click_the_button(String button) {
		practicepage.clickButton(button);
	}

	// **15. Verify new window opens**
	@Then("a new window should open")
	public void a_new_window_should_open() {
		Assert.assertTrue(practicepage.isNewWindowOpened(), "New window did not open");
	}

	// **16. Switch to new window**
	@And("I switch to the new window")
	public void i_switch_to_the_new_window() {
		practicepage.switchToNewWindow();
	}

	// **17. Verify new window title**
	@And("the title of the new window should be {string}")
	public void the_title_of_the_new_window_should_be(String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle, "New window title mismatch");

	}

	// New tab button
	@When("I click {string} button")
	public void i_click_Open_New_Window_button(String button) {
		practicepage.clickButton(button);
	}

	// . Verify new tab opens**
	@Then("a new tab should open")
	public void a_new_tab_should_open() {
		Assert.assertTrue(practicepage.isNewTabOpened(), "New tab did not open");
	}

	// **19. Switch to new tab**
	@Then("I switch back to practice page tab")
	public void i_switch_to_the_new_tab() {
		practicepage.switchback();
	}

	// **20. Verify new tab title**
	@Then("the title of the new tab should be {string}")
	public void the_title_of_the_new_tab_should_be(String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle, "New tab title mismatch");
	}

	// **21. Open multiple new windows**
	@When("I open {int} new windows")
	public void i_open_new_windows(Integer count) {
		for (int i = 0; i < count; i++) {
			practicepage.clickButton("Open Window");
		}
	}

	// **22. Switch between windows**
	@When("I switch between windows")
	public void i_switch_between_windows() {
		practicepage.switchBetweenWindows();
	}

	// **23. Verify all windows are navigable**
	@Then("all windows should be navigable")
	public void all_windows_should_be_navigable() {
		Assert.assertTrue(practicepage.areAllWindowsNavigable(), "Not all windows are navigable");
	}

	// **24. Disable input field**
	@When("I disable the input field")
	public void i_disable_the_input_field() {
		practicepage.clickButton("Disable");
	}

	// **25. Verify input field is disabled**
	@Then("the input field should be disabled")
	public void the_input_field_should_be_disabled() {
		Assert.assertFalse(practicepage.isInputFieldEnabled(), "Input field is not disabled");
	}

	// **26. Enable input field**
	@When("I enable the input field")
	public void i_enable_the_input_field() {
		practicepage.clickButton("Enable");
	}

	// **27. Verify input field is enabled**
	@Then("the input field should be enabled")
	public void the_input_field_should_be_enabled() {
		Assert.assertTrue(practicepage.isInputFieldEnabled(), "Input field is not enabled");
	}

	// **28. Hide textbox**
	@When("I hide the textbox")
	public void i_hide_the_textbox() {
		practicepage.clickButton("Hide");
	}

	// **29. Verify textbox is hidden**
	@Then("the textbox should be hidden")
	public void the_textbox_should_be_hidden() {
		Assert.assertFalse(practicepage.isTextboxVisible(), "Textbox is not hidden");
	}

	// **30. Show textbox**
	@When("I show the textbox")
	public void i_show_the_textbox() {
		practicepage.clickButton("Show");
	}

	// **31. Verify textbox is visible**
	@Then("the textbox should be visible")
	public void the_textbox_should_be_visible() {
		Assert.assertTrue(practicepage.isTextboxVisible(), "Textbox is not visible");
	}

	// **32. Enter text in alert field and click alert button**
	@When("I enter {string} in the alert field and click the alert button")
	public void i_enter_in_the_alert_field_and_click_the_alert_button(String text) {
		practicepage.enterAlertFieldAndClick(text);
	}

	// **33. Verify alert message**
	@Then("an alert with the message {string} should appear")
	public void an_alert_with_the_message_should_appear(String expectedMessage) {
		String actualMessage = practicepage.getAlertMessage();
		Assert.assertEquals(expectedMessage, actualMessage, "Alert message mismatch");
		practicepage.acceptAlert();
	}

	// **34. Hover over button**
	@When("I hover over the {string} button")
	public void i_hover_over_the_button(String button) {
		practicepage.hoverOverButton(button);
	}

	// **35. Verify hover options**
	@Then("the following options should appear:")
	public void the_following_options_should_appear(DataTable dataTable) {
		List<String> expectedOptions = dataTable.asList(String.class);
		List<String> actualOptions = practicepage.getHoverOptions();
		Assert.assertEquals(expectedOptions, actualOptions, "Hover options mismatch");
	}

	// **36. Check course**
	@When("I check the course {string}")
	public void i_check_the_course(String course) {
		practicepage.checkCourse(course);
	}

	// **37. Verify course price**
	@Then("the price should be {string}")
	public void the_price_should_be(String expectedPrice) {
		String actualPrice = practicepage.getCoursePrice("Selenium WebDriver With Java"); // Example course name
		Assert.assertEquals(expectedPrice, actualPrice, "Course price mismatch");
	}

}
