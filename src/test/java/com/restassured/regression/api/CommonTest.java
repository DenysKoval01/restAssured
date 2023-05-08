package com.restassured.regression.api;

import static io.restassured.RestAssured.given;

import com.restassured.api.request.book.AddBookToUserCollection;
import com.restassured.api.response.book.AllBooksInStore;
import com.restassured.api.response.book.BookInformation;
import com.restassured.common.info.Specifications;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class CommonTest extends BaseApiTest {
    private String randomIsbn;

    @Test(description = "END-2-END test")
    public void end2endtest() {
        // Get all books in store
        Response response = given().header("Authorization",
                        token)
                .when()
                .get("BookStore/v1/Books");

        AllBooksInStore informationAboutAllBooksInStore = response.getBody().as(AllBooksInStore.class);
        List<String> allIsbn = informationAboutAllBooksInStore.books.stream().map(eachBook -> eachBook.isbn).toList();
        Optional<String> anyIsbn = allIsbn.stream().findAny();
        if (anyIsbn.isPresent()) {
            randomIsbn = anyIsbn.get();
            System.out.println("Random isbn is : " + randomIsbn);
        }
        // Get books assigned to user
        List<BookInformation> assignedToUserBookInformation = commonMethods.getBooksAssignedToUser();
        Assertions.assertThat(assignedToUserBookInformation.size()).as("Size is not expected").isEqualTo(0);

        //Add book to user collection
        Specifications.installSpecification(Specifications.responseSpec201());
        AddBookToUserCollection.CollectionOfIsbn list = new AddBookToUserCollection.CollectionOfIsbn();
        list.isbn = randomIsbn;
        List<AddBookToUserCollection.CollectionOfIsbn> collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(list);
        AddBookToUserCollection addBookToUserCollection =
                new AddBookToUserCollection(properties.getProperty("user.id"), collectionOfIsbns);
        given().header("Authorization", token)
                .body(addBookToUserCollection).when()
                .post("BookStore/v1/Books");

        Specifications.installSpecification(Specifications.responseSpec200());
        List<BookInformation> assignedToUserBookInformation1 = commonMethods.getBooksAssignedToUser();
        Assertions.assertThat(assignedToUserBookInformation1.get(0).isbn).as("Size is not expected")
                .isEqualTo(randomIsbn);

    }
}
