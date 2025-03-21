package Runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = {"src\\test\\resources\\features\\allcoursespage.feature"},// Path to feature files
	    glue = {"StepDefinition","Hooks"},   // Package locations for step definition and hooks
	    plugin = {
	        "pretty", 
	        "html:target/cucumber-reports.html", // Generates HTML report
	        "json:target/cucumber-reports.json", // Generates JSON report
	        "rerun:target/failed_scenarios.txt"  // Stores failed scenarios for re-run
	    	}
	)
	
	public class AllCoursesRunner extends AbstractTestNGCucumberTests {
	}
