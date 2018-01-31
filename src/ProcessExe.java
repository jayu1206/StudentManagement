

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;

public class ProcessExe {

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost/mysql";

	// Database credentials
	final String USER = "root";
	final String PASS = "admin";
	boolean flag = false;
	
	int count = 0;
	public void checkMySqlSystem(int count){

		
		try {
				
				flag = checkMysql();
				System.out.println(flag);
				if (flag){
					//true
					FileInputStream in = new FileInputStream("First.properties");
					Properties props = new Properties();
					props.load(in);
					/*String user = props.getProperty("User");
					String pass = props.getProperty("Password");*/
					count = Integer.parseInt(props.getProperty("Count"));
					
					if (count != 0){
						
						System.out.println("Valid");
						
					}else{
						//ask for username password
						System.out.println("Invalid");
					}
					
//					System.out.println("User name : "+props.getProperty("User"));
//					System.out.println("Password : "+props.getProperty("Password"));
//					System.out.println("Count : "+props.getProperty("Count"));
					
					in.close();
					
				}else{
					//false
				}
				
				
//				FileInputStream in = new FileInputStream("First.properties");
//				Properties props = new Properties();
//				props.load(in);
//				System.out.println("User name : "+props.getProperty("User"));
//				System.out.println("Password : "+props.getProperty("Password"));
//				System.out.println("Count : "+props.getProperty("Count"));
//				count = Integer.parseInt(props.getProperty("Count"));
//				in.close();


				if (count == 0) {
					// Database credentials				
					FileOutputStream out = new FileOutputStream("First.properties");
					Properties props = new Properties();
					props.setProperty("User", USER);
					props.setProperty("Password", PASS);
					count = 1;
					props.setProperty("Count", count+"");
					props.store(out, null);				
					out.close();				

					System.out.println("Config Property Successfully Updated..");
				}else {
					//call to main GUI 
				}			

			} catch (Exception e) {
				System.out.println("error===" + e.getMessage());
				e.printStackTrace();
			}

		
		
		
	}
	
	
	private boolean checkMysql() {
		// TODO Auto-generated method stub
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select @@datadir");
			String Mysqlpath = "";

			while (res.next()) {
				Mysqlpath = res.getString(1);
			}

			Mysqlpath = Mysqlpath.replace("Data", "bin");
			System.err.println("Mysql path is :" + Mysqlpath);
			if (Mysqlpath.length() == 0){
				//Installation of Mysql
				System.out.println("Mysql is not installed");
				Runtime r = Runtime.getRuntime();
				Process p = null;
				String s = "D:/Softwares/putty_beta_0_66.exe";
				p = r.exec(s);
				flag = false;
			}else {
				System.out.println("Mysql is intsalled");
				flag = true;
			}
			
		}catch(SQLException se){
			
			String message = se.getMessage();
            if (message.contains("Access denied"))
            {
            	System.out.println("Call to GUI for ask DB User name and Password");
            	
            }
		} 
		catch (Exception ee) {
			ee.printStackTrace();
			
		}

		return flag;
	}

	
	public static void main(String args[]){  
		
		try{
			ProcessExe objExe=new ProcessExe();
			objExe.checkMySqlSystem(0);
			
		}catch(Exception e){
			
			System.exit(0);
		}
		
		
	}
	
	
	
	

	
	
}
