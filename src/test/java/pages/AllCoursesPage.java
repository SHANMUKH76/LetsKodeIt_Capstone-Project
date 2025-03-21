package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepo.Locators;
import utils.Reports;

public class AllCoursesPage {
    WebDriver driver;
    WebDriverWait wait;
    ExtentTest test;

    public AllCoursesPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Load All Courses page
    public void loadAllCoursesPage() {
        try {
           // driver.get("https://www.letskodeit.com/courses");
            wait.until(ExpectedConditions.urlContains("/courses"));
            Reports.generateReport(driver, test, Status.PASS, "Navigated to All Courses page.");
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to navigate to All Courses page.");
        }
    }

    // Verify All Courses Page is Displayed
    public boolean isAllCoursesPageDisplayed() {
        boolean isDisplayed = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.categoryDropdown));
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.searchField));
            isDisplayed = true;
            Reports.generateReport(driver, test, Status.PASS, "All Courses page is displayed.");
        } catch (TimeoutException e) {
            Reports.generateReport(driver, test, Status.FAIL, "All Courses page is not displayed.");
        }
        return isDisplayed;
    }

    // Select Category from Dropdown
    public boolean selectCategory(String category) {
        boolean actResult = true;
        try {
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.categoryDropdown));
            Select select = new Select(dropdown);
            select.selectByVisibleText(category);
            Reports.generateReport(driver, test, Status.PASS, "Selected category: " + category);
        } catch (TimeoutException e) {
            actResult = false;
            Reports.generateReport(driver, test, Status.FAIL, "Failed to select category: " + category);
        }
        return actResult;
    }

    // Validate Category Heading
    public boolean validateCategoryHeading(String expectedHeading) {
        boolean actResult = true;
        try {
            String actualHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.categoryHeading)).getText();
            Assert.assertEquals(actualHeading, expectedHeading, "Category heading validation failed!");
            Reports.generateReport(driver, test, Status.PASS, "Correct category heading displayed: " + actualHeading);
        } catch (AssertionError | TimeoutException e) {
            actResult = false;
            Reports.generateReport(driver, test, Status.FAIL, "Category heading mismatch! Expected: " + expectedHeading);
        }
        return actResult;
    }

    // Search Course
    public boolean searchCourse(String keyword) {
        boolean actResult = true;
        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.searchField));
            searchInput.clear();
            searchInput.sendKeys(keyword);
            driver.findElement(Locators.searchButton).click();
            Reports.generateReport(driver, test, Status.PASS, "Searched for course: " + keyword);
        } catch (TimeoutException e) {
            actResult = false;
            Reports.generateReport(driver, test, Status.FAIL, "Search field not found or failed to enter keyword: " + keyword);
        }
        return actResult;
    }

    // Verify if Course is Displayed
    public boolean isCourseDisplayed(String courseTitle) {
        boolean actResult = true;
        try {
            By courseLocator;
            switch (courseTitle) {
                case "Java Step By Step For Testers":
                    courseLocator = Locators.javaTitle;
                    break;
                case "Selenium WebDriver Automation":
                    courseLocator = Locators.seleniumTitle;
                    break;
                case "API Testing Automation":
                    courseLocator = Locators.apiTitle;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid course title: " + courseTitle);
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(courseLocator));
            Reports.generateReport(driver, test, Status.PASS, "Course found: " + courseTitle);
        } catch (TimeoutException e) {
            actResult = false;
            Reports.generateReport(driver, test, Status.FAIL, "Course not found: " + courseTitle);
        }
        return actResult;
    }
    
    public String getCategoryHeading() {
        try {
            WebElement headingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.categoryHeading));
            String headingText = headingElement.getText().trim();
            Reports.generateReport(driver, test, Status.PASS, "Category heading found: " + headingText);
            return headingText;
        } catch (TimeoutException e) {
            Reports.generateReport(driver, test, Status.FAIL, "Category heading not found!");
            return "";
        }
    }

    // New method for handling "All Courses"
    public String getAllCoursesHeading() {
        try {
            WebElement headingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.allCoursesHeading));
            String headingText = headingElement.getText().trim();
            Reports.generateReport(driver, test, Status.PASS, "All Courses heading found: " + headingText);
            return headingText;
        } catch (TimeoutException e) {
            Reports.generateReport(driver, test, Status.FAIL, "All Courses heading not found!");
            return "";
        }
    }

}
