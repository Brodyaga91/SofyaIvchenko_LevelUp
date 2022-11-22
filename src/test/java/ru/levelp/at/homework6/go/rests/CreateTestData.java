package ru.levelp.at.homework6.go.rests;
import com.github.javafaker.Faker;

import static io.restassured.RestAssured.given;


public class CreateTestData extends BaseApiTest{
    public static String id;

    public static String createTestData(){
        var faker = new Faker();
        
        id = given()
            .spec(requestSpecification)
            .body("{\"name\": \"Rageswari Prajapat\",\n"
                + "        \"email\": \"dageswari_prajapat@reynolds-sipes.org\",\n"
                + "        \"gender\": \"male\",\n"
                + "        \"status\": \"inactive\"}")
            .when()
            .post("https://gorest.co.in/public/v2/users")
            .then()
            .spec(responseSpecification)
            .statusCode(201)
            .extract()
            .body().jsonPath().getString("id");
        return id;
    }
}
