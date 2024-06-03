package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C11_Groovy extends JsonPlaceHolderBaseUrl {
/*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
         I send GET Request to the URL
    Then
         1)Status code is 200
         2)Print all ids greater than 190 on the console
           Assert that there are 10 ids greater than 190
         3)Print all userIds whose ids are less than 5 on the console
           Assert that the number of userIds whose ids are less than 5 is 4
         4)Print all titles whose ids are greater than 195
           Assert that "quis eius est sint explicabo" is one of the titles whose id is less than 5
         5)Print id whose title is "quo adipisci enim quam ut ab"
           Assert that id is 8
*/

    //Groovy: Java tabanlı bir proglamlama dili.


    @Test
    void groovyTest(){

        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();


        //Do assertion
        assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();  //Response'ı JsonPath'e çevirdik

//        Print all ids greater than 190 on the console
        //1. Yol:
        List<Integer> ids = jsonPath.getList("id");
        //System.out.println("ids = " + ids);
        int counter = 0;
        for (int w : ids) {
            if (w < 191) {
                continue;
            }
            System.out.println(w);
            counter++;
        }

        //2. Yol:
        List<Integer> idsGreaterThan190 = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println("idsGreaterThan190 = " + idsGreaterThan190);


//        Assert that there are 10 ids greater than 190
        assertEquals(counter, 10);
        assertEquals(idsGreaterThan190.size(), 10);

        //Print all titles which ids' are greater than 190
        List<String> titleList = jsonPath.getList("title");
        for (int i = titleList.size()-counter; i < titleList.size(); i++) {
            System.out.println(titleList.get(i));
        }

        List<String> titlesWhoseIdsGreaterThan190 = jsonPath.getList("findAll{it.id>190}.title");
        System.err.println("titlesWhoseIdsGreaterThan190 = " + titlesWhoseIdsGreaterThan190);


        //Devam Edecek...



    }


}