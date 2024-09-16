package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverUtils {

    private WebDriverWait wait;
    private WebDriver driver;

    // Constructor
    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    // Click an element
    public void clickElement(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        if (element != null) {
            element.click();
        } else {
            System.out.println("Element not clickable: " + locator);
        }
    }

    // Send keys to an element
    public void sendKeysToElement(By locator, String text) {
        WebElement element = waitForElement(locator);
        if (element != null) {
            element.sendKeys(text);
        } else {
            System.out.println("Element not found to send keys: " + locator);
        }
    }

    // Wait for an element to be present
    public WebElement waitForElement(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Element not found: " + locator);
            return null;
        }
    }

    // Wait for an element to be clickable
    private WebElement waitForElementToBeClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            System.out.println("Element not clickable: " + locator);
            return null;
        }
    }

    // Navigate to the next page
    public void navigateToNextPage(By nextPageLocator) {
        WebElement nextButton = waitForElementToBeClickable(nextPageLocator);
        if (nextButton != null) {
            nextButton.click();
            waitForPageLoad();
        } 
        else {
            System.out.println("Next page button not found or not clickable.");
        }
    }

    // Wait for the page to load completely
    private void waitForPageLoad() {
        wait.until(driver -> {
            String readyState = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
            return readyState.equals("complete");
        });
    }
}