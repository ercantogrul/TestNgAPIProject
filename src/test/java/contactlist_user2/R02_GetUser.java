package contactlist_user2;
import base_urls.ContactListBaseUrl2;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import pojos.contactList_UsersPojo.User;

import static contactlist_user2.R01_CreateUser.expectedDataUpdate;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R02_GetUser extends ContactListBaseUrl2 {
      /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/users/me
    When
        User sends get request
    Then
        Status code should be 200
    And
        Response body should be:
   {
    "user": {
        "_id": "6666f00d9ec6d9001383a5a5",
        "firstName": "John",
        "lastName": "Doe",
        "email": "mariella.funk@yahoo.com",
        "__v": 1
    },
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NjY2ZjAwZDllYzZkOTAwMTM4M2E1YTUiLCJpYXQiOjE3MTgwMjIxNTd9.D7X2wZ0UPfHRf6RzHqx0dR0t4BXRqsqlKGpI2qpjFEA"
}
     */

    @Test
    void getUserTest() {
        //Set the url
        spec.pathParams("first", "users", "second", "me");
//______________________________________________________________________________________

        //Set the expected data

        System.out.println("expectedContactData = " + expectedDataUpdate);
//______________________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
//______________________________________________________________________________________
        //Do assertion
        User actualData = response.as(User.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstName(), expectedDataUpdate.getFirstName());
        assertEquals(actualData.getLastName(), expectedDataUpdate.getLastName());
        assertEquals(actualData.getEmail(), expectedDataUpdate.getEmail());



    }




}
