package manegement;

import gui.CheckUsernamePassGUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import abstrac.LegalGUIDAO;

public class LegalGuiOpr extends LegalGUIDAO{

	ConnectionOpr connection=new ConnectionOpr();


		@Override
		public boolean checkUser() {
			// TODO Auto-generated method stub
			Statement stmt=null;
			Connection conn=null;
			ResultSet rs=null;
			boolean flag= false;
			try{
				
				conn=connection.getConnection();
				stmt = conn.createStatement();
			     
			     String sql = "SELECT * FROM admin";
			     rs = stmt.executeQuery(sql);
			     
			    //STEP 5: Extract data from result set
			      if(rs.next()){
			    	  flag=true;
			       }
			}catch(Exception e){
				e.printStackTrace();
				
				/*String message = e.getMessage();
			    if (message.contains("Access denied")) {
			     System.out.println("Call to GUI for ask DB User name and Password");
			     new CheckUsernamePassGUI("DB User name and Password is wrong");
			    }*/
			}finally{
				
				try {
					rs.close();
					stmt.close();
					conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
			return flag;
		}  
	    
	    
	    
	
	
}
