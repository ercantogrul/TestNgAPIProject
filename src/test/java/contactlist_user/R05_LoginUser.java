package contactlist_user;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactListPojo.UserResponsePojo;
import utilities.ObjectMapperUtils;

import java.util.Map;
import static contactlist_user.R01_CreateUser.token;
import static contactlist_user.R03_Update.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R05_LoginUser extends ContactListBaseUrl {

    @Test
    void logoutUserTest() {
        /*
   https://thinking-tester-contact-list.herokuapp.com/users/login

   {
    "email": "test2@fake.com",
    "password": "myNewPassword"
   }

        {
  "user": {
    "_id": "608b2db1add2691791c04c89",
    "firstName": "Updated",
    "lastName": "Username",
    "email": "test2@fake.com",
    "__v": 212
  },
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MDgyMWYzMDYyZmJiMjEzZTJhZDlhMjAiLCJpYXQiOjE2MTk3MzQ0NDB9.4CftGzYRmK04PJv6xKqmWWo9iOH2wlizEU8vk-L48LI"
}
         */


        //Set the url
        spec.pathParams("first","users","second","login");
//______________________________________________________________________________________

        //Set the expected data
        String strJson ="{\n" +
                "    \"email\": \""+email+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}";

//
//        String strJson = "{\n" +
//                "    \"email\": \"" + email + "\",\n" +
//                "    \"password\": \"" + password + "\"" +
//                "}";
        Map expectedData = ObjectMapperUtils.jsonToJava(strJson,Map.class);
        System.out.println("expectedData = " + expectedData);

//______________________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}/{second}");
        response.prettyPrint();
//______________________________________________________________________________________

        //Do assertion
        assertEquals(response.statusCode(),200);
        UserResponsePojo actualData = response.as(UserResponsePojo.class);

        assertEquals(actualData.getUser().getFirstName(), actual_expectedData.getFirstName());
        assertEquals(actualData.getUser().getLastName(), actual_expectedData.getLastName());
        assertEquals(actualData.getUser().getEmail(), actual_expectedData.getEmail());

        token=actualData.getToken();







    }
}
