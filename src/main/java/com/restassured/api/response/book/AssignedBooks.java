package com.restassured.api.response.book;

import java.util.List;
import lombok.Data;

@Data
public class AssignedBooks {
    public String userId;
    public String username;
    public List<BookInformation> books;
}
