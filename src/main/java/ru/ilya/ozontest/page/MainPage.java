package ru.ilya.ozontest.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainPage {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private final String CATALOG_BUTTON_XPATH = "/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div[1]/button";
    private final String APPLIANCES_CATALOG_ITEM_XPATH = "/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div[2]/div/div[1]/div/a[6]";
    private  final String COFFEE_MACHINE_LINK_XPATH = "/html/body/div[1]/div/div[1]/header/div[1]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div[2]/div/a[1]";
    public MainPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
    }

    @Step("Нажать кнопку каталог")
    public void clickCatalogButton() {
        webDriver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        WebElement catalogButton = webDriver.findElement(By.xpath(CATALOG_BUTTON_XPATH));
        catalogButton.click();
    }

    @Step("Перемещение курсора к ссылке бытовая техника")
    public void moveCursorToAppliancesCatalogItem() {
        WebElement appliancesLink = webDriver.findElement(By.xpath(APPLIANCES_CATALOG_ITEM_XPATH));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(appliancesLink).build().perform();
    }
    @Step("Клик по ссылке кофеварки и кофемашины")
    public void clickCoffeeMachineLink (){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(COFFEE_MACHINE_LINK_XPATH)));
        WebElement coffeeMachineLink = webDriver.findElement(By.xpath(COFFEE_MACHINE_LINK_XPATH));
        coffeeMachineLink.click();
    }

}
