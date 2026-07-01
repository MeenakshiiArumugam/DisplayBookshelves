package giftcards;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import com.aventstack.extentreports.Status;
import utils.LoggerManager;
import utils.ExtentReportManager;

public class TC_22_SelectAnniversaryGiftCardTest extends BaseTest {

    @Test
    public void selectAnniversaryCard() {

        ExtentReportManager.createTest("TC_22 - Select Anniversary Gift Card");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        LoggerManager.info("Starting TC_22 - Select Anniversary Gift Card");
        ExtentReportManager.getTest().log(Status.INFO, "Test started");

        // Click Gift Cards
        driver.findElement(By.xpath("//a[text()='Gift Cards']")).click();
        LoggerManager.info("Clicked Gift Cards");
        ExtentReportManager.getTest().log(Status.INFO, "Clicked Gift Cards");

        String parentWindow = driver.getWindowHandle();

        // Switch to new tab
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        wait.until(ExpectedConditions.urlContains("woohoo"));
        LoggerManager.info("Switched to Gift Card page");
        ExtentReportManager.getTest().log(Status.INFO, "Switched to Gift Card page");

        // Locate Anniversary card
        By anniversaryCard = By.xpath("(//*[@id='design-theme']//img)[3]");

        // Wait until element is visible
        WebElement card = wait.until(
                ExpectedConditions.visibilityOfElementLocated(anniversaryCard)
        );

        // Scroll to element smoothly
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                card
        );
        LoggerManager.info("Scrolled to Anniversary card");
        ExtentReportManager.getTest().log(Status.INFO, "Scrolled to Anniversary card");
        // Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(card));
        // Extra wait for UI stability
        wait.until(driver -> card.isDisplayed() && card.isEnabled());
        LoggerManager.info("Clicking Anniversary card");
        ExtentReportManager.getTest().log(Status.INFO, "Clicking Anniversary card");
        // Click using JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
        // Validate selection
        wait.until(ExpectedConditions.attributeContains(card, "class", "border-secondary"));
        Assert.assertTrue(
                card.getAttribute("class").contains("border-secondary"),
                "Failed to click Happy Anniversary card"
        );
        LoggerManager.info("Anniversary card selected successfully");
        ExtentReportManager.getTest().log(Status.PASS, "Clicked on Happy Anniversary card");
        //System.out.println("Clicked on Happy Anniversary card");
    }
}
