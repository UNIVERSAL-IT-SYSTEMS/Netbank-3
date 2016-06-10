package netbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Currency;
import java.util.UUID;
import model.servle;

public class DatabaseGet {
		
	public static ArrayList<Account> getAccounts(IDType type, UUID ID) {
		Database db = null;
		try {
			db = new Database();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("SELECT * FROM DTUGRP04.\"accounts\" WHERE \""+type.toString().toLowerCase()+"\"='"+ID.toString()+"'");
		ResultSet res = db.getters("SELECT * FROM DTUGRP04.\"accounts\" WHERE \""+type.toString().toLowerCase()+"\"='"+ID.toString()+"'");
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
	

	public static EmployeeInf getEmployee(IDType type, UUID ID) {
		ResultSet res = servle.getDb().getters("SELECT * FROM FROM DTUGRP04.\"employees\" WHERE \""+type.toString().toLowerCase()+"\" ='"+ID.toString()+"'");
		try {
			// 1 ID, 2 username, 3 name, 4 address, 5 language, 6 country, 7 salt, 8 hash
			return new EmployeeInf(UUID.fromString(res.getString(1)), res.getString(2), res.getString(3), res.getString(4), res.getString(5), 
					res.getString(6), res.getString(7), res.getString(8));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static CustomerInf getCustomer(IDType type, UUID ID) {
		System.out.println("SELECT * FROM DTUGRP04.\"customers\" WHERE \""+type.toString().toLowerCase()+"\" = '"+ID.toString()+"'");
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.\"customers\" WHERE \""+type.toString().toLowerCase()+"\" = '"+ID.toString()+"'");
		try {
			if(res.next()) {
			// 1 ID, 2 username, 3 name, 4 address, 5 language, 6 country, 7 salt, 8 hash
			return new CustomerInf(UUID.fromString(res.getString(1)), res.getString(2), res.getString(3), res.getString(4), res.getString(5), 
					res.getString(6), res.getString(7), res.getString(8));
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static CustomerInf getCustomer(String username) {
		System.out.println("SELECT * FROM DTUGRP04.\"customers\" WHERE \"username\" = '"+ username +"'");
		if(servle.getDb() == null) { servle.initDB(); };
		System.out.println("SELECT * FROM DTUGRP04.\"customers\" WHERE \"username\" = 'Mtngs';");
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.\"customers\" WHERE \"username\" = '"+ username +"'");
		try {
				if(res.next()) {
					System.out.println("RETURNING USER INFO");
					// 1 ID, 2 username, 3 name, 4 address, 5 language, 6 country, 7 salt, 8 hash
					return new CustomerInf(UUID.fromString(res.getString(1)), res.getString(2), res.getString(3), res.getString(4), res.getString(5), 
							res.getString(6), res.getString(7), res.getString(8));
				} else {
					return null;
				}
		} catch (SQLException e) {
			System.out.println("FAILED");
			e.printStackTrace();
		} 
		return null;
	}
	
	public static Transaction getTransaction(IDType type, UUID ID) {
		ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.transactions WHERE \""+type.toString().toLowerCase()+"\" = '"+ID.toString()+"'");
		try {
			// 1 transactionID, 2 senderID, 3 receiverID, 4 amount, 5 currency 7 type, 8 timestamp
			return new Transaction(UUID.fromString(res.getString(1)), UUID.fromString(res.getString(2)), UUID.fromString(res.getString(3)), 
					res.getDouble(4), Currency.getInstance(res.getString(5)), TransactionType.valueOf(res.getString(6)), 
					Timestamp.valueOf(res.getString(7)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
