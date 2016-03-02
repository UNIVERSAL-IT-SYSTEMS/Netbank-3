
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Person {
	
	private String name;
	private String address;
	private List<Account> accounts = new ArrayList<Account>();
	
	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public void newAccount() {
		accounts.add(new Account(null, address, null, null, address));
	}
	
}
