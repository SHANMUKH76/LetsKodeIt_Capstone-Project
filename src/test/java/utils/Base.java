package utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {

	public static WebDriver driver=null;
	
	 public static WebDriver getBrowser() 
	  {
		 Properties prop = PropertyReader.readProperty();
		  switch(prop.getProperty("Browser"))
		  {
		  case "chrome":
			  driver =new ChromeDriver();
			  break;
		  case "firefox":
			   driver =new FirefoxDriver();
			  break;
		  case "safari":
			    driver =new SafariDriver();
			  break;
			  
			  default:
			  System.out.println("Browser details are not found in src/test/resourses/config.properties");
		  }
		  driver.manage().window().maximize();
		  //driver.get(prop.getProperty("URL"));
		return driver;
	  }
}