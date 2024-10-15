package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingResponsePojo;
import pojos.JsonPlaceHolderPojo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utilities.ObjectMapperUtils.jsonToJava;

public class C28_PojoList extends JsonPlaceHolderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        I send a GET request to the Url
    And
        Accept type is "application/json"
    Then
        HTTP Status Code should be 200
    And
        There must be a todo like:
            {
                "userId": 1,
                "id": 4,
                "title": "et porro tempora",
                "completed": true
            }
*/


    @Test
    void pojoListTest() throws JsonProcessingException {

        //Set the url
        spec.pathParams("first", "todos");


        //Set the expected data
        String strJson = """
                            {
                                "userId": 1,
                                "id": 4,
                                "title": "et porro tempora",
                                "completed": true
                            }""";


        JsonPlaceHolderPojo expectedData = jsonToJava(strJson, JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}");
       // response.prettyPrint();

        //Burada bir list icinde dönen json datalari list icerisine pojo class objeleri seklinde atiyoruz
        List<JsonPlaceHolderPojo> toDolist = new ObjectMapper().readValue(response.asString(), new TypeReference<>() {});

        // System.out.println("toDolist = " + toDolist);
        //System.out.println("toDolist.getLast() = " + toDolist.getLast());

        int idx=0;
        for (JsonPlaceHolderPojo w: toDolist) {

            if (w.getTitle().equals("et porro tempora")){  // Bu title' a esit title a sahip elemanin indexi buluyoruz
                break;
            }
            idx++;
        }

        JsonPlaceHolderPojo actualData = toDolist.get(idx);
        System.out.println("actualData = " + actualData);

        //Do assertion
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getUserId(), expectedData.getUserId());
        assertEquals(actualData.getTitle(),expectedData.getTitle());
        assertEquals(actualData.getCompleted(),expectedData.getCompleted());


    }
}
