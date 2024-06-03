package Homework;

import base_urls.Hw05BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class Hw05 extends Hw05BaseUrl {
        /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json; charset=utf-8"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    void homwork05Test1(){
    //    1. Set the url
        String url = "https://reqres.in/api/unknown/3";

    //    3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

    response
            .then()
            .statusCode(200) // HTTP Status Code should be 200
            .contentType("application/json; charset=utf-8")  //// Response content type is "application/json; charset=utf-8"
            ////Response body should be like;(Soft Assertion)
            .body("data.id",equalTo(3),"data.name",equalTo("true red"),
                    "data.year",equalTo(2002),"data.color",equalTo("#BF1932"),"data.pantone_value",equalTo("19-1664"),
                    "support.url",equalTo("https://reqres.in/#support-heading"),
                    "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }

    @Test
    void homwork05Test2(){

        SoftAssert softAssert = new SoftAssert();
        //Set the url
        spec.pathParams("first","api","second","unknown","third","3");

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();

     // HTTP Status Code should be 200
        int statusCode = response.statusCode();
        softAssert.assertEquals(statusCode,200);

     // Response content type is "application/json; charset=utf-8"
        String contentType = response.contentType();
        softAssert.assertEquals(response.contentType(), ContentType.JSON.withCharset("utf-8"));

     // Response body should be like;(Soft Assertion)
        JsonPath jsonPath = response.jsonPath();

     int id = jsonPath.getInt("data.id");
     String name = jsonPath.getString("data.name");
     int year = jsonPath.getInt("data.year");
     String color = jsonPath.getString("data.color");
     String pantone_value = jsonPath.getString("data.pantone_value");
     String url = jsonPath.getString("support.url");
     String text = jsonPath.getString("support.text");

     softAssert.assertEquals(id,3);
     softAssert.assertEquals(name,"true red");
     softAssert.assertEquals(year,2002);
     softAssert.assertEquals(color,"#BF1932");
     softAssert.assertEquals(pantone_value,"19-1664");
     softAssert.assertEquals(url,"https://reqres.in/#support-heading");
     softAssert.assertEquals(text,"To keep ReqRes free, contributions towards server costs are appreciated!");

     softAssert.assertAll();




    }
}
