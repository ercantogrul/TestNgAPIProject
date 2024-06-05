package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // bu annoation ile bu classta olmayan fieldlar göz ardi edilir

public class JsonPlaceHolderPojo {

    // Pojo Class adimlari
    //1.adim: Private Variables

    private Integer userId;
    private String title;
    private  Boolean completed;

    //2.adim: Parametreli ve Parametresiz Constructor'lar


    public JsonPlaceHolderPojo() {  // De-serialization islemleri icin default constructor'a ihtiyac duyariz
        // bir java classinda herhangi bir constructor olusturuldugunda default constructor kayboldugundan olusturma geregi hissederiz
    }

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) { // Bu constructor ile olusturulan objede tüm field'lar atanmalidir
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    // 3. adim: Getter ve Setter methodlari:


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //4. adim: toString Methodu --> bu classtan olusan objeyi direk yazdirmak icin
    @Override
    public String toString() {
        return "jsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }


}
