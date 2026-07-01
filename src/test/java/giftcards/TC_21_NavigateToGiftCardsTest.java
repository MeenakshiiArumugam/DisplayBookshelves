package giftcards;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import com.aventstack.extentreports.Status;
import utils.LoggerManager;
import utils.ExtentReportManager;

public class TC_21_NavigateToGiftCardsTest extends BaseTest {

    @Test
    public void navigateToGiftCards() {

        ExtentReportManager.createTest("TC_21 - Navigate to Gift Cards");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        LoggerManager.info("Starting TC_21 - Navigate to Gift Cards");
        ExtentReportManager.getTest().log(Status.INFO, "Test started");

        // Click Gift Cards
        driver.findElement(By.xpath("//a[text()='Gift Cards']")).click();
        LoggerManager.info("Clicked Gift Cards link");
        ExtentReportManager.getTest().log(Status.INFO, "Clicked Gift Cards");

        String parentWindow = driver.getWindowHandle();

        // Wait for new tab
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Switch to new tab
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        LoggerManager.info("Switched to Gift Card window");
        ExtentReportManager.getTest().log(Status.INFO, "Switched to Gift Card window");

        // Wait for URL
        wait.until(ExpectedConditions.urlContains("woohoo"));

        String currentUrl = driver.getCurrentUrl();
        String title = driver.getTitle();

        LoggerManager.info("Gift Card URL: " + currentUrl);
        LoggerManager.info("Gift Card Title: " + title);

        // Assertion
        Assert.assertTrue(
                currentUrl.contains("woohoo"),
                "Gift card page not opened"
        );

        ExtentReportManager.getTest().log(Status.PASS, "Navigated to Gift Card page successfully");

        System.out.println("Gift Card Page Title: " + title);
        System.out.println("Current URL: " + currentUrl);
    }
}
