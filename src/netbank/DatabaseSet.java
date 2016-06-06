package netbank;

import java.sql.ResultSet;

import model.servle;

public class DatabaseSet {
	
	public static boolean setAccount(Account acc) {
		ResultSet res = servle.getDb().getters("UPDATE DTUGRP04.accounts SET UsrID="+acc.getOwnerID().toString()
				+ ", Balance="+acc.getBalance()+", Interest="+acc.getInterest()
				+ ", Debt="+acc.getDebt()+", Currency="+acc.getCurrency()
				+ "WHERE UsrID="+acc.getAccountID().toString());
		if(res == null) {
			return false;
		}
		return true;
	}
	
	public static boolean setEmployee(Account acc) {
		//Not finished since employee table has not been created
		ResultSet res = servle.getDb().getters("UPDATE DTUGRP04.employee SET UsrID="+acc.getOwnerID()
		+ ", Balance="+acc.getBalance()+", Interest="+acc.getInterest()
		+ ", Debt="+acc.getDebt()+", Currency="+acc.getCurrency()
		+ "WHERE UsrID="+acc.getAccountID().toString());
		if(res == null) {
			return false;
		}
		return true;
	}
	
	public static boolean setCostumer(CustomerInf cust) {
		ResultSet res = servle.getDb().getters("UPDATE DTUGRP04.customers SET userid="+cust.getID().toString()
		+ ", name="+cust.getName()+", address="+cust.getAddress()
		+ ", password="+cust.getHash()+", salt="+cust.getSalt()
		+ ", locale="+cust.getLocale().toString()
		+ "WHERE userid="+cust.getID().toString());
		if(res == null) {
			return false;
		}
		return true;
	}
	
	public static boolean setTransaction(Transaction trans) {
		ResultSet res = servle.getDb().getters("UPDATE DTUGRP04.transaction SET sender="+trans.getSenderID().toString()
				+ ", receiver="+trans.getReceiverID().toString()+", transtype="+trans.getTransactionType()
				+ ", amount="+trans.getAmount()+", timestamp="+trans.getTimestamp()
				+ ", currency="+trans.getCurrency()
				+ "WHERE transid="+trans.getTransactionID());
		return false;
	}
}
