package ru.ilya.ozontest.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FavoritesPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private final String PRICE_ELEMENT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[3]/div[2]/div[2]/div[1]/div/div/div[1]/div/div[1]/a[1]/div[1]/span[1]";
    private final String DISCOUNT_ELEMENT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[3]/div[2]/div[2]/div[1]/div/div/div[1]/div/div[1]/a[1]/div[2]/div[1]";


    public FavoritesPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
    }

    @Step("Сравнить цену и скидку")
    public void comparePriceAndDiscount(int oldPrice, int oldDiscount) {
        WebElement priceElement = webDriver.findElement(By.xpath(PRICE_ELEMENT_XPATH));
        WebElement discountElement = webDriver.findElement(By.xpath(DISCOUNT_ELEMENT_XPATH));

        int price = Integer.parseInt(priceElement.getText().replaceAll("[ ₽]", ""));
        int discount = Integer.parseInt(discountElement.getText().replaceAll("[ ₽]", ""));

        Assert.assertEquals(price, oldPrice);
        Assert.assertEquals(discount, oldDiscount);

    }

}