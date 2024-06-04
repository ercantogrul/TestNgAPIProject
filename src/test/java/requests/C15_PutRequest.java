package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.expectedDataMap;

public class C15_PutRequest extends JsonPlaceHolderBaseUrl {
 /*
    Given
        1) https://jsonplaceholder.typicode.com/todos/198
        2) {
             "userId": 21,
             "title": "Read Books",
             "completed": false
           }
    When
        I send PUT Request to the Url
    Then
       Status code is 200
       And response body is like  {
                                    "completed": false,
                                    "title": "Read Books",
                                    "userId": 21,
                                    "id": 198
                                }
*/

    @Test
    void putRequestTest() {

        //Set the url
        spec.pathParams("first", "todos", "second", "198");

        //Set the expected data
        Map<String, Object> expectedData = expectedDataMap(21, "Read Books", false);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).put("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("completed"), expectedData.get("completed"));
        assertEquals(actualData.get("title"), expectedData.get("title"));
        assertEquals(actualData.get("userId"), expectedData.get("userId"));


    }
}