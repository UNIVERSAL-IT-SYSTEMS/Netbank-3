package netbank;

import model.servle;

public class DatabaseSet {
	//Remember to test actual calls to the database
	//
	
	public static boolean setAccount(Account acc) {
		return servle.getDb().setters("UPDATE DTUGRP04.accounts SET UsrID="+acc.getOwnerID().toString()
				+ ", Balance="+acc.getBalance()+", Interest="+acc.getInterest()
				+ ", Debt="+acc.getDebt()+", Currency="+acc.getCurrency()
				+ "WHERE UsrID="+acc.getAccountID().toString());
	}
	
	public static boolean setEmployee(EmployeeInf emp) {
		//Not finished since employee table has not been created
		//After having finished the table in DataStudio, replace
		//with relevant data
		return servle.getDb().setters("UPDATE DTUGRP04.employee SET userid="+emp.getID().toString()
				+ ", name="+emp.getName()+", address="+emp.getAddress()
				+ ", password="+emp.getHash()+", salt="+emp.getSalt()
				+ ", locale="+emp.getLocale().toString()
				+ "WHERE userid="+emp.getID().toString());
	}
	
	public static boolean setCostumer(CustomerInf cust) {
		return servle.getDb().setters("UPDATE DTUGRP04.customers SET userid="+cust.getID().toString()
		+ ", name="+cust.getName()+", address="+cust.getAddress()
		+ ", password="+cust.getHash()+", salt="+cust.getSalt()
		+ ", locale="+cust.getLocale().toString()
		+ "WHERE userid="+cust.getID().toString());
		
	}
	
	public static boolean setTransaction(Transaction trans) {
		if(servle.getDb() == null) {
			servle.initDB();
		}
		return servle.getDb().setters("INSERT INTO DTUGRP04.transaction SET sender="+trans.getSenderID().toString()
				+ ", receiver="+trans.getReceiverID().toString()+", transtype="+trans.getTransactionType()
				+ ", amount="+trans.getAmount()+", timestamp="+trans.getTimestamp()
				+ ", currency="+trans.getCurrency()
				+ "WHERE transid="+trans.getTransactionID());
	}
}
