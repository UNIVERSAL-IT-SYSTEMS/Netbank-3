package netbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.jmx.snmp.Timestamp;

public class Database {

	// @Resource(lookup = "jdbc/db2")
	// private DataSource myDataSource;

	Connection connection;
	Statement stmt;

	private String name = "com.ibm.db2.jcc.DB2Driver";
	private String user = "DTU09";
	private String password = "FAGP2016";
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB:retrieveMessagesFromServerOnGetMessage=true;emulateParameterMetaDataForZCalls=1;";

	// DataSource dats
	public Database() throws SQLException {
		// myDataSource = dats;

	}

	public ResultSet getters(String qwy) {
		ResultSet res = null;
		try {
			Class.forName(name);
			connection = DriverManager.getConnection(url, user, password);
			// connection = myDataSource.getConnection();
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
			// connection = myDataSource.getConnection();
			stmt = connection.createStatement();
			stmt.execute(qwy);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setters(ArrayList<String> qwy) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			// connection = myDataSource.getConnection();
			stmt = connection.createStatement();
			System.out.println(new Timestamp());
			for (int i = 0; i < qwy.size(); i++) {
				stmt.addBatch(qwy.get(i));
			}
			int[] executedAll = stmt.executeBatch();
			System.out.println(new Timestamp());
			Boolean did = false;
			for (int i = 0; i < executedAll.length; i++) {
				if (executedAll[i] == 0) {
					return false;
				}
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
