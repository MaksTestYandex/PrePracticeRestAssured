package com.github.mablinov.prepracticerest;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class MestoPage {
    @Step
    public ValidatableResponse createNewUser(UserBody body) {
        return given()
                .spec(RequestSpecification.requestSpecification())
                .body(body)
                .when()
                .log().all()
                .post("/signup")
                .then()
                .log().all();
    }
    @Step
    public ValidatableResponse authNewUser(UserBody body) {
        return given()
                .spec(RequestSpecification.requestSpecification())
                .body(body)
                .when()
                .log().all()
                .post("/signin")
                .then()
                .log().all();
    }
}
