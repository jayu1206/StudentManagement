package manegement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.adminBean;
import abstrac.LoginDAO;

public class LoginOpr extends LoginDAO {

	ConnectionOpr connection= null;
	
	
	@Override
	public boolean getAuthentication(String userId, String psw) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		boolean flag= false;
		try{
			connection =  new ConnectionOpr();
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT * FROM admin where userid='"+userId+"' and password='"+psw+"' ";
		     rs = stmt.executeQuery(sql);
		     
		      if(rs.next()){
		    	  
		    	  flag=true;
		    	  System.out.println(flag);
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

	@Override
	public adminBean getUserName() {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		adminBean bean=null;
		try{
			connection =  new ConnectionOpr();
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT * FROM admin LIMIT 1";
		     rs = stmt.executeQuery(sql);
		     
		      if(rs.next()){
		    	  bean=new adminBean();
		    	  bean.setFirstName(rs.getString("firstname"));
		    	  bean.setLastName(rs.getString("lastname"));
		    	  bean.setUserID(rs.getString("userid"));
		    	  bean.setId(rs.getInt("id"));
		    	  bean.setEmail(rs.getString("email"));
		    	  bean.setPsw(rs.getString("password"));
		    	  
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
		
		return bean;
	}

}
