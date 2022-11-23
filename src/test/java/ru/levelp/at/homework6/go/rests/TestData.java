package ru.levelp.at.homework6.go.rests;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;


public class TestData extends BaseApiTest{
    public static String id;
    public static String gender = "male";
    public static String status = "active";
    public static String email;
    public static String name;
    public static String changeStatus = "inactive";
    public static final String INCORRECT_ID = "000";

    public static String createTestData(){
        var faker = new Faker();
        email = new Faker().internet().emailAddress();
        name = new Faker().name().toString();

        id = given()
            .spec(requestSpecification)
            .body("{\"name\": \"" + name
                + "\", \"email\": \"" + email
                + "\",       \"gender\": \"" + gender
                + "\",      \"status\": \"" + status+"\"}")
            .when()
            .post("/users")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .body().jsonPath().getString("id");
        return id;
    }
}
