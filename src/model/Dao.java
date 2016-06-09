package model;

import netbank.CustomerInf;
import netbank.DatabaseGet;
import netbank.Hash;  

public class Dao {
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
}
