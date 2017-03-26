package com.rozetka.platform.selenium.pages.sections;

import com.rozetka.platform.selenium.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

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
        /*WebElement element =
                displaySize.findElement(
                        By.xpath(String.format("../following-sibling::div//i[contains(text(),'%s'))]", value)));*/
        List<WebElement> elements = displaySize.findElements(By.xpath("../following-sibling::div//i[@class='filter-parametrs-i-l-i-default-title']"));
        WebElement webElement = elements.stream().filter(o -> o.getText().contains(value)).findAny().get();
        webElement.findElement(By.xpath("../../..")).click();
        return this;
    }

    @Step
    public CategoryPage setCostUpperLimit(final String value) {
        WebElement element = getDriver().findElement(By.cssSelector("input[id='price[max]']"));
        typeText(element, value);
        getDriver().findElement(By.cssSelector("#submitprice")).click();
        return this;
    }

    @Step
    public boolean isCheckboxCheckedByValue(final String value) {
        List<WebElement> elements = getDriver().findElements(
                By.xpath("//span[@data-detail-title='Диагональ экрана']/../following-sibling::div//i[@class='filter-parametrs-i-l-i-default-title']"));
        WebElement webElement = elements.stream().filter(o -> o.getText().contains(value)).findAny().get();
        return  isCheckBoxChecked(webElement.findElement(By.xpath("../../../input")));
    }
}
