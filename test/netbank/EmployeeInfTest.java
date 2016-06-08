package netbank;

import static org.junit.Assert.*;
import java.util.Locale;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

public class EmployeeInfTest {

	EmployeeInf empl;
	String salt;
	String hash;
	UUID ID;
	
	@Before
	public void initiate() {
		salt = Hash.getSalt();
		hash = Hash.SHA512("test123", salt);
		ID = UUID.randomUUID();
		empl = new EmployeeInf( ID , "testuserid","test", "testaddress", "da", "dk",salt, hash);
	}

	@Test
	public void newCustomer() {
		assertEquals("test",empl.getName());
		assertEquals("testaddress",empl.getAddress());
		assertEquals(ID,empl.getID());
		assertEquals(salt,empl.getSalt());
		assertEquals(hash,empl.getHash());
		assertEquals(new Locale.Builder().setLanguage("da").setRegion("dk").build(),empl.getLocale());
		assertEquals("da",empl.getLanguage());
		assertEquals("dk",empl.getCountry());
	}
	
	@Test
	public void ChangeInformation() {
		empl.setName("Alice");
		assertEquals("Alice",empl.getName());
		empl.setAddress("new place");
		assertEquals("new place",empl.getAddress());
		String hash = Hash.SHA512("test1", salt);
		empl.setHash(hash);
		assertEquals(hash,empl.getHash());
		empl.setLanguage("en");
		assertEquals("en",empl.getLanguage());
		assertEquals(new Locale.Builder().setLanguage("en").setRegion("dk").build(),empl.getLocale());
		empl.setCountry("us");
		assertEquals("us",empl.getCountry());
		assertEquals(new Locale.Builder().setLanguage("en").setRegion("us").build(),empl.getLocale());
	}

}
