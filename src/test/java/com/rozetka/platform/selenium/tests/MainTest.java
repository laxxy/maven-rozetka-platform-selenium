package com.rozetka.platform.selenium.tests;

import com.rozetka.platform.selenium.annotation.Page;
import com.rozetka.platform.selenium.listeners.TestListener;
import com.rozetka.platform.selenium.pages.BaseTest;
import com.rozetka.platform.selenium.pages.main.MainPage;
import com.rozetka.platform.selenium.pages.sections.CategoryPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;

@Listeners(TestListener.class)
@Features("Main test")
public class MainTest extends BaseTest {

    @Page private static MainPage mainPage;
    @Page private static CategoryPage categoryPage;

    @Test
    @TestCaseId("1")
    @Description("")
    public void tc1Test() {
        String category = "Смартфоны";
        String size = "До 4";
        String limit = "6000";

        mainPage.openMainPage();

        boolean mainPageOpened = mainPage.isMainPageOpened();
        Assert.assertTrue(mainPageOpened, "Main page was not opened!");

        mainPage.hoverOnTelephoneMenu();
        boolean menuOpened = mainPage.isMenuOpened();
        Assert.assertTrue(menuOpened, "Category selection menu was not opened!");

        mainPage.selectCategoryByName(category);
        boolean categoryOpenedByName = categoryPage.isCategoryOpenedByName(category);
        Assert.assertTrue(categoryOpenedByName, "Selected category page was not presented!");

        categoryPage.setDisplaySizeByValue(size);
        boolean checkboxCheckedByValue = categoryPage.isCheckboxCheckedByValue(size);
        Assert.assertTrue(checkboxCheckedByValue, String.format("Display size: [%s] was not selected!", size));

        categoryPage.setCostUpperLimit(limit);
    }

}
