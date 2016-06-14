package netbank;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	Account account;
	UUID cusID;
	UUID accID;

	@Before
	public void initiate() {
		cusID = UUID.randomUUID();
		accID = UUID.randomUUID();
		account = new Account(accID, cusID, 2.0, 2.0, 40.0, Currency.getInstance(Locale.GERMANY));
	}

	@Test
	public void newAccount() {
		assertEquals(2.0, account.getBalance(), 0);
		assertEquals(cusID, account.getOwnerID());
		assertEquals(2.0, account.getInterest(), 0);
		assertEquals(40.0, account.getDebt(), 0);
		assertEquals(Currency.getInstance(Locale.GERMANY), account.getCurrency());
		assertEquals(accID, account.getAccountID());
	}

	@Test
	public void ChangeInformation() {
		account.addBalance(22.22222);
		assertEquals(24.22, account.getBalance(), 0);
		account.subtractBalance(10.999999);
		assertEquals(13.22, account.getBalance(), 0);
		account.addDebt(200.9999);
		assertEquals(240.99, account.getDebt(), 0);
		account.subtractDebt(200.11111);
		assertEquals(40.87, account.getDebt(), 0);
		account.setInterest(9.9999);
		assertEquals(9.99, account.getInterest(), 0);
		UUID newID = UUID.randomUUID();
		account.setOwnerID(newID);
		assertEquals(newID, account.getOwnerID());

		account.setCurrency(Currency.getInstance(Locale.CHINA));
	}

	@Test
	public void BelowZero() {
		assertFalse(account.belowZero(1.0));
		assertTrue(account.belowZero(200.0));
	}

}
