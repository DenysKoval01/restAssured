package com.restassured.regression;





import static io.restassured.RestAssured.given;

import com.restassured.api.request.user.UserRequest;
import org.testng.annotations.Test;

public class AuthorizedUserTest extends BaseTest{


    @Test
    public void authorizedUser() {
        UserRequest userRequest = new UserRequest("Denys_Koval","String@12345");
                given().body(userRequest).when()
                        .post("Account/v1/Authorized").then()
                        .log().all();
    }
}
