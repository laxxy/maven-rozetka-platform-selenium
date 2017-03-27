package com.rozetka.platform.selenium.pages.sections;

import com.rozetka.platform.selenium.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

public class CategoryPage extends BasePage {
    private WebDriverWait wait;
    private Actions actions;

    public CategoryPage() {
        actions = new Actions(getDriver());
        wait = new WebDriverWait(getDriver(), 5);
    }

    @FindBy(css = ".filter-active-i")
    private List<WebElement> filters;

    @FindBy(xpath = "//span[@data-detail-title='Диагональ экрана']")
    private WebElement displaySize;

    @Step
    public boolean isCategoryOpenedByName(final String categoryName) {
        return filters.stream().anyMatch(o -> o.getText().contains(categoryName));
    }

    @Step
    public CategoryPage setDisplaySizeByValue(final String value) {
        List<WebElement> elements = displaySize.findElements(By.xpath("../following-sibling::div//i[@class='filter-parametrs-i-l-i-default-title']"));
        WebElement webElement = elements.stream().filter(o -> o.getText().contains(value)).findAny().get();
        webElement.findElement(By.xpath("../../..")).click();
        waitForPageLoad();
        return this;
    }

    @Step
    public CategoryPage setCostUpperLimit(final String value) {
        WebElement element = getDriver().findElement(By.cssSelector("input[id='price[max]']"));
        scrollToElement(element, false);
        typeText(element, value);
        return this;
    }

    @Step
    public boolean isCheckboxCheckedByValue(final String value) {
        List<WebElement> elements = getDriver().findElements(
                By.xpath("//span[@data-detail-title='Диагональ экрана']/../following-sibling::div//i[@class='filter-parametrs-i-l-i-default-title']"));
        WebElement webElement = elements.stream().filter(o -> o.getText().contains(value)).findAny().get();
        return  isCheckBoxChecked(webElement.findElement(By.xpath("../../../input")));
    }

    @Step
    public CategoryPage selectRandomManufacturers(int count) {
        String options = "#sort_producer li[class='pos-fix']";
        IntStream.range(0, count).forEach(o -> {
            openDropDown(By.cssSelector("#sort_producer"));
            List<WebElement> elements = getDriver().findElements(By.cssSelector(options));
            elements.get(o).click();
            waitForPageLoad();
        });
        return this;
    }

    private void openDropDown(By by) {
        waitForAnimation();
        WebElement element = getDriver().findElement(by).findElement(By.xpath("./following-sibling::a"));
        actions.moveToElement(element).click().build().perform();
    }
}
