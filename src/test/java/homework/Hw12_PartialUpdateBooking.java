package homework;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static booker_test.R01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Hw12_PartialUpdateBooking extends BookerBaseUrl {
    /*
Write an automation test to test all endpoints using the documentation
available at https://restful-booker.herokuapp.com/apidoc/index.html.
*/
    /*
    /*
    Given
        1) https://restful-booker.herokuapp.com/booking:id
        2) {
             "firstname" : "James",
              "lastname" : "Brown"

            }
    When
        I send PUT Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                            "firstname" : "James",
                                            "lastname" : "Brown",
                                            "totalprice" : 111,
                                            "depositpaid" : true,
                                            "bookingdates" : {
                                                "checkin" : "2018-01-01",
                                                "checkout" : "2019-01-01"
                                            },
                                            "additionalneeds" : "Breakfast"
                                        }

     */


    @Test
    void homework12_PartialUpdateBookingTest() {


        //Set the url
        //https://restful-booker.herokuapp.com/booking/:id
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String strJson = """
                {
                    "firstname" : "James",
                    "lastname" : "Brown"
                }"""; // bu Stringi en altta asserttion islemleri icin (expectedData ve actualData ya ihtiyac duyuldugu icin) olusturduk

        BookingPojo expectedData = ObjectMapperUtils.jsonToJava(strJson, BookingPojo.class);
        System.out.println("homework_12 expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getLastname(), expectedData.getLastname());



    }



}
