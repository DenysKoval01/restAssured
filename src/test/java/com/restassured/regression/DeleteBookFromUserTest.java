package com.restassured.regression;

import static io.restassured.RestAssured.given;

import com.restassured.api.request.book.AddBookToUserCollection;
import com.restassured.api.request.book.DeleteBookFromUserCollection;
import com.restassured.api.response.book.BookInformation;
import com.restassured.common.CommonMethods;
import com.restassured.info.Specifications;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import org.testng.annotations.Test;

public class DeleteBookFromUserTest extends BaseTest{

    @Test
    public void TC1()
    {
        List<BookInformation> bookInformation = commonMethods.getBooksAssignedToUser();
        Specifications.installSpecification(Specifications.requestSpec(properties.getProperty("base.url")),
                Specifications.responseSpec204());
        bookInformation.forEach(eachBook->
        {
            DeleteBookFromUserCollection deleteBookFromUserCollection =
                    new DeleteBookFromUserCollection(eachBook.isbn,properties.getProperty("user.id"));

            given().header("Authorization",
                            token).body(deleteBookFromUserCollection).when().contentType(ContentType.JSON)
                    .delete(properties.getProperty("base.url") + "BookStore/v1/Book");
        });
    }
}
