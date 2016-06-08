package netbank;

import java.util.Locale;
import java.util.UUID;

public class EmployeeInf {

	private String name;
	private String address;
	private String language;
	private String country;
	private Locale location;
	private UUID ID;
	private String salt;
	private String hash;
	
	public EmployeeInf(UUID ID, String name, String address, String language, String country, String salt, String hash) {
		this.name = name;
		this.address = address;
		this.language = language;
		this.country = country;
		this.location = new Locale.Builder().setLanguage(language).setRegion(country).build();
		this.ID = ID;
		this.salt = salt;
		this.hash = hash;
	}
	
	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getLanguage() { return language; }
	public String getCountry() { return country; }
	public Locale getLocale() { return location; }
	public UUID getID() { return ID; }
	public String getSalt() { return salt; }
	public String getHash() { return hash; }

	public void setName(String name) { this.name = name; }
	public void setAddress(String address) { this.address = address; }
	public void setLanguage(String language) { 
		this.language = language; 
		location = new Locale.Builder().setLanguage(language).setRegion(country).build(); 
	}
	public void setCountry(String country) { 
		this.country = country; 
		location = new Locale.Builder().setLanguage(language).setRegion(country).build(); 
	}
	public void setHash(String hash) { this.hash = hash; }
	
}
