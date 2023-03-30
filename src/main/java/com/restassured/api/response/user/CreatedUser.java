package com.restassured.api.response.user;

import java.util.List;
import lombok.Data;

@Data
public class CreatedUser {
    public String userID;
    public String username;
    public List<Object> books;
}
