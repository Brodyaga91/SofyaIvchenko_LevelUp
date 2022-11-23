package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;
import java.util.List;

public class UsersGetTest extends BaseApiTest {


    @Test
    void getOnePerson(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("userId", TestData.createTestData())
            .when()
            .get("/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(200)
            .body("name", Matchers.equalTo(TestData.name))
            .body("email", Matchers.equalTo(TestData.email))
            .body("gender", Matchers.equalTo(TestData.gender))
            .body("status", Matchers.equalTo(TestData.status));
    }

    @Test
    void getPersons(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .when()
            .get("/users")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .body(Matchers.not(Matchers.emptyArray()));
    }

    @Test
    void getIncorrectPerson(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("userId", TestData.INCORRECT_ID)
            .when()
            .get("/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(404);
    }
}
