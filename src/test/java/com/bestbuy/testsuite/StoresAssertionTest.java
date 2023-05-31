package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

public class StoresAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }


    @Test
    public void verifyLimit() {
        response.body("limit", equalTo(10));
    }

    @Test
    public void verifyParticularName() {
        response.body("data[1].name", equalTo("Inver Grove Heights"));
    }

    @Test
    public void verifyMultipleName() {
        response.body("data.name", hasItem(("Roseville")));
        response.body("data.name", hasItem(("Burnsville")));
        response.body("data.name", hasItem(("Maplewood")));
    }


    @Test
    public void verifyStoreidO7() {
        response.body("data[2].services[1].storeservices.storeId",equalTo(7));
    }

    @Test
    public void verifyCreatedAt() {
       response.body("data[2].services[0]",hasKey("createdAt"));
    }

    @Test
    public void verifyStateIsMn() {
        response.body("data[3].state",equalTo("MN"));
    }

    @Test
    public void verifyNameIsRochesterAt9thStore() {
        response.body("data[8].name",equalTo("Rochester"));
    }

    @Test
    public void verifyStoreIdis11() {
        response.body("data[5].services[5].storeservices.storeId",equalTo(11));
    }

    @Test
    public void verifyserviceIdis4() {
        response.body("data[6].services[3].storeservices.serviceId",equalTo(4));
    }




}
