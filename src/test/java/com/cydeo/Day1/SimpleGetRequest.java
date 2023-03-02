package com.cydeo.Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SimpleGetRequest {

    String url = "http://54.243.0.145:8000/api/spartans";

    @Test
    public void test1(){
        Response response=RestAssured.get(url);//send a get request and save response inside the Response object

        System.out.println(response.statusCode());//print response status code

        response.prettyPrint();//print response body

        Assertions.assertEquals(response.statusCode(),200);

    }





}
