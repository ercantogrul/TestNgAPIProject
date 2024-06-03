package requests;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class C01_RequestResponse {
    public static void main(String[] args) {

        // ilgili Url ye get reguest gönder:
        // Response variablesine aktardik
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/11");

        //System.out.println("response = " + response.toString()); // string formatinda yazdirir
        response.prettyPrint();  // Json formatinda yazdirir

        //Status Code yazdir
        int statusCode =response.statusCode();
        System.out.println("statusCode = " + statusCode);

        //Content Type yazdir
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

        //Statusline'i yazdir
        String statusLine = response.statusLine();
        System.out.println("statusLine = " + statusLine);

        //Header degerlerini bireysel olarak yazdir
        String server = response.getHeader("Server");
        System.out.println("server_Header = " + server);
        String date = response.getHeader("Date");
        System.out.println("date_Header = " + date);

         // tüm Header lari yazdir
        Headers headers = response.getHeaders();
        System.out.println("\nheaders = \n" + headers);
        headers.size();

        System.out.println("headers.getList(\"Server\") = " + headers.getList("Server"));
    }
}
