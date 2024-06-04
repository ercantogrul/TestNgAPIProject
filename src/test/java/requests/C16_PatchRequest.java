package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.expectedDataMap;

public class C16_PatchRequest extends JsonPlaceHolderBaseUrl {
    /*
       Given
           1) https://jsonplaceholder.typicode.com/todos/198
           2) {
                "title": "Study Lesson"
              }
       When
           I send PATCH Request to the Url
       Then
          Status code is 200
          And response body is like  {
                                           "userId": 10,
                                           "id": 198,
                                           "title": "Study Lesson",
                                           "completed": true
                                       }
   */
    @Test
    void patchRequestTest() {
        //Set the url
        spec.pathParams("first", "todos", "second", "198");

        //Set the expected data
        Map<String, Object> expectedData = expectedDataMap(null, "Study Lesson", null);
        System.out.println("expectedData = " + expectedData);//{title=Read Books}

        //Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(Map.class);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("title"), expectedData.get("title"));
        //Eğer tüm body assert edilecekse:
        assertEquals(actualData.get("completed"), true);
        assertEquals(actualData.get("userId"), 10);

    }
}