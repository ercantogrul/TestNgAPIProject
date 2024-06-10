package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static booker_test.R01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R06_NegativeGetBooking extends BookerBaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        User sends get request
    Then
        Status code should be 404
    And
        Response body should be"Not Found"

     */


    @Test
    void negativeGetBookingTest(){

        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);
//_______________________________________________________________________________________
        // set the expected
        String expectedData = "Not Found";
//_______________________________________________________________________________________
        //send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
//_______________________________________________________________________________________
        // Do assertion

        assertEquals(response.statusCode(), 404);
        assertEquals(response.asString(),expectedData);



    }

}