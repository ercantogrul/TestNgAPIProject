package requests;

import base_urls.BookerBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C21_GetRequestNestedPojo extends BookerBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/466
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
*/

    @Test
    void getRequestNestedPojoTest(){

        //Set the url
        spec.pathParams("first", "booking","second","42");

        //Set the expected data
        BookingDatesPojo bookingdatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jane","Doe",111,true,bookingdatesPojo,"Extra pillows please");

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        BookingPojo actualData = response.as(BookingPojo.class); // De-serialization islemi parametresiz constructor ile yapilir. cünkü actualData parametresizdir
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getLastname(), expectedData.getLastname());
        assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid());

        assertEquals(actualData.getBookingdates().getCheckin(), bookingdatesPojo.getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(), bookingdatesPojo.getCheckout());

        assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds());




    }

}
