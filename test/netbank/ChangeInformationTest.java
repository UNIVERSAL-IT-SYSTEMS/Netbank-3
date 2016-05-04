package netbank;

import static org.junit.Assert.*;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

public class ChangeInformationTest {
	Customer custom;
	Employee emp;
	Account account;
	
	@Before
	public void initiate() {
		custom = new Customer();
		emp = new Employee();
		account = new Account(2.0, "test", UUID.randomUUID(), 2.0, 40.0, Currency.getInstance(Locale.GERMANY));;
	}
	
	@Test
	public void ChangeInformationCustom() {
		
	}
	
	@Test
	public void ChangeInformationEmp() {
		emp.setAccountCurrency(account,Currency.getInstance(Locale.CANADA));
		assertEquals(Currency.getInstance(Locale.CANADA),emp.getAccountCurrency(account));
		emp.setAccountInterest(account,2.0);
		assertEquals(emp.getAccountInterest(account),2.0,0);
		emp.deposit(account,200.0);
		assertEquals(emp.getAccountBalance(account),202.0,0);
	}
}
