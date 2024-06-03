package requests;

import base_urls.BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class C09_JsonPath_SoftAssertion extends BookerBaseUrl {
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
        spec.pathParams("first","booking","second","771");

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

    //soft assertion yapmak icin:
        //1. Adım: Soft assertion objesi olusturulur
        SoftAssert softAssert = new SoftAssert();
        //2. Adım: assertion islemi yapilir

        softAssert.assertEquals(firstname, "Josh");
        softAssert.assertEquals(lastname, "Allen");
        softAssert.assertEquals(totalprice, 111);
        softAssert.assertTrue(depositpaid);
        softAssert.assertEquals(checkin, "2018-01-01");
        softAssert.assertEquals(checkout, "2019-01-01");
        softAssert.assertEquals(additionalneeds, "super bowls");

        //3.Adim AssertAll() methodu kullanilir
        softAssert.assertAll();



    }

}