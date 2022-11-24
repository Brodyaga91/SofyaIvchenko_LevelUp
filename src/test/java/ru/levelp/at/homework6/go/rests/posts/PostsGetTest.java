package ru.levelp.at.homework6.go.rests.posts;

import io.restassured.RestAssured;
import java.util.List;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;

public class PostsGetTest extends BaseApiTest {

    @Test
    void getOnePost(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("postId", TestData.createTestPost())
            .when()
            .get("/posts/{postId}")
            .then()
            .spec(responseSpecification)
            .statusCode(200)
            .body("user_id", Matchers.equalTo(GenerationPost.user_id))
            .body("title", Matchers.equalTo(GenerationPost.title))
            .body("body", Matchers.equalTo(GenerationPost.body));

        TestData.deleteTestPost();
    }

    @Test
    void getPosts(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .when()
            .get("/posts")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .body(Matchers.not(Matchers.emptyArray()));
    }

    @Test
    void getIncorrectPost(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("postId", TestData.INCORRECT_ID)
            .when()
            .get("/posts/{postId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
