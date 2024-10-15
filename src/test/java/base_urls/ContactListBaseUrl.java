package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;


import static utilities.Authentication.contactListToken;
import static utilities.Authentication.contactListToken2;


public class ContactListBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec() {

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer " + contactListToken2())
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .build();
    }



}