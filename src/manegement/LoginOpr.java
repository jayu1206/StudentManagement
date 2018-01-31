package manegement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import abstrac.LoginDAO;

public class LoginOpr extends LoginDAO {

	ConnectionOpr connection=new ConnectionOpr();
	
	@Override
	public boolean getAuthentication(String userId, String psw) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		boolean flag= false;
		try{
			
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT * FROM admin where userid='"+userId+"' and password='"+psw+"' ";
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
