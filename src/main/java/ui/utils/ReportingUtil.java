package ui.utils;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

public class ReportingUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingUtil.class);

    private ReportingUtil() {
    }

    public static void saveScreenshotIntoFile(String description) {
        String encodedScreenshot = Selenide.screenshot(OutputType.BASE64);
        LOG.info(MarkerFactory.getMarker("ATTACHMENT_CONTENT"), "RP_MESSAGE#BASE64#{}#{}", encodedScreenshot, description);
    }
}
