package requests;

import base_urls.DummyRestApiBaseUrl;
import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.DummyRestApiPojo;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class C30_GetRequestGroovy extends DummyRestApiBaseUrl {
    /*
        Given
            https://dummy.restapiexample.com/api/v1/employees
        When
            User sends Get Request to the Url
        Then
            Status code is 200
        And
            There are 24 employees
        And
           "Tiger Nixon" and "Garrett Winters" are among the employees
        And
           The greatest age is 66
        And
          The name of the lowest age is "Tatyana Fitzpatrick"
        And
           Total salary of all employees is 6,644,770
    */
    @Test
    void groovyGetTest() {

        //Set the url
        spec.pathParams("first", "employees");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        // The name of the lowest age is "Tatyana Fitzpatrick"
        List<Integer> ages = response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages);//[61, 63, 66, 22, 33, 61, 59, 55, 39, 23, 30, 22, 36, 43, 19, 66, 64, 59, 41, 35, 30, 40, 21, 23]
        Collections.sort(ages);
        System.out.println("ages = " + ages);
        int lowestAge = ages.getFirst();
        System.out.println("lowestAge = " + lowestAge);

        Object youngestEmployee =  response
                .jsonPath()
                .getList("data.findAll{it.employee_age=="+lowestAge+"}.employee_name")
                .getFirst();
        System.out.println("youngestEmployee = " + youngestEmployee);

        assertEquals(youngestEmployee,"Tatyana Fitzpatrick");

        //Derisi Ödev ...

        //Eğer body'yi Pojo class olarak almak istersek:
        DummyRestApiPojo actualData = response.as(DummyRestApiPojo.class);
        System.out.println("actualData = " + actualData);
        System.out.println(actualData.getData().get(14).getEmployee_name());


    }

}