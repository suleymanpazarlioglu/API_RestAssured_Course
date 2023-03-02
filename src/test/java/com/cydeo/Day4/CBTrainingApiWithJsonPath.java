package com.cydeo.Day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI= "https://api.training.cydeo.com";

    }


    //send a get request to student id 66 as a path parameter and accept header application/json
    //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
    //verify Date header exists
    //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */
    @Test
    public void test(){
        Response response=RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",66)
                .log()
                .all()
                .when().get("/student/{id}");

        Assertions.assertEquals(200,response.statusCode());

        Assertions.assertEquals("application/json;charset=UTF-8",response.contentType());
        Assertions.assertEquals("chunked",response.header("transfer-encoding"));
        System.out.println(response.header("Date"));

        JsonPath jsonPath = response.jsonPath();

        String first_name=jsonPath.getString("students[0].firstName");
        int batch=jsonPath.getInt("students[0].batch");
        String section=jsonPath.getString("students[0].section");
        String email=jsonPath.getString("students[0].contact.emailAddress");
        String companyName=jsonPath.getString("students[0].company.companyName");
        String state=jsonPath.getString("students[0].company.address.state");
        int zipCode=jsonPath.getInt("students[0].company.address.zipCode");


        System.out.println("first_name = " + first_name);
        System.out.println("batch = " + batch);
        System.out.println("section = " + section);
        System.out.println("email = " + email);
        System.out.println("companyName = " + companyName);
        System.out.println("state = " + state);
        System.out.println("zipCode = " + zipCode);


    }

}
