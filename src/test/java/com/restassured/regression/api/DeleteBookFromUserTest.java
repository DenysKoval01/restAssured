package com.restassured.regression.api;

import static io.restassured.RestAssured.given;

import com.restassured.api.request.book.DeleteBookFromUserCollection;
import com.restassured.api.response.book.BookInformation;
import com.restassured.common.info.Specifications;
import java.util.List;
import org.testng.annotations.Test;

public class DeleteBookFromUserTest extends BaseApiTest {

    @Test
    public void TC1()
    {
        List<BookInformation> bookInformation = commonMethods.getBooksAssignedToUser();
        Specifications.installSpecification(
                Specifications.responseSpec204());
        bookInformation.forEach(eachBook->
        {
            DeleteBookFromUserCollection deleteBookFromUserCollection =
                    new DeleteBookFromUserCollection(eachBook.isbn,properties.getProperty("user.id"));

            given().header("Authorization",
                            token).body(deleteBookFromUserCollection).when()
                    .delete("BookStore/v1/Book");
        });
    }
}
