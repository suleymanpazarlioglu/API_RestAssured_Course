package com.cydeo.Day4;

import com.cydeo.utilities.HRTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class review3 extends HRTestBase {

    @DisplayName("Get request to Countries")
    @Test
    public void test1(){

        Response response=get("countries");

        JsonPath jsonPath=response.jsonPath();

        String secondCountryName=jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        List<String> allCountryNames=jsonPath.getList("items.country_name");
        System.out.println("allCountryNames = " + allCountryNames);

        List<String> allCountryIds=jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);

        List<String> allCountryIdsRegionId2=jsonPath.getList("items.findAll {it.region_id== 2}.country_id");
        System.out.println("allCountryIdsRegionId2 = " + allCountryIdsRegionId2);


    }

    @Test
    public void test2(){
        Response response=given().queryParam("limit",107)
                .when()
                .get("/employees");

        JsonPath jsonPath=response.jsonPath();

        List<String> email=jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("email = " + email);

        List<String> paralilar=jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("paralilar = " + paralilar);

        String enGodoman=jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println(enGodoman);


    }




}
