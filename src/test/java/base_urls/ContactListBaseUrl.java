package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;


import static utilities.Authentication.contactListToken;


public class ContactListBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec() {

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer " + contactListToken())
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .build();
    }


}