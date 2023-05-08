package ui;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public interface ActionsOnPage {
    default void clickOnElement(String xpath)
    {
        $(By.xpath(xpath)).click();
    }

    default String getTextOfElement(String xpath)
    {
        return $(By.xpath(xpath)).getText();
    }

    default void setTextIntoElement(String xpath,String valueToSet)
    {
        $(By.xpath(xpath)).setValue(valueToSet);
    }
}
