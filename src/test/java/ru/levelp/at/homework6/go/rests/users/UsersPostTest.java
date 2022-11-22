package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class UsersPostTest {

    String bearerToken = "98536256fe8c96313bf8ec05dd0763e8ab3676056d6fbf3ccc767f7aa89a5c29";

    @Test
    void createNewUser(){
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

    @Test
    void failAuth(){}

    @Test
    void incorrectName(){}

    @Test
    void incorrectEmail(){}

    @Test
    void incorrectGender(){}

    @Test
    void incorrectStatus(){}
}
