package com.cydeo.Day5;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.*;
import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;

public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("Get one Spartan from api and database")
    @Test
    public void testDB1(){

        // get id ,name,gender and phone from database

        String query= "select spartan_id,name,gender,phone from spartans\n" +
                "where spartan_id=15";

        Map<String,Object> dbMap= DBUtils.getRowMap(query);
        System.out.println(dbMap);

        //get info from api

        Map<String,Object> apiMap=given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response().as(Map.class);

        System.out.println(apiMap);

        //compare
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));


    }



}
