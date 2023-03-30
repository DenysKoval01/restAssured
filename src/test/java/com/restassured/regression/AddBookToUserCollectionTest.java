package com.restassured.regression;


import static io.restassured.RestAssured.given;

import com.restassured.api.request.book.AddBookToUserCollection;
import com.restassured.api.response.book.AllBooksAfterAdded;
import com.restassured.info.Specifications;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddBookToUserCollectionTest extends BaseTest {

    @Test(description = "Запит даних щодо валідного ID")
    public void TC1() {
        String bookIsbn = "9781593275846";
        Specifications.installSpecification(Specifications.requestSpec(properties.getProperty("base.url")),
                Specifications.responseSpec201());
        AddBookToUserCollection.CollectionOfIsbn list = new AddBookToUserCollection.CollectionOfIsbn();
        list.isbn = bookIsbn;
        List<AddBookToUserCollection.CollectionOfIsbn> collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(list);
        AddBookToUserCollection addBookToUserCollection =
                new AddBookToUserCollection(properties.getProperty("user.id"), collectionOfIsbns);
        Response response =          given().header("Authorization",token)
                .body(addBookToUserCollection).when().contentType(ContentType.JSON)
                .post(properties.getProperty("base.url") + "BookStore/v1/Books");

        AllBooksAfterAdded.AddedBooks isbnOfBook =
                response.getBody().as(AllBooksAfterAdded.class).books.get(0);


        Assert.assertEquals(isbnOfBook.isbn,bookIsbn);

    }

}
