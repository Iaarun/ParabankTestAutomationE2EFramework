package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10;

    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return waitForVisibility(driver, locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForVisibility(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
        return waitForVisibility(driver, element, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return waitForClickable(driver, locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForClickable(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForTitleContains(WebDriver driver, String text) {
        return waitForTitleContains(driver, text, DEFAULT_TIMEOUT);
    }

    public static boolean waitForTitleContains(WebDriver driver, String text, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.titleContains(text));
    }

    public static <T> T waitForCondition(WebDriver driver, ExpectedCondition<T> condition, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(condition);
    }
}

