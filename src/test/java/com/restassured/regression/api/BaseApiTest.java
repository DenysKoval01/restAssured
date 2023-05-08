package com.restassured.regression.api;

import static com.restassured.common.CommonMethods.authorizeUser;
import static com.restassured.common.CommonMethods.getAuthorizationToken;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.restassured.common.CommonMethods;
import com.restassured.common.info.Specifications;
import com.restassured.configuration.ConfigurationReader;
import java.util.Properties;
import listener.ExtendedRPListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ReportPortalTestNGListener.class})
public class BaseApiTest  {
    private static final Logger LOG = LoggerFactory.getLogger(BaseApiTest.class);
    protected Properties properties = ConfigurationReader.getPropertiesFromFile("base.properties");
    public static String token;
    protected CommonMethods commonMethods = new CommonMethods();


    @BeforeMethod
    public void beforeWholeTest() {
        LOG.info("{} properties were initialized", properties.size());
        //Specifications.installSpecification(Specifications.responseSpec200());
        authorizeUser();
        token = getAuthorizationToken();
    }
}
