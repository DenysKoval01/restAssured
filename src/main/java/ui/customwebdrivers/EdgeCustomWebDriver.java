package ui.customwebdrivers;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import javax.annotation.Nonnull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeCustomWebDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments(System.getProperty("driver.switches").split(","));
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(edgeOptions);
    }
}

