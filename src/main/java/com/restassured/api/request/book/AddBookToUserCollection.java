package com.restassured.api.request.book;

import java.util.List;
import lombok.Data;

@Data
public class AddBookToUserCollection {

    public String userId;
    public List<CollectionOfIsbn> collectionOfIsbns;

    public AddBookToUserCollection(String userId, List<CollectionOfIsbn> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }


    public static class CollectionOfIsbn{
        public String isbn;
    }

}
