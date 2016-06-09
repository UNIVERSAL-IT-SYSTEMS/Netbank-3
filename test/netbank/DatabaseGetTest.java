package netbank;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Currency;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import model.servle;

public class DatabaseGetTest {
	
	Account account;
	
	@Before
	public void initiate() {
		System.out.println(UUID.randomUUID());
		account = new Account(UUID.fromString("b4266dd3-d099-4522-ab04-89f96895b963"), UUID.fromString("52070c04-ac28-4426-adf7-2ea3597994f4"),2.0, 3.0, 0.0, Currency.getInstance("DKK"));
		servle.initDB();

	}
	
	

	@Test
	public void test() {
		DatabaseGet.getAccounts(IDType.ACCID, UUID.fromString("b4266dd3-d099-4522-ab04-89f96895b963"));
	}

}
