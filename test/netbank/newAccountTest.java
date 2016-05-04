package netbank;

import static org.junit.Assert.*;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class newAccountTest {
	Employee emp;
	CustomerInf customerInf;
	Account account;
	
	@Before
	public void initiate() {
		emp = new Employee();
		customerInf = new CustomerInf("testCustomer", "test 1", Locale.GERMAN.toString(), Locale.GERMAN.toString());
		account = new Account(2.0, "test", customerInf.getID(), 2.0, 40.0, Currency.getInstance(Locale.GERMANY));
	}
	
	@Test
	public void newAccount() {
		assertEquals(2.0,emp.getAccountBalance(account),0);
		assertEquals("test",emp.getAccountOwner(account));
		assertEquals(customerInf.getID(),emp.getAccountOwnerID(account));
		assertTrue( UUID.randomUUID() != emp.getAccountID(account) && emp.getAccountID(account).toString().getBytes().length==36);
		assertEquals(2.0,emp.getAccountInterest(account),0);
		assertEquals(40.0,emp.getAccountDebt(account),0);
		assertEquals(Currency.getInstance(Locale.GERMANY),emp.getAccountCurrency(account));
	}
}


