package homework;

import base_urls.PetstoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;
import pojos.PetstorePojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class Hw10 extends PetstoreBaseUrl {
    /*
    Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
    Note: Use POJO payload
    */
    //https://petstore.swagger.io/v2/user

    @Test
    void homework10Test() {
        //Set the url
        spec.pathParams("first", "v2","second", "user");


        //Set the expected data
        PetstorePojo expectedData =new PetstorePojo(0,"Ali_Veli","Ali","Veli","ali09@gmail.com","1234567","171232323",0);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}/{second}");
        response.prettyPrint();

        //Do assertion


        //2.yoll
        assertEquals(response.statusCode(), 200);


    }
}
