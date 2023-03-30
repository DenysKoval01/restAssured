package com.restassured.common;


import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.restassured.api.request.user.UserRequest;
import com.restassured.api.response.book.BookInformation;
import com.restassured.api.response.user.UserTokenResponse;
import com.restassured.configuration.ConfigurationReader;
import com.restassured.info.Specifications;
import com.restassured.regression.BaseTest;
import io.restassured.http.ContentType;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class CommonMethods {
    private static Properties properties = ConfigurationReader.getPropertiesFromFile("base.properties");

    public static void authorizeUser() {
        UserRequest userRequest = new UserRequest(properties.getProperty("login"), properties.getProperty("password"));
        given().body(userRequest).when().contentType(ContentType.JSON)
                .post(properties.getProperty("base.url")  + "Account/v1/Authorized").then()
                .log().all();
    }

    public static String getAuthorizationToken() {
        UserRequest userRequest = new UserRequest(properties.getProperty("login"), properties.getProperty("password"));
        return "Bearer " + given().body(userRequest).when().contentType(ContentType.JSON)
                .post(properties.getProperty("base.url") + "Account/v1/GenerateToken").then()
                .log().all().extract().as(UserTokenResponse.class).token;
    }

    public void createUser() {
        Specifications.installSpecification(Specifications.requestSpec(properties.getProperty("base.url")),
                Specifications.responseSpec201());
        Map<String, String> testUser = Map.of("login", String.join(EMPTY,"Denys_Koval", String.format("%04d",
                        new Random().nextInt(1001))), "password",
                String.join(EMPTY,"String@", String.format("%04d", new Random().nextInt(1001))));

        UserRequest userRequest = new UserRequest(testUser.get("login"), testUser.get("password"));
        given().body(userRequest).when().contentType(ContentType.JSON)
                .post(properties.getProperty("base.url")  + "Account/v1/User");
    }

    public List<BookInformation> getBooksAssignedToUser()
    {
        return given().header("Authorization",
                        BaseTest.token)
                .when()
                .get(format("Account/v1/User/%s", properties.getProperty("user.id")))
                .then().log().all().extract().body().jsonPath().getList("books", BookInformation.class);
    }
}
