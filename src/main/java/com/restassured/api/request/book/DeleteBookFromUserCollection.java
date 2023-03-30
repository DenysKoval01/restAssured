package com.restassured.api.request.book;

import lombok.Data;

@Data
public class DeleteBookFromUserCollection {
    public String isbn;
    public String userId;

    public DeleteBookFromUserCollection(String isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }
}
