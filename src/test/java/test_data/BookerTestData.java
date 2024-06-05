package test_data;

import java.util.HashMap;
import java.util.Map;

public class BookerTestData {

    //Bu method inner map'i oluşturmak için.
    public static Map<String, String> bookingdatesMap(String checkin, String checkout){

        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);

        return bookingdates;

    }

    //Bu method ile expected map'i oluşturuyoruz.
    public static Map<String, Object> expectedDataMap(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates, String additionalneeds){

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice",totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates",  bookingdates);
        expectedData.put("additionalneeds", additionalneeds);

        return  expectedData;

    }



}