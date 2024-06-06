package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {

    // Bu method ile json datayi java objesine ceviriyoruz
    // Object mapper kullanirken atilan exception'i burada try-catch ile handel ediyoruz.
    public static <T> T jsonToJava(String json, Class<T> tClass) {//Generic Method  (gönderilecek String data , hangi class a cevrilmesini istiyorsak o class giriyoruz )

        try {
            return new ObjectMapper().readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
