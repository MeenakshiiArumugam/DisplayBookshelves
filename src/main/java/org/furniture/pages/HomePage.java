package org.furniture.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.LoggerManager;
import utils.ExtentReportManager;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//span[normalize-space()='New Arrivals']")
    WebElement newArrivalsMenu;

    @FindBy(id = "category-menu-0")
    WebElement newArrivalsDropdown;

    @FindBy(xpath = "//a[text()='Terra Collection']")
    WebElement terraCollection;

    @FindBy(xpath = "//a[text()='Terra Collection']//ancestor::li")
    WebElement terraSection;

    @FindBy(xpath = "//a[text()='Terra Collection']//ancestor::li//ul//a")
    java.util.List<WebElement> terraItems;


    // Hover action
    public void hoverOnNewArrivals() {

        LoggerManager.info("Waiting for New Arrivals menu");
        wait.until(ExpectedConditions.visibilityOf(newArrivalsMenu));

        LoggerManager.info("Hovering on New Arrivals menu");
        ExtentReportManager.getTest().info("Hovering on New Arrivals");

        Actions actions = new Actions(driver);
        actions.moveToElement(newArrivalsMenu)
                .pause(Duration.ofSeconds(2))
                .perform();

        LoggerManager.info("Hover action completed");
        ExtentReportManager.getTest().info("Hover completed successfully");
    }


    // Validate dropdown
    public boolean isNewArrivalsDropdownDisplayed() {

        LoggerManager.info("Validating New Arrivals dropdown visibility");
        ExtentReportManager.getTest().info("Checking dropdown visibility");

        wait.until(ExpectedConditions.visibilityOf(newArrivalsDropdown));
        LoggerManager.info("Dropdown container is visible");

        wait.until(ExpectedConditions.visibilityOf(terraCollection));
        LoggerManager.info("Terra Collection is visible inside dropdown");

        boolean result = newArrivalsDropdown.isDisplayed()
                && terraCollection.isDisplayed();

        LoggerManager.info("Final dropdown validation result: " + result);
        ExtentReportManager.getTest().info("Dropdown displayed: " + result);

        return result;
    }


    // Capture Terra submenu items
    public java.util.List<String> getTerraCollectionItems() {

        java.util.List<String> itemsText = new java.util.ArrayList<>();

        LoggerManager.info("Capturing Terra Collection submenu items");
        ExtentReportManager.getTest().info("Fetching Terra Collection items");

        wait.until(ExpectedConditions.visibilityOf(terraSection));

        for (WebElement item : terraItems) {
            String text = item.getText().trim();
            LoggerManager.info("Found item: " + text);
            itemsText.add(text);
        }

        return itemsText;
    }
}
