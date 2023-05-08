package com.restassured.api.response.book;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssignedBooks {
    @SerializedName("userId")
    public String userId;
    @SerializedName("username")
    public String username;
    @SerializedName("books")
    public List<BookInformation> books;
}
