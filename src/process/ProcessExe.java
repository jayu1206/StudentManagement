package process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;

import gui.CheckOSGUI;
import gui.CheckUsernamePassGUI;
import gui.legalGUI;
import gui.welcomeGUI;

public class ProcessExe extends JFrame{

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost/mysql";

	// Database credentials
	final String USER = "root";
	final String PASS = "admin";
	boolean flag = false;

	int count = 0;

	public void checkMySqlSystem(int count) {

		try {

			flag = checkMysql();
			// System.out.println(flag);
			if (flag) {
			
				
				String resourceName = "First.properties";
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				Properties props = new Properties();
				try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
					System.out.println(resourceStream.toString());
				    props.load(resourceStream);
				}
				count = Integer.parseInt(props.getProperty("Count"));

				if (count != 0) {
					//System.out.println("Welcome page");
					new legalGUI();
				} else {
					// ask for username password
					// System.out.println("Invalid");
					this.setVisible(false);					
					new legalGUI();
				}
				

			} else {
				// Install Process of Mysql
				//new CheckOSGUI();
				String osname = System.getProperty("os.name");
				if (osname.contains("Windows")){
					
					System.out.println("Windows PC");
					System.out.println("Processing...");
					Runtime r = Runtime.getRuntime();
					Process p = null;  
					File file = new File(this.getClass().getResource("/mysql/mysql-5.5.15-winx64.msi").getFile());
					String mysqlpath = file.getAbsolutePath();
					System.out.println(mysqlpath);
					try {
						p = r.exec("cmd /c "+mysqlpath);
						System.out.println("Install Success");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("Install Not Success");
						e1.printStackTrace();
					}
				}else if (osname.contains("Linux")){
					System.out.println("Linux PC");
				}else{
					System.out.println("MAC PC");
				}			
				
			}

		} catch (Exception e) {
			//System.out.println("error===" + e.getMessage());
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
			if (Mysqlpath.length() == 0) {
				// Installation of Mysql
				System.out.println("Mysql is not installed");
				flag = false;
			} else {
				System.out.println("Mysql is intsalled");
				flag = true;
			}
			
			if(flag==false){
				
				
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Connecting to database...");
				 conn = DriverManager.getConnection(DB_URL, USER, "");
				 stmt = conn.createStatement();
				 res = stmt.executeQuery("select @@datadir");
				 Mysqlpath = "";

				while (res.next()) {
					Mysqlpath = res.getString(1);
				}

				Mysqlpath = Mysqlpath.replace("Data", "bin");
				System.err.println("Mysql path is :" + Mysqlpath);
				if (Mysqlpath.length() == 0) {
					// Installation of Mysql
					System.out.println("Mysql is not installed");
					flag = false;
				} else {
					System.out.println("Mysql is intsalled");
					flag = true;
				}
			}
			
			
			

		} catch (SQLException se) {

			String message = se.getMessage();
			if (message.contains("Access denied")) {
				System.out.println("Call to GUI for ask DB User name and Password");
				
				new CheckUsernamePassGUI("DB User name and Password is wrong");
			}
		} catch (Exception ee) {
			ee.printStackTrace();

		}

		return flag;
	}

	public static void main(String args[]) {

		try {
			ProcessExe objExe = new ProcessExe();
			objExe.checkMySqlSystem(0);
		} catch (Exception e) {

			System.exit(0);
		}

	}

}
