package netbank;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public abstract class User {

	public BigDecimal change(BigDecimal amount) {
		return amount.setScale(2, BigDecimal.ROUND_FLOOR);
	}
	
	public Boolean checkIfBelowZero(BigDecimal amount, BigDecimal balance) {
		if (balance.subtract(amount).doubleValue() < 0) {
			return false;
		} else { 
			return true;
		}
	}
	
	public String getCurrentEchangeRate(Currency currency) throws IOException {
		URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=9d966ccd4fef4ff3ba3b48613802985a");
		try (InputStream is = url.openStream();
		JsonReader rdr = Json.createReader(is)) { 
			JsonObject obj = rdr.readObject();
			JsonObject rates = obj.getJsonObject("rates");
			return rates.get(currency.getCurrencyCode()).toString();
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public Double changeCurrency(Currency oldCurrency, Currency newCurrency) {
		try {
			Double oldAmount = Double.parseDouble(getCurrentEchangeRate(newCurrency));
			Double newAmount = Double.parseDouble(getCurrentEchangeRate(oldCurrency));
			return newAmount/oldAmount;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
