package worldcup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection  {
	// DB_ID, DB_Password
	String db_Id= "admin";
	String db_Password= "1111";
	
	// set DB URL
	String dbHost = "52.79.219.67:56767";
	String dbName = "worldcup";
	String serverTimezone = "serverTimezone=Asia/Seoul";
	String dbURL= "jdbc:mysql://"+dbHost+"/"+dbName+"?"+serverTimezone;

	static Connection conn;
	static DBConnection dbconn;

	PreparedStatement pstmt;
	ResultSet rs;

	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DB connecting...");

			conn = DriverManager.getConnection(dbURL, db_Id ,db_Password);

			System.out.println("Connecting success!");
		} catch (SQLException e) {
			System.out.println("Connecting false"+e);
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot found JDBC driver..."+e);
		}
	}

	public static DBConnection getInstance() {
		if(dbconn == null) {
			dbconn = new DBConnection();
		}
		return dbconn;
	}
}
