package homework;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.util.Map;

import static booker_test.R01_CreateBooking.bookingId;
import static booker_test.R03_UpdateBooking.expectedUpData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Hw12 extends BookerBaseUrl {
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
        User send patch Request to the Url
    Then
        Status code should be 200
    And
        Response body should be like {
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


    @Test (priority = 1)
    void homework12_PartialUpdateBookingTest() {
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);
//_______________________________________________________________________________________
        //Set the expected data
        //Patch request de tüm body gönderilmeyecegi icin Map kullanmak önerilir
        String strJson = """
                {
                    "firstname" : "James",
                    "lastname" : "Brown"
                }"""; // bu Stringi en altta asserttion islemleri icin (expectedData ve actualData ya ihtiyac duyuldugu icin) olusturduk

        Map expectedData = ObjectMapperUtils.jsonToJava(strJson, Map.class);
        System.out.println("expectedData = " + expectedData);
//_______________________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();
//_______________________________________________________________________________________
        //Do assertion
        Map actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        //Ne gönderdiysek onu assert etmek ilk görevimizdir:
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        assertEquals(actualData.get("lastname"), expectedData.get("lastname"));

        //Eğer tüm body'nin assert edilmesi isteniyorsa: Hard codding ile assert edilebilir fakat tavsiye edilmez
        assertEquals(actualData.get("totalprice"), 111);
        assertEquals(actualData.get("depositpaid"), true);
        assertEquals(    ((Map)actualData.get("bookingdates")).get("checkin"),  "2018-01-01" );
        assertEquals(    ((Map)actualData.get("bookingdates")).get("checkout"),  "2019-01-01");
        assertEquals(actualData.get("additionalneeds"),"Breakfast");

        // Bir önceki classta olusturulan expectedUpData data cagrilabilinir
        assertEquals(actualData.get("totalprice"), expectedUpData.getTotalprice());
        assertEquals(actualData.get("depositpaid"), expectedUpData.getDepositpaid());
        assertEquals(  ((Map)actualData.get("bookingdates")).get("checkin"),  expectedUpData.getBookingdates().getCheckin() );
        assertEquals(  ((Map)actualData.get("bookingdates")).get("checkout"),  expectedUpData.getBookingdates().getCheckout() );
        assertEquals(actualData.get("additionalneeds"),expectedUpData.getAdditionalneeds());

    }

    @Test (priority = 2)
    void homework12_DeleteBookingTest() {

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
