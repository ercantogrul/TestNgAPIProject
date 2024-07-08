package contactList_contacts;

public class R04_UpdateContacts {
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

}
