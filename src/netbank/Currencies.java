 package netbank;

import java.io.IOException;
import java.net.URL;
import java.util.Currency;
import java.util.Hashtable;
import java.util.Scanner;
import org.json.*;

public class Currencies {
	
	private static Hashtable<Currency, Double> currencies = new Hashtable<Currency, Double>();
	private static Boolean currencyConversionEnabled = true;

	public static void UpdateCurrencies() throws IOException {
		Hashtable<Currency, Double> tempCurrencies = new Hashtable<Currency, Double>();
		URL url = new URL("http://openexchangerates.org/api/latest.json?app_id=9d966ccd4fef4ff3ba3b48613802985a");
		Currency[] currencyNames = Currency.getAvailableCurrencies().toArray(new Currency[Currency.getAvailableCurrencies().size()]);
		try {
			Scanner scan = new Scanner(url.openStream());
			String text = new String();
			while (scan.hasNext()) {
				text += scan.nextLine();
			}
			scan.close();
			JSONObject obj = new JSONObject(text);
			JSONObject rates = obj.getJSONObject("rates");
			for(int i = 0; i < Currency.getAvailableCurrencies().size(); i++) {
				try {
					tempCurrencies.put(currencyNames[i], 
						rates.getDouble(currencyNames[i].getCurrencyCode()));
					rates.getDouble(currencyNames[i].getCurrencyCode());
				} catch(JSONException e) {
					//System.err.println("Couldn't add: "+Currency.getAvailableCurrencies().toArray()[i]+e);
				}
			}
			currencies = tempCurrencies;
			currencyConversionEnabled = true;
		} catch(JSONException e) {
			currencyConversionEnabled = false;
			System.err.println("Failed to retrieve currencies. "+e);
		}
	}
	
	public static Boolean isCurrencyConversionEnabled() { return currencyConversionEnabled; }
	
	public static Double getCurrency(Currency currency) { return currencies.get(currency); }
	
	public static Object[] getCurrencies() { return currencies.keySet().toArray(); }
	
	public static Double changeCurrency(Currency oldCurrency, Currency newCurrency) {
		Double oldAmount = getCurrency(oldCurrency);
		Double newAmount = getCurrency(newCurrency);
		return newAmount/oldAmount;
	}
}
