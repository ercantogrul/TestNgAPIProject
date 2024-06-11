package contactList_contacts;

import base_urls.ContactListBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contectList_ContactsPojo.ContactsResponsePojo;

import java.util.List;
import static contactList_contacts.R01_CreateContact.createdContacts_actualData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R02_GetContects extends ContactListBaseUrl {
/*
    Given
        https://thinking-tester-contact-list.herokuapp.com/contacts
    When
        User sends get request
    Then
        Stays code should be 200
    And
        Body should be like:
        [
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
              },
              {
                "_id": "607b29861ba4d3a0b96733bc",
                "firstName": "Jan",
                "lastName": "Brady",
                "birthdate": "2001-11-11",
                "email": "fake2@gmail.com",
                "phone": "8008675309",
                "street1": "100 Elm St.",
                "city": "Springfield",
                "stateProvince": "NE",
                "postalCode": "23456",
                "country": "United States",
                "owner": "6085a21efcfc72405667c3d4",
                "__v": 0
              }
        ]
     */

    @Test
    void getContactsTest() throws JsonProcessingException {

        //Set the url
        spec.pathParams("first","contacts");
//______________________________________________________________________________________
        //Set the expected data --> Bir önceki class'ta oluşturukuan User objesi burada kullanılabilir.
        ContactsResponsePojo expectedData = createdContacts_actualData;

        System.out.println("expectedData = " + expectedData);
//______________________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();
//______________________________________________________________________________________
        //Do assertion

        List<ContactsResponsePojo> toDoList =new ObjectMapper().readValue(response.asString(), new TypeReference<>() {});
        ContactsResponsePojo actualData = new ContactsResponsePojo();

        for (ContactsResponsePojo w : toDoList) {
            if (w.get_id().equals(createdContacts_actualData.get_id())){
                actualData = w;
                break;
            }
        }
        System.out.println("getContacts_actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstName(),expectedData.getFirstName());
        assertEquals(actualData.getLastName(),expectedData.getLastName());
        assertEquals(actualData.getCountry(),expectedData.getCountry());
        assertEquals(actualData.getBirthdate(),expectedData.getBirthdate());
        assertEquals(actualData.getPhone(),expectedData.getPhone());
        assertEquals(actualData.getCity(),expectedData.getCity());
        assertEquals(actualData.getPostalCode(),expectedData.getPostalCode());
        assertEquals(actualData.getStateProvince(),expectedData.getStateProvince());
        assertEquals(actualData.getStreet1(),expectedData.getStreet1());
        assertEquals(actualData.getStreet2(),expectedData.getStreet2());
        assertEquals(actualData.getEmail(),expectedData.getEmail());

    }


}
