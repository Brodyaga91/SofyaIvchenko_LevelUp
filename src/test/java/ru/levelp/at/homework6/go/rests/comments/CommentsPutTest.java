package ru.levelp.at.homework6.go.rests.comments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;
import ru.levelp.at.homework6.go.rests.posts.GenerationPost;

public class CommentsPutTest extends BaseApiTest {
    @Test
    void updateComment(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("commentId", TestData.createTestComment())
            .contentType(ContentType.JSON)
            .body(GenerationComment.createNewComment())
            .when()
            .put("/comments/{commentId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .body("post_id", Matchers.equalTo(GenerationComment.post_id))
            .body("name", Matchers.equalTo(GenerationComment.name))
            .body("body", Matchers.equalTo(GenerationComment.body))
            .body("email", Matchers.equalTo(GenerationComment.email));

        TestData.deleteTestComment();
    }

    @Test
    void failAuth() {

        RestAssured
            .given()
            .log().all()
            .pathParam("commentId", TestData.createTestComment())
            .contentType(ContentType.JSON)
            .body(GenerationComment.createNewComment())
            .when()
            .put("/comments/{commentId}")
            .then()
            .spec(failRespSpecification());

        TestData.deleteTestComment();

    }

    @Test
    void incorrectResource(){

        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("commentId", TestData.INCORRECT_ID)
            .contentType(ContentType.JSON)
            .body(GenerationComment.createNewComment())
            .when()
            .put("/comments/{commentId}")
            .then()
            .spec(failRespSpecification());

    }

    @Test//параметризировать
    void sendBlankName(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("commentId", TestData.createTestComment())
            .contentType(ContentType.JSON)
            .body(GenerationComment.createNewComment())
            .when()
            .put("/comments/{commentId}")
            .then()
            .spec(blankFieldRespSpecification());

        TestData.deleteTestComment();
    }
}
