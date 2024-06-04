package test_data;

import java.util.HashMap;
import java.util.Map;

public class PetstoreTestData {

    //Bu method ile test classında map data tipinde bir payload oluşturuyoruz.
    public static Map<String, Object> expectedDataMap(Integer id, String username, String firstName, String lastName,
                                                      String email, String password, String phone,Integer userStatus) {

        Map<String, Object> expectedData = new HashMap<>();

        if (id != null) {//Eğer userId paramtre olarak null girilirse map'te bu field bulunmaz
            expectedData.put("id", id);
        }

        if (username != null) {
            expectedData.put("username", username);
        }

        if (firstName != null) {
            expectedData.put("firstName", firstName);
        }

        if (lastName != null) {
            expectedData.put("lastName", lastName);
        }

        if (email != null) {
            expectedData.put("email", email);
        }

        if (password != null) {
            expectedData.put("password", password);
        }
        if (phone != null) {
            expectedData.put("phone", phone);
        }

        if (userStatus != null) {
            expectedData.put("userStatus", userStatus);
        }

        return expectedData;
    }

}