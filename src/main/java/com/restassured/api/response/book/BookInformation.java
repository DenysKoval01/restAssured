package com.restassured.api.response.book;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookInformation {
    public String isbn;
    public String title;
    public String subTitle;
    public String author;
    public Date publish_date;
    public String publisher;
    public int pages;
    public String description;
    public String website;
}
