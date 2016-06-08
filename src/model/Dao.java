package model;

import java.sql.*;

import netbank.Hash;  

public class Dao {
	public static boolean loginValidate(String salt, String hash, String password) {  
		boolean status = false;  
		try{ 
			System.out.println(salt);
			System.out.println(password);
			if(Hash.SHA512(password, salt) == hash) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e) {
			System.out.println(e);
		}  
		return status;  
	}
}
