package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BookerBaseUrl {

    protected RequestSpecification spec;  // Her requestte yapılacak tekrarlı işlemler bir kez buraya alınarak tekrardan sakınılır. null olarak tanimlandi

    @BeforeTest
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
    }
}
