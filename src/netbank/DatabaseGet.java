package netbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.UUID;
import model.servle;

public class DatabaseGet {
		
	public static ArrayList<Account> getAccounts(UUID ID) {
		if(servle.getDb() == null) { servle.initDB(); };
		System.out.println("SELECT * FROM DTUGRP04.\"accounts\" WHERE \"cusid\"='"+ID.toString()+"'");
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.\"accounts\" WHERE \"cusid\"='"+ID.toString()+"'");
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			if(res == null) {
				return null;
			}
			while(res.next()) {
				accounts.add(new Account(UUID.fromString(res.getString(1)), UUID.fromString(res.getString(2)), res.getDouble(3), res.getDouble(4), 
					res.getDouble(5), Currency.getInstance(res.getString(6))));
			}
			// 1 balance, 2 owner, 3 ownerID, 4 interest, 5 debt, 6 currency, 7 accountID
			return accounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static UserInf getUser(UUID ID) {
		System.out.println("SELECT * FROM DTUGRP04.\"customers\" WHERE \"cusid\" = '"+ID.toString()+"'");
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.\"customers\" WHERE \"cusid\" = '"+ID.toString()+"'");
		try {
			if(res.next()) {
			// 1 ID, 2 username, 3 name, 4 address, 5 language, 6 country, 7 salt, 8 hash, 9 isEmployee
			return new UserInf(UUID.fromString(res.getString(1)), res.getString(2), res.getString(3), res.getString(4), res.getString(5), 
					res.getString(6), res.getString(7), res.getString(8), res.getBoolean(9));
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static UserInf getCustomer(String username) {
		if(servle.getDb() == null) { servle.initDB(); };
		System.out.println("SELECT * FROM DTUGRP04.\"customers\" WHERE \"username\" = '"+ username +"'");
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.\"customers\" WHERE \"username\" = '"+ username +"'");
		try {
				if(res.next()) {
					System.out.println("RETURNING USER INFO");
					// 1 ID, 2 username, 3 name, 4 address, 5 language, 6 country, 7 salt, 8 hash, 9 isEmployee
					return new UserInf(UUID.fromString(res.getString(1)), res.getString(2), res.getString(3), res.getString(4), res.getString(5), 
							res.getString(6), res.getString(7), res.getString(8), (res.getString(9).equals("0")?false:true));
				} else {
					return null;
				}
		} catch (SQLException e) {
			System.out.println("FAILED");
			e.printStackTrace();
		} 
		return null;
	}
	
	public static ArrayList<Transaction> getTransaction(UUID ID) {
		if(servle.getDb() == null) { servle.initDB(); };
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.\"transactions\" WHERE (\"senderid\" = '"+ID.toString()+"' OR \"receiverid\" = '"+ID.toString() +"')");
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			// 1 transactionID, 2 timestamp, 3 senderID, 4 receiverID, 5 amount, 6 type, 7 currency
			while(res.next()) {
				transactions.add(new Transaction(UUID.fromString(res.getString(1)), UUID.fromString(res.getString(3)), UUID.fromString(res.getString(4)), 
						res.getDouble(5), Currency.getInstance(res.getString(7)), TransactionType.valueOf(res.getString(6)), 
						Timestamp.valueOf(res.getString(2).substring(0, 23))));
			}
			
			return transactions;
		} catch (SQLException e) {
			System.out.println("getTransactions SQL ERROR");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
