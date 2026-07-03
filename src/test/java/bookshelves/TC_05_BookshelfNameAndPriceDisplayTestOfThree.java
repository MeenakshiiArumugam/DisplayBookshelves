package bookshelves;
import utils.ExcelUtils;
import org.testng.annotations.Test;
import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import com.aventstack.extentreports.Status;
import utils.LoggerManager;
import utils.ExtentReportManager;
import org.testng.Assert;

import java.util.List;

public class TC_05_BookshelfNameAndPriceDisplayTestOfThree extends BaseTest {


    @Test
    public void displayTopThreeBookshelves() {
        ExtentReportManager.createTest("TC_05 - Display Top 3 Bookshelves Name and Price");
        BookshelvesPage page = new BookshelvesPage(driver);
        LoggerManager.info("Starting TC_05");
        ExtentReportManager.getTest().log(Status.INFO, "Test Started");
        page.searchBookshelves();
        ExtentReportManager.getTest().log(Status.INFO, "Searched Bookshelves");
        page.openFilters();
        ExtentReportManager.getTest().log(Status.INFO, "Opened Filters");
        page.selectOpenStorage();
        ExtentReportManager.getTest().log(Status.INFO, "Selected Open Storage");
        page.applyFilters();
        ExtentReportManager.getTest().log(Status.INFO, "Applied Filters");
        //Fetching the data
        List<String> names = page.getTopThreeBookshelfNames();
        List<String> prices = page.getTopThreeBookshelfPrices();
        Assert.assertEquals(names.size(), 3);
        Assert.assertEquals(prices.size(), 3);
        ExcelUtils.writeBookshelfData(names, prices);
        for (int i = 0; i < 3; i++) {
            String result = "Bookshelf " + (i+1) + " → Name: " + names.get(i) + " | Price: " + prices.get(i);
            LoggerManager.info(result);
            ExtentReportManager.getTest().log(Status.PASS, result);
        }
        LoggerManager.info("TC_05 Completed Successfully");
    }
}


