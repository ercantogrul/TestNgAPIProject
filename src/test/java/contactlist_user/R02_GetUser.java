package contactlist_user;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactList_UsersPojo.User;

import static contactlist_user.R01_CreateUser.createdUser;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R02_GetUser extends ContactListBaseUrl {
    /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/users/me
    When
        User sends get request
    Then
        Stays code should be 200
    And
        Body should be like:
        {
          "_id": "6667fab40842020013c0b460",
          "firstName": "John",
          "lastName": "Doe",
          "email": "dave.gottlieb@hotmail.com",
          "__v": 1
        }
     */

    @Test
    void getUserTest(){

        //Set the url
        spec.pathParams("first","users","second","me");
//______________________________________________________________________________________
        //Set the expected data --> Bir önceki class'ta oluşturukuan User objesi burada kullanılabilir.
        User expectedData = createdUser;
        System.out.println("expectedData = " + expectedData);
//______________________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
//______________________________________________________________________________________
        //Do assertion
        User actualData = response.as(User.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstName(), expectedData.getFirstName());
        assertEquals(actualData.getLastName(), expectedData.getLastName());
        assertEquals(actualData.getEmail(), expectedData.getEmail());

    }

}