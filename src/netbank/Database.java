package netbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.eclipse.persistence.internal.queries.StatementQueryMechanism;

public class Database {
	
	//@Resource(lookup = "jdbc/db2")
	//private DataSource myDataSource;
	
	Connection connection;
	Statement stmt;
	
	private String name = "com.ibm.db2.jcc.DB2Driver";
	private String user = "DTU09";
	private String password = "FAGP2016";
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB:retrieveMessagesFromServerOnGetMessage=true;emulateParameterMetaDataForZCalls=1;";

	
	//DataSource dats
	public Database() throws SQLException {
		//myDataSource = dats;
		
	}
	
	public ResultSet getters(String qwy) {
		ResultSet res = null;
		try {
			Class.forName(name);
			connection = DriverManager.getConnection(url, user, password);
			//connection = myDataSource.getConnection();
			stmt = connection.createStatement();
			res = stmt.executeQuery(qwy);
			return res;
			
		} catch (SQLException e) {
			System.out.println("SQLEXCEPTION");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("CLASSNOTFOUND");
			e.printStackTrace();
		}	
		return null;	
	}
	
	public boolean setters(String qwy) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			//connection = myDataSource.getConnection();
			stmt = connection.createStatement();
			stmt.execute(qwy);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
