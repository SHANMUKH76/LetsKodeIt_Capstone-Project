package Runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = {"src\\test\\resources\\features\\homepage.feature",
	    		"src\\test\\resources\\features\\loginpage.feature",
	    		"src\\test\\resources\\features\\allcoursespage.feature",
	    		"src\\test\\resources\\features\\practicepage.feature",
	    		"src\\test\\resources\\features\\shoppingcart.feature"
	    		},// Path to feature files
	    glue = {"StepDefinition","Hooks"},   // Package locations for step definition and hooks
	    plugin = {
	        "pretty", 
	        "html:target/cucumber-reports.html", // Generates HTML report
	        "json:target/cucumber-reports.json", // Generates JSON report
	        "rerun:target/failed_scenarios.txt"  // Stores failed scenarios for re-run
	    	},
	    tags="@Shoppingcart"
	)
	
	public class LetsKodeItTestRunner extends AbstractTestNGCucumberTests {
	}
