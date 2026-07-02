package giftcards;
import base.BaseTest;
import org.furniture.pages.GiftCardsPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_24_CaptureInvalidEmailValidationTest extends BaseTest {

    @Test
    public void validateInvalidSenderEmail() throws Exception {
        ExtentReportManager.createTest(
                "TC_24 - Validate Invalid Sender Email"
        );
        LoggerManager.info("Starting TC_24");
        try {
            GiftCardsPage page = new GiftCardsPage(driver);
            // Open Gift Card page
            page.clickGiftCards();
            page.switchToGiftCardWindow();
            page.selectAnniversaryCard();
            // Wait for form
            page.waitForGiftCardFormToLoad();
            // Read Excel data
            String filePath = System.getProperty("user.dir")
                    + "/src/test/resources/testdata/testdata.xlsx";
            ExcelUtils.setExcelFile(
                    filePath,
                    "giftcardinput"
            );
            int row = 2;
            String amount = ExcelUtils.getCellData(row, 0);
            String quantity = ExcelUtils.getCellData(row, 1);
            String senderFName = ExcelUtils.getCellData(row, 2);
            String senderLName = ExcelUtils.getCellData(row, 3);
            String senderEmail = ExcelUtils.getCellData(row, 4); // invalid email
            String senderMobile = ExcelUtils.getCellData(row, 5);
            String receiverFName = ExcelUtils.getCellData(row, 6);
            String receiverLName = ExcelUtils.getCellData(row, 7);
            String receiverEmail = ExcelUtils.getCellData(row, 8);
            String message = ExcelUtils.getCellData(row, 9);
            LoggerManager.info("Excel data fetched successfully");

            // Fill form
            page.fillGiftCardForm(
                    amount,
                    quantity,
                    senderFName,
                    senderLName,
                    senderEmail,
                    senderMobile,
                    receiverFName,
                    receiverLName,
                    receiverEmail,
                    message
            );

            // Trigger validation
            page.triggerEmailValidation();
            // Capture validation message
            String actualError =
                    page.getSenderEmailErrorMessage();
            LoggerManager.info(
                    "Actual Validation Message : " + actualError
            );
            // Validate
            Assert.assertEquals(
                    actualError,
                    "Enter valid Email ID.",
                    "Validation message mismatch"
            );
            LoggerManager.info(
                    "Invalid Email Validation Verified Successfully"
            );
            // Trigger validation
            WebElement firstName =
                    driver.findElement(page.senderFirstName);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    firstName
            );
            Thread.sleep(2000);
            // Validation
            Assert.assertEquals(
                    actualError,
                    "Enter valid Email ID.",
                    "Validation message mismatch"
            );
            LoggerManager.info(
                    "Invalid Email Validation Verified Successfully"
            );

            ExtentReportManager.getTest().log(
                    Status.PASS,
                    "Validation message verified successfully : "
                            + actualError
            );
        } catch (Exception e) {
            LoggerManager.error(
                    "Test failed: " + e.getMessage()
            );

            ExtentReportManager.getTest().log(
                    Status.FAIL,
                    e.getMessage()
            );
            throw e;
        }
    }
}