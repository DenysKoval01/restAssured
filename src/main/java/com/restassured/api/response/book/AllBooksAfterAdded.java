package com.restassured.api.response.book;

import java.util.List;
import lombok.Data;

@Data
public class AllBooksAfterAdded {

    public List<AddedBooks> books;

    public static class AddedBooks {
        public String isbn;
    }

}
