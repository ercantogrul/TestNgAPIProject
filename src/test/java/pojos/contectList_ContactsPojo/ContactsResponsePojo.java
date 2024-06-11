package pojos.contectList_ContactsPojo;

public class ContactsResponsePojo{
	private String owner;
	private String lastName;
	private String country;
	private String birthdate;
	private String city;
	private String postalCode;
	private String stateProvince;
	private String firstName;
	private String phone;
	private Integer __v;
	private String street1;
	private String _id;
	private String street2;
	private String email;

	public void setOwner(String owner){
		this.owner = owner;
	}

	public String getOwner(){
		return owner;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setBirthdate(String birthdate){
		this.birthdate = birthdate;
	}

	public String getBirthdate(){
		return birthdate;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}

	public void setStateProvince(String stateProvince){
		this.stateProvince = stateProvince;
	}

	public String getStateProvince(){
		return stateProvince;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void set__v(Integer __v){
		this.__v = __v;
	}

	public Integer get__v(){
		return __v;
	}

	public void setStreet1(String street1){
		this.street1 = street1;
	}

	public String getStreet1(){
		return street1;
	}

	public void set_id(String _id){
		this._id = _id;
	}

	public String get_id(){
		return _id;
	}

	public void setStreet2(String street2){
		this.street2 = street2;
	}

	public String getStreet2(){
		return street2;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"ContactsResponsePojo{" + 
			"owner = '" + owner + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",country = '" + country + '\'' + 
			",birthdate = '" + birthdate + '\'' + 
			",city = '" + city + '\'' + 
			",postalCode = '" + postalCode + '\'' + 
			",stateProvince = '" + stateProvince + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",phone = '" + phone + '\'' + 
			",__v = '" + __v + '\'' +
			",street1 = '" + street1 + '\'' + 
			",_id = '" + _id + '\'' +
			",street2 = '" + street2 + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
