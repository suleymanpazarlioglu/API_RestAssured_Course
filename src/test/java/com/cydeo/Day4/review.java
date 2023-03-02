package com.cydeo.Day4;

import com.cydeo.utilities.HRTestBase;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class review extends HRTestBase {

    @DisplayName("Get request to countries with Path method")
    @Test
    public void test1(){


        Response response=given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\": 2}")
                .when()
                .get("/countries");

        Assertions.assertEquals(200,response.statusCode());

        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        String firstCountryId=response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        String secondCountryName=response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));

        List<String> Country_IDs=response.path("items.country_id");
        System.out.println("Country_IDs = " + Country_IDs);

        List<Integer> allRegionsId=response.path("items.region_id");

        for(Integer regionsId : allRegionsId){
            System.out.println("regionsId = " + regionsId);
            assertEquals(2,regionsId);
        }


    }
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

        List<String> allJobIds=response.path("items.job_id");

        for (String jobIds:allJobIds) {
            System.out.println("jobIds = " + jobIds);
            Assertions.assertEquals("IT_PROG",jobIds);

        }


    }




}
