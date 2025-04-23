package baseUtils;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {


    private static final String SCREENSHOT_DIR = "screenshots/";

   
    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = SCREENSHOT_DIR + screenshotName + "_" + timestamp + ".png";

            File destFile = new File(filename);
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Screenshot saved: " + filename);
        } catch (IOException | WebDriverException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
