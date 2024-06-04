package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class JsonPlaceHolderBaseUrl {

    protected RequestSpecification spec;  // Her requestte yapılacak tekrarlı işlemler bir kez buraya alınarak tekrardan sakınılır. null olarak tanimlandi

    @BeforeTest
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)  // bu kod Map olan paylaod'un çevrileceği içerik tipi icin eklendi
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .build();
    }
}
