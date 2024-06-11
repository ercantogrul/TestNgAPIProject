package contactList_contacts;

import base_urls.ContactListBaseUrl;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactList_UsersPojo.User;
import pojos.contectList_ContactsPojo.ContactsPojo;
import pojos.contectList_ContactsPojo.ContactsResponsePojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R01_CreateContact extends ContactListBaseUrl {
     /*
    Given
        1) https://thinking-tester-contact-list.herokuapp.com/contacts
        2) {
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
            "country": "USA"
        }
    When
        User sends post request
    Then
        Status code should be 201
    And
        Response body should be like:
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
     public static ContactsResponsePojo createdContacts_actualData;  //Get user yaparken bunu expected data olarak kullanabiliriz.
    @Test
    void createContactsTest(){
        //Set the url
        spec.pathParams("first", "contacts");
//____________________________________________________________________________
        //Set the expected data
        String json = """
                {
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
                            "country": "USA"
                        }""";
        ContactsPojo expectedData = ObjectMapperUtils.jsonToJava(json, ContactsPojo.class);
        expectedData.setEmail(Faker.instance().internet().emailAddress());
        System.out.println("createContacts_expectedData = " + expectedData);
//____________________________________________________________________________
        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
//______________________________________________________________________________________
        //Do assertion
        ContactsResponsePojo actualData =  response.as(ContactsResponsePojo.class);
        assertEquals(response.statusCode(), 201);
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

        createdContacts_actualData = actualData;

    }

}
