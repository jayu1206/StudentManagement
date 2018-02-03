package process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	String flag = "N";    // Y = Installed  N = Not installed U= Call for DB user id and psw

	int count = 0;

	public void checkMySqlSystem(int count) {

		try {

			// First Check mysql installation
			
			
			flag = checkMysql();
			// System.out.println(flag);
			
			if(flag.equals("D")){
				new CheckUsernamePassGUI("DB User name and Password is wrong");
			}
			
			else if(flag.equals("Y")){
			
				new legalGUI();
				
				/*String resourceName = "First.properties";
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
*/				

			} 
			else {
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
					
				}else if (osname.contains("Mac")){
					System.out.println("Mac");
					System.out.println("Processing...");
					
					
					// mysql workbanch installer 
					
					
					
					File file = new File(this.getClass().getResource("/mysql/mysql-workbench-community-6.3.10-macos-x86_64.dmg").getFile());
					
					String mysqlpath = file.getAbsolutePath();
					System.out.println(mysqlpath);
					
					//String cmd = "/Users/mrugalpanchal/Desktop/Jar/mysql/mysql-5.7.21-1-macos10.13-x86_64.dmg"; 
					Runtime r = Runtime.getRuntime(); 
					ProcessBuilder p = new ProcessBuilder(new String[] { "/usr/bin/open", mysqlpath }); 
					Process pro; 
					try { 
						pro = p.start(); 
						InputStream is = pro.getInputStream(); 
						InputStreamReader isr = new InputStreamReader(is); 
						BufferedReader br = new BufferedReader(isr); 
						String line; 
						while ((line = br.readLine()) != null) { 
							System.out.println(line); 
							} 
						
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
					
					// Mysql installer
					
					 file = new File(this.getClass().getResource("/mysql/mysql-5.7.21-1-macos10.13-x86_64.dmg").getFile());
					
					 mysqlpath = file.getAbsolutePath();
					System.out.println(mysqlpath);
					
					//String cmd = "/Users/mrugalpanchal/Desktop/Jar/mysql/mysql-5.7.21-1-macos10.13-x86_64.dmg"; 
					 r = Runtime.getRuntime(); 
					 p = new ProcessBuilder(new String[] { "/usr/bin/open", mysqlpath }); 
					
					try { 
						pro = p.start(); 
						InputStream is = pro.getInputStream(); 
						InputStreamReader isr = new InputStreamReader(is); 
						BufferedReader br = new BufferedReader(isr); 
						String line; 
						while ((line = br.readLine()) != null) { 
							System.out.println(line); 
							} 
						
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}			
				
			}

		} catch (Exception e) {
			//System.out.println("error===" + e.getMessage());
			e.printStackTrace();
		}

	}

	
	private String checkMysql() {
		// TODO Auto-generated method stub
		try {

			System.out.println("First check mysql server .........");
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
				flag = "N";
			} else {
				System.out.println("Mysql is intsalled");
				flag = "Y";
			}
			
			
			
			
			if(flag.equals("N")){
				
				System.out.println("First check XAMPP server .........");
				
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
					System.out.println("XAMPP is not installed");
					flag = "N";
				} else {
					System.out.println("XAMPP is intsalled");
					flag = "Y";
				}
			}
			
			
			

		} catch (SQLException se) {

			
			String message = se.getMessage();
			if (message.contains("Access denied")) {
				System.out.println("Call to GUI for ask DB User name and Password");
				flag="D";
				//new CheckUsernamePassGUI("DB User name and Password is wrong");
			}
		} catch (Exception ee) {
			ee.printStackTrace();

		}

		return flag;
	}

	/*public static void main(String args[]) {

		try {
			ProcessExe objExe = new ProcessExe();
			objExe.checkMySqlSystem(0);
		} catch (Exception e) {

			e.printStackTrace();
			System.exit(0);
		}

	}*/

}
