package com.cydeo.Day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class HamcrestMatchersApiTest {

     /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("One spartan with Hamcrest and chaining")
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when()
                .get("http://54.243.0.145:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id",equalTo(15),
                        "name",equalTo("Meta"),
                        "gender",equalTo("Female"),
                        "phone",equalTo(1938695106));
    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",9)
                .when().get("https://api.training.cydeo.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("transfer-encoding",is("chunked"))
                .and()
                .header("date",notNullValue())
                .and()
                .body("teachers[0].firstName",is("Yet"))
                .body("teachers[0].lastName",is("No"))
                .body("teachers[0].gender",is("Male"));

    }

    @DisplayName("Get request to teacher/all and chaining")
    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .when().get("https://api.training.cydeo.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .body("teachers.firstName",hasItems("Tet","Test","Ron"));

    }



}
