package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C06_Matchers {
        /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        I send a GET request to the Url
    Then
        HTTP Status Code should be 200
    And
        Response format should be "application/json"
    And
        There should be 200 todos
    And
        "quis eius est sint explicabo" should be one of the todos title
    And
        2, 7, and 9 should be among the userIds
*/

    @Test
    void matchersTest(){


//    API testinde izlenecek adımlar:
//    1. Set the url
        String url = "https://jsonplaceholder.typicode.com/todos";

//    2. Set the expected data

//    3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();
//    4. Do assertion
        response
                .then()
                .statusCode(200) // HTTP Status Code should be 200
                .contentType(ContentType.JSON)  // Response format should be "application/json"
                .body("",hasSize(200)) //There should be 200 todos
                .body("title", hasItem("quis eius est sint explicabo"))  //"quis eius est sint explicabo" should be one of the todos title
                // hasItem () methodu contains() methodu gibi calisir
                .body("userId", hasItems(2,7,9))  //2, 7, and 9 should be among the userIds
               // hasItems () methodu containsAll() methodu gibi calisir
        ;


    }
}
