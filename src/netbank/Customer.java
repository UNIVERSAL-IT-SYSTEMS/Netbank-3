package netbank;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public class Customer extends User {

	Person person;
	Account account;
	
	public Customer() {
		
	}
	
public Boolean transaction(BigDecimal amount, UUID recieverId) {
		
		// TODO first receive the account, then check currency. If currency is different, then change amount to different currency.
		
		Currency diffcurrency = null;
		
		if (diffcurrency.getNumericCode() != account.currency.getNumericCode()) {
			try {
				int hey = Integer.parseInt(getCurrentEchangeRate(diffcurrency));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (checkIfBelowZero(amount,account.balance)) {
			account.balance = change(account.balance.subtract(amount));
			
			// TODO Actually send the money: get true or false whether it's received. 
			
			return true;
		}
		
		return false;
	}
	

	
}
