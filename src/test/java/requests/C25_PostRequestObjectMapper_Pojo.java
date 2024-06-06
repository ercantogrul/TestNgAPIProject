package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C25_PostRequestObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
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
        //JsonPlaceHolderPojo default Constructor ile expectedData olusturuyor. yani herhangi bir constructor a ihtiyac duymuyor
        JsonPlaceHolderPojo expectedData = objectMapper.readValue(strjson, JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        //1.yol
        JsonPlaceHolderPojo actualData = objectMapper.readValue(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.getUserId(), expectedData.getUserId());
        assertEquals(actualData.getTitle(), expectedData.getTitle());
        assertEquals(actualData.getCompleted(), expectedData.getCompleted());


        //2.yol
        Map<String,String> actualData1 = response.as(Map.class);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.getUserId(), expectedData.getUserId());
        assertEquals(actualData.getTitle(), expectedData.getTitle());
        assertEquals(actualData.getCompleted(), expectedData.getCompleted());


    }




}