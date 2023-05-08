package com.restassured.regression.api;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.restassured.api.HttpMethod;
import com.restassured.api.RestClient;
import com.restassured.api.request.user.UserRequest;
import com.restassured.api.response.book.AssignedBooks;
import com.restassured.common.info.Specifications;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseApiTest {

    @Test(description = "Заповнені всі поля валідними даними")
    public void TC1() {
        commonMethods.createUser();
    }


    @Test(description = "Не заповнено жодне поле")
    public void TC2() {
        Specifications.installSpecification(Specifications.responseSpec400());
        UserRequest userRequest = new UserRequest(EMPTY, EMPTY);
        given().body(userRequest).when()
                .post("Account/v1/User").then().log().all();
    }
}
