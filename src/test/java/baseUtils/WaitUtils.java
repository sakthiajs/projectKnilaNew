package baseUtils;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils {

    
    private static final int DEFAULT_TIMEOUT = 10;

    public static WebElement waitForElementVisible(WebDriver driver, WebElement element) {
        return waitForElementVisible(driver, element, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementVisible(WebDriver driver, WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return waitForElementVisible(driver, locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // === Wait for element to be clickable ===
    public static WebElement waitForElementClickable(WebDriver driver, WebElement element) {
        return waitForElementClickable(driver, element, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementClickable(WebDriver driver, WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // === Wait for URL to contain partial string ===
    public static boolean waitForUrlToContain(WebDriver driver, String partialUrl) {
        return waitForUrlToContain(driver, partialUrl, DEFAULT_TIMEOUT);
    }

    public static boolean waitForUrlToContain(WebDriver driver, String partialUrl, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    public static boolean waitForElementInvisible(WebDriver driver, By locator) {
        return waitForElementInvisible(driver, locator, DEFAULT_TIMEOUT);
    }

    public static boolean waitForElementInvisible(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static Alert waitForAlert(WebDriver driver) {
        return waitForAlert(driver, DEFAULT_TIMEOUT);
    }

    public static Alert waitForAlert(WebDriver driver, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
