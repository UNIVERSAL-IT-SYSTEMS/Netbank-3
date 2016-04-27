package netbank;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public class Customer extends User {

	CustomerInf person;
	Account account;
	
	public Boolean transaction(Double amount, UUID recieverId) {
			
		// TODO first receive the account, then check currency. If currency is different, then change amount to different currency.
		
		Account account2 = null;
		Double tempValue;
		try {
			account.setBalance(account.getBalance()-amount);
			tempValue = getCurrentEchangeRate(account.getCurrency())*amount;
			// TODO send!
			account2.setBalance(account2.getBalance()+tempValue);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Boolean withdrawal(Double amount) {
		return account.setBalance(account.getBalance()-amount);
	}
	
}
