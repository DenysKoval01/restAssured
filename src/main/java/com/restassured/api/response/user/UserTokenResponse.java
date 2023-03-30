package com.restassured.api.response.user;

import java.util.Date;
import lombok.Data;

@Data
public class UserTokenResponse {

    public String token;
    public Date expires;
    public String status;
    public String result;
}
