package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;


import static booker_test.R01_CreateBooking.bookingId;
import static booker_test.R01_CreateBooking.expectedData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R02_GetBooking extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        User sends get request
    Then
        Status code should be 200
    And
        Response body should be:
            {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 15,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-03-07",
                "checkout": "2024-09-25"
            },
            "additionalneeds": "Lunch"
           }
     */

    @Test
    void getBookingTest() {
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);  // R01_CreateBooking de static olarak tanimlanan bookingId yi buraya ekliyoruz


        //Set the expected data
        //***** ilk classta yapilan islemi tekrar etmek istemiyorsak orada atanan degeri buraya import edebiliriz****
//        String strJson = """
//                            {
//                            "firstname": "John",
//                            "lastname": "Doe",
//                            "totalprice": 15,
//                            "depositpaid": true,
//                            "bookingdates": {
//                                "checkin": "2023-03-07",
//                                "checkout": "2024-09-25"
//                            },
//                            "additionalneeds": "Lunch"
//                           }
//                           """; // bu Stringi en altta asserttion islemleri icin (expectedData ve actualData ya ihtiyac duyuldugu icin) olusturduk
//
//        BookingPojo expectedData = ObjectMapperUtils.jsonToJava(strJson, BookingPojo.class);
//        System.out.println("expectedData = " + expectedData);

        System.out.println("expectedData = " + expectedData); // yukarda oldugu gibi yeniden yazdirmaktansa expectedData yi R01_ clasindan import ederek aldik. ancak bunun icin R01_ classi once calismaki expectedData null kalmasin
//______________________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
//______________________________________________________________________________________
        //Do assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getLastname(), expectedData.getLastname());
        assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid());

        assertEquals(actualData.getBookingdates().getCheckin(), expectedData.getBookingdates().getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(), expectedData.getBookingdates().getCheckout());

        assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds());


    }

}