import java.sql.*;
import javax.sql.*;

public class MysqlPathFinderDemo{

public static void main(String args[]){
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://localhost/mysql";

	   //  Database credentials
	   final String USER = "root";
	   final String PASS = "admin";

try {

	Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Connecting to database...");
    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery("select @@datadir");
      String Mysqlpath = "";

      while(res.next()){
          Mysqlpath=res.getString(1) ;
      }

      Mysqlpath = Mysqlpath.replace("Data", "bin"); 
      System.err.println("Mysql path is :"+Mysqlpath);
   } catch(Exception ee) {
	   ee.printStackTrace();
   }
 }
}