package process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;

import sun.util.logging.resources.logging;

import com.ibatis.common.jdbc.ScriptRunner;
import com.orsonpdf.font.TrueTypeFont;

import gui.CheckOSGUI;
import gui.CheckUsernamePassGUI;
import gui.LoginGUI;
import gui.legalGUI;
import gui.welcomeGUI;

public class ProcessExe extends JFrame{

/*	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost/mysql";*/

	 Connection c = null;
	 Statement stmt = null;
	 ResultSet rs = null;
	public void checkMySqlSystem(int count) {

		try {
			boolean flag = false;
			
			 Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:students.db");
	            
	         System.out.println("Opened database successfully");
	         
	         
	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM admin" );
	         
	         if(rs.next()){
	        	 flag =true;
	         }
	         
	         if(flag){
	        	 
	        	 rs.close();
	        	 stmt.close();
	        	 c.close();
	        	 new LoginGUI();
	         }
	        	 
			

		} catch (Exception e) {
			//System.out.println("error===" + e.getMessage());
			//e.printStackTrace();
			try {
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				
		        c.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            String str = e.getMessage();
            if(str.contains("no such table")){
            	 try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:students.db");
	                 
	                // c.setAutoCommit(false);
	                 System.out.println("Opened database successfully");
	                 
	                 Statement stmt1 = null;
	     	    	
	     	    	ScriptRunner sr = new ScriptRunner(c, false, false);
	     	    	InputStream in2 = getClass().getResourceAsStream("/mysql/students.db.sql"); 
	     	    	
	     	    	Reader reader = new BufferedReader(
	     	    			new InputStreamReader(in2));

	     			// Exctute script
	     			sr.runScript(reader);
	     			
	     			System.out.println("script run done");
	     			//flag=true;
	                 
	     			
	                 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					 try {
						 if(rs!=null){
								rs.close();
							}
							/*if(stmt!=null){
								stmt.close();
							}*/
					        c.close();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
				           
						
					}
            	 
            	 new legalGUI();
            	 
            }   
			
		}

	}

	
	
}
