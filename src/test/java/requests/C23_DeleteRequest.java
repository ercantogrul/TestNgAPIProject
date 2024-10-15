package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C23_DeleteRequest extends JsonPlaceHolderBaseUrl {
    /*
    Given
      https://jsonplaceholder.typicode.com/todos/198
    When
      I send DELETE Request to the Url
    Then
      Status code is 200
    And
      Response body is { }
*/


    @Test
    void deleteRequestTest(){

        //Set the url
        spec.pathParams("first", "todos","second","198");

        //Set the expected data



        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,String> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assert actualData.isEmpty();
       // assertEquals(actualData.size(),0); // veya actualData Map inin eleman sayisinin 0 oldununu da assert yapilabilinir



    }


}
