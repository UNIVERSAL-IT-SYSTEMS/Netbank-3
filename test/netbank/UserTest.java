package netbank;

import static org.junit.Assert.*;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	CustomerInf cust;
	Account acc;
	UUID cusID;
	UUID accID;
	
	@Before
	public void initiate() {
		cusID = UUID.randomUUID();
		accID = UUID.randomUUID();
		acc = new Account(2.0, "Alice", cusID, 2.0, 40.0, Currency.getInstance(Locale.GERMANY), accID);
		cust = new CustomerInf("Alice", "testaddress", "da", "dk", cusID , Hash.getSalt(), Hash.SHA512("test", Hash.getSalt()));
	}

	@Test
	public void Customer() {
	}

}
