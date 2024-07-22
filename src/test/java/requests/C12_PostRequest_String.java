package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C12_PostRequest_String extends JsonPlaceHolderBaseUrl {

/*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
          }
    When
        I send POST Request to the Url

    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
*/

    @Test
    void postRequestTest() {
        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected data --> Payload
        //Assertion işleminde String body'den specific olarak değerler alınamayacağından bu yöntem tavsiye edilmez.
        String expectedData = """
                {
                 "userId": "55",
                 "title": "Tidy your room",
                 "completed": false
                }
                """;//Text Block --> Test classında daha düzenli kod görünümü için kullanıyorum.

        //Send the request and get the response
        Response response = given(spec)
                .contentType(ContentType.JSON)//String olan paylaod'un çevrileceği içerik tipi belirtilmeli
                .body(expectedData)//Gönderilecek data burada belitilmeli
                .post("{first}");

        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body("userId", equalTo("55"),
                        "title", equalTo("Tidy your room"),
                        "completed", equalTo(false));


    }


}