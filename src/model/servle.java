package model;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import netbank.Database;
import netbank.Hash;

/**
 * Servlet implementation class servle
 */
@WebServlet("/")
public class servle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//@Resource(lookup = "jdbc/db2")
    //private static DataSource myDataSource;
	static Database db;
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public servle() throws SQLException {
        super();
        
//        try {
//        	// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
//        	Class.forName("com.ibm.db2.jcc.DB2Driver");
//        	System.out.println("did it");
//        } catch (ClassNotFoundException e) {
//        	     e.printStackTrace();
//        	     System.out.println("NO NAME");
        		//Superfluous comment
//        }
        // TODO Auto-generated constructor stub
    }

    public static void main() throws SQLException {
    	
    }
    
	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Previously used to ocnfigure Database connection
//		Properties properties = new Properties(); //Properties object
//    	properties.put("user", "DTU09");
//    	properties.put("password", "FAGP2016");
//    	String url = "jdbc:db2://192.86.32.54:5040/DALLASB:retrieveMessagesFromServerOnGetMessage=true;emulateParameterMetaDataForZCalls=1;";
//    	Connection con;
//		try {
//			con = DriverManager.getConnection(url,properties);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		if(db == null) {
//			try {
//				db = new Database(myDataSource);
//	//			ResultSet res = db.getters("SELECT * FROM DTUGRP04.Sample");
//	//			while(res.next()) {
//	//				String name = res.getString(1);
//	//				String name2 = res.getString(2);
//	//				response.getWriter().println(name + " " + name2);
//	//			}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		initDB();
		response.sendRedirect("index.jsp");
		
		
//    	Connection connection = null;
//		try {
//			connection = myDataSource.getConnection();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Statement stmt = null;
//		try {
//			stmt = connection.createStatement();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			ResultSet result = stmt.executeQuery("SELECT * FROM DTUGRP04.Sample");
//			while(result.next()) {
//				String name = result.getString(1);
//				String name2 = result.getString(2);
//				response.getWriter().println(name + " " + name2);
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		//response.getWriter().append(con.toString());//"Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("sqldo") != null) {
			//System.out.println(request.getParameter("user"));
			db.setters(request.getParameter("user"));
        } else if(request.getParameter("register") != null){
        	db.setters("INSERT INTO \"DTUGRP04\".\"customers\" VALUES ('"+UUID.randomUUID()
        			+ "','"+request.getParameter("user")+"','"+request.getParameter("addr")
        			+ "','"+Hash.SHA512(request.getParameter("pass"),Hash.getSalt())
        			+ "','"+Hash.getSalt()
        			+ "','"+request.getParameter("country")+ "');");
        	System.out.println("INSERT INTO \"DTUGRP04\".\"customers\" VALUES ('"+UUID.randomUUID()
			+ "','"+request.getParameter("user")+"','"+request.getParameter("addr")
			+ "','"+Hash.SHA512(request.getParameter("pass"),Hash.getSalt())
			+ "','"+Hash.getSalt()
			+ "','"+request.getParameter("country")+ "');");
        } else {
        	System.out.println("NOTHING");
        }
		
		
		//response.getWriter().append("clicked");
		doGet(request, response);
	}
	
	public static Database getDb() {
		return db;
	}
	
	public static void initDB() {
		if(db == null) {
			try {
				db = new Database();//myDataSource);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
