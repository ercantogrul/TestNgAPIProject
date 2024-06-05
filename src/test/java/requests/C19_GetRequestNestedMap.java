package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.BookerTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C19_GetRequestNestedMap extends BookerBaseUrl {
/*
    Given
        https://restful-booker.herokuapp.com/booking/2650
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
    void getRequestMapTest() {

        //Set the url
        spec.pathParams("first", "booking", "second", "608");

        //Set the expected data
        //Assertion bölümünde hard codding yapmamak için set işlemini burada yapıyoruz.
        //Expected data ile actual data karşılaştırılacak.
        Map<String, String> bookingdates = BookerTestData.bookingdatesMap("2018-01-01", "2019-01-01");
        Map<String, Object> expectedData = BookerTestData.expectedDataMap("Jane", "Doe", 111, true, bookingdates, "Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData =  response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.get("firstname"),expectedData.get("firstname"));
        assertEquals(actualData.get("lastname"),expectedData.get("lastname"));
        assertEquals(actualData.get("totalprice"),expectedData.get("totalprice"));
        assertEquals(actualData.get("depositpaid"),expectedData.get("depositpaid"));
        assertEquals(actualData.get("bookingdates"),expectedData.get("bookingdates"));

        assertEquals(  ((Map)actualData.get("bookingdates")).get("checkin"), bookingdates.get("checkin"));
        assertEquals(  ((Map)actualData.get("bookingdates")).get("checkout"), bookingdates.get("checkout"));

        assertEquals(actualData.get("additionalneeds"),expectedData.get("additionalneeds"));



    }

}