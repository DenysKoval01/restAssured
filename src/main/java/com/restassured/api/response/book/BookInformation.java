package com.restassured.api.response.book;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
