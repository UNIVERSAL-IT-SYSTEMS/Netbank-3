package netbank;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Currency;
import java.util.Hashtable;
import java.util.Locale;

import javax.json.*;

public class Currencies {
	
	static Hashtable<Currency, Double> currencies = new Hashtable<Currency, Double>();
	static Boolean currencyConversionEnabled = true;

	public static void UpdateCurrencies() throws IOException {
		Hashtable<Currency, Double> tempCurrencies = new Hashtable<Currency, Double>();
		URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=9d966ccd4fef4ff3ba3b48613802985a");
		try {
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonObject rates = obj.getJsonObject("rates");
			for(int i = 0; i<Currency.getAvailableCurrencies().size(); i++) {
				try {
					tempCurrencies.put((Currency) Currency.getAvailableCurrencies().toArray()[i], 
						Double.parseDouble(rates.get(Currency.getAvailableCurrencies().toArray()[i].toString()).toString()));
				} catch(Exception e) {
					
				}
			}		
			if (!tempCurrencies.isEmpty()) {
				for (int i = 0; i < tempCurrencies.size(); i++) {
					currencies.replace(tempCurrencies.keys().nextElement(), tempCurrencies.get(currencies.keys().nextElement()));
				} 
			}
			currencyConversionEnabled = true;
		} catch(Exception e) {
			currencyConversionEnabled = false;
		}
	}	
	
	public static Boolean isCurrencyConversionEnabled() { return currencyConversionEnabled; }
	
	public static Double getCurrency(Currency currency) { return currencies.get(currency); }
	
	public static Double changeCurrency(Currency oldCurrency, Currency newCurrency) {
		Double oldAmount = getCurrency(newCurrency);
		Double newAmount = getCurrency(oldCurrency);
		return newAmount/oldAmount;
	}
}
