package netbank;

import static org.junit.Assert.*;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	UserInf cust;
	Account acc;
	UUID cusID;
	UUID accID;

	@Before
	public void initiate() {
		cusID = UUID.randomUUID();
		accID = UUID.randomUUID();
		acc = new Account(accID, cusID, 2.0, 2.0, 40.0, Currency.getInstance(Locale.GERMANY));
		cust = new UserInf(cusID, "username", "Alice", "testaddress", "da", "dk", Hash.getSalt(),
				Hash.SHA512("test", Hash.getSalt()), false);
	}

	@Test
	public void Customer() {
	}

}
