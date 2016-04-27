package netbank;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Currency;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public abstract class User {
	
	public Double getCurrentEchangeRate(Currency currency) throws IOException {
		URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=9d966ccd4fef4ff3ba3b48613802985a");
		try (InputStream is = url.openStream();
		JsonReader rdr = Json.createReader(is)) { 
			JsonObject obj = rdr.readObject();
			JsonObject rates = obj.getJsonObject("rates");
			return Double.parseDouble(rates.get(currency.getCurrencyCode()).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 1.0;
		}
	}
	
	public Double changeCurrency(Currency oldCurrency, Currency newCurrency) {
		try {
			Double oldAmount = getCurrentEchangeRate(newCurrency);
			Double newAmount = getCurrentEchangeRate(oldCurrency);
			return newAmount/oldAmount;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1.0;
		}
	}
}
