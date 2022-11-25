package ru.levelp.at.homework6.go.rests;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import ru.levelp.at.homework6.go.rests.comments.GenerationComment;
import ru.levelp.at.homework6.go.rests.posts.GenerationPost;
import ru.levelp.at.homework6.go.rests.users.CreateUserData;
import ru.levelp.at.homework6.go.rests.users.GenerationUser;

import static io.restassured.RestAssured.given;


public class TestData extends BaseApiTest{
    public static String id;
    public static final String INCORRECT_ID = "000";

    public static String createTestUser(){


        id = given()
            .spec(requestSpecification)
            .body(GenerationUser.createNewUser())
            .when()
            .post("/users")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .body().jsonPath().getString("id");
        return id;
    }

    public static void deleteTestUser(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("userId", id)
            .contentType(ContentType.JSON)
            .when()
            .delete("/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    public static String createTestPost(){


        id = given()
            .spec(requestSpecification)
            .body(GenerationPost.createNewPost())
            .when()
            .post("/posts")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .body().jsonPath().getString("id");
        return id;
    }

    public static void deleteTestPost(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("postId", id)
            .contentType(ContentType.JSON)
            .when()
            .delete("/posts/{postId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    public static String createTestComment(){


        id = given()
            .spec(requestSpecification)
            .body(GenerationComment.createNewComment())
            .when()
            .post("/comments")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .body().jsonPath().getString("id");
        return id;
    }

    public static void deleteTestComment(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("commentId", id)
            .contentType(ContentType.JSON)
            .when()
            .delete("/comments/{commentId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NO_CONTENT);

    }
}
