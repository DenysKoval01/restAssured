package ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.ActionsOnPage;
import ui.LoadablePage;
import ui.annotations.AsSelenideElement;

@Data
public class LoginPage implements LoadablePage, ActionsOnPage {
    private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);
    @AsSelenideElement
    public static final String LOGIN_MAIN_MENU = "//form[@id='userForm']";
    @AsSelenideElement
    private static final String HEADER_TEXT = "Login in Book Store";
    @AsSelenideElement
    public static final String USER_NAME = "//input[@id='userName']";
    @AsSelenideElement
    public static final String PASSWORD = "//input[@id='password']";

    @AsSelenideElement
    public static final String LOGIN = "//button[@id='login']";

    @Override
    public void waitUntilPageIsLoaded() {
        $(By.xpath(LOGIN_MAIN_MENU))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.textCaseSensitive(HEADER_TEXT));
        LOG.info("{} page is loaded", this.getClass());
    }
}
