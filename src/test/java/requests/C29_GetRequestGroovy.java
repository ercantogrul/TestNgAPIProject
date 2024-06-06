package requests;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C29_GetRequestGroovy extends GorestBaseUrl {
    /*
            Given
                https://gorest.co.in/public/v1/users
            When
                User send GET Request
            Then
                The value of "pagination limit" is 10
            And
                The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
            And
                The number of users should  be 10
            And
                We have at least one "active" status
            And
                "Abhaidev Kaur", "Fr. Deenabandhu Adiga", "Akshita Singh DC" are among the users -> Bu data değişken
            And
                The female users are less than or equals to male users -> Bu data değişken
    */
    @Test
    void groovyGetTest(){
        //Set the url
        spec.pathParams("first", "users");

        //Set the expected data


       //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assert

        //The value of "pagination limit" is 10
        response
                .then()
                .statusCode(200)
                .body("meta.pagination.limit", equalTo(10))//The value of "pagination limit" is 10
                .body("meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"))
                .body("data",hasSize(10)) // The number of users should  be 10
                .body("data.status", hasItem("active")) // We have at least one "active" status
                .body("data.name", hasItems("Baalagopaal Gandhi","Rep. Daevika Guha"))  // "Abhaidev Kaur", "Fr. Deenabandhu Adiga", "Akshita Singh DC" are among the users -> Bu data değişken
                //The female users are less than or equals to male users -> Bu data değişken


        ;



    }

}