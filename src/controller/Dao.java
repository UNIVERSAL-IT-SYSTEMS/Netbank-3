package controller;

import java.util.UUID;

import netbank.*;

public class Dao {

	public static boolean loginValidate(String salt, String hash, String password) {
		try {
			if (Hash.SHA512(password, salt).equals(hash)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static boolean userNameExists(String username) {
		if (DatabaseGet.getUserByUsername(username) == null) {
			System.out.println("Username is ok");
			return false;
		} else {
			System.out.println("Username already exists");
			return true;
		}
	}

	public static boolean accountExists(UUID accountID) {
		if (DatabaseGet.getAccountByAccountID(accountID) == null) {
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
			if(am < 0) {
				return false;
			}
			System.out.println("GETTING ACCOUNTS");
			Account account = DatabaseGet.getAccountByAccountID(sID);
			System.out.println("PERFORMING TRANSACTION");
			return Customer.transaction(sID, account, am, rID);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Withdrawal(String senderID, String amount) {
		try {
			UUID sID = UUID.fromString(senderID);
			Double am = Double.parseDouble(amount);
			System.out.println("GETTING ACCOUNTS");
			Account account = DatabaseGet.getAccountByAccountID(sID);
			System.out.println("PERFORMING WITHDRAWAL");
			return Customer.withdrawal(account, am);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
