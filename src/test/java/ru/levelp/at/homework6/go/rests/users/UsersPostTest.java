package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;

public class UsersPostTest extends BaseApiTest {


    @Test
    void createNewUser() {
        RestAssured
            .given()
            .spec(requestSpecification)
            .body(GenerationUser.createNewUser())
            .when()
            .post("/users")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .body("name", Matchers.equalTo(GenerationUser.name))
            .body("email", Matchers.equalTo(GenerationUser.email))
            .body("gender", Matchers.equalTo(GenerationUser.gender))
            .body("status", Matchers.equalTo(GenerationUser.status));

    }

    @Test
    void failAuth() {
        RestAssured
            .given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(GenerationUser.createNewUser())
            .when()
            .post("/users")
            .then()
            .spec(failAuthSpecification());
    }


    @Test(dataProvider = "negativeDataProviderUsers")
    void sendBlankName(CreateUserData request) {
        RestAssured
            .given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(request)
            .when()
            .post("/users")
            .then()
            .spec(blankFieldRespSpecification());
    }

    @DataProvider
    public Object[][] negativeDataProviderUsers() {
        return new Object[][]{
            {GenerationUser.createUserWithoutField(
                "", GenerationUser.email, GenerationUser.gender, GenerationUser.status)},
            {GenerationUser.createUserWithoutField(
                GenerationUser.name, "", GenerationUser.gender, GenerationUser.status)},
            {GenerationUser.createUserWithoutField(
                GenerationUser.name, GenerationUser.email, "", GenerationUser.status)},
            {GenerationUser.createUserWithoutField(
                GenerationUser.name, GenerationUser.email, GenerationUser.gender, "")}

        };
    }
}
