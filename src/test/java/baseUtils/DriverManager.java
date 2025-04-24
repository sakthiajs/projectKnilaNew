package baseUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
        	ChromeOptions options = new ChromeOptions();

        	
        	Map<String, Object> prefs = new HashMap<>();
        	prefs.put("credentials_enable_service", false);
        	prefs.put("profile.password_manager_enabled", false);

        	
        	prefs.put("autofill.profile_enabled", false);
        	prefs.put("profile.autofill_address_enabled", false);
        	prefs.put("profile.default_content_setting_values.autofill_address", 2);

        	
        	options.setExperimentalOption("prefs", prefs);

        	
        	options.addArguments("--disable-save-password-bubble");
        	options.addArguments("--disable-autofill-keyboard-accessory-view");
        	options.addArguments("--disable-popup-blocking");
        	options.addArguments("--disable-notifications");
        	options.addArguments("--no-default-browser-check");
        	options.addArguments("--disable-translate");
        	options.addArguments("--disable-features=AutofillServerCommunication");
            //options.addArguments("--incognito");

        	
        	driver = new ChromeDriver(options);
        	driver.manage().window().maximize();
        	
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
