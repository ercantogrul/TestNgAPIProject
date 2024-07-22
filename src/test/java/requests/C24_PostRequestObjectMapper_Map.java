package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C24_PostRequestObjectMapper_Map extends JsonPlaceHolderBaseUrl {
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
    void objectMapperMapTest() throws JsonProcessingException {

        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected data
        //object mapper ile Json datayi map' cevir
        //1. adim: objectMapper objesi olustur
        ObjectMapper objectMapper = new ObjectMapper();

        //2. adim: readValue() Methodu ile ceviri yap: önce json datayi string bir containera al



        String strjson = """
                       {
                         "userId": 55,
                         "title": "Tidy your room",
                         "completed": false
                       }""";

        //readValue() methodunda ilk parametrede cevrilmek istenen json datanin String hali, 2. parametrede ise json'in cerilecegi class belirtilmelidir
        Map expectedData = objectMapper.readValue(strjson, Map.class);
        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        //1.yol
        Map actualData = objectMapper.readValue(response.asString(),Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("userId"), expectedData.get("userId"));
        assertEquals(actualData.get("title"), expectedData.get("title"));
        assertEquals(actualData.get("completed"), expectedData.get("completed"));

        //2.yol
        Map<String,String> actualData1 = response.as(Map.class);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData1.get("userId"), expectedData.get("userId"));
        assertEquals(actualData1.get("title"), expectedData.get("title"));
        assertEquals(actualData1.get("completed"), expectedData.get("completed"));


    }




}