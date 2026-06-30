package org.furniture.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;


import utils.LoggerManager;

import java.time.Duration;

public class BookshelvesPage {

    WebDriver driver;
    WebDriverWait wait;

    public BookshelvesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ✅ ✅ CORRECT SEARCH BOX
    @FindBy(id = "searchInput")
    WebElement searchBox;

    // ✅ Result title
    @FindBy(xpath = "//h1[contains(text(),'Bookshelves')]")
    WebElement title;

    // ✅ All Filters button
    @FindBy(className = "qJoGr")
    WebElement allFiltersBtn;

    // ✅ Price section
    @FindBy(xpath = "//div[@aria-label='Price']")
    WebElement priceSection;

    // ✅ Max price input (IMPORTANT)
    @FindBy(xpath = "//input[@type='text' and contains(@aria-label,'Maximum')]")
    WebElement maxPriceInput;

    // ✅ Apply filter button
    @FindBy(xpath = "//button[.//text()='Apply' or contains(.,'Apply')]")
    WebElement applyFilterBtn;

    /*@FindBy(xpath = "//div[@role='checkbox']//div[text()='Open Storage']")
    WebElement openStorageOption;

    @FindBy(xpath = "//div[@role='tab' and @aria-label='Storage Type']")
    WebElement storageTypeSection;*/
    // ✅ Bookshelf names
    @FindBy(xpath = "//h2[contains(text(),'Bookshelf')]")
    java.util.List<WebElement> bookshelfNames;

    // ✅ Bookshelf prices
    @FindBy(xpath ="//div[@role='link']//div[contains(text(),'₹')]")
    java.util.List<WebElement> bookshelfPrices;


    // ✅ Action
    public void searchBookshelves() {

        LoggerManager.info("Waiting for search box");

        wait.until(ExpectedConditions.visibilityOf(searchBox));

        LoggerManager.info("Clicking search box");
        searchBox.click();

        LoggerManager.info("Typing Bookshelves");
        searchBox.sendKeys("Bookshelves");

        LoggerManager.info("Pressing Enter");
        searchBox.sendKeys(Keys.ENTER);
    }

    public boolean isBookshelvesPageDisplayed() {

        wait.until(ExpectedConditions.visibilityOf(title));
        return title.isDisplayed();
    }

    public void applyPriceFilter() {

        LoggerManager.info("Clicking All Filters");

        wait.until(ExpectedConditions.elementToBeClickable(allFiltersBtn));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", allFiltersBtn);

        LoggerManager.info("All Filters opened");

        LoggerManager.info("Scrolling to Price");

        wait.until(ExpectedConditions.visibilityOf(priceSection));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", priceSection);

        // Ensure section is active
        priceSection.click();

        LoggerManager.info("Setting max price");

        wait.until(ExpectedConditions.elementToBeClickable(maxPriceInput));

        maxPriceInput.clear();
        maxPriceInput.sendKeys("15000");

        // ✅ IMPORTANT: trigger blur event
        maxPriceInput.sendKeys(Keys.TAB);

        LoggerManager.info("Waiting for UI to update after entering price");

        // small wait to allow UI to apply value internally
        wait.until(ExpectedConditions.attributeContains(maxPriceInput, "value", "15"));

        LoggerManager.info("Clicking Apply button");

        wait.until(ExpectedConditions.elementToBeClickable(applyFilterBtn));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", applyFilterBtn);

        LoggerManager.info("Price filter applied successfully");
    }

    /*public void selectOpenStorage() {

        LoggerManager.info("Scrolling inside filter panel to find Storage Type");

        // ✅ Scroll inside panel using JS (IMPORTANT FIX)
        //((JavascriptExecutor) driver).executeScript(
         //       "document.querySelector('div[style*=\"overflow\"]').scrollTop=500"
        //);

        LoggerManager.info("Locating Storage Type section");

        WebElement storageType = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[text()='Storage Type']")
                ));

        storageType.click();

        LoggerManager.info("Storage Type expanded");

        LoggerManager.info("Selecting Open Storage");

        WebElement openStorage = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='checkbox']//div[text()='Open Storage']")
                ));

        openStorage.click();

        LoggerManager.info("Open Storage selected successfully");
    }*/

    public void selectOpenStorage() {

        LoggerManager.info("Scrolling inside All Filters panel");

        // ✅ Optional scroll inside panel
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector(\"div[role='dialog']\").scrollTop=300"
        );

        // ✅ Step 1: Click Storage Type dropdown
        LoggerManager.info("Clicking Storage Type");

        WebElement storageType = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//span[text()='Storage Type'])[2]")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", storageType);

        LoggerManager.info("Storage Type expanded");

        // ✅ Step 2: Select Open Storage checkbox
        LoggerManager.info("Selecting Open Storage");

        WebElement openStorage = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(text(),'Open Storage')]")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", openStorage);

        LoggerManager.info("Open Storage selected successfully ✅");
    }


    public void applyFilters() {

        LoggerManager.info("Clicking Apply Filter button");

        WebElement applyBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@class='zTzmw undefined']")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", applyBtn);

        LoggerManager.info("Filters applied successfully ✅");

        // ✅ Wait for filter panel to disappear
        wait.until(ExpectedConditions.invisibilityOf(applyBtn));
    }

    public void openFilters() {

        LoggerManager.info("Clicking All Filters button");

        wait.until(ExpectedConditions.elementToBeClickable(allFiltersBtn));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", allFiltersBtn);

        LoggerManager.info("Filters panel opened successfully");
    }


    // ✅ Get top 3 bookshelf names
    public java.util.List<String> getTopThreeBookshelfNames() {

        wait.until(ExpectedConditions.visibilityOfAllElements(bookshelfNames));

        return bookshelfNames.stream()
                .limit(3)
                .map(WebElement::getText)
                .collect(java.util.stream.Collectors.toList());

    }

    public List<String> getTopThreeBookshelfPrices() {

        return bookshelfPrices.stream()
                .filter(WebElement::isDisplayed)   // ✅ KEY FIX
                .limit(3)
                .map(WebElement::getText)
                .collect(java.util.stream.Collectors.toList());
    }
}

