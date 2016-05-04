package netbank;

import java.util.Locale;
import java.util.UUID;

public class CustomerInf {
	
	private String name;
	private String address;
	private Locale location;
	private UUID ID;
	private String salt;
	private String hash;

	public CustomerInf(String name, String address, String language, String country) {
		this.name = name;
		this.address = address;
		this.location = new Locale.Builder().setLanguage(language).setRegion(country).build();
		ID = UUID.randomUUID();
	}
	
	public String getName() { return name; }
	public String getAddress() { return address; }
	public Locale getLocale() { return location; }
	public UUID getID() { return ID; }
	public String getSalt() { return salt; }
	public String getHash() { return hash; }

	public void setName(String name) { this.name = name; }
	public void setAddress(String address) { this.address = address; }
	public void setLocale(Locale location) { this.location = location; }
	public void setHash(String hash) { this.hash = hash; }
	
}
