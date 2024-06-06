package requests;

import base_urls.BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class C17_PostRequestMastedMap extends BookerBaseUrl {
/*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
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
    void nestedMapTest() {

        //Set the url
        spec.pathParams("first", "booking");

        //Set the expected data
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2023-03-07");
        bookingdates.put("checkout", "2024-09-25");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "John");
        expectedData.put("lastname", "Doe");
        expectedData.put("totalprice", 471);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);
        expectedData.put("additionalneeds", "Lunch");

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(Map.class);  // veriler bir Object conteinera eklenerek verildi
        System.out.println("actualData = " + actualData);

        //1.yoll
        response.then().statusCode(200).body("booking.firstname",equalTo(expectedData.get("firstname")));  // Bu yöntem daha kolay
        response.then().body("booking.bookingdates.checkin",equalTo(bookingdates.get("checkin")));

        //2.yoll
        assertEquals(response.statusCode(), 200);
        // Tüm key'ler String, Value'lar Object container'i icerisinde bulundugundan dönen value'lari kendi methodlari ile kullanmak icin type casting yapilir
        assertEquals(((Map) actualData.get("booking")).get("firstname"), expectedData.get("firstname"));
        assertEquals(((Map) actualData.get("booking")).get("lastname"), expectedData.get("lastname"));
        assertEquals(((Map) actualData.get("booking")).get("totalprice"), expectedData.get("totalprice"));
        assertEquals(((Map) actualData.get("booking")).get("depositpaid"), expectedData.get("depositpaid"));

        assertEquals(    ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkin")    , bookingdates.get("checkin"));
        assertEquals(    ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkout")    , bookingdates.get("checkout"));

        assertEquals(((Map) actualData.get("booking")).get("additionalneeds"), expectedData.get("additionalneeds"));


    }

}