package netbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.UUID;
import model.servle;

public class DatabaseGet {
	
	// type = accID, cusID, empID, traID
	
	public static Account getAccount(String type, UUID ID) {
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.accounts WHERE "+type+" = "+ID.toString());
		try {
			// 1 balance, 2 owner, 3 ownerID, 4 interest, 5 debt, 6 currency, 7 accountID
			return new Account(UUID.fromString(res.getString(6)), UUID.fromString(res.getString(2)), res.getDouble(1), res.getDouble(3), 
					res.getDouble(4), Currency.getInstance(res.getString(5)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static EmployeeInf getEmployee(String type, UUID ID) {
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.employees WHERE "+type+" = "+ID.toString());
		try {
			// 1 name, 2 address, 3 language, 4 country
			return new EmployeeInf(UUID.fromString(res.getString(5)), res.getString(1), res.getString(2), res.getString(3), 
					res.getString(4), res.getString(6), res.getString(7));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static CustomerInf getCustomer(String type, UUID ID) {
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.customers WHERE "+type+" = "+ID.toString());
		try {
			// 1 name, 2 address, 3 language, 4 country, 5 ID, 6 salt, 7 hash
			return new CustomerInf(UUID.fromString(res.getString(5)), res.getString(1), res.getString(2), res.getString(3), 
					res.getString(4), res.getString(6), res.getString(7));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Transaction getTransaction(String type, UUID ID) {
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.transactions WHERE "+type+" = "+ID.toString());
		try {
			// 1 amount, 2 currency, 3 senderID, 4 senderName, 5 receiverID, 6 receiverName, 7 type, 8 timestamp, 9 transactionID
			return new Transaction(res.getDouble(1), Currency.getInstance(res.getString(2)), UUID.fromString(res.getString(3)), 
					UUID.fromString(res.getString(5)), TransactionType.valueOf(res.getString(7)), 
					Timestamp.valueOf(res.getString(8)),UUID.fromString(res.getString(9)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
