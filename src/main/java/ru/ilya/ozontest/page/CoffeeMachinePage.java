package ru.ilya.ozontest.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CoffeeMachinePage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private final String MIN_PRICE_INPUT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/aside/div[3]/div[2]/div[2]/div[1]/input";
    private final String MAX_PRICE_INPUT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/aside/div[3]/div[2]/div[2]/div[2]/input";

    public CoffeeMachinePage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
    }

    @Step("Ввод минимальной цены")
    public void inputMinPrice() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MIN_PRICE_INPUT_XPATH)));
        WebElement minPriceInput = webDriver.findElement(By.xpath(MIN_PRICE_INPUT_XPATH));
        minPriceInput.click();
        minPriceInput.sendKeys(Keys.CONTROL + "A");
        minPriceInput.sendKeys("10000");
        minPriceInput.sendKeys(Keys.ENTER);
    }

    @Step
    public void inputMaxPrice() {
        WebElement maxPriceInput = webDriver.findElement(By.xpath(MAX_PRICE_INPUT_XPATH));
        maxPriceInput.click();
        maxPriceInput.sendKeys(Keys.CONTROL + "A");
        maxPriceInput.sendKeys("11000");
        maxPriceInput.sendKeys(Keys.ENTER);
    }
}
