package model;

import java.util.ArrayList;
import java.util.UUID;

import netbank.*; 

public class Dao {
	
	Customer custom;
	
	public static boolean loginValidate(String salt, String hash, String password) {  
		try{ 
			if(Hash.SHA512(password, salt).equals(hash)) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e) {
			System.out.println(e);
		}  
		return false;  
	}
	
	public static boolean userNameExists(String username) {
		if(DatabaseGet.getCustomer(username) == null) {
			System.out.println("Username is ok");
			return false;
		} else {
			System.out.println("Username already exists");
			return true;
		}
	}
	
	public static boolean accountExists(IDType type, UUID accountID) {
		if(DatabaseGet.getAccounts(type, accountID) == null) {
			System.out.println("No account found");
			return false;
		} else {
			System.out.println("Account exists");
			return true;
		}
	}
	
	public static boolean Transaction(String senderID, String amount, String receiverID) {
		try {
			UUID sID = UUID.fromString(senderID);
			Double am = Double.parseDouble(amount);
			UUID rID = UUID.fromString(receiverID);
			System.out.println("GETTING ACCOUNTS");
			ArrayList<Account> accounts = DatabaseGet.getAccounts(IDType.ACCID, sID);
			System.out.println("PERFORMING TRANSACTION");
			Customer.transaction(sID, accounts.get(0), am, rID);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
