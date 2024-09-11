package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class C03_AssertHeaderNegative {
/*
   Given
       https://restful-booker.herokuapp.com/booking/0
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Response body contains "Not Found"
   And
       Response body does not contain "Clarusway"
   And
       Server is "Cowboy"
*/

    @Test
    public void assertionTest(){

//https://restful-booker.herokuapp.com/booking/0
//User send a GET Request to the url
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/0");
        response.prettyPrint();


    //HTTP Status code should be 404
        assertEquals(response.statusCode(), 404);
        response.then().statusCode(404);

    //Status Line should be HTTP/1.1 404 Not Found
        assertEquals(response.statusLine(),"HTTP/1.1 404 Not Found");
        response.then().statusLine("HTTP/1.1 404 Not Found");

    //Response body contains "Not Found"
        assertTrue(response.asString().contains("Not Found"));
        response.then().body(equalTo("Not Found"));

    //Response body does not contain "Clarusway"
        assertFalse(response.asString().contains("Clarusway"));

    //Server is "Cowboy"
        assertEquals(response.header("Server"), "Cowboy");




    }


}