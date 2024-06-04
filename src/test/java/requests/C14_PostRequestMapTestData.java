package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class C14_PostRequestMapTestData extends JsonPlaceHolderBaseUrl {

/*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
          }
    When
        I send POST Request to the Url

    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
*/

    @Test
    void postRequestTest() {
        //Set the url
        spec.pathParams("first","todos");

        //Set the expected data --> Payload



        Map<String, Object> expectedData = JsonPlaceHolderTestData.expectedDataMap(55,"Tidy your room",false);

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec)
                .body(expectedData)
                .post("{first}");

        response.prettyPrint();

        //Do assertion

        Map<String,Object> actualData = response.as(Map.class);  //De-seriallization
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("usetId"), expectedData.get("usetId"));
        assertEquals(actualData.get("title"), expectedData.get("title"));
        assertEquals(actualData.get("completed"), expectedData.get("completed"));

    }


}