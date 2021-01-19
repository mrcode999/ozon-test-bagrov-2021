package ru.ilya.ozontest.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CoffeeMachinePage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private final String MIN_PRICE_INPUT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/aside/div[3]/div[2]/div[2]/div[1]/input";
    private final String MAX_PRICE_INPUT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/aside/div[3]/div[2]/div[2]/div[2]/input";
    private final String COFFEE_MACHINE_LIST_CONTAINER = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div";
    private final String COFFEE_MACHINE_PRICE_ELEMENT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div/div/div/div/div[3]/a/div[2]/span[1]";

    private final String SORTING_SELECT_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/div/input";

    private final String ADD_FIRST_COFFEE_MACHINE_TO_CART_BUTTON = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/div/div/div[3]/div[2]/div/div[1]/div/button";

    private final String OPEN_CART_LINK_XPATH = "/html/body/div[1]/div/div[1]/header/div[1]/div[4]/a[2]";

    private final String ESPRESSO_MACHINE_TYPE_CHECKBOX_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/aside/div[5]/div[2]/div/span[2]/label/div[1]";
    private final String CUP_HEAT_CHECKBOX_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[1]/aside/div[8]/div[2]/div/span[2]/label/div[1]";

    private final String ADD_TO_FAVORITES_XPATH = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div[1]/div/div[1]/div/div/div[3]/div[1]/div/div/button";
    private final String FAVORITES_LINK_XPATH = "/html/body/div[1]/div/div[1]/header/div[1]/div[4]/a[1]";

    private final String FIRST_MACHINE_PRICE = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/div/div/div[3]/a/div[2]/span[1]";
    private final String FIRST_MACHINE_DISCOUNT = "/html/body/div[1]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/div/div/div[3]/a/div[3]/div[1]";

    private final Integer MIN_PRICE = 10000;
    private final Integer MAX_PRICE = 11000;

    public CoffeeMachinePage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
    }

    @Step("Ввод минимальной цены")
    public void inputMinPrice() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MIN_PRICE_INPUT_XPATH)));
        WebElement minPriceInput = webDriver.findElement(By.xpath(MIN_PRICE_INPUT_XPATH));
        minPriceInput.click();
        minPriceInput.sendKeys(Keys.CONTROL + "A");
        minPriceInput.sendKeys(MIN_PRICE.toString());
        minPriceInput.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    @Step("Ввод максимальной цены")
    public void inputMaxPrice() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAX_PRICE_INPUT_XPATH)));
        WebElement maxPriceInput = webDriver.findElement(By.xpath(MAX_PRICE_INPUT_XPATH));
        maxPriceInput.click();
        maxPriceInput.sendKeys(Keys.CONTROL + "A");
        maxPriceInput.sendKeys(MAX_PRICE.toString());
        maxPriceInput.sendKeys(Keys.ENTER);
    }

    private boolean priceInRange(int price) {
        return price >= MIN_PRICE && price <= MAX_PRICE;
    }

    @Step
    public void checkCoffeeMachineListPrice() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(COFFEE_MACHINE_LIST_CONTAINER)));
        List<WebElement> coffeeMachineList = webDriver.findElements(By.xpath(COFFEE_MACHINE_PRICE_ELEMENT_XPATH));

        for (WebElement coffeeMachine : coffeeMachineList) {
            System.out.println(coffeeMachine.getText());
            int price = Integer.parseInt(coffeeMachine.getText().replaceAll("[ ₽]", ""));

            Assert.assertTrue("Цена вне указанного диапазона. Значение: " + price , priceInRange(price));
        }
    }

    @Step
    public void sortCoffeeMachineListByPriceDesc() throws InterruptedException {
        WebElement sortingSelect = webDriver.findElement(By.xpath(SORTING_SELECT_XPATH));

        sortingSelect.click();

        sortingSelect.sendKeys(Keys.DOWN);
        sortingSelect.sendKeys(Keys.DOWN);

        sortingSelect.sendKeys(Keys.ENTER);

        Thread.sleep(1000);
    }

    @Step("Добавление самой дешевой кофемашины в корзину")
    public void addFirstCoffeeMachineToCart() {

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ADD_FIRST_COFFEE_MACHINE_TO_CART_BUTTON)));

        WebElement addToCartButton = webDriver.findElement(By.xpath(ADD_FIRST_COFFEE_MACHINE_TO_CART_BUTTON));

        addToCartButton.click();
    }

    @Step("Перейти в корзину")
    public void openCart() {
        WebElement openCartLink = webDriver.findElement(By.xpath(OPEN_CART_LINK_XPATH));

        openCartLink.click();
    }

    @Step("Выбрать тип кофеварки рожковая")
    public void checkEspressoMachineTypeCheckbox() throws InterruptedException {
        WebElement checkbox = webDriver.findElement(By.xpath(ESPRESSO_MACHINE_TYPE_CHECKBOX_XPATH));

        checkbox.click();

        Thread.sleep(2000);
    }

    @Step("Выбрать тип приготовления подогрев чашек")
    public void checkCupHeatCheckbox() throws InterruptedException {
        WebElement checkbox = webDriver.findElement(By.xpath(CUP_HEAT_CHECKBOX_XPATH));

        checkbox.click();

        Thread.sleep(2000);
    }

    @Step("Добавить в избранное")
    public void addToFavorites() throws InterruptedException {
        WebElement addToFavoritesButton = webDriver.findElement(By.xpath(ADD_TO_FAVORITES_XPATH));

        addToFavoritesButton.click();

        Thread.sleep(1000);
    }

    @Step("Перейти в Избранное")
    public void openFavoritesPage() throws InterruptedException {
        WebElement favoritesLink = webDriver.findElement(By.xpath(FAVORITES_LINK_XPATH));

        favoritesLink.click();

        Thread.sleep(1000);
    }

    public int getCoffeeMachinePrice() {
        WebElement price = webDriver.findElement(By.xpath(FIRST_MACHINE_PRICE));
        return Integer.parseInt(price.getText().replaceAll("[ ₽]", ""));
    }

    public int getCoffeeMachineDiscount() {
        WebElement discount = webDriver.findElement(By.xpath(FIRST_MACHINE_DISCOUNT));
        return Integer.parseInt(discount.getText().replaceAll("[ ₽]", ""));
    }
}

