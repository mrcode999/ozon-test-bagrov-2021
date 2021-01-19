package ru.ilya.ozontest;

import org.testng.annotations.Test;
import ru.ilya.ozontest.page.CoffeeMachinePage;
import ru.ilya.ozontest.page.FavoritesPage;
import ru.ilya.ozontest.page.MainPage;
import ru.ilya.ozontest.util.TestSupport;

public class CoffeeMachineThirdTest extends TestSupport {
    @Test
    public void coffeeMachineThirdTest() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver, webDriverWait);
        mainPage.clickCatalogButton();
        mainPage.moveCursorToAppliancesCatalogItem();
        mainPage.clickCoffeeMachineLink();
        CoffeeMachinePage coffeeMachinePage = new CoffeeMachinePage(webDriver, webDriverWait);

        coffeeMachinePage.inputMinPrice();
        coffeeMachinePage.inputMaxPrice();

        coffeeMachinePage.checkCoffeeMachineListPrice();

        coffeeMachinePage.sortCoffeeMachineListByPriceDesc();

        int price = coffeeMachinePage.getCoffeeMachinePrice();
        int discount = coffeeMachinePage.getCoffeeMachineDiscount();

        coffeeMachinePage.addToFavorites();

        coffeeMachinePage.openFavoritesPage();

        FavoritesPage favoritesPage = new FavoritesPage(webDriver, webDriverWait);

        favoritesPage.comparePriceAndDiscount(price, discount);
    }
}