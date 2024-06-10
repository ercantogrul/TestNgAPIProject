package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

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


    public static void main(String[] args) {  // main methodu yukardaki token alan methodu denemek icin olusturduk
      //  generateToken();
    }

}