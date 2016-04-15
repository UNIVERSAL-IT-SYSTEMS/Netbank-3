package netbank;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Transaction {
	Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	UUID transactionID = UUID.randomUUID();
	UUID sender;
	UUID receiver;
	BigDecimal amount;
	String senderName;
	String receiverName;
	
	public Transaction(BigDecimal amount, UUID sender, String senderName, UUID receiver, String receiverName) {
		this.amount = amount;
		this.sender = sender;
		this.receiver = receiver;
		this.senderName = senderName;
		this.receiverName = receiverName;
	}
	
}
