package ru.levelp.at.homework6.go.rests.comments;

import io.restassured.RestAssured;
import java.util.List;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;

public class CommentsGetTest extends BaseApiTest {

    @Test
    void getOnePerson(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("userId", TestData.id)
            .when()
            .get("https://gorest.co.in/public/v2/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(200)
            .body("name", Matchers.equalTo("Ekaling Agarwal"))
            .body("email", Matchers.equalTo("ekaling_agarwal@cruickshank.net"))
            .body("gender", Matchers.equalTo("female"))
            .body("status", Matchers.equalTo("active"));
    }

    @Test //не работает
    void getPersons(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .when()
            .get("https://gorest.co.in/public/v2/users")
            .then()
            .spec(responseSpecification)
            .statusCode(200)
            .body(Matchers.containsInAnyOrder(List.of(3787, 3786, 3785, 3783, 3781, 3780, 3778, 3774, 3768, 3762)));
    }

    @Test
    void getIncorrectPerson(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("userId", "000")
            .when()
            .get("https://gorest.co.in/public/v2/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(404);
    }
}
