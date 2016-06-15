package netbank;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Currency;
import java.util.Locale;
import org.json.JSONException;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.mockito.*;

public class CurrenciesTest {
	
	@Test
	public void withData() throws IOException {
		Currencies cur = new Currencies();
		assertTrue(Currencies.UpdateCurrencies());
		assertNotEquals(0.0,DatabaseGet.getCurrency(Currency.getInstance(Locale.CANADA)));
		assertEquals(DatabaseGet.getCurrency(Currency.getInstance(Locale.CANADA)) /
				DatabaseGet.getCurrency(Currency.getInstance(Locale.FRANCE)),
				Currencies.changeCurrency(Currency.getInstance(Locale.FRANCE), Currency.getInstance(Locale.CANADA)),0);
	} 
	
//	@Test(expected = IOException.class)
//	public void withoutData() throws IOException {
//
//	}

}
