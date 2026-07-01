package bookshelves;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import com.aventstack.extentreports.Status;
import utils.LoggerManager;
import utils.ExtentReportManager;

// ✅ ADD THESE IMPORTS
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TC_06_NavigateToFirstProductTest extends BaseTest {

    @Test
    public void navigateToFirstProduct() {

        ExtentReportManager.createTest("TC_06 - Navigate to First Product");

        BookshelvesPage page = new BookshelvesPage(driver);

        LoggerManager.info("Starting TC_06");
        ExtentReportManager.getTest().log(Status.INFO, "Test Started");

        // ✅ Search and apply filters
        page.searchBookshelves();
        page.openFilters();
        page.selectOpenStorage();
        page.applyFilters();

        // ✅ Click first product
        page.clickFirstProduct();
        ExtentReportManager.getTest().log(Status.INFO, "Clicked on first product");

        // ✅ Switch to new tab (IMPORTANT)
        page.switchToProductTab();
        page.waitForProductPage();
        /* ✅ ✅ ADD THIS WAIT (ONLY CHANGE)
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(d -> d.getCurrentUrl().contains("bookshelf")); */

        // ✅ Validate navigation
        String currentUrl = driver.getCurrentUrl();
        LoggerManager.info("Navigated URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("bookshelf"),
                "Navigation to product page failed");

        ExtentReportManager.getTest().log(Status.PASS,
                "Successfully navigated to product page");

        LoggerManager.info("TC_06 Completed ✅");
    }
}