package ru.ilya.ozontest;

import org.testng.annotations.Test;
import ru.ilya.ozontest.page.CoffeeMachinePage;
import ru.ilya.ozontest.page.MainPage;
import ru.ilya.ozontest.util.TestSupport;

public class CoffeeMachineFirstTest extends TestSupport {

    @Test
    public void coffeeMachineFirstTest() {
        MainPage mainPage = new MainPage(webDriver, webDriverWait);
        mainPage.clickCatalogButton();
        mainPage.moveCursorToAppliancesCatalogItem();
        mainPage.clickCoffeeMachineLink();
        CoffeeMachinePage coffeeMachinePage = new CoffeeMachinePage(webDriver, webDriverWait);

        coffeeMachinePage.inputMinPrice();
        coffeeMachinePage.inputMaxPrice();
    }

}
