package netbank;

import java.util.ArrayList;
import java.util.Locale;
import java.util.List;
import java.util.UUID;

public class Person {
	
	String name;
	String address;
	Locale location;
	List<Account> accounts = new ArrayList<Account>();
	UUID id = UUID.randomUUID();
	// TODO let the server check if there is already someone with this ID. 

	public Person(String name, String address, String language, String region) {
		this.name = name;
		this.address = address;
		this.location = new Locale.Builder().setLanguage(language).setRegion(region).build();
	}
	
}
