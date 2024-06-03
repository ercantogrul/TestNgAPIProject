package requests;

import base_urls.BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.*;

public class C08_JsonPath_HardAssertion extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/32
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
        {
            "firstname": "Josh",
            "lastname": "Allen",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "super bowls"
        }
*/

    @Test
    void jsonPathTest(){

        //Set the url
        spec.pathParams("first","booking","second","212");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
//        HTTP Status Code should be 200
        int statusCode = response.statusCode();
        assertEquals(statusCode,200);

//        Response content type is "application/json"
        String contentType = response.contentType();
        assertEquals(contentType, ContentType.JSON.withCharset("utf-8"));

//*******************Body'den specific bir field'ı almak için JsonPath kullanılabilir:***************************************
        //1. Adım: Response'ı JsonPath'e çevir
        JsonPath jsonPath = response.jsonPath();

        //2. Adım: jsonPath objesinden specific bir datayı çağır:
        String firstname = jsonPath.getString("firstname");
        System.out.println("firstname = " + firstname);

        String lastname = jsonPath.getString("lastname");
        int totalprice = jsonPath.getInt("totalprice");
        boolean depositpaid = jsonPath.getBoolean("depositpaid");
        String checkin = jsonPath.getString("bookingdates.checkin");
        String checkout = jsonPath.getString("bookingdates.checkout");
        String additionalneeds = jsonPath.getString("additionalneeds");

        assertEquals(firstname, "Josh");
        assertEquals(lastname, "Allen");
        assertEquals(totalprice, 111);
        assertTrue(depositpaid);
        assertEquals(checkin, "2018-01-01");
        assertEquals(checkout, "2019-01-01");
        assertEquals(additionalneeds, "super bowls");



    }

}