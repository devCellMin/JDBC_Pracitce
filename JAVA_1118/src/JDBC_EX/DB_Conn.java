package JDBC_EX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Conn {
	String ipAddr = "localhost/";
	String dbName = "member";
	String condition = "?serverTimeZone=Asia/Seoul";
	String dbURL = "jdbc:mysql://"+ipAddr+dbName+condition;
	String dbID = "root";
	String dbPWD = "";
	
	Connection conn;
	
	public DB_Conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connecting DB...");
			conn = DriverManager.getConnection(dbURL, dbID, dbPWD);
			System.out.println("DB Connection Success");
			
		}catch (SQLException e) {
			System.out.println("DB Connection Failed(SQL ERR) : "+ e.getMessage());
		}catch (ClassNotFoundException e) {
			System.out.println("DB Connection Failed(CNF ERR) : "+ e.getMessage());
		}catch (Exception e) {
			System.out.println("DB Connection Failed"+ e.getMessage());
		}
	}
	
}
