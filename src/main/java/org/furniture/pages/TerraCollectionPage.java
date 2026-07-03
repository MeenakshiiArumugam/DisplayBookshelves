package org.furniture.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentReportManager;
import utils.LoggerManager;
import java.time.Duration;

public class TerraCollectionPage {
    WebDriver driver;
    WebDriverWait wait;
    public TerraCollectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//a[contains(@href,'/collection/terra-collection')]")
    WebElement discoverAllTerraProducts;

    public void scrollToDiscoverAllTerraProducts() {
        LoggerManager.info("Scrolling to Discover all Terra products link");
        ExtentReportManager.getTest().info("Scrolling to Discover all Terra products link");
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});",
                        discoverAllTerraProducts);
    }

    public void clickDiscoverAllTerraProducts() {
        LoggerManager.info("Clicking Discover all Terra products");
        ExtentReportManager.getTest().info("Clicking Discover all Terra products");
        wait.until(ExpectedConditions.elementToBeClickable(discoverAllTerraProducts));
        discoverAllTerraProducts.click();
    }

    public void switchToNewTab() {
        String currentWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public boolean isTerraProductsPageDisplayed() {
        LoggerManager.info("Validating Terra Collection products page");
        wait.until(ExpectedConditions.urlContains("terra-collection"));
        String currentUrl = driver.getCurrentUrl();
        LoggerManager.info("Current URL: " + currentUrl);
        return currentUrl.contains("/collection/terra-collection");
    }
}
