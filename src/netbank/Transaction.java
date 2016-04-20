package netbank;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Transaction {
	private Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
	private UUID transactionID = UUID.randomUUID();
	private UUID sender;
	private UUID receiver;
	private BigDecimal amount;
	private String senderName;
	private String receiverName;
	
	public Transaction(BigDecimal amount, UUID sender, String senderName, UUID receiver, String receiverName) {
		this.amount = amount;
		this.sender = sender;
		this.receiver = receiver;
		this.senderName = senderName;
		this.receiverName = receiverName;
	}
	
	public Double getAmount() { return amount.doubleValue(); }
	
	public Boolean setAmount(Double value) { if( value >= 0 ) { amount.setScale(2, BigDecimal.ROUND_FLOOR); return true; } return false; }
	
}
