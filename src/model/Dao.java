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

	public static boolean Transaction(String senderID, String amount, String receiverID) {
		try {
			UUID sID = UUID.fromString(senderID);
			Double am = Double.parseDouble(amount);
			UUID rID = UUID.fromString(receiverID);
			ArrayList<Account> accounts = DatabaseGet.getAccounts(IDType.ACCID, sID);
			Customer.transaction(sID, accounts.get(0), am, rID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
