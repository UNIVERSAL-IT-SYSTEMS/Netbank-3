package netbank;

import java.util.ArrayList;
import java.util.Locale;
import java.util.List;
import java.util.UUID;

public class Person {
	
	private String name;
	private String address;
	private Locale location;
	private List<Account> accounts = new ArrayList<Account>();
	private UUID id = UUID.randomUUID();
	// TODO let the server check if there is already someone with this ID. 

	public Person(String name, String address, String language, String region) {
		this.name = name;
		this.address = address;
		this.location = new Locale.Builder().setLanguage(language).setRegion(region).build();
	}
	
	public String getName() { return name; }
	public String getAddress() { return address; }
	public Locale getLocale() { return location; }

	public void setName(String name) { this.name = name; }
	public void setAddress(String address) { this.address = address; }
	public void setLocale(Locale location) { this.location = location; }

	public void addAccount(Account e) { accounts.add(e); }
	
}
