package manegement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.adminBean;
import abstrac.RegistrationDAO;

public class RegistrationOpr extends RegistrationDAO{

	ConnectionOpr connection= null;
	
	@Override
	public boolean insertUser(adminBean bean) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		try{
			connection =  new ConnectionOpr();
			 conn=connection.getConnection();
			
			//STEP 4: Execute a query
		      
		      String sql = "INSERT INTO admin(userid,password,firstname,lastname,email) VALUES(?,?,?,?,?)";
		      
		      statement = conn.prepareStatement(sql);
		     
		      statement.setString(1, bean.getUserID());
		      statement.setString(2, bean.getPsw());
		      statement.setString(3, bean.getFirstName());
		      statement.setString(4, bean.getLastName());
		      statement.setString(5, bean.getEmail());
		      statement.execute();
		      
		      flag=true;
		     
			 
		 }catch(Exception e){
			 flag=false;
			 e.printStackTrace();
			 
		 }finally{
				//STEP 6: Clean-up environment
		      try {
		    	  statement.close();
			     conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				 flag=false;
				e.printStackTrace();
			}
		      
			 
		 }
		
		
		
		return flag;
				
	}

	@Override
	public boolean checkUserAlreadyExist(String text) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		boolean flag= false;
		try{
			connection =  new ConnectionOpr();
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT * FROM admin where userid='"+text+"' ";
		     rs = stmt.executeQuery(sql);
		     
		      if(rs.next()){
		    	  flag=true;
		       }
		}catch(Exception e){
			e.printStackTrace();
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
