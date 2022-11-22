package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import ru.levelp.at.homework6.go.rests.BaseApiTest;

public class UsersPostTest extends BaseApiTest {

    @Test
    void createNewUser(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body("{\"name\": \"Bndjfhjk Ff\",\n"
                + "        \"email\": \"Bndjfhjkerer@ya.rg\",\n"
                + "        \"gender\": \"male\",\n"
                + "        \"status\": \"inactive\"}")
            .when()
            .post("https://gorest.co.in/public/v2/users")
            .then()
            .spec(responseSpecification)
            .statusCode(201);
    }

    @Test
    void failAuth(){
        RestAssured
            .given()
            .log().all()
            .contentType(ContentType.JSON)
            .body("{\"name\": \"Bndjfhjk Ff\",\n"
                + "        \"email\": \"Bndjfhjkerer@ya.rg\",\n"
                + "        \"gender\": \"male\",\n"
                + "        \"status\": \"inactive\"}")
            .when()
            .post("https://gorest.co.in/public/v2/users")
            .then()
            .spec(failAuthRespSpecification());
    }

    @Test
    void incorrectName(){}

    @Test
    void incorrectEmail(){}

    @Test
    void incorrectGender(){}

    @Test
    void incorrectStatus(){}
}
