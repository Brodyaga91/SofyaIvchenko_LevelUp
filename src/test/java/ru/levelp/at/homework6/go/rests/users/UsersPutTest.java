package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;

public class UsersPutTest extends BaseApiTest {

    @Test
    void updateData(){}

    @Test
    void failAuth(){
        {
            RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Bndjfhjk Ff\",\n"
                    + "        \"email\": \"Bndjfhjkerer@ya.rg\",\n"
                    + "        \"gender\": \"male\",\n"
                    + "        \"status\": \"inactive\"}")
                .when()
                .put("https://gorest.co.in/public/v2/users/123")
                .then()
                .spec(failAuthRespSpecification());
        }
    }

    @Test
    void incorrectResource(){
    }

    @Test//непонятно, что тут должно быть
    void fail422(){}
}
