package netbank;

import static org.junit.Assert.*;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	UserInf custInf;
	Account senderAccount,receiverAccount,foreignCurrencyAccount;
	UUID sender;
	UUID receiver;
	UUID accID1,accID2,accID3;
	String salt;
	String hash;

	@Before
	public void initiate() {
		sender = UUID.fromString("181a4993-03ee-448a-b3d4-f9b9e2a4a51e");
		receiver = UUID.fromString("181a4993-03ee-448a-b3d4-f9b9e2a4a51e");
		accID1 = UUID.fromString("aa844216-ccea-4b8a-ba80-a6f8c9acfa65");
		accID2 = UUID.fromString("6a473ef4-ba7e-4c9d-b484-9d08de24fe9c");
		accID3 = UUID.fromString("6a473ef4-ba7e-4c9d-b484-9d08de24fe9d");
		salt = Hash.getSalt();
		hash = Hash.SHA512("test", salt);
		senderAccount = new Account(accID1, sender, 200.0, 2.0, 40.0, Currency.getInstance(Locale.GERMANY));
		receiverAccount = new Account(accID2, receiver, 5.0, 1.0, 20.0, Currency.getInstance(Locale.GERMANY));
		foreignCurrencyAccount = new Account(accID3, receiver, 5.0, 1.0, 20.0, Currency.getInstance(Locale.JAPAN));
		custInf = new UserInf(sender, "testuserid", "test", "testaddress", "da", "dk", salt, hash, false);
		assertTrue(DatabaseSet.setAccount(receiverAccount));
		assertTrue(DatabaseSet.setAccount(senderAccount));
		assertTrue(DatabaseSet.setAccount(foreignCurrencyAccount));
	}

	@Test
	public void test() {
		Customer c = new Customer();
		assertTrue(Customer.transaction(accID1, senderAccount, 1.0, accID2));
		assertEquals(199.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertEquals(6.0,DatabaseGet.getAccountByAccountID(accID2).getBalance(),0);
		System.out.println("-------------");
		assertTrue(Customer.transaction(accID1, senderAccount, 1.0, accID1));
		assertEquals(199.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertFalse(Customer.transaction(accID2, senderAccount, 200.0, accID1));
		assertEquals(199.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertEquals(6.0,DatabaseGet.getAccountByAccountID(accID2).getBalance(),0);
		assertFalse(Customer.transaction(accID2, senderAccount, -20.0, accID1));
		assertEquals(199.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertEquals(6.0,DatabaseGet.getAccountByAccountID(accID2).getBalance(),0);
		assertFalse(Customer.transaction(accID2, senderAccount, 1.0, UUID.randomUUID()));
		assertEquals(6.0,DatabaseGet.getAccountByAccountID(accID2).getBalance(),0);
		assertTrue(Customer.withdrawal(senderAccount, 20.0));
		assertEquals(179.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertFalse(Customer.withdrawal(senderAccount, 400.0));
		assertEquals(179.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertFalse(Customer.withdrawal(senderAccount, -20.0));
		assertEquals(179.0,DatabaseGet.getAccountByAccountID(accID1).getBalance(),0);
		assertTrue(Customer.transaction(accID1, senderAccount, 2.0, accID3));
	}

	@Test
	public void AbstractUserClass() {
		assertTrue(Customer.ChangePassword(custInf, "123"));
		assertEquals(Hash.SHA512("123", salt), custInf.getHash());
	}

	@After
	public void deleteTestsFromDatabase() {
		System.out.println("-------------");
		DatabaseSet.removeAccount(receiverAccount);
		DatabaseSet.removeAccount(senderAccount);	
		DatabaseSet.removeAccount(foreignCurrencyAccount);
		System.out.println("-------------");

	}

}
