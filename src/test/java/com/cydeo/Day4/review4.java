package com.cydeo.Day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;

import static io.restassured.RestAssured.baseURI;

public class review4  {

    @BeforeAll
    public static void init(){
        baseURI="https://api.training.cydeo.com";
    }

    @DisplayName("Get request to individual student")
    @Test
    public void test1(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",22)
                .log().all()
                .when().get("/student/{id}");

       Assertions.assertEquals(200,response.statusCode());

       Assertions.assertEquals("application/json;charset=UTF-8",response.contentType());

       Assertions.assertEquals("chunked",response.header("transfer-encoding"));

       Assertions.assertTrue(response.headers().hasHeaderWithName("date"));

       Assertions.assertEquals("Orlando",response.path("students[0].firstName"));
       Assertions.assertEquals("11",response.path("students[0].batch").toString());
       Assertions.assertEquals("N/A",response.path("students[0].section"));
       Assertions.assertEquals("gwarlawe8@delicious.com",response.path("students[0].contact.emailAddress"));
       Assertions.assertEquals("Photobug",response.path("students[0].company.companyName"));
       Assertions.assertEquals("New York",response.path("students[0].company.address.state"));
       Assertions.assertEquals("10620",response.path("students[0].company.address.zipCode").toString());

    }

}
