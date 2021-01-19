package ru.ilya.ozontest.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private final String COFFEE_MACHINE_COUNT_SELECT_XPATH = "/html/body/div[1]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[4]/div/div[1]/div/div[1]/div/div/input";
    private final String COFFEE_MACHINE_PRICE_XPATH = "/html/body/div[1]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div[1]/div/span";

    private Integer coffeeMachinePrice = 0;

    public CartPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
    }

    @Step("Увеличить количество кофемашин до 3")
    public void increaseCoffeeMachineCount() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(COFFEE_MACHINE_COUNT_SELECT_XPATH)));
        WebElement coffeeMachineCountSelect = webDriver.findElement(By.xpath(COFFEE_MACHINE_COUNT_SELECT_XPATH));
        WebElement coffeeMachinePriceElement = webDriver.findElement(By.xpath(COFFEE_MACHINE_PRICE_XPATH));

        coffeeMachinePrice = Integer.parseInt(coffeeMachinePriceElement.getText().replaceAll("[ ₽]", ""));

        coffeeMachineCountSelect.click();

        coffeeMachineCountSelect.sendKeys(Keys.DOWN);
        coffeeMachineCountSelect.sendKeys(Keys.DOWN);

        coffeeMachineCountSelect.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
    }

    @Step("Проверить, что цена увеличилась втрое")
    public void checkPriceIncrease() {
        WebElement coffeeMachinePriceElement = webDriver.findElement(By.xpath(COFFEE_MACHINE_PRICE_XPATH));

        int newPrice = Integer.parseInt(coffeeMachinePriceElement.getText().replaceAll("[ ₽]", ""));

        Assert.assertEquals(newPrice, coffeeMachinePrice * 3);
    }
}

