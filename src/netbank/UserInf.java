package netbank;

import java.util.Locale;
import java.util.UUID;

public class UserInf {
	
	private String username;
	private String name;
	private String address;
	private String language;
	private String country;
	private Locale location;
	private UUID ID;
	private String salt;
	private String hash;
	private Boolean isEmployee;

	public UserInf(UUID ID, String username, String name, String address, String language, String country, String salt, String hash, Boolean isEmployee) {
		this.username = username;
		this.name = name;
		this.address = address;
		this.language = language;
		this.country = country;
		this.location = buildLocation(language,country);
		this.ID = ID;
		this.salt = salt;
		this.hash = hash;
		this.isEmployee = isEmployee;
	}
	
	public String getUsername() { return username; }
	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getLanguage() { return language; }
	public String getCountry() { return country; }
	public Locale getLocale() { return location; }
	public UUID getID() { return ID; }
	public String getSalt() { return salt; }
	public String getHash() { return hash; }
	public Boolean getIsEmployee() { return isEmployee; }

	public void setName(String name) { this.name = name; }
	public void setAddress(String address) { this.address = address; }
	public void setLanguage(String language) { 
		this.language = language; 
		location = buildLocation(language,country);
	}
	public void setCountry(String country) { 
		this.country = country; 
		location = buildLocation(language,country);
	}
	public void setHash(String hash) { this.hash = hash; }
	
	public Locale buildLocation(String language, String country) {
		return new Locale.Builder().setLanguage(language).setRegion(country).build(); 
	}
}
