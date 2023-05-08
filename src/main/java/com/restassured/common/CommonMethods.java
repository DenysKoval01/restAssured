package com.restassured.common;


import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.google.gson.GsonBuilder;
import com.restassured.api.HttpMethod;
import com.restassured.api.RestClient;
import com.restassured.api.request.user.UserRequest;
import com.restassured.api.response.book.AssignedBooks;
import com.restassured.api.response.book.BookInformation;
import com.restassured.api.response.user.UserTokenResponse;
import com.restassured.common.info.Specifications;
import com.restassured.configuration.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class CommonMethods {
    public static Properties properties = ConfigurationReader.getPropertiesFromFile("base.properties");


    public static void authorizeUser() {
/*        UserRequest userRequest = new UserRequest(properties.getProperty("login"), properties.getProperty("password"));
        given().body(userRequest).when().contentType(ContentType.JSON)
                .post("Account/v1/Authorized").then()
                .log().all();*/
    }

    public static String getAuthorizationToken() {
       /* UserRequest userRequest = new UserRequest(properties.getProperty("login"), properties.getProperty("password"));
        return "Bearer " + given().body(userRequest).when().contentType(ContentType.JSON)
                .post("Account/v1/GenerateToken").then()
                .log().all().extract().as(UserTokenResponse.class).token;*/
        return null;
    }

    public void createUser() {
        Specifications.installSpecification(Specifications.responseSpec201());
        Map<String, String> testUser = Map.of("login", String.join(EMPTY, "Denys_Koval", String.format("%04d",
                        new Random().nextInt(1001))), "password",
                String.join(EMPTY, "String@", String.format("%04d", new Random().nextInt(1001))));

       /* UserRequest userRequest = new UserRequest(testUser.get("login"), testUser.get("password"));
        given().body(userRequest).when().contentType(ContentType.JSON)
                .post("Account/v1/User");*/
    }

    public List<BookInformation> getBooksAssignedToUser() {
        String stringAssignedBooks =
                given().header("Authorization", getAuthorizationToken()).when()
                        .get(format("Account/v1/User/%s", properties.getProperty("user.id"))).getBody().asString();

        AssignedBooks assignedBooks = new GsonBuilder().setPrettyPrinting().create().fromJson(stringAssignedBooks,
                AssignedBooks.class);

        Response response =
                RestClient.getInstance()
                        .sendRequest(HttpMethod.GET, format("Account/v1/User/%s", properties.getProperty("user.id")),
                                new AssignedBooks());
        AssignedBooks fastCheckerErrorResponse =
                new GsonBuilder().setPrettyPrinting().create().fromJson(response.getBody().asString(),
                        AssignedBooks.class);

        return given().header("Authorization",
                        getAuthorizationToken())
                .when()
                .get(format("Account/v1/User/%s", properties.getProperty("user.id")))
                .then().log().all().extract().
                body().jsonPath().getList("books", BookInformation.class);
    }
}
