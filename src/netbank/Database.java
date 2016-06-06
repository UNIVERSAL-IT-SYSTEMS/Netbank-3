package netbank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class Database {
	
	@Resource(lookup = "jdbc/db2")
	private DataSource myDataSource;
	
	Connection connection;
	Statement stmt;
	
	public Database(DataSource dats) throws SQLException {
		myDataSource = dats;
	}
	
	public ResultSet getters(String qwy) {
		try {
			connection = myDataSource.getConnection();
			stmt = connection.createStatement();
			return stmt.executeQuery(qwy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;	
	}
	
}
