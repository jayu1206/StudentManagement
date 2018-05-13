package manegement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.GroupBean;
import abstrac.GroupDAO;

public class GroupOpr extends GroupDAO {
	
	ConnectionOpr connection=new ConnectionOpr();

	@Override
	public ArrayList<GroupBean> getAllGroups() {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		ArrayList<GroupBean> list=new ArrayList<GroupBean>();
		GroupBean bean=null;
	try{
			
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT * FROM groups ";
		     rs = stmt.executeQuery(sql);
		     
		      while(rs.next()){
		    	  bean = new GroupBean();
		    	  bean.setGroupID(rs.getInt(1));
		    	  bean.setGroupName(rs.getString(2));
		    	  bean.setStartDate(rs.getString(3));
		    	  list.add(bean);
		    	  
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
		
		return list;
	}

	@Override
	public boolean insertGroups(GroupBean bean) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		try{
			 conn=connection.getConnection();
			
			//STEP 4: Execute a query
		      
		      String sql = "INSERT INTO groups(groupName,startDate) VALUES(?,?)";
		      
		      statement = conn.prepareStatement(sql);
		     
		      statement.setString(1, bean.getGroupName());
		      statement.setString(2, bean.getStartDate());
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
	public boolean deleteGroups(String value) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			
			
			ArrayList<String> studlist=getStudentlistBaseonGroupID(value);
			
			StudentOpr studOpr=new StudentOpr();
			
			if(studlist.get(0)!=null){
				for(String val : studlist){
					flag = studOpr.deleteStudent(val);
				}
			}
			
			if(flag){
				conn=connection.getConnection();
				 String sql = "DELETE FROM  groups  WHERE groupId=?";
			      System.out.println("SQL : "+sql);
			      statement = conn.prepareStatement(sql);
			      statement.setInt(1, Integer.parseInt(value));
			      statement.executeUpdate();
			      flag=true;
			}
			  
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//STEP 6: Clean-up environment
		      try {
		    	  statement.close();
			     conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
			 
		 }
		
		return flag;
	}

	private ArrayList<String> getStudentlistBaseonGroupID(String id) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		ArrayList<String> list =new ArrayList<String>();
		try{
			
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT id FROM student where groupid="+id+"  ";
		     rs = stmt.executeQuery(sql);
		    
		      while(rs.next()){
		    	  
		    	  list.add(rs.getInt("id")+"");
		    	  
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
		
		return list;
	}

	@Override
	public GroupBean getGroup(int id) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		//ArrayList<GroupBean> list=new ArrayList<GroupBean>();
		GroupBean bean=null;
	try{
			
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT * FROM groups where groupId="+id+"  ";
		     rs = stmt.executeQuery(sql);
		     
		      while(rs.next()){
		    	  bean = new GroupBean();
		    	  bean.setGroupID(rs.getInt(1));
		    	  bean.setGroupName(rs.getString(2));
		    	  bean.setStartDate(rs.getString(3));
		    	 // list.add(bean);
		    	  
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

	@Override
	public boolean updateGroups(GroupBean bean) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			connection =  new ConnectionOpr();
			 conn=connection.getConnection();
			
			//STEP 4: Execute a query
			
		      String sql = "UPDATE groups SET groupName=?,startDate=? WHERE groupID=?";
		      
		      statement = conn.prepareStatement(sql);
		     
		      statement.setString(1, bean.getGroupName());
		      statement.setString(2, bean.getStartDate());
		      statement.setInt(3, bean.getGroupID());
		      statement.executeUpdate();
		      
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

}
