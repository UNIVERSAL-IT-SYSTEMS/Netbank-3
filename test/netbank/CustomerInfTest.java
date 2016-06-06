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
		String salt = Hash.getSalt();
		assertEquals(salt,cust.getSalt());
		String hash = Hash.SHA512("test1", salt);
		assertEquals(hash,cust.getHash());
	}

}
