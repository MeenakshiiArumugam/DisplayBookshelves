package giftcards;

import base.BaseTest;
import org.furniture.pages.GiftCardsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.LoggerManager;
import utils.ScreenshotUtils;

public class TC_25_FormSubmissionValidationTest extends BaseTest {

    @Test
    public void previewGiftCardVoucher() throws Exception {

        ExtentReportManager.createTest("TC_25 - Preview Gift Card Voucher");
        GiftCardsPage page = new GiftCardsPage(driver);
        page.clickGiftCards();
        page.switchToGiftCardWindow();
        page.selectAnniversaryCard();
        page.waitForGiftCardFormToLoad();
        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/testdata.xlsx";

        ExcelUtils.setExcelFile(filePath, "giftcardinput");
        int row = 1;
        String amount = ExcelUtils.getCellData(row, 0);
        String quantity = ExcelUtils.getCellData(row, 1);
        String senderFName = ExcelUtils.getCellData(row, 2);
        String senderLName = ExcelUtils.getCellData(row, 3);
        String senderEmail = ExcelUtils.getCellData(row, 4);
        String senderMobile = ExcelUtils.getCellData(row, 5);
        String receiverFName = ExcelUtils.getCellData(row, 6);
        String receiverLName = ExcelUtils.getCellData(row, 7);
        String receiverEmail = ExcelUtils.getCellData(row, 8);
        String message = ExcelUtils.getCellData(row, 9);

        page.fillGiftCardForm(
                amount, quantity,
                senderFName, senderLName,
                senderEmail, senderMobile,
                receiverFName, receiverLName,
                receiverEmail, message
        );
        page.clickPreviewButton();
        Thread.sleep(5000);
        System.out.println(driver.getPageSource().contains("previewModal"));
        WebElement voucher = page.getVoucherPopup();
        Assert.assertTrue(voucher.isDisplayed(),"Voucher preview is not displayed");
        ScreenshotUtils.captureElementScreenshot(voucher,"VoucherPreview");
        LoggerManager.info("Voucher screenshot captured successfully");
        ExtentReportManager.getTest().log(Status.PASS,"Voucher preview displayed and screenshot captured successfully");
    }
}