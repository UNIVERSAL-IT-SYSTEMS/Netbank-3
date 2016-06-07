package netbank;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class TransactionTest {
	
	Transaction tr;
	UUID senderUUID = UUID.randomUUID();
	UUID receiverUUID = UUID.randomUUID();
	
	@Before
	public void initiate() {
		tr = new Transaction(4.0, Currency.getInstance(Locale.CANADA), senderUUID, 
				"Alice", receiverUUID, "Bob", TransactionType.Transaction, 
				new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID());
	}

	@Test
	public void test() {
		assertEquals(4.0,tr.getAmount(),0.0);
		assertEquals(Currency.getInstance(Locale.CANADA),tr.getCurrency());
		assertEquals(senderUUID,tr.getSenderID());
		assertEquals("Alice",tr.getSenderName());
		assertEquals(receiverUUID,tr.getReceiverID());
		assertEquals("Bob",tr.getReceiverName());
		assertEquals(TransactionType.valueOf("Transaction"),tr.getTransactionType());
		assertNotNull(tr.getTransactionID());
		assertNotNull(tr.getTimestamp());
	}

}
