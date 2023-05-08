package listener;

import com.codeborne.selenide.Selenide;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import ui.utils.ReportingUtil;

public class ExtendedRPListener extends ReportPortalTestNGListener {

    @Override
    public void onTestFailure(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            String webDriverLogs = String.join("\n", Selenide.getWebDriverLogs(LogType.BROWSER));
            ReportingUtil.saveScreenshotIntoFile(webDriverLogs);
        }
        super.onTestFailure(testResult);
    }
}
