package netbank;

import java.text.SimpleDateFormat;

import model.Dao;
import model.servle;

public class DatabaseSet {
	//Remember to test actual calls to the database
	//
	
	public static boolean setAccount(Account acc) {
		if(Dao.accountExists(IDType.ACCID, acc.getAccountID())) {
			System.out.println("UPDATE DTUGRP04.\"accounts\" SET \"cusid\"='"+acc.getOwnerID().toString()
					+ "', \"balance\"="+acc.getBalance()+", \"interest\"="+acc.getInterest()
					+ ", \"debt\"="+acc.getDebt()+", CURRENCY='"+acc.getCurrency() +"' WHERE \"accid\"='"+ acc.getAccountID() + "';");
			return servle.getDb().setters("UPDATE DTUGRP04.\"accounts\" SET \"cusid\"='"+acc.getOwnerID().toString()
					+ "', \"balance\"="+acc.getBalance()+", \"interest\"="+acc.getInterest()
					+ ", \"debt\"="+acc.getDebt()+", CURRENCY='"+acc.getCurrency() +"' WHERE \"accid\"='"+ acc.getAccountID() + "';");
		} else {
			System.out.println("INSERT INTO DTUGRP04.\"accounts\" VALUES ('"
					+ acc.getOwnerID().toString()
					+ "',"+acc.getBalance()+","+acc.getInterest()
					+ ","+ acc.getDebt()+",'"+acc.getCurrency()+";");
			return servle.getDb().setters("INSERT INTO DTUGRP04.\"accounts\" VALUES ('"
					+ acc.getOwnerID().toString()
					+ "',"+acc.getBalance()+","+acc.getInterest()
					+ ","+ acc.getDebt()+",'"+acc.getCurrency()+";");
		}
	}
	
	public static boolean setEmployee(EmployeeInf emp) {
		//Not finished since employee table has not been created
		//After having finished the table in DataStudio, replace
		//with relevant data
		if(Dao.userNameExists(emp.getUsername())) {
			return servle.getDb().setters("UPDATE DTUGRP04.employee SET userid="+emp.getID().toString()
					+ ", name="+emp.getName()+", address="+emp.getAddress()
					+ ", password="+emp.getHash()+", salt="+emp.getSalt()
					+ ", locale="+emp.getLocale().toString()
					+ "WHERE userid="+emp.getID().toString());
		} else {
			return servle.getDb().setters("INSERT INTO DTUGRP04. VALUES ('"+emp.getID().toString()
					+ "','"+emp.getName()+"','"+emp.getAddress()
					+ "','"+emp.getHash()+", salt="+emp.getSalt()
					+ ", locale="+emp.getLocale().toString()
					+ "WHERE userid="+emp.getID().toString());
		}
	}
	
	public static boolean setCostumer(CustomerInf cust) {
		if(Dao.userNameExists(cust.getUsername())) {
			return servle.getDb().setters("UPDATE DTUGRP04.\"customers\" SET userid="+cust.getID().toString()
					+ ", name="+cust.getName()+", address="+cust.getAddress()
					+ ", password="+cust.getHash()+", salt="+cust.getSalt()
					+ ", locale="+cust.getLocale().toString()
					+ "WHERE userid="+cust.getID().toString());
		} else {
			return servle.getDb().setters("INSERT INTO DTUGRP04.\"customers\" VALUES ('"+cust.getID().toString()
					+ "','"+cust.getName()+"','"+cust.getAddress()
					+ "','"+cust.getHash()+", salt="+cust.getSalt()
					+ ", locale="+cust.getLocale().toString()
					+ "WHERE userid="+cust.getID().toString());
		}
		
		
	}
	
	public static boolean setTransaction(Transaction trans) {
		if(servle.getDb() == null) {
			servle.initDB();
		}
		System.out.println("INSERT INTO DTUGRP04.\"transactions\" VALUES ('"+trans.getTransactionID()
				+ "','" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS").format(trans.getTimestamp())+ "','" + trans.getSenderID()
				+ "','" + trans.getReceiverID() +"'," + trans.getAmount() 
				+ ",'" + trans.getTransactionType() + "','" + trans.getCurrency()+"')");
		return servle.getDb().setters("INSERT INTO DTUGRP04.\"transactions\" VALUES ('"+trans.getTransactionID()
		+ "','" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS").format(trans.getTimestamp())+ "','" + trans.getSenderID()
		+ "','" + trans.getReceiverID()+"'," + trans.getAmount() 
		+ ",'" + trans.getTransactionType() + "','" + trans.getCurrency()+"')");
	}
}
