package contactList_contacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactList_UsersPojo.User;
import pojos.contectList_ContactsPojo.ContactsResponsePojo;
import base_urls.ContactListBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contectList_ContactsPojo.ContactsResponsePojo;
import utilities.ObjectMapperUtils;

import java.util.List;
import static contactList_contacts.R01_CreateContact.createdContacts_actualData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.List;

import static contactList_contacts.R01_CreateContact.createdContacts_actualData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R03_GetContacts extends ContactListBaseUrl{
    /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/contacts/
    When
        User sends get request
    Then
        Stays code should be 200
    And
        Body should be like:
                    {
              "_id": "6085a221fcfc72405667c3d4",
              "firstName": "John",
              "lastName": "Doe",
              "birthdate": "1970-01-01",
              "email": "jdoe@fake.com",
              "phone": "8005555555",
              "street1": "1 Main St.",
              "street2": "Apartment A",
              "city": "Anytown",
              "stateProvince": "KS",
              "postalCode": "12345",
              "country": "USA",
              "owner": "6085a21efcfc72405667c3d4",
              "__v": 0
            }
     */

    @Test
    void getContactsTest() throws JsonProcessingException {
        //Set the url

        spec.pathParams("first","contacts","second","");
//______________________________________________________________________________________
        //Set the expected data --> Bir önceki class'ta oluşturukuan User objesi burada kullanılabilir.
        ContactsResponsePojo expectedData = createdContacts_actualData;

        System.out.println("expectedData = " + expectedData);
//______________________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
//______________________________________________________________________________________
        //Do assertion

        ContactsResponsePojo actualData = response.as(ContactsResponsePojo.class);
        System.out.println("actualData = " + actualData);




    }

}
