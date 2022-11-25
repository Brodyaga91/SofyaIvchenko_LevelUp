package ru.levelp.at.homework6.go.rests.comments;

import io.restassured.RestAssured;
import java.util.List;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;
import ru.levelp.at.homework6.go.rests.posts.GenerationPost;

public class CommentsGetTest extends BaseApiTest {

    @Test
    void getOneComment(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("commentId", TestData.createTestComment())
            .when()
            .get("/comments/{commentId}")
            .then()
            .spec(responseSpecification)
            .statusCode(200)
            .body("user_id", Matchers.equalTo(GenerationComment.post_id))
            .body("title", Matchers.equalTo(GenerationComment.name))
            .body("body", Matchers.equalTo(GenerationComment.body))
            .body("email", Matchers.equalTo(GenerationComment.email));

        TestData.deleteTestComment();
    }

    @Test
    void getComments(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .when()
            .get("/comments")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .body(Matchers.not(Matchers.emptyArray()));
    }

    @Test
    void getIncorrectComment(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("commentId", TestData.INCORRECT_ID)
            .when()
            .get("/comments/{commentId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
