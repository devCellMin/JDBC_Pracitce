package db_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_conn {
   String dbURL= "jdbc:mysql://localhost/member?serverTimezone=Asia/Seoul";
   String id= "root";
   String pass= "1111";
   
   Connection conn;
   PreparedStatement pstmt;
   ResultSet rs;
   
   public db_conn() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("DB connecting...");
         conn= DriverManager.getConnection(dbURL,id ,pass);
         System.out.println("Connecting success!");
      } catch (SQLException e) {
         System.out.println("Connecting false"+e);
      }catch (ClassNotFoundException e) {
         System.out.println("Cannot found JDBC driver..."+e);
      }
   }
   
   public static void main(String [] args) {
      new db_conn();
   }
}