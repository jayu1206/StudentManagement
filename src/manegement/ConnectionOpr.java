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
				static final String JDBC_DRIVER = "org.sqlite.JDBC";  
				static final String DB_URL = "jdbc:sqlite:students.db";

		    public Connection getConnection(){
		    	Connection conn = null;
		    	try{
		    	//STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL);
		    	}catch(Exception e){
		    		e.printStackTrace();
		    	}
		    	
				return conn;
		    	
		    }

}
