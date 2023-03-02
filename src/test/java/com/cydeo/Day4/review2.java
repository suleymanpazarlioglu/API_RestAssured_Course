package com.cydeo.Day4;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class review2 extends SpartanTestBase {

    @DisplayName("Get one spartan with JsonPath")
    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when()
                .get("/api/spartans/{id}");

        Assertions.assertEquals(200,response.statusCode());

        Assertions.assertEquals("application/json",response.contentType());

        System.out.println(response.path("name").toString());

        JsonPath jsonPath=response.jsonPath();
        int id=jsonPath.getInt("id");
        String name=jsonPath.getString("name");
        System.out.println("id = " + id);
        System.out.println("name = " + name);


    }

}
