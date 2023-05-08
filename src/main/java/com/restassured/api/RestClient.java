package com.restassured.api;

import static com.restassured.common.CommonMethods.getAuthorizationToken;

import com.google.gson.Gson;
import com.restassured.configuration.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);
    private static final String BASE_URL =
            ConfigurationReader.getPropertiesFromFile("base.properties").getProperty("base.url");

    private String authorizationValue;
    private static final String AUTHORIZATION = "Authorization";
    private RequestSpecification client;


    private RestClient() {
        RestAssured.defaultParser = Parser.JSON;
        updateHeaders();
    }

    private void updateHeaders() {
        try {
            authorizationValue = getAuthorizationToken();
        } catch (Exception tee) {
            LOGGER.error(tee.getMessage());
        }
    }


    public static RestClient getInstance() {
        return RestClientWrapper.instance;
    }

    public RequestSpecification createRequestSpecification() {
        client = RestAssured.given().headers(getHeaders());
        return client;
    }

    public <T> Response sendRequest(HttpMethod httpMethod, String template,T entity) {
        Response response;
        createRequestSpecification();
        String templatePath = BASE_URL + template;

            response = sendRequestForHttpMethod(httpMethod,
                    client
                            .contentType(ContentType.JSON)
                            .body(new Gson().toJson(entity)),
                    templatePath);
        return response;
    }

    private Headers getHeaders() {
        return new Headers(new Header(AUTHORIZATION, authorizationValue));
    }

    private Response sendRequestForHttpMethod(HttpMethod httpMethod, RequestSpecification requestSpecification,
                                              String url) {
        return switch (httpMethod) {
            case GET -> requestSpecification.get(url);
            case POST -> requestSpecification.post(url);
            case PUT -> requestSpecification.put(url);
            case DELETE -> requestSpecification.delete(url);
            case PATCH -> requestSpecification.patch(url);
        };
    }

    private static final class RestClientWrapper {
        static RestClient instance = new RestClient();

        private RestClientWrapper() {
        }
    }
}
