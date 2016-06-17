package netbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.UUID;

public class DatabaseGet {

	public static ArrayList<Account> getAccountsByUserID(UUID ID) {
		System.out.println("SELECT * FROM DTUGRP04.\"accounts\" WHERE \"cusid\"='" + ID.toString() + "'");
		ResultSet res = Database
				.getters("SELECT * FROM DTUGRP04.\"accounts\" WHERE \"cusid\"='" + ID.toString() + "'");
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			if (res == null) {
				return null;
			}
			while (res.next()) {
				// 1 accountID, 2 ownerID, 3 balance, 4 interest, 5 debt, 7
				// currency
				accounts.add(new Account(UUID.fromString(res.getString(1)), UUID.fromString(res.getString(2)),
						res.getDouble(3), res.getDouble(4), res.getDouble(5), Currency.getInstance(res.getString(6))));
			}
			return accounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Account getAccountByAccountID(UUID ID) {
		System.out.println("SELECT * FROM DTUGRP04.\"accounts\" WHERE \"accid\"='" + ID.toString() + "'");
		ResultSet res = Database
				.getters("SELECT * FROM DTUGRP04.\"accounts\" WHERE \"accid\"='" + ID.toString() + "'");
		try {
			if (res.next()) {

				// 1 accountID, 2 ownerID, 3 balance, 4 interest, 5 debt, 7
				// currency
				return new Account(UUID.fromString(res.getString(1)), UUID.fromString(res.getString(2)),
						res.getDouble(3), res.getDouble(4), res.getDouble(5), Currency.getInstance(res.getString(6)));
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static UserInf getUserByUserID(UUID ID) {
		System.out.println("SELECT * FROM DTUGRP04.\"customers\" WHERE \"cusid\" = '" + ID.toString() + "'");
		ResultSet res = Database
				.getters("SELECT * FROM DTUGRP04.\"customers\" WHERE \"cusid\" = '" + ID.toString() + "'");
		try {
			if (res.next()) {
				// 1 ID, 2 username, 3 name, 4 address, 5 language, 6 country, 7
				// salt, 8 hash, 9 isEmployee
				boolean isEmployee = ((Integer.parseInt(res.getString(9).substring(1, 2)) == 0)) ? false : true;
				return new UserInf(UUID.fromString(res.getString(1)), res.getString(2), res.getString(3),
						res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8),
						isEmployee);
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static UserInf getUserByUsername(String username) {
		System.out.println("SELECT * FROM DTUGRP04.\"customers\" WHERE \"username\" = '" + username + "'");
		ResultSet res = Database
				.getters("SELECT * FROM DTUGRP04.\"customers\" WHERE \"username\" = '" + username + "'");
		try {
			if (res.next()) {
				System.out.println("RETURNING USER INFO");
				// 1 ID, 2 username, 3 name, 4 address, 5 language, 6 country, 7
				// salt, 8 hash, 9 isEmployee
				boolean isEmployee = ((Integer.parseInt(res.getString(9).substring(1, 2)) == 0)) ? false : true;
				return new UserInf(UUID.fromString(res.getString(1)), res.getString(2), res.getString(3),
						res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8),
						isEmployee);
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("FAILED");
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Transaction> getTransactionByAccountID(UUID ID) {
		ResultSet res = Database.getters("SELECT * FROM DTUGRP04.\"transactions\" WHERE (\"senderid\" = '"
				+ ID.toString() + "' OR \"receiverid\" = '" + ID.toString() + "')");
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			if (res == null) {
				return null;
			}
			// 1 transactionID, 2 timestamp, 3 senderID, 4 receiverID, 5 amount,
			// 6 type, 7 currency
			while (res.next()) {
				transactions.add(new Transaction(UUID.fromString(res.getString(1)), UUID.fromString(res.getString(3)),
						UUID.fromString(res.getString(4)), res.getDouble(5), Currency.getInstance(res.getString(7)),
						TransactionType.valueOf(res.getString(6)),
						Timestamp.valueOf(res.getString(2).substring(0, 23))));
			}

			return transactions;
		} catch (SQLException e) {
			System.out.println("getTransactions SQL ERROR");
			e.printStackTrace();
		}
		return null;
	}

	public static Double getCurrency(Currency currency) {
		ResultSet res = Database.getters(
				"SELECT * FROM DTUGRP04.\"currencies\" WHERE \"currency\" = '" + currency.getCurrencyCode() + "'");
		try {
			if (res.next()) {
				System.out.println("RETURNING DOUBLE RATE");
				// 1 CurrencyCode, 2 rate
				return res.getDouble(2);
			} else {
				System.out.println("Currency doesn't exist");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("FAILED");
			e.printStackTrace();
		}
		return null;
	}

	public static HashMap<Currency, Double> getCurrencies() {
		HashMap<Currency, Double> currencies = new HashMap<Currency, Double>();
		ResultSet res = Database.getters("SELECT * FROM DTUGRP04.\"currencies\"");
		try {
			while (res.next()) {
				currencies.put(Currency.getInstance(res.getString(1)), res.getDouble(2));
			}
			return currencies;
		} catch (SQLException e) {
			System.out.println("FAILED");
			e.printStackTrace();
		}
		return null;
	}

}
