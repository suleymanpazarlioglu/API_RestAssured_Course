package com.cydeo.Day12;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class MockApi {

    //https://3880a556-82cf-4695-b9ea-3539e56a4cba.mock.pstmn.io

    @Test
    public void test1(){
        given().baseUri("https://3880a556-82cf-4695-b9ea-3539e56a4cba.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/customer")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"))
                .body("address.state",equalTo("NY"))
                .extract().response().prettyPrint();
    }
    @Test
    public void test2(){

        given().baseUri("https://3880a556-82cf-4695-b9ea-3539e56a4cba.mock.pstmn.io")
                    .accept(ContentType.JSON)
                    .when()
                    .get("/employees")
                    .prettyPrint();
    }



}
