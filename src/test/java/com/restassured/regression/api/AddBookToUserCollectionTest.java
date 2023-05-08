package com.restassured.regression.api;


import static io.restassured.RestAssured.given;

import com.restassured.api.request.book.AddBookToUserCollection;
import com.restassured.api.response.book.AllBooksAfterAdded;
import com.restassured.common.info.Specifications;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddBookToUserCollectionTest extends BaseApiTest {

    @Test(description = "Запит даних щодо валідного ID")
    public void TC1() {
        String bookIsbn = "9781449365035";
        Specifications.installSpecification(
                Specifications.responseSpec201());
        AddBookToUserCollection.CollectionOfIsbn list = new AddBookToUserCollection.CollectionOfIsbn();
        list.isbn = bookIsbn;
        List<AddBookToUserCollection.CollectionOfIsbn> collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(list);
        AddBookToUserCollection addBookToUserCollection =
                new AddBookToUserCollection(properties.getProperty("user.id"), collectionOfIsbns);
        Response response =          given().header("Authorization",token)
                .body(addBookToUserCollection).when()
                .post("BookStore/v1/Books");

        AllBooksAfterAdded.AddedBooks isbnOfBook =
                response.getBody().as(AllBooksAfterAdded.class).books.get(0);


        Assert.assertEquals(isbnOfBook.isbn,bookIsbn);

    }

}
