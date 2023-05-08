package com.restassured.api.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    public String userName;
    public String password;

}
