package netbank;

import static org.junit.Assert.*;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest { 
	Customer custom;
	Account account;
	UUID sender;
	UUID receiver;
	
	@Before
	public void initiate() {
		sender = UUID.randomUUID();
		account = new Account(2.0, "test", sender, 2.0, 40.0, Currency.getInstance(Locale.GERMANY));
	}

	@Test
	public void test() {
		custom.transaction(account, 20.0, receiver);
	}

}
