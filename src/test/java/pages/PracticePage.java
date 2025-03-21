package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepo.Locators;
import utils.Reports;

public class PracticePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest test;

	public PracticePage(WebDriver driver, ExtentTest test) {
		if (driver == null) {
			throw new IllegalArgumentException("Driver is NULL in PracticePage! Check Step Definition.");
		}

		this.driver = driver;
		this.test = test;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Navigate to Practice Page
	public boolean navigateToPracticePage() {
		try {
			driver.get("https://www.letskodeit.com/practice");
			wait.until(ExpectedConditions.titleContains("Practice"));
			Reports.generateReport(driver, test, Status.PASS, "Navigated to Practice Page.");
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to navigate to Practice Page: " + e.getMessage());
			return false;
		}
	}

	// Select Radio Button
	public boolean selectRadioButton(String radioButton) {
	    try {
	        By locator = getRadioButtonLocator(radioButton);
	        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	        Reports.generateReport(driver, test, Status.PASS, "Selected radio button: " + radioButton);
	        return true;
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to select radio button: " + e.getMessage());
	        return false;
	    }
	}

	// Verify Radio Button is Selected
	public boolean isRadioButtonSelected(String radioButton) {
	    try {
	        By locator = getRadioButtonLocator(radioButton);
	        boolean isSelected = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isSelected();
	        Reports.generateReport(driver, test, Status.PASS, "Verified radio button: " + radioButton);
	        return isSelected;
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to verify radio button: " + e.getMessage());
	        return false;
	    }
	}


	// Select Checkbox
	public boolean selectCheckbox(String checkbox) {
		try {
			By locator = getCheckboxLocator(checkbox);
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			Reports.generateReport(driver, test, Status.PASS, "Selected checkbox: " + checkbox);
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to select checkbox: " + e.getMessage());
			return false;
		}
	}

	// Verify Checkbox is Selected
	public boolean isCheckboxSelected(String checkbox) {
		try {
			By locator = getCheckboxLocator(checkbox);
			boolean isSelected = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isSelected();
			Reports.generateReport(driver, test, Status.PASS, "Verified checkbox: " + checkbox);
			return isSelected;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to verify checkbox: " + e.getMessage());
			return false;
		}
	}

	// Select from Dropdown
	public boolean selectFromDropdown(String value) {
		try {
			Select dropdown = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(Locators.carDropdown)));
			dropdown.selectByVisibleText(value);
			Reports.generateReport(driver, test, Status.PASS, "Selected from dropdown: " + value);
			return true;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to select from dropdown: " + e.getMessage());
			return false;
		}
	}

	// Verify Dropdown Selection
	public String getSelectedDropdownValue() {
		try {
			Select dropdown = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(Locators.carDropdown)));
			String selectedValue = dropdown.getFirstSelectedOption().getText();
			Reports.generateReport(driver, test, Status.PASS, "Verified dropdown selection: " + selectedValue);
			return selectedValue;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to verify dropdown selection: " + e.getMessage());
			return null;
		}
	}

	// Helper Methods to Get Locators
	private By getRadioButtonLocator(String radioButton) {
		switch (radioButton) {
		case "BMW":
			return Locators.bmwRadio;
		case "Benz":
			return Locators.benzRadio;
		case "Honda":
			return Locators.hondaRadio;
		default:
			throw new IllegalArgumentException("Invalid radio button: " + radioButton);
		}
	}

	private By getCheckboxLocator(String checkbox) {
		switch (checkbox) {
		case "BMW":
			return Locators.bmwCheckbox;
		case "Benz":
			return Locators.benzCheckbox;
		case "Honda":
			return Locators.hondaCheckbox;
		default:
			throw new IllegalArgumentException("Invalid checkbox: " + checkbox);
		}

	}

	// Type in Auto-Suggest Field
	public void typeInAutoSuggest(String text) {
		try {
			WebElement autoSuggestField = wait
					.until(ExpectedConditions.visibilityOfElementLocated(Locators.autoSuggestField));
			autoSuggestField.clear();
			autoSuggestField.sendKeys(text);
			Reports.generateReport(driver, test, Status.PASS, "Typed in auto-suggest field: " + text);
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Failed to type in auto-suggest field: " + e.getMessage());
		}
	}

	// Click Button Based on Text
	public void clickButton(String button) {
		try {
			By locator = getButtonLocator(button);
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			Reports.generateReport(driver, test, Status.PASS, "Clicked button: " + button);
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Failed to click button: " + button + " - " + e.getMessage());
		}
	}

	// Helper method to get button locators dynamically
	private By getButtonLocator(String button) {
		switch (button) {
		case "Open Window":
			return Locators.openWindowButton;
		case "Open Tab":
			return Locators.openTabLink;
		case "Show":
			return Locators.showButton;
		case "Hide":
			return Locators.hideButton;
		case "Enable":
			return Locators.enableButton;
		case "Disable":
			return Locators.disableButton;
		case "Alert":
			return Locators.alertButton;
		default:
			throw new IllegalArgumentException("Invalid button: " + button);
		}
	}

	// Multiple Select Methods
	public void selectMultipleValues(List<String> values) {
		try {
			Select multiSelect = new Select(
					wait.until(ExpectedConditions.presenceOfElementLocated(Locators.multipleSelect)));
			multiSelect.deselectAll(); // Clear any previous selections
			for (String value : values) {
				multiSelect.selectByVisibleText(value);
			}
			Reports.generateReport(driver, test, Status.PASS, "Selected multiple values: " + values);
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to select multiple values: " + e.getMessage());
		}
	}

	public List<String> getSelectedMultipleValues() {
		try {
			Select multiSelect = new Select(
					wait.until(ExpectedConditions.presenceOfElementLocated(Locators.multipleSelect)));
			List<WebElement> selectedOptions = multiSelect.getAllSelectedOptions();
			List<String> selectedValues = new ArrayList<>();
			for (WebElement option : selectedOptions) {
				selectedValues.add(option.getText());
			}
			Reports.generateReport(driver, test, Status.PASS, "Retrieved selected multiple values: " + selectedValues);
			return selectedValues;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Failed to get selected multiple values: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	// Auto Suggest Methods
	public List<String> getAutoSuggestOptions() {
		try {
			List<WebElement> suggestions = wait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all']/li")));
			List<String> suggestionTexts = new ArrayList<>();
			for (WebElement suggestion : suggestions) {
				suggestionTexts.add(suggestion.getText());
			}
			Reports.generateReport(driver, test, Status.PASS, "Retrieved auto-suggest options: " + suggestionTexts);
			return suggestionTexts;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to get auto-suggest options: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	public void selectAutoSuggestOption(String option) {
		try {
			By suggestionLocator = By.xpath("//li[@class='ui-menu-item']/a[contains(text(),'"+option+"')]");
			wait.until(ExpectedConditions.elementToBeClickable(suggestionLocator)).click();
			Reports.generateReport(driver, test, Status.PASS, "Selected auto-suggest option: " + option);
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Failed to select auto-suggest option: " + e.getMessage());
		}
	}

	public String getAutoSuggestFieldValue() {
		try {
			String value = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.autoSuggestField))
					.getAttribute("value");
			Reports.generateReport(driver, test, Status.PASS, "Retrieved auto-suggest field value: " + value);
			return value;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Failed to get auto-suggest field value");
			return "";
		}
	}

	// Window/Tab Methods
	private String originalWindowHandle;

	public boolean isNewWindowOpened() {
		originalWindowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		return windowHandles.size() > 1;
	}

	public void switchToNewWindow() {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(originalWindowHandle)) {
				driver.switchTo().window(handle);
				Reports.generateReport(driver, test, Status.PASS, "Switched to new window: " + handle);
				break;
			}
		}
	}

	public boolean isNewTabOpened() {
		originalWindowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		return windowHandles.size() > 1;
	}

	public void switchback() {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(originalWindowHandle)) {
				driver.switchTo().window(handle);
				Reports.generateReport(driver, test, Status.PASS, "Switched to new tab: " + handle);
				break;
			}
		}
	}

	public void switchBetweenWindows() {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			Reports.generateReport(driver, test, Status.PASS, "Switched to window: " + handle);
		}
	}

	public boolean areAllWindowsNavigable() {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			try {
				driver.switchTo().window(handle);
				driver.manage().window().maximize();
			} catch (Exception e) {
				Reports.generateReport(driver, test, Status.FAIL, "Window not navigable: " + handle);
				return false;
			}
		}
		Reports.generateReport(driver, test, Status.PASS, "All windows are navigable");
		return true;
	}

	// Enabled/Disabled Methods
	public boolean isInputFieldEnabled() {
		try {
			boolean isEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.enabledInputField))
					.isEnabled();
			Reports.generateReport(driver, test, Status.PASS, "Checked input field enabled state: " + isEnabled);
			return isEnabled;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to check input field state: " + e.getMessage());
			return false;
		}
	}

	// Hide/Show Methods
	public boolean isTextboxVisible() {
		try {
			boolean isVisible = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.displayedText))
					.isDisplayed();
			Reports.generateReport(driver, test, Status.PASS, "Checked textbox visibility: " + isVisible);
			return isVisible;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to check textbox visibility: " + e.getMessage());
			return false;
		}
	}

	// Alert Methods
	public void enterAlertFieldAndClick(String text) {
		try {
			WebElement alertField = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.alertField));
			alertField.clear();
			alertField.sendKeys(text);
			clickButton("Alert");
			Reports.generateReport(driver, test, Status.PASS,
					"Entered text in alert field and clicked alert button: " + text);
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to enter alert field: " + e.getMessage());
		}
	}

	public String getAlertMessage() {
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			String message = alert.getText();
			Reports.generateReport(driver, test, Status.PASS, "Retrieved alert message: " + message);
			return message;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to get alert message: " + e.getMessage());
			return "";
		}
	}

	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			Reports.generateReport(driver, test, Status.PASS, "Accepted alert");
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to accept alert: " + e.getMessage());
		}
	}

	// Mouse Hover Methods
	public void hoverOverButton(String button) {
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Locators.mouseHoverButton));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
			Reports.generateReport(driver, test, Status.PASS, "Hovered over button: " + button);
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to hover over button: " + e.getMessage());
		}
	}

	public List<String> getHoverOptions() {
		try {
			List<WebElement> options = wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='mouse-hover-content']//a")));
			List<String> optionTexts = new ArrayList<>();
			for (WebElement option : options) {
				optionTexts.add(option.getText());
			}
			Reports.generateReport(driver, test, Status.PASS, "Retrieved hover options: " + optionTexts);
			return optionTexts;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to get hover options: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	// Web Table Methods
	public void checkCourse(String course) {
		// No-op as price check is direct
	}

	public String getCoursePrice(String course) {
		try {
			String price = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.coursePrice)).getText();
			Reports.generateReport(driver, test, Status.PASS, "Retrieved course price for " + course + ": " + price);
			return price;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to get course price: " + e.getMessage());
			return "";
		}
	}
	
	//getpage title
	public String getPageTitle() {
	    try {
	        String title = driver.getTitle();
	        Reports.generateReport(driver, test, Status.PASS, "Retrieved page title: " + title);
	        return title;
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to get page title: " + e.getMessage());
	        return "";
	    }
	}
	
	//click on link
	
	public void clickLink(String linkText) {
	    try {
	        By linkLocator = By.linkText(linkText);
	        wait.until(ExpectedConditions.elementToBeClickable(linkLocator)).click();
	        Reports.generateReport(driver, test, Status.PASS, "Clicked on link: " + linkText);
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to click on link: " + linkText + " - " + e.getMessage());
	    }
	}
	
	//is element displayed
	public boolean isElementDisplayed(String elementName) {
	    try {
	        By locator = getElementLocator(elementName);
	        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
	        Reports.generateReport(driver, test, Status.PASS, elementName + " is displayed: " + isDisplayed);
	        return isDisplayed;
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to check if " + elementName + " is displayed: " + e.getMessage());
	        return false;
	    }
	}

	// Helper method for element locators
	private By getElementLocator(String elementName) {
	    switch (elementName) {
	        case "Auto Suggest Field":
	            return Locators.autoSuggestField;
	        case "Displayed Text":
	            return Locators.displayedText;
	        case "Mouse Hover Button":
	            return Locators.mouseHoverButton;
	        default:
	            throw new IllegalArgumentException("Invalid element name: " + elementName);
	    }
	}
	
	//enter text in the input field
	
	public void enterTextInField(String fieldName, String text) {
	    try {
	        By locator = getFieldLocator(fieldName);
	        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	        field.clear();
	        field.sendKeys(text);
	        Reports.generateReport(driver, test, Status.PASS, "Entered text in " + fieldName + ": " + text);
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to enter text in " + fieldName + ": " + e.getMessage());
	    }
	}

	// Helper method for field locators
	private By getFieldLocator(String fieldName) {
	    switch (fieldName) {
	        case "Auto Suggest Field":
	            return Locators.autoSuggestField;
	        case "Alert Field":
	            return Locators.alertField;
	        case "Enabled Input Field":
	            return Locators.enabledInputField;
	        default:
	            throw new IllegalArgumentException("Invalid field name: " + fieldName);
	    }
	}
	
	//
	public String getFieldValue(String fieldName) {
	    try {
	        By locator = getFieldLocator(fieldName);
	        String value = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getDomAttribute("value");
	        Reports.generateReport(driver, test, Status.PASS, "Retrieved value from " + fieldName + ": " + value);
	        return value;
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to get value from " + fieldName + ": " + e.getMessage());
	        return "";
	    }
	}
	
	//select from dropdown
	public void selectFromDropdownByIndex(int index) {
	    try {
	        Select dropdown = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(Locators.carDropdown)));
	        dropdown.selectByIndex(index);
	        Reports.generateReport(driver, test, Status.PASS, "Selected dropdown option by index: " + index);
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to select dropdown option by index: " + index + " - " + e.getMessage());
	    }
	}
	
	//get dropdown index
	public String getDropdownOptionByIndex(int index) {
	    try {
	        Select dropdown = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(Locators.carDropdown)));
	        String optionText = dropdown.getOptions().get(index).getText();
	        Reports.generateReport(driver, test, Status.PASS, "Retrieved dropdown option at index " + index + ": " + optionText);
	        return optionText;
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to get dropdown option at index " + index + ": " + e.getMessage());
	        return "";
	    }
	}
	
	//deselect all check boxex
	public void deselectAllCheckboxes() {
	    try {
	        List<By> checkboxLocators = List.of(Locators.bmwCheckbox, Locators.benzCheckbox, Locators.hondaCheckbox);
	        for (By locator : checkboxLocators) {
	            WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	            if (checkbox.isSelected()) {
	                checkbox.click();
	            }
	        }
	        Reports.generateReport(driver, test, Status.PASS, "Deselected all checkboxes");
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to deselect all checkboxes: " + e.getMessage());
	    }
	}
	
	//all checkboxes
	public boolean areAllCheckboxesDeselected() {
	    try {
	        List<By> checkboxLocators = List.of(Locators.bmwCheckbox, Locators.benzCheckbox, Locators.hondaCheckbox);
	        for (By locator : checkboxLocators) {
	            if (wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isSelected()) {
	                Reports.generateReport(driver, test, Status.FAIL, "Checkbox is still selected");
	                return false;
	            }
	        }
	        Reports.generateReport(driver, test, Status.PASS, "All checkboxes are deselected");
	        return true;
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to check if all checkboxes are deselected: " + e.getMessage());
	        return false;
	    }
	}
	//upload file
	public void uploadFile(String filePath) {
	    try {
	        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file-upload")));
	        fileInput.sendKeys(filePath);
	        Reports.generateReport(driver, test, Status.PASS, "Uploaded file: " + filePath);
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to upload file: " + e.getMessage());
	    }
	}
	
	//verify file upload
	
	public boolean isFileUploaded() {
	    try {
	        // Assuming a confirmation element appears after upload (adjust locator as needed)
	        By confirmationLocator = By.id("file-uploaded");
	        boolean isUploaded = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationLocator)).isDisplayed();
	        Reports.generateReport(driver, test, Status.PASS, "File upload confirmed: " + isUploaded);
	        return isUploaded;
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to verify file upload: " + e.getMessage());
	        return false;
	    }
	}
	
	//Drag and Drop element
	
	public void dragAndDrop(String sourceElement, String targetArea) {
	    try {
	        WebElement source = wait.until(ExpectedConditions.presenceOfElementLocated(getElementLocator(sourceElement)));
	        WebElement target = wait.until(ExpectedConditions.presenceOfElementLocated(getElementLocator(targetArea)));
	        Actions actions = new Actions(driver);
	        actions.dragAndDrop(source, target).perform();
	        Reports.generateReport(driver, test, Status.PASS, "Dragged " + sourceElement + " to " + targetArea);
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to drag and drop: " + e.getMessage());
	    }
	}
	
	//verify drag and drop
	
	public boolean isElementInArea(String element, String area) {
	    try {
	        By areaLocator = getElementLocator(area);
	        WebElement areaElement = wait.until(ExpectedConditions.presenceOfElementLocated(areaLocator));
	        WebElement draggedElement = areaElement.findElement(getElementLocator(element));
	        boolean isInArea = draggedElement.isDisplayed();
	        Reports.generateReport(driver, test, Status.PASS, element + " is in " + area + ": " + isInArea);
	        return isInArea;
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to verify if " + element + " is in " + area + ": " + e.getMessage());
	        return false;
	    }
	}
	
	
}
