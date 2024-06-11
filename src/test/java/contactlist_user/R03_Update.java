package contactlist_user;

import base_urls.ContactListBaseUrl;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import pojos.contactList_UsersPojo.User;
import pojos.contactList_UsersPojo.UserPojo;
import utilities.ObjectMapperUtils;

import static contactlist_user.R01_CreateUser.expectedData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R03_Update extends ContactListBaseUrl {
    public static String email;
    public static String password;
    public static User actual_expectedData;

     /*
    Given
      1-  https://thinking-tester-contact-list.herokuapp.com/users/me
      2- {
                    "firstName": "Ali",
                    "lastName": "Veli",
                    "email": "abc@gmail.com",
                    "password": "1234567"
                }
    When
        User sends patch request
    Then
        Status code should be 200
    And
      Response body shoul be like:{
                                      "_id": "608b2db1add2691791c04c89",
                                      "firstName": "Ali",
                                      "lastName": "Veli",
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


        expectedData = ObjectMapperUtils.jsonToJava(strJson, UserPojo.class);
        // R01_CreateUser.expectedData yi kullanarak token üretmistik
        // Burada update ettigimiz email ve password bilgilerini yine R01_CreateUser.expectedData assaine yapiyoruz ki token i yeni güncel email ve passwort ile aliyoruz.
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
