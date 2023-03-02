package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.naming.spi.ResolveResult;

import static io.restassured.RestAssured.given;

public class HrGetRequests {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init(){

        //save baseurl inside this variable so that we dont need to type each http method.
        RestAssured.baseURI= "http://54.243.0.145:1000/ords/hr";

    }

    @DisplayName("Get request to /regions")
    @Test
    public void test1(){
        Response response=RestAssured.get("/regions");

        //print the status code
        System.out.println(response.statusCode());

    }

    /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */

    @DisplayName("Get request to /regions/2")
    @Test
    public void test2(){

        Response response=given().accept(ContentType.JSON)
                .when()
                .get("/regions/2");

        Assertions.assertEquals(response.contentType(),"application/json");

        Assertions.assertEquals(response.statusCode(),200);

        response.prettyPrint();

        Assertions.assertEquals(response.body().asString().contains("Americas"),true);





    }




}
