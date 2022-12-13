package db_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_select {
   String dbURL= "jdbc:mysql://localhost/member?serverTimezone=Asia/Seoul";
   String id= "root";
   String pass= "1111";
   
   Connection conn;
   PreparedStatement pstmt;
   ResultSet rs = null;
   
   public void db_select() {
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
   
   public void getListAll() {
      try {
         String qurey= "Select * from member";
         pstmt= conn.prepareStatement(qurey);
         rs = pstmt.executeQuery();//데이터베이스 데이터를 가져옴
         
         while (rs.next()) {
            String name= rs.getString(1);
            String age= rs.getString(2);
            String gender= rs.getString(3);
            String addr= rs.getString(4);
            String phone= rs.getString(5);
            String email= rs.getString(6);
            System.out.println(name+" "+age+" "+gender+" "+addr+" "+phone+" "+email);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public static void main(String[] args) {
	  db_select db_test= new db_select();
      db_test.db_select();
      db_test.getListAll();
   }

}