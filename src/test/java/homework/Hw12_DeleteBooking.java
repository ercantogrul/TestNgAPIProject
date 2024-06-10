package homework;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static booker_test.R01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Hw12_DeleteBooking extends BookerBaseUrl {

    @Test
    void homework12_DeleteBookingTest() {
        // Set the URL
        spec.pathParams("first", "booking", "second", bookingId);

        // Set the expected data

        // Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        // Do assertion

        assertEquals(response.statusCode(), 201);

        // String actualStatus = response.getStatusLine();
        // assertTrue(actualStatus.contains("Created") );// bu ifade response dan geliyor

    }

}
