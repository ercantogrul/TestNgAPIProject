package requests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class C05_QueryParameters {
    @Test
    void queryParametersTest(){

//    API testinde izlenecek adımlar:
//    1. Set the url
        String url = "https://restful-booker.herokuapp.com/booking?firstname=Jane&lastname=Doe";


//    2. Set the expected data

//    3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

//    4. Do assertion
        //1.yoll
        response
                .then()
                .statusCode(200)
                .body("bookingid",Matchers.hasSize(greaterThan(0))); // Tavsive edilen yontem

       //2.yoll
        assert  response.asString().contains("bookingid");
        assert  response.asString().contains("bookingid"):"Body does not contain bookingid";  // burada not eklendi. yukardaki cod ile ayni
        // string yazi herhangi bir yerde olabilir buda dogruymus gibi algilanir. java assertion
        // :"Body does not contain bookingid" notu da eklene bilir
        System.out.printf("===========================================================\n");

        String url1 = "https://restful-booker.herokuapp.com/booking/11";
        Response response1 = given().get(url1);

        JsonPath jsonPath = response1.jsonPath();
        String date = jsonPath.getString("bookingdates.checkin");
        System.out.println("date = " + date);
        given().get(url1).then().header("Connection",equalTo("keep-alive"));

    }
}
