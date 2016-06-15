package netbank;

import java.io.IOException;
import java.net.URL;
import java.util.Currency;
import java.util.Hashtable;
import java.util.Scanner;
import org.json.*;

public class Currencies {

	public static Boolean UpdateCurrencies() throws IOException {
		Hashtable<Currency, Double> currencies = new Hashtable<Currency, Double>();
		URL url = new URL("http://openexchangerates.org/api/latest.json?app_id=9d966ccd4fef4ff3ba3b48613802985a");
		Currency[] currencyNames = Currency.getAvailableCurrencies()
				.toArray(new Currency[Currency.getAvailableCurrencies().size()]);
		try {
			Scanner scan = new Scanner(url.openStream());
			String text = new String();
			
			while (scan.hasNext()) {
				text += scan.nextLine();
			}
			scan.close();
			JSONObject obj = new JSONObject(text);
			JSONObject rates = obj.getJSONObject("rates");
			for (int i = 0; i < Currency.getAvailableCurrencies().size(); i++) {
				try {
					currencies.put(currencyNames[i], rates.getDouble(currencyNames[i].getCurrencyCode()));
					rates.getDouble(currencyNames[i].getCurrencyCode());
				} catch (Exception e) {
					// System.err.println("Couldn't add:
					// "+Currency.getAvailableCurrencies().toArray()[i]+e);
				}
			}
			return DatabaseSet.setCurrencies(currencies);
		} catch (JSONException e) {
			System.err.println("Failed to retrieve currencies. " + e);
			return false;
		}
	}

	public static Double changeCurrency(Currency oldCurrency, Currency newCurrency) {
		Double oldAmount = DatabaseGet.getCurrency(oldCurrency);
		Double newAmount = DatabaseGet.getCurrency(newCurrency);
		System.out.println("Ratio: " + newAmount / oldAmount);
		return newAmount / oldAmount;
	}
}
