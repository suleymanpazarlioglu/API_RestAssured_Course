package com.cydeo.Day7;

import com.cydeo.utilities.SpartanTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import com.cydeo.pojo.Spartan;
import io.restassured.path.json.JsonPath;
import io.restassured.http.ContentType;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to one Spartan for update with Map")
    @Test
    public void PutRequest(){

        Map<String,Object> putRequestMap=new LinkedHashMap<>();
        putRequestMap.put("name","Adem");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",8877445596L);

        given().and().contentType(ContentType.JSON)
                .body(putRequestMap).log().all()
                .and().pathParam("id",106)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);

        Spartan expectedSpartan=given().accept(ContentType.JSON)
                .and().pathParam("id",106)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response().as(Spartan.class);

        assertThat(expectedSpartan.getId(),is(106));
        assertThat(expectedSpartan.getName(),is(putRequestMap.get("name")));
        assertThat(expectedSpartan.getGender(),is(putRequestMap.get("gender")));
        assertThat(expectedSpartan.getPhone(),is(putRequestMap.get("phone")));

    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest() {
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 8811111111L);
        putRequestMap.put("name", "Salomon");

        given().contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id", 106)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        Spartan expectedSpartan = given().accept(ContentType.JSON)
                .and().pathParam("id", 106)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response().as(Spartan.class);

        assertThat(expectedSpartan.getId(), is(106));
        assertThat(expectedSpartan.getName(), is(putRequestMap.get("name")));
        assertThat(expectedSpartan.getPhone(), is(putRequestMap.get("phone")));
    }

    @DisplayName("DELETE one Spartan")
    @Test
    public void deleteSpartan(){

        int idToDelete=105;
        given().pathParam("id",idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        given().accept(ContentType.JSON)
                .and().pathParam("id",idToDelete)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);
    }

}
