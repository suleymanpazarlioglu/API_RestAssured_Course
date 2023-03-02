package com.cydeo.Day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {
    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",10)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                .log().all();

    }



}
