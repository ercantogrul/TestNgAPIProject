package homework;

import base_urls.PetstoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.PetstoreTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Hw08 extends PetstoreBaseUrl {
    /*
Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
*/
    @Test
    void HW08Test(){
        // Set the Url
        //https://petstore.swagger.io/v2/user
        spec.pathParams("first","v2","second","user");

        //Create expected Data
        Map<String, Object> expectedData = PetstoreTestData.expectedDataMap(0,"Ali_Veli","Ali",
                "Veli","ali_veli@gmail.com","1234567","171232323",0);

        //Send the Data and get hte Response

        Response response = given(spec).body(expectedData).post("{first}/{second}");
        response.prettyPrint();
        Map<String,Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        //Do the assertion
        assertEquals(response.statusCode(),200);
    }
    @Test
    void homwork08Test() {
        //Set the url"https://petstore.swagger.io/v2/user/createWithList"
        //https://petstore.swagger.io/v2/user
        spec.pathParams("first", "v2","second","user","third","createWithList");

        //Set the expected data
        System.out.println("**********//Set the expected data************");
        Map<String, Object> expectedData = PetstoreTestData.expectedDataMap(0,"Ali_Veli","Ali",
                "Veli","ali_veli@gmail.com","1234567","171232323",0);

        System.out.println("expectedData = " + expectedData+"\n");
        System.out.println("_____________");
        System.out.println("["+expectedData+"]");

        //Send the request and get the response
        System.out.println("**************//Send the request and get the response********************\n");
        Response response = given(spec)
                .body("["+expectedData+"]")
                .post("{first}/{second}/{third}");
        response.prettyPrint();


        //Do assertion
        Map<String,Object> actualData = response.as(Map.class);  //De-seriallization
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get(""), expectedData.get(""));
        assertEquals(actualData.get("type"), expectedData.get("type"));
        assertEquals(actualData.get("message"), expectedData.get("message"));



    }
    @Test
    void homwork08Test2() {
        //Set the url"https://petstore.swagger.io/v2/user/createWithList"

        spec.pathParams("first", "v2","second","user","third","createWithList");

        //Set the expected data
        String data = """
                [
                  {
                    "id": 0,
                    "username": "Ali_Veli",
                    "firstName": "Ali",
                    "lastName": "Veli",
                    "email": "ali_veli@gmail.com",
                    "password": "1234567",
                    "phone": "17123232",
                    "userStatus": 0
                  }
                ]""";

        System.out.println("**********//Set the expected data************");

        //Send the request and get the response
        System.out.println("**************//Send the request and get the response********************\n");
        Response response = given(spec)
                .body(data)
                .post("{first}/{second}/{third}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200);

    }

}


