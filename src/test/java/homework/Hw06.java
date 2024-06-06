package homework;

import base_urls.RegresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Hw06 extends RegresBaseUrl {
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
//2.yol
        List<String> uctenBuyukIDler = jsonPath.getList("data.findAll{it.id > 3}.id");
        System.out.println("uctenBuyukIDler = " + uctenBuyukIDler);

        List<String> IDsi3TenBuyukIsimleri = jsonPath.getList("data.findAll{it.id > 3}.name");
        System.out.println("IDsi3TenBuyukIsimleri = " + IDsi3TenBuyukIsimleri);

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

        //2.yol
        List<String> IDsi3TenKucukIsimleri = jsonPath.getList("data.findAll{it.id < 3}.name");
        System.out.println("IDsi3TenKucukIsimleri = " + IDsi3TenKucukIsimleri);


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
    @Test
    void testYol2() {
        // Set the url
        spec.pathParams("first", "api", "second", "unknown");

        //User send a GET request to the URL
        Response response = given(spec).get("{first}/{second}");


        // Do Asertion
        // HTTP Status Code should be 200
        System.out.println("*********HTTP Status Code should be 200**********");
        response
                .then()
                .statusCode(200);//HTTP Status Code should be 200
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        List<String> dataList = jsonPath.getList("data.pantone_value");

        System.out.println("**********Print all pantone_value values***********");
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println("pantone_value "+ i+" = " + dataList.get(i));
        }

        System.out.println("**********Print all ids greater than 3 on the console***********");

        List<String> uctenBuyukIDler = jsonPath.getList("data.findAll{it.id > 3}.id");
        System.out.println("uctenBuyukIDler = " + uctenBuyukIDler);

        System.out.println("**********3'ten büyük 3 adet id olduğunu doğrulayınız***********");
        assertEquals(3, uctenBuyukIDler.size());

        System.out.println("**********ID si ücten büyük olanlar***********");
        List<String> IDsi3TenBuyukler = jsonPath.getList("data.findAll{it.id > 3}.name");
        System.out.println("IDsi3TenBuyukler = " + IDsi3TenBuyukler);

        System.out.println("**********ID si ücten kucuk 2 kisi old. dogrula***********");
        List<String> IDsi3TenKucukler = jsonPath.getList("data.findAll{it.id < 3}.name");
        assertEquals(2, IDsi3TenKucukler.size());
        System.out.println("IDsi3TenKucukler = " + IDsi3TenKucukler);
    }

    @Test
    void testYol3() {
        // Set the url
        spec.pathParams("first", "api", "second", "unknown");
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
        //1)Status code is 200
        assertEquals(response.statusCode(), 200);

//        2)Print all pantone_values
//                (Tüm pantone_value değerlerini yazdırınız)
        JsonPath jsonPath = response.jsonPath();
        List<String> pantone_valueList = jsonPath.getList("data.pantone_value");

        for (String pantone_value : pantone_valueList) {
            System.out.println("pantone_value = " + pantone_value);
        }

//        3)Print all ids greater than 3 on the console
//                (3'ten büyük id'leri yazdırınız)
//        Assert that there are 3 ids greater than 3
//        (3'ten büyük 3 adet id olduğunu doğrulayınız)
        List<Integer> ids = jsonPath.getList("data.id");
        System.out.println("ids = " + ids);
        int count = 0;
        for (int element : ids) {
            if (element > 3) {
                count++;
                System.out.println(element);
            }
        }
        assertEquals(count, 3);
//        4)Print all names whose ids are less than 3 on the console
//                (id'si 3'ten küçük isimleri yazdırınız)
//        Assert that the number of names whose ids are less than 3 is 2
//        (id'si 3'ten küçük 2 isim olduğunu doğrulayınız)
        count = 0;
        List<String> names = jsonPath.getList("data.name");
        for (int i = 0; i < ids.size() ; i++) {
            if (ids.get(i) < 3) {
                count++;
                System.out.println("uctenKucuk = " + names.get(i));
            }
        }
        assertEquals(count, 2);
    }
}
