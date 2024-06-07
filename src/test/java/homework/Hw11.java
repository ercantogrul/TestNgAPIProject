package homework;

import base_urls.AutomationexerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class Hw11 extends AutomationexerciseBaseUrl {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12

    Note: Print using JsonPath: response.jsonPath().prettyPrint();

*/

    @Test
    void homwork11Test() {

        //Set the url
        spec.pathParams("first", "api","second","productsList");

        //Set the expected data


        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        //response.prettyPrint();
        System.out.println("response.jsonPath().prettyPrint() = " + response.jsonPath().prettyPrint());

        //The value of "pagination limit" is 10
        response
                .then()
                .statusCode(200)
        ;

        JsonPath jsonPath = response.jsonPath();  //Response'ı JsonPath'e çevirdik

        List<Integer> womenListId = jsonPath.getList("products.findAll{it.category.usertype.usertype=='Women'}.id");
        System.out.println("womenListId = " + womenListId);
        assertEquals(womenListId.size(),12);

    }

}
