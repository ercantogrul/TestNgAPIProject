package contactlist_user2;

import base_urls.ContactListBaseUrl;
import base_urls.ContactListBaseUrl2;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R06_DeleteUser extends ContactListBaseUrl2 {

     /*
    Given
       https://thinking-tester-contact-list.herokuapp.com/users/me
    When
        User sends delete request
    Then
        Status code should be 201
    And
        Response body should be "Created"



     */
     @Test
     void deleteUserTest(){

         //Set the url
         spec.pathParams("first", "users", "second", "me");
//_______________________________________________________________________________________
         // set the expected
         String expectedData = "Created";
//_______________________________________________________________________________________
         //send the request and get the response
         Response response = given(spec).delete("{first}/{second}");
         response.prettyPrint();
//_______________________________________________________________________________________
         // Do assertion

         assertEquals(response.statusCode(), 200);





     }


}
