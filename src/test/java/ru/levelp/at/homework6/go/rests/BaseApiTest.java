package ru.levelp.at.homework6.go.rests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.filter.log.LogDetail;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;

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
            .addHeader("Authorization", "Bearer " + bearerToken)
            .setContentType(ContentType.JSON)
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

    }

    public ResponseSpecification failAuthRespSpecification(){
        return new ResponseSpecBuilder()
            .expectStatusCode(401)
            .expectBody("message", Matchers.equalTo("Authentication failed"))
            .log(LogDetail.ALL)
            .build();
    }



}
