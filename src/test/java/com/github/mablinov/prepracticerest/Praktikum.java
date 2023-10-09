package com.github.mablinov.prepracticerest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Praktikum {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    @Test
    public void registrationAndAuth() {
        // Составили email
        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        // составь json, используя переменную email. Не забудь про экранизацию кавычек с помощью '/'
        String json = "{\"email\": \"" + email + "\", \"password\": \"password\"}";

// POST запрос на регистрацию signup
        given()
                .header("Content-type", "application/json")
                .body(json)
                .when()
                .log().all()
                .post("/api/signup")
                .then()
                .log().all()
                // проверь статус ответа
                .assertThat().statusCode(201);
        Response response = given()
                .header("Content-type", "application/json")
                .body(json)
                .when()
                .log().all()
                .post("/api/signin");
        response.then().assertThat()
                // проверь статус ответа
                .statusCode(200)
                .and()
                .body("token", notNullValue());
        // Попытка зарегистрироваться с теми же параметрами ещё раз
        given()
                .header("Content-type", "application/json")
                .body(json)
                .post("/api/signup")
                .then()
                .log().all()
                // проверь, что статус ответа изменился
                .statusCode(409);
    }
}