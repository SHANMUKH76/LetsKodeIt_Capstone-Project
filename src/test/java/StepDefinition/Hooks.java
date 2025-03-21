package StepDefinition;



import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import utils.Base;



public class Hooks extends Base{
	public static WebDriver driver;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeAll
	public static void beforeAll() {
	    try {
	        // Initialize Extent Reports
	        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myReport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);

//	        // Initialize test here to avoid NullPointerException
//	        test = extent.createTest("Test Execution");

	        // Launch Browser Once for All Scenarios
	        driver = Base.getBrowser();
	        Base.driver = driver;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	 @AfterAll
	    public static void afterAll() {
	        if (driver != null) {
	            driver.quit();  // Close the browser after all scenarios
	        }
	        extent.flush(); // Writes the test info to the report
	    }
	 
	 @Before()
	 // Initialize test here to avoid NullPointerException
	 public void before() {
	 test = extent.createTest("Test Execution");
	 }
	 
	 @Before("@AllCourses")
	 public void beforeallcourses(){
		 
		 Base.driver.get("https://www.letskodeit.com/courses");
		 
	 }
	

}


