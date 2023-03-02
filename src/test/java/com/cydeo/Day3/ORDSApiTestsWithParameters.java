package com.cydeo.Day3;

import org.junit.jupiter.api.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {
    @BeforeAll
    public static void init() {
        baseURI = "http://54.243.0.145:1000/ords/hr";
    }

    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\": 4}")
                .log().all()
                .when()
                .get("/countries");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.header("Content-Type"));

        assertTrue(response.body().asString().contains("Egypt"));
    }

    /*
        Send a GET request to employees and get only employees who works as a IT_PROG
     */

    @Test
    public void test2(){
        Response response=given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .log().all()
                .when()
                .get("/employees");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.header("Content-Type"));

        assertTrue(response.body().asString().contains("IT_PROG"));
    }







}
