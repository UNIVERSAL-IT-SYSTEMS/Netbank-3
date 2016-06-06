package netbank;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class CustomerInfTest {
	CustomerInf cust;
	
	@Before
	public void initiate() {
		cust = new CustomerInf("test", "testaddress", "da", "dk");
	}

	@Test
	public void newCustomer() {
		assertEquals("test",cust.getName());
		assertEquals("testaddress",cust.getAddress());
		assertEquals(UUID.randomUUID(),cust.getID());
		String salt = Hash.getSalt();
		assertEquals(salt,cust.getSalt());
		String hash = Hash.SHA512("test1", salt);
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
