package netbank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	UserInf empInf;
	Account account1,account2;
	UUID cusID;
	UUID accID1,accID2,accID3,accID4,accID5;
	String salt;
	String hash;
	
	@Before
	public void initiate() {
		cusID = UUID.fromString("181a4993-03ee-448a-b3d4-f9b9e2a4a51e");
		accID1 = UUID.fromString("aa844216-ccea-4b8a-ba80-a6f8c9acfa65");
		accID2 = UUID.fromString("aa844216-ccea-4b8a-ba80-a6f8c9acfa66");
		accID3 = UUID.fromString("aa844216-ccea-4b8a-ba80-a6f8c9acfa67");
		accID4 = UUID.fromString("aa844216-ccea-4b8a-ba80-a6f8c9acfa68");
		accID4 = UUID.fromString("aa844216-ccea-4b8a-ba80-a6f8c9acfa69");
		accID5 = UUID.fromString("aa844216-ccea-4b8a-ba80-a6f8c9acfa70");
		salt = Hash.getSalt();
		hash = Hash.SHA512("test", salt);
		account1 = new Account(accID1, cusID, 200.0, 2.0, 40.0, Currency.getInstance(Locale.GERMANY));
		account2 = new Account(accID5, cusID, 0.0, 2.0, 2.0, Currency.getInstance(Locale.KOREA));
		empInf = new UserInf(cusID, "testuserid", "test", "testaddress", "da", "dk", salt, hash, true);
		
		assertTrue(DatabaseSet.setAccount(account1));
	}
	
	@Test
	public void AbstractUserClass() {
		assertTrue(Customer.ChangePassword(empInf, "123"));
		assertEquals(Hash.SHA512("123", salt), empInf.getHash());
	}
	
	@Test
	public void test() {
		Employee c = new Employee();
		assertTrue(Employee.newAccount(cusID, 2.0, Currency.getInstance(Locale.FRANCE)));
		assertEquals(2.0,DatabaseGet.getAccountsByUserID(cusID).get(0).getInterest(),0);
		assertFalse(Employee.addAccountDebt(account1, -2.0));
		assertEquals(40.0,DatabaseGet.getAccountByAccountID(accID1).getDebt(),0);
		assertTrue(Employee.addAccountDebt(account1, 2.0));
		assertEquals(40.0+2.0,DatabaseGet.getAccountByAccountID(accID1).getDebt(),0);
		assertFalse(Employee.subtractAccountDebt(account1, -2.0));
		assertEquals(42.0,DatabaseGet.getAccountByAccountID(accID1).getDebt(),0);
		assertTrue(Employee.subtractAccountDebt(account1, 2.0));
		assertEquals(42.0-2.0,DatabaseGet.getAccountByAccountID(accID1).getDebt(),0);
		assertFalse(Employee.subtractAccountDebt(account1, 2000.0));
		assertEquals(40.0,DatabaseGet.getAccountByAccountID(accID1).getDebt(),0);
		assertFalse(Employee.deposit(account1, -2.0));
		assertEquals(200.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertTrue(Employee.deposit(account1, 2.0));
		assertEquals(200.0+2.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertTrue(Employee.subtractAccountBalance(account1, 2.0));
		assertEquals(202.0-2.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertFalse(Employee.subtractAccountBalance(account1, -2.0));
		assertEquals(200.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertFalse(Employee.subtractAccountBalance(account1, 2000.0));
		assertEquals(200.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertTrue(Employee.changeOwnershipOfAccount(account1, cusID));
		assertEquals(cusID,DatabaseGet.getAccountByAccountID(accID1).getOwnerID());
		assertFalse(Employee.changeOwnershipOfAccount(account1, UUID.randomUUID()));
		assertEquals(cusID,DatabaseGet.getAccountByAccountID(accID1).getOwnerID());
		assertTrue(Employee.setAccountCurrency(account1, Currency.getInstance(Locale.CANADA)));
		assertEquals(Currency.getInstance(Locale.CANADA),DatabaseGet.getAccountByAccountID(accID1).getCurrency());
		assertTrue(Employee.setAccountInterest(account1, 2.0));
		assertEquals(2.0,DatabaseGet.getAccountByAccountID(accID1).getInterest(),0);
	}
	
	@Test
	public void deleteTestsFromDatabase() {
		DatabaseSet.setAccount(new Account(accID2, cusID, 200.0, 2.0, 400.0, Currency.getInstance(Locale.GERMANY)));
		DatabaseSet.setAccount(new Account(accID3, cusID, 0.0, 2.0, 0.0, Currency.getInstance(Locale.GERMANY)));
		DatabaseSet.setAccount(new Account(accID4, cusID, 0.0, 2.0, 20.0, Currency.getInstance(Locale.US)));
		
		ArrayList<Account> accounts = DatabaseGet.getAccountsByUserID(cusID);
		for(int i = 0; i<accounts.size(); i++) {
			assertTrue(Employee.deleteAccount(accounts.get(i)));
			accounts = DatabaseGet.getAccountsByUserID(cusID);
		}
		System.out.println("SETTING ACCOUNT");
		DatabaseSet.setAccount(account2);
		assertTrue(Employee.deleteAccount(account2));
	}
	
	@After
	public void deleteAccounts() {
		ArrayList<Account> accounts = DatabaseGet.getAccountsByUserID(cusID);
		for(int i = 0; i<accounts.size(); i++) {
			Employee.deleteAccount(accounts.get(i));
		}
	}
}
