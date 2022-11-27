package ru.levelp.at.homework6.go.rests;

import static org.hamcrest.Matchers.containsString;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;

public abstract class BaseApiTest {

    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;

    String bearerToken = "98536256fe8c96313bf8ec05dd0763e8ab3676056d6fbf3ccc767f7aa89a5c29";

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";


        requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setBaseUri(RestAssured.baseURI)
            .setBasePath(RestAssured.basePath)
            .addHeader("Authorization", "Bearer " + bearerToken)
            .setContentType(ContentType.JSON)
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

    }

    public ResponseSpecification failRespSpecification() {
        return new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_NOT_FOUND)
            .expectBody("message", Matchers.equalTo("Resource not found"))
            .addResponseSpecification(responseSpecification)
            .build();
    }

    public ResponseSpecification failAuthSpecification() {
        return new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_UNAUTHORIZED)
            .expectBody("message", Matchers.equalTo("Authentication failed"))
            .addResponseSpecification(responseSpecification)
            .build();
    }

    public ResponseSpecification blankFieldRespSpecification() {
        return new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
            .expectBody(Matchers.anyOf(containsString("can't be blank"), containsString("must exist")))
            .addResponseSpecification(responseSpecification)
            .build();
    }




}
