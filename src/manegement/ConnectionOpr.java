package manegement;

import gui.CheckUsernamePassGUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionOpr {

	// JDBC driver name and database URL
				static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
				   static final String DB_URL = "jdbc:mysql://localhost/students";

				  
				   
				   //  Database credentials
				     String USER = "";
				     String PASS ="";
				 
				   ConnectionOpr(){
					   //FileInputStream in;
					try {
						/*String resourceName = "First.properties";
						ClassLoader loader = Thread.currentThread().getContextClassLoader();
						Properties props = new Properties();
						try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
						    props.load(resourceStream);
						}*/
						
						InputStream in = this.getClass().getClassLoader().getResourceAsStream("./First.properties");
						Properties props = new Properties();
						props.load(in);
						USER = props.getProperty("User");
						PASS = props.getProperty("Password");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
						
				   }
				   
				
		    public Connection getConnection(){
		    	Connection conn = null;
		    	try{
		    	//STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		    	}catch(Exception e){
		    		e.printStackTrace();
		    		/*String message = e.getMessage();
		    		 if (message.contains("Access denied")) {
		    		      System.out.println("Call to GUI for ask DB User name and Password");      
		    		      new CheckUsernamePassGUI("DB User name and Password is wrong");
		    		     }*/
		    		 
		    		 
		    	}
		    	
				return conn;
		    	
		    }

}
