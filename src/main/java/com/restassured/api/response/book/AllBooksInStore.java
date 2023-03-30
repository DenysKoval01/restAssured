package com.restassured.api.response.book;

import java.util.List;
import lombok.Data;

@Data
public class AllBooksInStore {
    public List<BookInformation> books;
}
