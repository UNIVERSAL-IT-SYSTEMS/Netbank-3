package netbank;

import static org.junit.Assert.*;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest { 
	CustomerInf custInf;
	Account account;
	UUID sender;
	UUID receiver;
	UUID accID;
	String salt;
	String hash;
	
	@Before
	public void initiate() {
		sender = UUID.randomUUID();
		receiver = UUID.randomUUID();
		accID = UUID.randomUUID();
		salt = Hash.getSalt();
		hash = Hash.SHA512("test", salt);
		account = new Account(accID,sender,2.0,2.0, 40.0, Currency.getInstance(Locale.GERMANY));
		custInf = new CustomerInf(sender,"testuserid","test", "testaddress", "da", "dk", salt, hash);
	}

	@Test
	public void test() {
		Customer.transaction(sender, account, 3.0, receiver);
		Customer.withdrawal(account, 400.0);
		Customer.ChangePassword(custInf, "test123");
	}
	
	@Test
	public void AbstractUserClass() {
		
		assertEquals("test",Customer.getName(custInf));
		assertEquals("testaddress",Customer.getAddress(custInf));
		assertEquals("da",Customer.getLanguage(custInf));
		assertEquals("dk",Customer.getCountry(custInf));
		assertEquals(new Locale.Builder().setLanguage("da").setRegion("dk").build(),Customer.getLocale(custInf));
		assertEquals(sender,Customer.getID(custInf));

		assertEquals(2.0,Customer.getAccountBalance(account),0.0);
		assertEquals(2.0,Customer.getAccountInterest(account),0.0);
		assertEquals(40.0,Customer.getAccountDebt(account),0.0);
		assertEquals(sender,Customer.getAccountOwnerID(account));
		assertEquals(Currency.getInstance(Locale.GERMANY),Customer.getAccountCurrency(account));
		assertEquals(accID,Customer.getAccountID(account));
		
		Customer.ChangePassword(custInf, "123");
		assertEquals(Hash.SHA512("123", salt),custInf.getHash());
	}

}
