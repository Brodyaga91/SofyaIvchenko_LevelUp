package ru.levelp.at.homework6.go.rests.posts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class PostsPostTest {

    String bearerToken = "98536256fe8c96313bf8ec05dd0763e8ab3676056d6fbf3ccc767f7aa89a5c29";

    @Test
    void CreateNewUser(){
        RestAssured
            .given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType(ContentType.JSON)
            .body("{\"name\": \"Bndjfhjk Ff\",\n"
                + "        \"email\": \"Bndjfhjkerer@ya.rg\",\n"
                + "        \"gender\": \"male\",\n"
                + "        \"status\": \"inactive\"}")
            .log().all()
            .when()
            .post("https://gorest.co.in/public/v2/users")
            .then()
            .log().all()
            .statusCode(201);
    }
}
