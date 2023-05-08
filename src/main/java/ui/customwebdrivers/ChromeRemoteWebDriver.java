package ui.customwebdrivers;

import com.codeborne.selenide.WebDriverProvider;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeRemoteWebDriver implements WebDriverProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ChromeRemoteWebDriver.class);

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(System.getProperty("remote.browser.name", "chrome"));
        desiredCapabilities.setVersion(System.getProperty("remote.browser.version", "100.0"));
        desiredCapabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));
        desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        desiredCapabilities.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--ignore-ssl-errors=yes", "--ignore-certificate-errors");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        String hubUrl = System.getProperty("remote.hub.url", "http://localhost:4444/wd/hub");
        WebDriver webDriver = null;
        try {
            webDriver = new RemoteWebDriver(URI.create(hubUrl).toURL(), desiredCapabilities);
        } catch (MalformedURLException e) {
            LOG.error("Incorrect Selenium Grid/Selenoid hub URL", e);
        }
        return Objects.requireNonNull(webDriver);
    }
}
