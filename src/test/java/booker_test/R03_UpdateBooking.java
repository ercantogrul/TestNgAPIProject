package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static booker_test.R01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R03_UpdateBooking extends BookerBaseUrl {
/*
//***************UpdateBooking icin "token" isteniyor**************
    Given
        1) https://restful-booker.herokuapp.com/booking/:id
        2) {
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

 public static BookingPojo expectedUpData;  // diger classlarda kullanmak icin olusturduk
    @Test
    void nestedPojoTest() {
        //Set the url
        spec.pathParams("first", "booking", "second",bookingId);

        //Set the expected data
        String strJson = """
                        {
                            "firstname" : "James",
                            "lastname" : "Brown",
                            "totalprice" : 111,
                            "depositpaid" : true,
                            "bookingdates" : {
                                "checkin" : "2018-01-01",
                                "checkout" : "2019-01-01"
                            },
                            "additionalneeds" : "Breakfast"
                        }""";

        BookingPojo expectedData = ObjectMapperUtils.jsonToJava(strJson, BookingPojo.class);
        System.out.println("expectedData = " + expectedData);


        // ilk class ta olusturulan expectedData üzerinden setter methodlari ile degisiklik yaparak güncellenecek body olusturulabilir.
//        expectedData.setFirstname("James");
//        expectedData.setLastname("Brown");
//        expectedData.setTotalprice(111);
//        expectedData.setDepositpaid(true);
//        expectedData.getBookingdates().setCheckin("2018-01-01");
//        expectedData.getBookingdates().setCheckout("2019-01-01");
//        expectedData.setAdditionalneeds("Breakfast");
//        System.out.println("expectedData = " + expectedData);
//______________________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec)
                //.header("Cookie","token=730eb0ae5b733bc") //--spec'te belirtilecek (yani baseUrl de tanimlanacak)
                .body(expectedData)
                .put("{first}/{second}");

        response.prettyPrint();
//_______________________________________________________________________________________
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


        expectedUpData = expectedData;

    }

}