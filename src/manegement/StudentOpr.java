package manegement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.GroupBean;
import bean.StudentBean;
import bean.StudentDecoding;
import bean.StudentRate;
import abstrac.StudentDAO;

public class StudentOpr extends StudentDAO {

	ConnectionOpr connection=new ConnectionOpr();
	
	
	@Override
	public ArrayList<StudentBean> getAllStudents(String id) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		ArrayList<StudentBean> list=new ArrayList<StudentBean>();
		StudentBean bean=null;
	try{
			
			conn=connection.getConnection();
			stmt = conn.createStatement();
			String sql="";
		    if(id.length()>0){
		    	sql = "SELECT * FROM student where groupid="+Integer.parseInt(id)+" ";
		    }else{
		    	sql = "SELECT * FROM student ";
		    	
		    }
			
		      
		     rs = stmt.executeQuery(sql);
		     
		      while(rs.next()){
		    	 bean = new StudentBean();
		    	 bean.setId(rs.getInt(1));
		    	 bean.setGroupId(rs.getInt(2));
		    	 bean.setStudFirstName(rs.getString(3));
		    	 bean.setGrade(rs.getInt(4));
		    	 bean.setDob(rs.getString(5));
		    	 bean.setStDate(rs.getString(6));
		    	 bean.setTeacher(rs.getString(7));
		    	 bean.setStudLastName(rs.getString(8));
		    	 bean.setAge(rs.getString(9));
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
	public StudentBean getAllStudentsWithDecod_Rate_Data(int id) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		
		Statement stmt2=null;
		ResultSet rs2=null;
		
		Statement stmt3=null;
		ResultSet rs3=null;
		
		
		Connection conn=null;
		
		//ArrayList<StudentBean> list=new ArrayList<StudentBean>();
		StudentBean bean=null;
		
		
	try{
			
			conn=connection.getConnection();
			stmt = conn.createStatement();
			String sql="";
		    if(id >0){
		    	sql = "SELECT * FROM student where id="+id+" ";
		    }else{
		    	sql = "SELECT * FROM student ";
		    	
		    }
			
		      
		     rs = stmt.executeQuery(sql);
		     ArrayList<StudentDecoding> listDeco=null;
		     ArrayList<StudentRate> listRate=null;
		      while(rs.next()){
		    	 bean = new StudentBean();
		    	 bean.setId(rs.getInt(1));
		    	 bean.setGroupId(rs.getInt(2));
		    	 bean.setStudFirstName(rs.getString(3));
		    	 bean.setGrade(rs.getInt(4));
		    	 bean.setDob(rs.getString(5));
		    	 bean.setStDate(rs.getString(6));
		    	 bean.setTeacher(rs.getString(7));
		    	 bean.setStudLastName(rs.getString(8));
		    	 bean.setAge(rs.getString(9));
		    	 
		    	 sql = "SELECT * FROM decoding where studId="+bean.getId()+" ";
		    	 stmt2 = conn.createStatement();
		    	 rs2 = stmt2.executeQuery(sql);
		    	 listDeco = new ArrayList<StudentDecoding>();
		    	 while(rs2.next()){
		    		 StudentDecoding decobean=new StudentDecoding();
		    		 decobean.setDecoId(rs2.getInt(1));
		    		 decobean.setStudId(bean.getId());
		    		 decobean.setWeek(rs2.getInt(3));
		    		 decobean.setDate(rs2.getString(4));
		    		 decobean.setBook(rs2.getInt(5));
		    		 decobean.setLesson(rs2.getInt(6));
		    		 decobean.setForm(rs2.getString(7));
		    		 decobean.setScore(rs2.getInt(8));
		    		 listDeco.add(decobean);
		    		 
		    	 }
		    	 rs2.close();
		    	 stmt2.close();
		    	 bean.setListDecoding(listDeco);
		    	 
		    	 sql = "SELECT * FROM rate where studId="+bean.getId()+" ";
		    	 stmt3 = conn.createStatement();
		    	 rs3= stmt3.executeQuery(sql);
		    	 listRate = new ArrayList<StudentRate>();
		    	 while(rs3.next()){
		    		 StudentRate ratebean=new StudentRate();
		    		 ratebean.setRateId(rs3.getInt(1));
		    		 ratebean.setStudId(rs3.getInt(2));
		    		 ratebean.setDate(rs3.getString(3));
		    		 ratebean.setText(rs3.getInt(4));
		    		 ratebean.setTime(rs3.getInt(5));
		    		 ratebean.setCwpm(rs3.getInt(6));
		    		 ratebean.setErrors(rs3.getInt(7));
		    		 ratebean.setWeek(rs3.getInt(8));
		    		 listRate.add(ratebean);
		    		 
		    	 }
		    	 rs3.close();
		    	 stmt3.close();
		    	 
		    	 bean.setListRate(listRate);
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
	public boolean insertStudent(StudentBean bean) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			 conn=connection.getConnection();
			
			//STEP 4: Execute a query
		      
		      String sql = "INSERT INTO student(groupid,studFirstName,grade,dob,startDt,teacher,studLastName,age) VALUES(?,?,?,?,?,?,?,?)";
		      
		      statement = conn.prepareStatement(sql);
		     
		      statement.setInt(1, bean.getGroupId());
		      statement.setString(2, bean.getStudFirstName());
		      statement.setInt(3, bean.getGrade());
		      statement.setString(4, bean.getDob());
		      statement.setString(5, bean.getStDate());
		      statement.setString(6, bean.getTeacher());
		      statement.setString(7, bean.getStudLastName());
		      statement.setString(8, bean.getAge());
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
	public boolean deleteStudent(String value) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			connection =  new ConnectionOpr();
			/* delete from decoding and rate data first   */
			
			flag = deleteDecodingData(Integer.parseInt(value));
			
			flag=deleteRateData(Integer.parseInt(value));
			
			conn=connection.getConnection();
			 String sql = "DELETE FROM  student  WHERE id=?";
		      System.out.println("SQL : "+sql);
		      statement = conn.prepareStatement(sql);
		      statement.setInt(1, Integer.parseInt(value));
		      statement.executeUpdate();
		      flag=true;
			
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

	@Override
	public boolean updateStudentInfo(StudentBean bean) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			connection =  new ConnectionOpr();
			 conn=connection.getConnection();
			
			//STEP 4: Execute a query
		      
		      String sql = "UPDATE student SET studFirstName=?,studLastName=?,grade=?,teacher=? WHERE id=?";
		      
		      statement = conn.prepareStatement(sql);
		     
		      statement.setString(1, bean.getStudFirstName());
		      statement.setString(2, bean.getStudLastName());
		      statement.setInt(3, bean.getGrade());
		      statement.setString(4, bean.getTeacher());
		      statement.setInt(5, bean.getId());
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
	public int getNextValue() {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		int i=0;
	try{
		connection =  new ConnectionOpr();
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT decoId FROM  decoding order by decoId desc LIMIT 1";
		     rs = stmt.executeQuery(sql);
		     
		      if(rs.next()){
		    	 i=rs.getInt("decoId");
		    	 
		    	  
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
		i++;
		return i;
	}

	
	@Override
	public int getNextValueRate() {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		int i=0;
	try{
			
		connection =  new ConnectionOpr();
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT rateId FROM  rate order by rateId desc LIMIT 1";
		     rs = stmt.executeQuery(sql);
		     
		      if(rs.next()){
		    	 i=rs.getInt("rateId");
		    	 
		    	  
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
		i++;
		return i;
	}

	
	
	@Override
	public boolean insertDecoderData(StudentDecoding bean) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			connection =  new ConnectionOpr();
			 conn=connection.getConnection();
			
			 
			 
			//STEP 4: Execute a query
		      
			 conn=connection.getConnection();
			 flag = checkDecodingData(bean.getDecoId());
			 String sql="";
			 if(flag){
				 sql = "UPDATE decoding  SET week=?,date=?,book=?,lesson=?,form=?,score=? WHERE decoId=?";
				 
				 statement = conn.prepareStatement(sql);
				     
				 statement.setInt(1, bean.getWeek());
			      statement.setString(2, bean.getDate());
			      statement.setInt(3, bean.getBook());
			      statement.setInt(4, bean.getLesson());
			      statement.setString(5, bean.getForm());
			      statement.setInt(6, bean.getScore());
			      statement.setInt(7, bean.getDecoId());
			      statement.execute();
				 
			 }else{
				 sql = "INSERT INTO decoding(studId,week,date,book,lesson,form,score) VALUES(?,?,?,?,?,?,?)";
				 
				 statement = conn.prepareStatement(sql);
			     
			    /*  statement.setInt(1, bean.getDecoId());*/
			      statement.setInt(1, bean.getStudId());
			      statement.setInt(2, bean.getWeek());
			      statement.setString(3, bean.getDate());
			      statement.setInt(4, bean.getBook());
			      statement.setInt(5, bean.getLesson());
			      statement.setString(6, bean.getForm());
			      statement.setInt(7, bean.getScore());
			      statement.execute();
				 
			 }
			     
		      
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
	public boolean checkDecodingData(int decodId) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		boolean i=false;
	try{
		connection =  new ConnectionOpr();
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT decoId FROM  decoding WHERE decoId="+decodId+"";
		     rs = stmt.executeQuery(sql);
		     
		      if(rs.next()){
		    	  i=true;
		    	 
		    	  
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
		
		return i;
	}

	@Override
	public boolean insertRateData(StudentRate bean) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			connection =  new ConnectionOpr();
			 conn=connection.getConnection();
			
			 
			 
			//STEP 4: Execute a query
		      
			 conn=connection.getConnection();
			 flag = checkRateData(bean.getRateId());
			 String sql="";
			 if(flag){
				 sql = "UPDATE rate  SET date=?,text=?,time=?,cwpm=?,errors=?,week=? WHERE rateId=?";
				 
				 statement = conn.prepareStatement(sql);
				     
				 
			      statement.setString(1, bean.getDate());
			      statement.setInt(2, bean.getText());
			      statement.setInt(3, bean.getTime());
			      statement.setInt(4, bean.getCwpm());
			      statement.setInt(5, bean.getErrors());
			      statement.setInt(6, bean.getWeek());
			      statement.setInt(7, bean.getRateId());
			      statement.execute();
				 
			 }else{
				 sql = "INSERT INTO rate(studId,date,text,time,cwpm,errors,week) VALUES(?,?,?,?,?,?,?)";
				 
				 statement = conn.prepareStatement(sql);
			     
			      statement.setInt(1, bean.getStudId());
			      statement.setString(2, bean.getDate());
			      statement.setInt(3, bean.getText());
			      statement.setInt(4, bean.getTime());
			      statement.setInt(5, bean.getCwpm());
			      statement.setInt(6, bean.getErrors());
			      statement.setInt(7, bean.getWeek());
			      statement.execute();
				 
			 }
			     
		      
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
	public boolean checkRateData(int rateId) {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		boolean i=false;
	try{
		connection =  new ConnectionOpr();
			conn=connection.getConnection();
			stmt = conn.createStatement();
		     
		     String sql = "SELECT rateId FROM  rate WHERE rateId="+rateId+"";
		     rs = stmt.executeQuery(sql);
		     
		      if(rs.next()){
		    	  i=true;
		    	 
		    	  
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
		
		return i;
	}

	@Override
	public boolean deleteDecodingData(int studId) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			connection =  new ConnectionOpr();
			
			/* delete from decoding and rate data first   */
			
			conn=connection.getConnection();
			 String sql = "DELETE FROM  decoding  WHERE studId=?";
		      System.out.println("SQL : "+sql);
		      statement = conn.prepareStatement(sql);
		      statement.setInt(1, studId);
		      statement.executeUpdate();
		      flag=true;
			
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

	@Override
	public boolean deleteRateData(int studId) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			connection =  new ConnectionOpr();
			/* delete from decoding and rate data first   */
			
			conn=connection.getConnection();
			 String sql = "DELETE FROM  rate  WHERE studId=?";
		      System.out.println("SQL : "+sql);
		      statement = conn.prepareStatement(sql);
		      statement.setInt(1, studId);
		      statement.executeUpdate();
		      flag=true;
			
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

	@Override
	public boolean deleteDecoding(String value) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			connection =  new ConnectionOpr();
			conn=connection.getConnection();
			 String sql = "DELETE FROM  decoding  WHERE decoId=?";
		      System.out.println("SQL : "+sql);
		      statement = conn.prepareStatement(sql);
		      statement.setInt(1, Integer.parseInt(value));
		      statement.executeUpdate();
		      flag=true;
			
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

	@Override
	public boolean deleteRate(String value) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		boolean flag=false;
		
		try{
			connection =  new ConnectionOpr();
			conn=connection.getConnection();
			 String sql = "DELETE FROM  rate  WHERE rateId=?";
		      System.out.println("SQL : "+sql);
		      statement = conn.prepareStatement(sql);
		      statement.setInt(1, Integer.parseInt(value));
		      statement.executeUpdate();
		      flag=true;
			
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

	@Override
	public StudentBean getStudentById(int studentID) {
		
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		StudentBean bean = new StudentBean();
		try{
			
			conn=connection.getConnection();
			stmt = conn.createStatement();
			String sql="";
		    sql = "SELECT * FROM student where id="+studentID;
		    rs = stmt.executeQuery(sql);
		     
		      while(rs.next()){
		    	 bean = new StudentBean();
		    	 bean.setId(rs.getInt(1));
		    	 bean.setGroupId(rs.getInt(2));
		    	 bean.setStudFirstName(rs.getString(3));
		    	 bean.setGrade(rs.getInt(4));
		    	 bean.setDob(rs.getString(5));
		    	 bean.setStDate(rs.getString(6));
		    	 bean.setTeacher(rs.getString(7));
		    	 bean.setStudLastName(rs.getString(8));
		    	 bean.setAge(rs.getString(9));
		    	  
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
