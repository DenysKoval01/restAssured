package ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.ActionsOnPage;
import ui.LoadablePage;
import ui.annotations.AsSelenideElement;

public class BookStorePage implements LoadablePage, ActionsOnPage {

    private static final Logger LOG = LoggerFactory.getLogger(BookStorePage.class);
    @AsSelenideElement
    public static final String LOG_OUT = "//button[@id='submit']";

    @AsSelenideElement
    public static final String USER_NAME = "//label[@id='userName-value']";

    @Override
    public void waitUntilPageIsLoaded() {
        $(By.xpath(LOG_OUT))
                .shouldBe(Condition.visible);
        LOG.info("{} page is loaded", this.getClass());
    }
}
