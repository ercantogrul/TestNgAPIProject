package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utilities.ObjectMapperUtils.jsonToJava;

public class C27_PostRequestObjectMapperUtils extends BookerBaseUrl {
/*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 471,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-03-07",
                "checkout": "2024-09-25"
            },
            "additionalneeds": "Lunch"
           }
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                            "bookingid": 2243,
                                            "booking": {
                                                "firstname": "John",
                                                "lastname": "Doe",
                                                "totalprice": 471,
                                                "depositpaid": true,
                                                "bookingdates": {
                                                    "checkin": "2023-03-07",
                                                    "checkout": "2024-09-25"
                                                },
                                                "additionalneeds": "Lunch"
                                            }
                                        }
 */

    @Test
    void nestedPojoTest() {
        //Set the url
        spec.pathParams("first", "booking");


        //Set the expected data
        String strJson = """
                {
                            "firstname": "John",
                            "lastname": "Doe",
                            "totalprice": 471,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2023-03-07",
                                "checkout": "2024-09-25"
                            },
                            "additionalneeds": "Lunch"
                           }""";


        BookingPojo expectedData = jsonToJava(strJson,BookingPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

       //Do assertion
        BookingResponsePojo actualData = jsonToJava(response.asString(),BookingResponsePojo.class);
       // BookingResponsePojo actualData = response.as(BookingResponsePojo.class); // De-serialization islemi parametresiz constructor ile yapilir. cünkü actualData parametresizdir
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getBooking().getFirstname(),expectedData.getFirstname());
        assertEquals(actualData.getBooking().getLastname(),expectedData.getLastname());
        assertEquals(actualData.getBooking().getTotalprice(),expectedData.getTotalprice());
        assertEquals(actualData.getBooking().getDepositpaid(),expectedData.getDepositpaid());

        assertEquals(actualData.getBooking().getBookingdates().getCheckin(),expectedData.getBookingdates().getCheckin());
        assertEquals(actualData.getBooking().getBookingdates().getCheckout(),expectedData.getBookingdates().getCheckout());


        assertEquals(actualData.getBooking().getAdditionalneeds(),expectedData.getAdditionalneeds());




    }


}