package netbank;

import static org.junit.Assert.*;
import java.util.Locale;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

public class CustomerInfTest {
	CustomerInf cust;
	Account acc;
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
		assertEquals("da",cust.getLanguage());
		assertEquals("dk",cust.getCountry());
	}
	
	@Test
	public void ChangeInformation() {
		cust.setName("Alice");
		assertEquals("Alice",cust.getName());
		cust.setAddress("new place");
		assertEquals("new place",cust.getAddress());
		String hash = Hash.SHA512("test1", salt);
		cust.setHash(hash);
		assertEquals(hash,cust.getHash());
		cust.setLanguage("en");
		assertEquals("en",cust.getLanguage());
		assertEquals(new Locale.Builder().setLanguage("en").setRegion("dk").build(),cust.getLocale());
		cust.setCountry("us");
		assertEquals("us",cust.getCountry());
		assertEquals(new Locale.Builder().setLanguage("en").setRegion("us").build(),cust.getLocale());
	}
}
