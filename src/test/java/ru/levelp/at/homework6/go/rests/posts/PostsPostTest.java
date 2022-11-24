package ru.levelp.at.homework6.go.rests.posts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.users.GenerationUser;

public class PostsPostTest extends BaseApiTest {

    @Test
    void createNewPost() {
        RestAssured
            .given()
            .spec(requestSpecification)
            .body(GenerationPost.createNewPost())
            .when()
            .post("/posts")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .body("user_id", Matchers.equalTo(GenerationPost.user_id))
            .body("title", Matchers.equalTo(GenerationPost.title))
            .body("body", Matchers.equalTo(GenerationPost.body));

    }

    @Test
    void failAuth(){
        RestAssured
            .given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(GenerationPost.createNewPost())
            .when()
            .post("/posts")
            .then()
            .spec(failAuthSpecification());
    }

    @Test//параметризовать
    void sendBlankName(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(GenerationPost.createNewPost())
            .when()
            .post("/posts")
            .then()
            .spec(blankFieldRespSpecification());
    }

    @Test
    void incorrectEmail(){}

    @Test
    void incorrectGender(){}

    @Test
    void incorrectStatus(){}
}
