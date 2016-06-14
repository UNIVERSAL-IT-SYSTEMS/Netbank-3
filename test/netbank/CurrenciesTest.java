package netbank;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Currency;
import java.util.Locale;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class CurrenciesTest {
	
	@Before
	public void initiate() throws IOException  {
		Currencies.UpdateCurrencies();
	}

	@Test
	public void withData() {
		assertNotEquals(0.0,DatabaseGet.getCurrency(Currency.getInstance(Locale.CANADA)));
		assertEquals(DatabaseGet.getCurrency(Currency.getInstance(Locale.CANADA)) /
				DatabaseGet.getCurrency(Currency.getInstance(Locale.FRANCE)),
				Currencies.changeCurrency(Currency.getInstance(Locale.FRANCE), Currency.getInstance(Locale.CANADA)),0);
	} 
	
	@Test(expected = JSONException.class)
	public void withoutData() throws JSONException {
		throw new JSONException("message");
	}

}
