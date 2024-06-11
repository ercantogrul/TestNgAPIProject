package contactlist_user2;

import base_urls.ContactListBaseUrl2;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R04_LogoutUser extends ContactListBaseUrl2 {

    @Test
    //https://thinking-tester-contact-list.herokuapp.com/users/logout
    void logoutUserTest() {
        //Set the url
        spec.pathParams("first", "users", "second", "logout");
//______________________________________________________________________________________

        //Set the expected data


//______________________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
//______________________________________________________________________________________
        //Do assertion

        assertEquals(response.statusCode(), 200);




    }
}
