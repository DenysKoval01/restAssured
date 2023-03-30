package com.restassured.regression;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.restassured.api.request.user.UserRequest;
import com.restassured.info.Specifications;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {

    @Test(description = "Заповнені всі поля валідними даними")
    public void TC1() {
        commonMethods.createUser();
    }


    @Test(description = "Не заповнено жодне поле")
    public void TC2() {
        Specifications.installSpecification(Specifications.requestSpec(properties.getProperty("base.url")),
                Specifications.responseSpec400());
        UserRequest userRequest = new UserRequest(EMPTY, EMPTY);
        given().body(userRequest).when().contentType(ContentType.JSON)
                .post(properties.getProperty("base.url") + "Account/v1/User").then().log().all();
    }
}
