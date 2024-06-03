package Homework;

import base_urls.Hw05BaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Hw06 extends Hw05BaseUrl {
    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
              (Tüm pantone_value değerlerini yazdırınız)
            3)Print all ids greater than 3 on the console
              (3'ten büyük id'leri yazdırınız)
              Assert that there are 3 ids greater than 3
              (3'ten büyük 3 adet id olduğunu doğrulayınız)
            4)Print all names whose ids are less than 3 on the console
              (id'si 3'ten küçük isimleri yazdırınız)
              Assert that the number of names whose ids are less than 3 is 2
              (id'si 3'ten küçük 2 isim olduğunu doğrulayınız)
    */

    @Test
    void homework06Test()  {

        SoftAssert softAssert = new SoftAssert();
        //Set the url
        spec.pathParams("first","api","second","unknown");

     //I send GET Request to the URL
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

    //1)Status code is 200
        softAssert.assertEquals(response.statusCode(),200);


    System.out.println("*********2)Print all pantone_values**************");
    //2)Print all pantone_values
        // (Tüm pantone_value değerlerini yazdırınız)

        JsonPath jsonPath = response.jsonPath();
        List<Integer> data = jsonPath.getList("data.id");

        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));

        }
        //2.yol
        List<Integer> data1 = jsonPath.getList("data.id");
        System.out.println("data1 = " + data1);

    System.out.println("*********3)Print all ids greater than 3 on the console**************");
    // 3)Print all ids greater than 3 on the console
        //(3'ten büyük id'leri yazdırınız)
        for (int w : data) {
            if (w >3) {
                System.out.println(w);
            }
        }


        System.out.println("*********Assert that there are 3 ids greater than 3**************");
        //  Assert that there are 3 ids greater than 3
        //  (3'ten büyük 3 adet id olduğunu doğrulayınız)
        int counter =0;
        for (int w : data) {
            if (w >3) {
                System.out.println(w);
                counter++;
            }
        }
        softAssert.assertEquals(counter,3);


        System.out.println("********* 4)Print all names whose ids are less than 3 on the console **************");
//        4)Print all names whose ids are less than 3 on the console
//                (id'si 3'ten küçük isimleri yazdırınız)
        List<String> names = jsonPath.getList("data.name");
        System.out.println("names.size() = " + names.size());
        for (int i = 0; i < names.size()-(counter+1); i++) {
            System.out.println("names.get("+(i+1)+") = " + names.get(i));
        }


//        Assert that the number of names whose ids are less than 3 is 2
//        (id'si 3'ten küçük 2 isim olduğunu doğrulayınız)
        counter=0;
        for (int w:data) {
            if (w<3){
                counter++;
            }
        }
        System.out.println("id'si 3'ten küçük names counter :"+ counter);
        softAssert.assertEquals(counter,2);



    }
}
