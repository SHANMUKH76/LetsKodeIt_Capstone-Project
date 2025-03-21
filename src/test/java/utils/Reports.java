package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Reports {

    public static void generateReport(WebDriver driver, ExtentTest test, Status status, String stepMessage) {
        if (status.equals(Status.PASS)) {
            test.log(status, stepMessage);
        } else if (status.equals(Status.FAIL)) {
            String screenshotPath = captureScreenshot(driver, stepMessage);
            test.log(status, stepMessage, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else {
            test.log(status, stepMessage); // For INFO, WARNING, SKIP
        }
    }

    public static String captureScreenshot(WebDriver driver, String errorMessage) {
        // Get user directory dynamically
        String userDir = System.getProperty("user.dir");

        // Ensure screenshot folder exists
        String screenshotFolder = userDir + File.separator + "screenshots";
        File directory = new File(screenshotFolder);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Sanitize filename (remove special characters)
        String sanitizedMessage = errorMessage.replaceAll("[^a-zA-Z0-9]", "_");

        // Format timestamp
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTime = df.format(new Date());
        
        // Construct file path
        String fileName = screenshotFolder + File.separator + sanitizedMessage + "_" + dateTime + ".png";

        // Capture screenshot
        TakesScreenshot scrShot = (TakesScreenshot) driver;
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(fileName);

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }

        return fileName;
    }
}
