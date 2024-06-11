package contactlist_user2;

import base_urls.ContactListBaseUrl;

import base_urls.ContactListBaseUrl2;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import pojos.contactListPojo.User;
import pojos.contactListPojo.UserPojo;
import utilities.ObjectMapperUtils;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R03_Update extends ContactListBaseUrl2 {
    public static String email;
    public static String password;
    public static User actual_expectedData;

     /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/users/me
        'Authorization: Bearer {{token}}'
    When
        User sends patch request
                        '{
                            "firstName": "Updated",
                            "lastName": "Username",
                            "email": "test2@fake.com",
                            "password": "myNewPassword"
                        }'
    Then
        Status code should be 200
    And
      Response body shoul be like:
                                    {
                                      "_id": "608b2db1add2691791c04c89",
                                      "firstName": "Updated",
                                      "lastName": "Username",
                                      "email": "test2@fake.com",
                                      "__v": 1
                                    }
     */

    @Test
    void updateUserTest(){


        // set the Url
        spec.pathParams("first","users","second","me");
//______________________________________________________________________________________
        // Set the expected Data;
        String strJson= """
                {
                    "firstName": "Ali",
                    "lastName": "Veli",
                    "email": "abc@gmail.com",
                    "password": "1234567"
                }""";

        UserPojo expectedData = ObjectMapperUtils.jsonToJava(strJson, UserPojo.class);
        expectedData.setEmail(Faker.instance().internet().emailAddress());
        System.out.println("expectedData = " + expectedData);
//______________________________________________________________________________________
        // Send the Request and GET the Response

        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

//______________________________________________________________________________________
        // Do Assertion
        User actualData = response.as(User.class);
        System.out.println("actualData = " + actualData);


        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getFirstName(),expectedData.getFirstName());
        assertEquals(actualData.getLastName(),expectedData.getLastName());
        assertEquals(actualData.getEmail(),expectedData.getEmail());

        actual_expectedData =actualData;
        email=actualData.getEmail();
        password=expectedData.getPassword();

    }
}
