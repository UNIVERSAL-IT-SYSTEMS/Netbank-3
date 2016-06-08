package model;

import java.sql.*;

import netbank.Hash;  

public class Dao {
	public static boolean loginValidate(String name, String pass){  
		boolean status = false;  
		try{ 
			ResultSet res = servle.getDb().getters("SELECT username,hash,salt FROM \"DTUGRP04\".\"customers\" WHERE username=" + name +";" );
//			Class.forName("oracle.jdbc.driver.OracleDriver");  
//			Connection con = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
//			PreparedStatement ps = con.prepareStatement(
//					"select * from userreg where name=? and pass=?");  
//			ps.setString(1,name);  
//			ps.setString(2,pass);  
//			
			if(!res.next()) {
				return false;
			}
			
			if(Hash.SHA512(pass, res.getString(3)) == res.getString(2)) {
				return true;
			} else {
				return false;
			}
//			
//			ResultSet rs=ps.executeQuery();  
//			status=rs.next();  
//			
		}catch(Exception e) {
			System.out.println(e);
		}  
		return status;  
	}
}
