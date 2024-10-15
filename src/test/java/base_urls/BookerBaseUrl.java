package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static utilities.Authentication.generateToken;

public class BookerBaseUrl {

    protected RequestSpecification spec;//Her requestte yapılacak tekrarlı işlemler bir kez buraya alınarak tekrardan sakınılır.

    @BeforeMethod
    public void setSpec() {

        spec = new RequestSpecBuilder()
                .addHeader("Cookie", "token=" + generateToken())  // utilies.Authentication da  "generateToken" methodun da olusturulan token value olarak Header a buraya ekleniyor
                .setContentType(ContentType.JSON)
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();


    }


}