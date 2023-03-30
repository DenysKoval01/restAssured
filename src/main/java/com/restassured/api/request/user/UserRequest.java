package com.restassured.api.request.user;

import lombok.Builder;
import lombok.Data;

@Data
public class UserRequest {
    public String userName;
    public String password;

    public UserRequest(String userName, String password){
        this.userName=userName;
        this.password=password;
    }
}
