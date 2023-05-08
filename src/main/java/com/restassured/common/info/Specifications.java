package com.restassured.common.info;


import com.restassured.configuration.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.Properties;

public final class Specifications {

    private static Properties properties = ConfigurationReader.getPropertiesFromFile("base.properties");

    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder().setBaseUri(url).setContentType(ContentType.JSON).build();
    }

    public static ResponseSpecification responseSpec200() {
        return new ResponseSpecBuilder().expectStatusCode(200).build();
    }
    public static ResponseSpecification responseSpec201() {
        return new ResponseSpecBuilder().expectStatusCode(201).build();
    }

    public static ResponseSpecification responseSpec204() {
        return new ResponseSpecBuilder().expectStatusCode(204).build();
    }

    public static ResponseSpecification responseSpec400() {
        return new ResponseSpecBuilder().expectStatusCode(400).build();
    }

    public static ResponseSpecification responseSpec401() {
        return new ResponseSpecBuilder().expectStatusCode(401).build();
    }

    public static void installSpecification(ResponseSpecification response){
        RestAssured.requestSpecification = Specifications.requestSpec(properties.getProperty("base.url"));
        RestAssured.responseSpecification = response;
    }
}
