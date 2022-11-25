package ru.levelp.at.homework6.go.rests.posts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;
import ru.levelp.at.homework6.go.rests.users.GenerationUser;

public class PostsPutTest extends BaseApiTest {
    @Test
    void updatePost(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("postId", TestData.createTestPost())
            .contentType(ContentType.JSON)
            .body(GenerationPost.createNewPost())
            .when()
            .put("/posts/{postId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .body("user_id", Matchers.equalTo(GenerationPost.user_id))
            .body("title", Matchers.equalTo(GenerationPost.title))
            .body("body", Matchers.equalTo(GenerationPost.body));

        TestData.deleteTestPost();
    }

    @Test
    void failAuth() {

        RestAssured
            .given()
            .log().all()
            .pathParam("postId", TestData.createTestPost())
            .contentType(ContentType.JSON)
            .body(GenerationPost.createNewPost())
            .when()
            .put("/posts/{postId}")
            .then()
            .spec(failRespSpecification());

        TestData.deleteTestPost();

    }

    @Test
    void incorrectResource(){

        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("postId", TestData.INCORRECT_ID)
            .contentType(ContentType.JSON)
            .body(GenerationPost.createNewPost())
            .when()
            .put("/posts/{postId}")
            .then()
            .spec(failRespSpecification());

    }

    @Test//параметризировать
    void sendBlankName(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("postId", TestData.createTestPost())
            .contentType(ContentType.JSON)
            .body(GenerationPost.createNewPost())
            .when()
            .put("/posts/{postId}")
            .then()
            .spec(blankFieldRespSpecification());

        TestData.deleteTestPost();
    }
}
