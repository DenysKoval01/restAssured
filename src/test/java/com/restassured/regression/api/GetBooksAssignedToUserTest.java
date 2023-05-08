package com.restassured.regression.api;


import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.restassured.api.response.book.BookInformation;
import com.restassured.api.response.user.UserNotFound;
import com.restassured.common.info.Specifications;
import io.restassured.response.Response;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class GetBooksAssignedToUserTest extends BaseApiTest {


    @Test(description = "Запит даних щодо валідного ID")
    public void TC1() {
        List<BookInformation> bookInformation = commonMethods.getBooksAssignedToUser();
        Assertions.assertThat(bookInformation.size()).as("Size is not expected").isEqualTo(0);

    }

    @Test(description = "Запит даних щодо не валідного ID")
    public void TC2() {
        Specifications.installSpecification(
                Specifications.responseSpec401());
        Response response = given().header("Authorization",
                        token)
                .when()
                .get(format("Account/v1/User/%s", String.join(EMPTY, properties.getProperty("user.id"), "TEST")));
        Assertions.assertThat(response.getBody().as(UserNotFound.class).message).as("Size is not expected")
                .contains("not found");
    }

}
