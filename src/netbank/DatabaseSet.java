package netbank;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import model.Dao;
import model.servle;

public class DatabaseSet {
	// Remember to test actual calls to the database
	//

	public static boolean setAccount(Account acc) {
		if (servle.getDb() == null) {
			servle.initDB();
		}
		if (Dao.accountExists(acc.getAccountID())) {
			System.out.println("UPDATE DTUGRP04.\"accounts\" SET \"cusid\"='" + acc.getOwnerID() + "', \"balance\"="
					+ acc.getBalance() + ", \"interest\"=" + acc.getInterest() + ", \"debt\"=" + acc.getDebt()
					+ ", CURRENCY='" + acc.getCurrency() + "' WHERE \"accid\"='" + acc.getAccountID() + "';");
			return servle.getDb()
					.setters("UPDATE DTUGRP04.\"accounts\" SET \"cusid\"='" + acc.getOwnerID() + "', \"balance\"="
							+ acc.getBalance() + ", \"interest\"=" + acc.getInterest() + ", \"debt\"=" + acc.getDebt()
							+ ", CURRENCY='" + acc.getCurrency() + "' WHERE \"accid\"='" + acc.getAccountID() + "';");
		} else {
			System.out.println("INSERT INTO DTUGRP04.\"accounts\" VALUES ('" + acc.getAccountID() + "','"
					+ acc.getOwnerID().toString() + "'," + acc.getBalance() + "," + acc.getInterest() + ","
					+ acc.getDebt() + ",'" + acc.getCurrency() + "')");
			return servle.getDb()
					.setters("INSERT INTO DTUGRP04.\"accounts\" VALUES ('" + acc.getAccountID() + "','"
							+ acc.getOwnerID().toString() + "'," + acc.getBalance() + "," + acc.getInterest() + ","
							+ acc.getDebt() + ",'" + acc.getCurrency() + "')");
		}
	}

	public static boolean setUser(UserInf cust) {
		if (servle.getDb() == null) {
			servle.initDB();
		}
		if (Dao.userNameExists(cust.getUsername())) {
			// "INSERT INTO DTUGRP04.\"customers\" VALUES ('?','?')";
			System.out.println("INSERT INTO DTUGRP04.\"customers\" VALUES ('" + cust.getID().toString() + "','"
					+ cust.getUsername() + "','" + cust.getName() + "','" + cust.getAddress() + "','"
					+ cust.getLanguage() + "','" + cust.getCountry() + "','" + cust.getSalt() + "','" + cust.getHash()
					+ "','" + (cust.getIsEmployee() ? 1 : 0) + "')");
			return servle.getDb().setters("UPDATE DTUGRP04.\"customers\" SET \"userid\"='" + cust.getID().toString()
					+ "', \"name\"='" + cust.getUsername() + "', \"name\"='" + cust.getName() + "', \"address\"='"
					+ cust.getAddress() + "', \"language\"='" + cust.getLanguage() + "', \"country\"='"
					+ cust.getCountry() + "', \"salt\"='" + cust.getSalt() + "', \"hash\"='" + cust.getHash()
					+ "' WHERE \"userid\"='" + cust.getID().toString() + "'");
		} else {
			String emp = cust.getIsEmployee() ? "1" : "0";
			System.out.println("INSERT INTO DTUGRP04.\"customers\" VALUES ('" + cust.getID().toString() + "','"
					+ cust.getUsername() + "','" + cust.getName() + "','" + cust.getAddress() + "','"
					+ cust.getLanguage() + "','" + cust.getCountry() + "','" + cust.getSalt() + "','" + cust.getHash()
					+ "','" + cust.getIsEmployee() + "')");
			return servle.getDb()
					.setters("INSERT INTO DTUGRP04.\"customers\" VALUES ('" + cust.getID().toString() + "','"
							+ cust.getUsername() + "','" + cust.getName() + "','" + cust.getAddress() + "','"
							+ cust.getLanguage() + "','" + cust.getCountry() + "','" + cust.getSalt() + "','"
							+ cust.getHash() + "','" + emp + "')");
		}

	}

	public static boolean setTransaction(Transaction trans) {
		if (servle.getDb() == null) {
			servle.initDB();
		}
		System.out.println("INSERT INTO DTUGRP04.\"transactions\" VALUES ('" + trans.getTransactionID() + "','"
				+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS").format(trans.getTimestamp()) + "','"
				+ trans.getSenderID() + "','" + trans.getReceiverID() + "'," + trans.getAmount() + ",'"
				+ trans.getTransactionType() + "','" + trans.getCurrency() + "')");
		return servle.getDb()
				.setters("INSERT INTO DTUGRP04.\"transactions\" VALUES ('" + trans.getTransactionID() + "','"
						+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS").format(trans.getTimestamp()) + "','"
						+ trans.getSenderID() + "','" + trans.getReceiverID() + "'," + trans.getAmount() + ",'"
						+ trans.getTransactionType() + "','" + trans.getCurrency() + "')");
	}

	public static boolean removeAccount(Account acc) {
		System.out.println("DELETE FROM DTUGRP04.\"accounts\" WHERE \"accid\"='" + acc.getAccountID() + "'");
		return servle.getDb().setters("DELETE FROM DTUGRP04.\"accounts\" WHERE \"accid\"='" + acc.getAccountID() + "'");
	}

	public static boolean setCurrencies(Hashtable<Currency, Double> currencies) {
		if (servle.getDb() == null) {
			servle.initDB();
		}
		Enumeration<Currency> e = currencies.keys();
		ArrayList<String> qwy = new ArrayList<String>();
		HashMap<Currency, Double> databaseCurrencies = DatabaseGet.getCurrencies();
		while (e.hasMoreElements()) {
			Currency key = e.nextElement();
			if (databaseCurrencies.get(key) != null) {
				System.out.println("UPDATE DTUGRP04.\"currencies\" SET \"rate\"=" + currencies.get(key)
						+ " WHERE \"currency\"='" + key.getCurrencyCode() + "';");
				qwy.add("UPDATE DTUGRP04.\"currencies\" SET \"rate\"=" + currencies.get(key) + " WHERE \"currency\"='"
						+ key.getCurrencyCode() + "';");
			} else {
				System.out.println(
						"INSERT INTO DTUGRP04.\"currencies\" VALUES ('" + key + "'," + currencies.get(key) + ")");
				qwy.add("INSERT INTO DTUGRP04.\"currencies\" VALUES ('" + key + "','" + currencies.get(key) + "')");
			}
		}
		return servle.getDb().setters(qwy);
	}
}
