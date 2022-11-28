package ru.levelp.at.homework6.go.rests.posts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;

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
    void failAuth() {
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

    @Test(dataProvider = "negativeDataProviderPosts")
    void sendBlankName(CreatePostData request) {
        RestAssured
            .given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(request)
            .when()
            .post("/posts")
            .then()
            .spec(blankFieldRespSpecification());
    }

    @DataProvider
    public Object[][] negativeDataProviderPosts() {
        return new Object[][]{
            {GenerationPost.createPostWithoutField("", GenerationPost.body, GenerationPost.user_id)},
            {GenerationPost.createPostWithoutField(GenerationPost.title, "", GenerationPost.user_id)},
            {GenerationPost.createPostWithoutField(GenerationPost.title, GenerationPost.body, 0)},

        };
    }
}
