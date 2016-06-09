package model;

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
}
