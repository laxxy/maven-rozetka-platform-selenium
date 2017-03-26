package com.rozetka.platform.selenium.pages.main;

import com.rozetka.platform.selenium.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

public class MainPage extends BasePage {
    private WebDriverWait wait;
    private Actions actions;
    private final String baseUrl = System.getProperty("base.url");

    public MainPage() {
        actions = new Actions(getDriver());
        wait = new WebDriverWait(getDriver(), 5);
    }

    @FindBy(css = ".f-menu-l-i.hover .f-menu-cols")
    private WebElement menu;

    @Step
    public MainPage openMainPage() {
        getDriver().get(baseUrl);
        waitForPageLoad();
        return this;
    }

    @Step
    public boolean isMainPageOpened() {
        return getDriver().getCurrentUrl().equals(baseUrl);
    }

    @Step
    public MainPage hoverOnTelephoneMenu() {
        WebElement element = getDriver().findElement(By.cssSelector("li[class='f-menu-l-i']>a[href*='telefony']"));
        actions.moveToElement(element).build().perform();
        return this;
    }

    @Step
    public boolean isMenuOpened() {
        return isElementPresentedWithWait(menu);
    }

    @Step
    public MainPage selectCategoryByName(final String name) {
        menu.findElement(By.xpath(String.format(".//a[contains(text(),'%s')]", name))).click();
        return this;
    }
}
