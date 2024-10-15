package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C10_JsonPathList extends JsonPlaceHolderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
         I send GET Request to the URL
    Then
         1)Status code is 200
         2)Print all ids greater than 190 on the console
           Assert that there are 10 ids greater than 190
*/


    @Test
    void jsonPathListTest() {

        //Set the url
        spec.pathParams("first", "todos");  // url ye "/todos" u ekliyor

        //Set the expected data

  //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();
        //  Response response = given(spec).get("/todos");  // bu yukardaki iki kodun yerine kullanabiliriz
        // response.prettyPrint();

   //Do assertion
        assertEquals(response.statusCode(), 200);

//        Print all ids greater than 190 on the console
        List<Integer> ids = response.jsonPath().getList("id");
        //System.out.println("ids = " + ids);
        int counter = 0;
        for (int w : ids) {
            if (w < 191) {
                continue;
            }
            System.out.println(w);
            counter++;
        }

//        Assert that there are 10 ids greater than 190
        assertEquals(counter, 10);

        //Print all titles which ids' are greater than 190
        List<String> titleList = response.jsonPath().getList("title");
        for (int i = titleList.size()-counter; i < titleList.size(); i++) {
            System.out.println(titleList.get(i));
        }
    }
}

//List<Objects> list = jsonPath.getList("id");
//List<String> list = jsonPath.getList("title");
//System.out.println("list = " + list.getFirst().substring(0,5));