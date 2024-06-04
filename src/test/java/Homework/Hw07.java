package Homework;

import base_urls.RegresBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Hw07 extends RegresBaseUrl {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */

    @Test
    void homwork07Test() {
        //Set the url
        spec.pathParams("first", "api", "second", "users");

        //Set the expected data
        System.out.println("**********//Set the expected data************");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("name", "morpheus");
        expectedData.put("job", "leader");

        System.out.println("expectedData = " + expectedData);

        //I Send the request and get the response
        System.out.println("**********//I Send the request and get the response************");
        Response response= given(spec).body(expectedData).post("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        System.out.println("**********actualData************");
        Map<String,Object> actualData = response.as(Map.class);//De-serialization
        System.out.println("actualData = " + actualData);

 //        Status code is 201
 //        And response body should be like
        assertEquals(response.statusCode(),201);
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));






    }
}
