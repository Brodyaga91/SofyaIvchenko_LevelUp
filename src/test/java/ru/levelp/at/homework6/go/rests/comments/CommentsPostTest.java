package ru.levelp.at.homework6.go.rests.comments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;

public class CommentsPostTest extends BaseApiTest {

    @Test
    void createNewComment() {
        RestAssured
            .given()
            .spec(requestSpecification)
            .body(GenerationComment.createNewComment())
            .when()
            .post("/comments")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .body("post_id", Matchers.equalTo(GenerationComment.post_id))
            .body("name", Matchers.equalTo(GenerationComment.name))
            .body("body", Matchers.equalTo(GenerationComment.body))
            .body("email", Matchers.equalTo(GenerationComment.email));

    }

    @Test
    void failAuth() {
        RestAssured
            .given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(GenerationComment.createNewComment())
            .when()
            .post("/comments")
            .then()
            .spec(failAuthSpecification());
    }

    @Test(dataProvider = "negativeDataProviderComments")
    void sendBlankName(CreateCommentData request) {
        RestAssured
            .given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(request)
            .when()
            .post("/comments")
            .then()
            .spec(blankFieldRespSpecification());
    }

    @DataProvider
    public Object[][] negativeDataProviderComments() {
        return new Object[][]{
            {GenerationComment.createCommentWithoutField(0, GenerationComment.body, GenerationComment.name, GenerationComment.email)},
            {GenerationComment.createCommentWithoutField(GenerationComment.post_id, "", GenerationComment.name, GenerationComment.email)},
            {GenerationComment.createCommentWithoutField(GenerationComment.post_id, GenerationComment.body, "", GenerationComment.email)},
            {GenerationComment.createCommentWithoutField(GenerationComment.post_id, GenerationComment.body, GenerationComment.name, "")}

        };
    }

}
