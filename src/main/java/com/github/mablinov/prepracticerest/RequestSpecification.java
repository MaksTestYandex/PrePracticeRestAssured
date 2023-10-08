package com.github.mablinov.prepracticerest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class RequestSpecification {
    public static final String BASE_URL = "https://qa-mesto.praktikum-services.ru";

    public static io.restassured.specification.RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}
