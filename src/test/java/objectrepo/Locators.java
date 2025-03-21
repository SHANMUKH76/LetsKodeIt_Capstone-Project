package objectrepo;

import org.openqa.selenium.By;

public class Locators {

	// Locator for Home page

	public static By home = By.xpath("//a[@href='/home' and text()='HOME']");
	public static By allCourses = By.xpath("//a[@href='/courses' and text()='ALL COURSES']");
	public static By interview = By.xpath("//a[@href='/interview' and text()='INTERVIEW']");
	public static By support = By.xpath("//a[@href='/support' and text()='SUPPORT']");
	public static By blog = By.xpath("//a[@href='/blog' and text()='BLOG']");
	public static By practice = By
			.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/div[1]/a[1]");
	public static By myCourses = By.xpath("//a[text()='MY COURSES']");
	public static By community = By.xpath("//a[text()='COMMUNITY']");
	public static By signIn = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/nav[1]/div[1]/div[2]/div[1]/div[1]/a[1]");

	// =====================================================================

	// Locators for Registration

	public static By signUp = By.xpath("//a[normalize-space()='Sign Up']");
	public static By fname = By.id("name");
	public static By lname = By.id("last_name");
	public static By rEmail = By.xpath("//form[@name='signup_form']//input[@id='email']");
	public static By rPassword = By.id("password");
	public static By rPassConfirmation = By.id("password_confirmation");
	public static By captcha = By.id("zen-recaptcha-div");
	public static By rSubmit = By.xpath("(//input[@value='Sign Up'])[1]");
	public static By pwdsDoNotMatch = By.cssSelector(".dynamic-text.help-block");
	public static By myCoursesPage = By.xpath("//h1[contains(text(),'My Courses')]");

	// =======================================================================

	// Locators for Login & Logout

	public static By lEmail = By.id("email");
	public static By lPassword = By.id("login-password");
	public static By lsubmit = By.id("login");
	public static By emailFieldErr = By.cssSelector("div[class='form-group'] span[class='error']");
	public static By passwordFieldErr = By.cssSelector("div[class='form-group'] span[class='error']");
	public static By incorrectDetailsErr = By.cssSelector("#incorrectdetails");
	public static By validEmailErr = By.cssSelector("div[class='form-group'] span[class='error']");
	public static By forgotPassword = By.xpath("//a[normalize-space()='Forgot Password?']");

	public static By arrowDropdown = By.className("zl-navbar-rhs-img");
	public static By logoutbtn = By.partialLinkText("Logout");

	// Locators for Forgot Password

	public static By fEmail = By.id("email");
	public static By fsubmit = By.xpath("//input[@value='Send Password Reset Link']");
	public static By confirmationMessage = By.xpath("//div[@class='alert alert-success']");
	public static By errorMessage = By.xpath("//span[@class='dynamic-text help-block']");

	// ===================================================================================

	// Locators for All Courses page

	public static By categoryDropdown = By.name("categories");
	public static By searchField = By.xpath("//input[@id='search']");
	public static By searchButton = By.xpath("//button[@type='submit']");
	public static By categoryHeading = By.xpath("//h1[contains(text(), 'Category')]");
	public static By javaTitle = By.xpath("//h4[normalize-space()='Java Step By Step For Testers']");
	public static By seleniumTitle = By.xpath("//h4[normalize-space()='Selenium WebDriver Advanced']");
	public static By apiTitle = By.cssSelector("h4[class='dynamic-heading']");
	public static By allCoursesHeading = By.xpath("//h1[normalize-space()='All Courses']");

	// =====================================================================================

	// Locators for Shopping cart

	public static By shopnowBtn = By.xpath(
			"//button[@class=\"Button-module--button--c17ef Button-module--primary--2e17d Hero-module--ctaButton--06285 undefined\"]");
	public static By addedgarmetprice = By.xpath("(//span[contains(text(),'13.90')])[1]");
	public static By selectgarmetimg = By.xpath("(//img[@alt='relaxed t shirt woman'])[1]");
	public static By addtocart = By.xpath("//div[@class='sample-module--addToButtonContainer--c7fa2']//button[@class='Button-module--button--c17ef Button-module--primary--2e17d Button-module--fullWidth--737a0'][normalize-space()='Add to Bag']");
	public static By checkoutcart = By.cssSelector("svg[width='18']");
	public static By checkoutpage = By.xpath("//button[normalize-space()='checkout']");
	public static By totalcartvalue = By
			.xpath("//div[contains(@class,'OrderSummary-module--totalContainer--491aa')]/span[2]");
	public static By removedefaultgarmet = By.xpath("//div[contains(@class,'cart-module--cartItemsContainer--da3d6')]//div[1]//div[5]//div[1]");
	public static By checkoutBtn = By.xpath("//button[normalize-space()='checkout']");
	public static By thankyoupage = By.xpath("//h1[normalize-space()='Thank You!']");

	// =========================================================================================

	// Locators for practice page

	// Radio Button
	public static By bmwRadio = By.id("bmwradio");
	public static By benzRadio = By.id("benzradio");
	public static By hondaRadio = By.id("hondaradio");

	// Checkbox
	public static By bmwCheckbox = By.id("bmwcheck");
	public static By benzCheckbox = By.id("benzcheck");
	public static By hondaCheckbox = By.id("hondacheck");

	// Switch Window
	public static By openWindowButton = By.id("openwindow");

	// Switch Tab
	public static By openTabLink = By.id("opentab");

	// Dropdown
	public static By carDropdown = By.id("carselect");

	// Multiple Select
	public static By multipleSelect = By.id("multiple-select-example");

	// Auto Suggest Example
	public static By autoSuggestField = By.id("autosuggest");

	// Enabled/Disabled Example
	public static By enabledInputField = By.id("enabled-example-input");
	public static By disableButton = By.id("disabled-button");
	public static By enableButton = By.id("enabled-button");

	// Hide/Show Example
	public static By hideButton = By.id("hide-textbox");
	public static By showButton = By.id("show-textbox");
	public static By displayedText = By.id("displayed-text");

	// Alert Example
	public static By alertField = By.id("name");
	public static By alertButton = By.id("alertbtn");

	// Mouse Hover Example
	public static By mouseHoverButton = By.id("mousehover");
	public static By mouseHoverTopOption = By.xpath("//a[text()='Top']");
	public static By mouseHoverReloadOption = By.xpath("//a[text()='Reload']");

	// Web Table Example
	public static By courseName = By.xpath("//td[text()='Selenium WebDriver With Java']");
	public static By coursePrice = By.xpath("//td[normalize-space()='35']");
}
