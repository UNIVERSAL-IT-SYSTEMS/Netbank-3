package netbank;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class CustomerInfTest {
	CustomerInf cust;
	String salt;
	String hash;
	UUID ID;
	
	@Before
	public void initiate() {
		salt = Hash.getSalt();
		hash = Hash.SHA512("test123", salt);
		ID = UUID.randomUUID();
		cust = new CustomerInf("test", "testaddress", "da", "dk", ID , salt, hash);
	}

	@Test
	public void newCustomer() {
		assertEquals("test",cust.getName());
		assertEquals("testaddress",cust.getAddress());
		assertEquals(ID,cust.getID());
		assertEquals(salt,cust.getSalt());
		assertEquals(hash,cust.getHash());
		assertEquals(new Locale.Builder().setLanguage("da").setRegion("dk").build(),cust.getLocale());
	}
	
	@Test
	public void ChangeInformation() {
		cust.setName("Alice");
		assertEquals("Alice",cust.getName());
		cust.setAddress("new place");
		assertEquals("new place",cust.getAddress());
		cust.setLocale(new Locale.Builder().setLanguage("en").setRegion("US").build());
		assertEquals(Locale.US,cust.getLocale());
		String hash = Hash.SHA512("test1", salt);
		cust.setHash(hash);
		assertEquals(hash,cust.getHash());
	}

}
