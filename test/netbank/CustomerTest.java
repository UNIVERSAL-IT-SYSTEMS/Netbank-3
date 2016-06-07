package netbank;

import static org.junit.Assert.*;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest { 
	CustomerInf custInf;
	Customer custom;
	Account account;
	UUID sender;
	UUID receiver;
	UUID accID;
	String salt;
	String hash;
	
	@Before
	public void initiate() {
		sender = UUID.randomUUID();
		accID = UUID.randomUUID();
		salt = Hash.getSalt();
		hash = Hash.SHA512("test", salt);
		account = new Account(2.0, sender, 2.0, 40.0, Currency.getInstance(Locale.GERMANY), accID);
		custInf = new CustomerInf("test", "testaddress", "da", "dk", sender, salt, hash);
	}

	@Test
	public void test() {
		
	}
	
	@Test
	public void AbstractUserClass() {
		
		assertEquals("test",custom.getName(custInf));
		assertEquals("testaddress",custom.getAddress(custInf));
		assertEquals("da",custom.getLanguage(custInf));
		assertEquals("dk",custom.getCountry(custInf));
		assertEquals(new Locale.Builder().setLanguage("da").setRegion("dk").build(),custom.getLocale(custInf));
		assertEquals(sender,custom.getID(custInf));

		assertEquals(2.0,custom.getAccountBalance(account),0.0);
		assertEquals(2.0,custom.getAccountInterest(account),0.0);
		assertEquals(40.0,custom.getAccountDebt(account),0.0);
		assertEquals(sender,custom.getAccountOwnerID(account));
		assertEquals(Currency.getInstance(Locale.GERMANY),custom.getAccountCurrency(account));
		assertEquals(accID,custom.getAccountID(account));
		
		custom.ChangePassword(custInf, "123");
		assertEquals(Hash.SHA512("123", salt),custInf.getHash());
	}

}
