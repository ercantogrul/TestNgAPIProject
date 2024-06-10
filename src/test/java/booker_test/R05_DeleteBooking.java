package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static booker_test.R01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R05_DeleteBooking extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/id
    When
        User sends delete request
    Then
        Status code should be 201
    And
        Response body should be "Created"
     */

    @Test
    void deleteBookingTest(){

        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);
//_______________________________________________________________________________________
        // set the expected
        String expectedData = "Created";
//_______________________________________________________________________________________
        //send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();
//_______________________________________________________________________________________
        // Do assertion

        assertEquals(response.statusCode(), 201);
        assertEquals(response.asString(),expectedData);



    }

}