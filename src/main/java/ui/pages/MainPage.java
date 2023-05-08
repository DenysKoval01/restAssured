package ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.ActionsOnPage;
import ui.LoadablePage;
import ui.annotations.AsSelenideElement;

public class MainPage implements LoadablePage, ActionsOnPage {

    private static final Logger LOG = LoggerFactory.getLogger(MainPage.class);
    @AsSelenideElement
    public static final String BOOK_STORE_APPLICATION_MENU = "//div[@class='card mt-4 top-card'][last()]";
    @AsSelenideElement
    private static final String HEADER_TEXT = "Book Store Application";



    @Override
    public void waitUntilPageIsLoaded() {
        $(By.xpath(BOOK_STORE_APPLICATION_MENU))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.textCaseSensitive(HEADER_TEXT));
        LOG.info("{} page is loaded", this.getClass());
    }

    public static class MainLoginPage implements LoadablePage, ActionsOnPage
    {
        @AsSelenideElement
        public static final String LOGIN_BUTTON = "//button[@id='login']";
        @AsSelenideElement
        private static final String HEADER_TEXT = "Login";

        @Override
        public void waitUntilPageIsLoaded() {
            $(By.xpath(LOGIN_BUTTON))
                    .shouldBe(Condition.visible)
                    .shouldBe(Condition.textCaseSensitive(HEADER_TEXT));
            LOG.info("{} page is loaded", this.getClass());
        }
    }
}
