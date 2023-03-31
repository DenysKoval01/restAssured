package com.restassured.regression;

import static com.restassured.common.CommonMethods.authorizeUser;
import static com.restassured.common.CommonMethods.getAuthorizationToken;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.restassured.common.CommonMethods;
import com.restassured.configuration.ConfigurationReader;
import com.restassured.info.Specifications;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({ReportPortalTestNGListener.class})
public class BaseTest {
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);
    protected Properties properties = ConfigurationReader.getPropertiesFromFile("base.properties");
    public static String token;
    protected CommonMethods commonMethods = new CommonMethods();

    @BeforeClass
    public void beforeWholeTest() {
        LOG.info("{} properties were initialized", properties.size());
        Specifications.installSpecification(Specifications.requestSpec(properties.getProperty("base.url")),
                Specifications.responseSpec200());
        authorizeUser();
        token = getAuthorizationToken();
    }
}
