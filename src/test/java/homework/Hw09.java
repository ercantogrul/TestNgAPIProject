package homework;

import base_urls.PetstoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.Assert.assertTrue;

public class Hw09 extends PetstoreBaseUrl {
    /*
    Using the https://petstore.swagger.io/ document, write an automation test that finds the number of
    "pets" with the status "available" and asserts that there are more than 100.
     */

    @Test
    void homwork09(){
        //https://petstore.swagger.io/v2/pet/findByStatus?status=available

        //Set the url
        spec.pathParams("first", "v2", "second", "pet", "third", "findByStatus")
                .queryParams("status", "available");

        // Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}/{third}");
     //   response.prettyPrint();

        //finds the number of "pets" with the status "available"

        JsonPath jsonPath = response.jsonPath();

        //1.yol
        List<String> statusList = jsonPath.getList("status");
       // System.out.println("statusData = " + statusData);
        System.out.println("statusData.size() = " + statusList.size());

       assert (statusList.size()>100);

       //2.yoll
        List<String> statusList1 = jsonPath.getList("findAll{it.status == 'available'}.name");
       // System.out.println("statusList1 = " + statusList1);
        System.out.println("statusList1.size() = " + statusList1.size());
        assert (statusList.size()>100);

    }
    @Test
    void HW09Test() {

        //Set the url
        spec.pathParams("first", "v2", "second", "pet", "third", "findByStatus")
                .queryParams("status", "available");

        // Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}/{third}");
//        response.prettyPrint();


        //finds the number of "pets" with the status "available"
        List<String> statusList = response.jsonPath().getList("status");
        int count = 0;
        for (String status : statusList) {
            count++;
        }
// assert that there are more than 100.
        assertThat("Number of available pets should be greater than 100", statusList.size(), greaterThan(100));
        assert count > 100;

    }


}
