package bookshelves;

import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_02_FilterByPriceBelow15000Test extends BaseTest {

    @Test
    public void filterByPriceBelow15000() {
        LoggerManager.info("Starting TC_02 - Price Filter");
        ExtentReportManager.getTest().info("Searching Bookshelves");
        BookshelvesPage page = new BookshelvesPage(driver);
        page.searchBookshelves();
        ExtentReportManager.getTest().info("Bookshelves searched");
        page.applyPriceFilter();
        ExtentReportManager.getTest().pass("Price filter applied successfully (<=15000)");
    }
}