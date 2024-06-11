package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static contactlist_user.R01_CreateUser.actualData;
import static contactlist_user.R01_CreateUser.expectedData;
import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken(){

        String json = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }
                """;

        Response response = given()
                .body(json)
                .contentType(ContentType.JSON)
                .post("https://restful-booker.herokuapp.com/auth");

      //  response.prettyPrint();

      //  System.out.println("response.jsonPath().getString(\"token\") = " + response.jsonPath().getString("token"));

        return response.jsonPath().getString("token");  // Json data icerisinde dönen token in key adi token, deger token in kendisi
        // response den token i alip return ediyoruz ---response.jsonPath().getString("token") = 1002bcdb2c7a352---
    }


    public static String contactListToken() {

        Map<String, String> payload = new HashMap<>();
        if(expectedData!=null){  // eger user create edilirse bu user ile token alinir
            payload.put("email", expectedData.getEmail());
            payload.put("password", expectedData.getPassword());

        }else { // User olusturulmadiysa clarusway kullanicisi ile token alinir
            payload.put("email", "clarusway_91@hotmail.com");
            payload.put("password", "Clarusway.1234");
        }

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("https://thinking-tester-contact-list.herokuapp.com/users/login");
        //response.prettyPrint();

        return response.jsonPath().getString("token");
    }

    public static String contactListToken2() { // contactList de token üretmenin ikinci yolu

        if (actualData != null) {//Eğer user create edilirse bu use'ın direkt token'ı alınabilir

            return actualData.getToken();//Bu user'ın token'ını dön

        } else {
            Map<String, String> payload = new HashMap<>();
            payload.put("email", "jasonsteel@gmail.com");
            payload.put("password", "Jason.123");

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .post("https://thinking-tester-contact-list.herokuapp.com/users/login");
            //response.prettyPrint();

            try {//Default olarak alınan alınamadığı taktirde exceptiın'ı handle etmek için kullanıyoruz.
                return response.jsonPath().getString("token");
            }catch (Exception e){
                System.out.println("Token alınamadı");
                return "Token alınamadı";
            }

        }

    }



}