package org.furniture.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import utils.LoggerManager;

import java.time.Duration;
import java.util.Set;

public class GiftCardsPage {

    WebDriver driver;
    WebDriverWait wait;

    public GiftCardsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    // Gift Cards link
    @FindBy(xpath = "//a[text()='Gift Cards']")
    WebElement giftCardsLink;

    // Anniversary Card
    By anniversaryCard = By.xpath("(//*[@id='design-theme']//img)[3]");

    // FORM LOCATORS
    By amountField = By.id("denomination");
    By quantityField = By.id("quantity");

    // Sender
    By senderFirstName = By.xpath("//div[@id='sender-details']//input[@id='firstname']");
    By senderLastName = By.xpath("//div[@id='sender-details']//input[@id='lastname']");
    By senderEmail = By.xpath("//div[@id='sender-details']//input[@id='email']");
    By senderMobile = By.xpath("//div[@id='sender-details']//input[@id='telephone']");

    // Receiver
    By receiverFirstName = By.xpath("//div[@id='receiver-details']//input[@id='firstname']");
    By receiverLastName = By.xpath("//div[@id='receiver-details']//input[@id='lastname']");
    By receiverEmail = By.xpath("//div[@id='receiver-details']//input[@id='email']");

    // Message
    By messageBox = By.id("giftMessage");

    // NAVIGATION
      public void clickGiftCards() {
        LoggerManager.info("Clicking Gift Cards link");
        wait.until(ExpectedConditions.elementToBeClickable(giftCardsLink));
        giftCardsLink.click();
    }

    public void switchToGiftCardWindow() {

        String parentWindow = driver.getWindowHandle();
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        wait.until(ExpectedConditions.urlContains("woohoo"));
        LoggerManager.info("Switched to Gift Card window");
    }

    public boolean isGiftCardPageOpened() {
        return driver.getCurrentUrl().contains("woohoo");
    }

    //SELECT ANNIVERSARY CARD
       public void selectAnniversaryCard() {
        WebElement card = wait.until(
                ExpectedConditions.visibilityOfElementLocated(anniversaryCard)
        );

        // Scroll to center
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", card
        );

        try {
            Thread.sleep(1000); // stabilize UI
        } catch (Exception ignored) {}

        // JS click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
        LoggerManager.info("Anniversary card selected");
    }

    public boolean isAnniversaryCardSelected() {
        try {
            WebElement card = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(anniversaryCard)
            );
            return card.getAttribute("class").contains("border-secondary");
        } catch (Exception e) {
            return false;
        }
    }

    // WAIT FOR FORM LOAD
        public void waitForGiftCardFormToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
        LoggerManager.info("Gift card form loaded");
    }

    //FILL FORM
    public void fillGiftCardForm(String amount, String quantity,
                                 String senderFName, String senderLName,
                                 String senderEmailVal, String senderMobileVal,
                                 String receiverFName, String receiverLName,
                                 String receiverEmailVal, String message) {

        LoggerManager.info("Filling Gift Card form");

        // Amount
        WebElement amt = wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
        amt.clear();
        amt.sendKeys(amount);

        // Quantity
        WebElement qty = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityField));
        qty.clear();
        qty.sendKeys(quantity);

        // Sender
        wait.until(ExpectedConditions.visibilityOfElementLocated(senderFirstName))
                .sendKeys(senderFName);
        driver.findElement(senderLastName).sendKeys(senderLName);
        driver.findElement(senderEmail).sendKeys(senderEmailVal);
        driver.findElement(senderMobile).sendKeys(senderMobileVal);

        // Receiver
        wait.until(ExpectedConditions.visibilityOfElementLocated(receiverFirstName))
                .sendKeys(receiverFName);
        driver.findElement(receiverLastName).sendKeys(receiverLName);
        driver.findElement(receiverEmail).sendKeys(receiverEmailVal);

        // Message
        driver.findElement(messageBox).sendKeys(message);
        LoggerManager.info("Gift Card form filled successfully");
    }
}
