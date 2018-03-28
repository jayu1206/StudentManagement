package manegement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import com.ibatis.common.jdbc.ScriptRunner;

import abstrac.ProcessDAO;

public class ProcessOpr extends ProcessDAO{

	int count = 0;
	@Override
	public boolean addProperty(String uname, String pass) {
		// TODO Auto-generated method stub
			
		try {
			
			/*ClassLoader classLoader = getClass().getClassLoader();
			@SuppressWarnings("static-access")
			File file = new File(this.getClass().getClassLoader().getResourceAsStream("First.properties"));
			System.out.println("file.getAbsolutePath()  "+file.getAbsolutePath());*/
			//System.out.println(file.getPath());
			
			/*String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();	
			System.out.println("Full path : "+path);
			String[] arrPath=path.split("/");
			String projectFolderPath="";
			for (int i=0;i<=arrPath.length-2;i++)
				projectFolderPath=projectFolderPath+arrPath[i]+"/";			
			
			
			System.out.println(projectFolderPath + "First.properties");*/
			//File compiledJar = resource.getParentFile();
			//File newResource = new File(compiledJar.getParentFile(), fileName);
			
			
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("./First.properties");
			Properties props = new Properties();
			props.load(in);
			String dbcn=props.getProperty("DbCount");
			in.close();
			
			File resource = new File(this.getClass().getClassLoader().getResource("First.properties").getFile());
			String fileName = resource.getName();
			
			System.out.println("The file you want (Hope so, I got no IDE): " + fileName.toString());
			
			
				FileOutputStream out = new FileOutputStream(fileName);
				 props = new Properties();
				props.setProperty("User", uname);
				props.setProperty("Password", pass);
				count = 1;
				props.setProperty("Count", count+"");
				props.setProperty("DbCount", dbcn);
				props.store(out, null);
				System.out.println("Config Property Successfully Updated..");
				out.close();	
				return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}	
		return false;
		
	}
	@Override
	public boolean runSQLFile() {
		// TODO Auto-generated method stub
		boolean flag= false;
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	
	    	InputStream in = this.getClass().getClassLoader().getResourceAsStream("./First.properties");
			Properties props = new Properties();
			props.load(in);
			String userName = props.getProperty("User");
			String psw = props.getProperty("Password");
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/",userName,psw);
			
			/*Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/","root","admin");*/
			Statement stmt = null;
	    	
	    	ScriptRunner sr = new ScriptRunner(con, false, false);
	    	InputStream in2 = getClass().getResourceAsStream("/data/student.sql"); 
	    	
	    	Reader reader = new BufferedReader(
	    			new InputStreamReader(in2));

			// Exctute script
			sr.runScript(reader);
			flag=true;
			
			
			
	    	in.close();
			File resource = new File(this.getClass().getClassLoader().getResource("First.properties").getFile());
			String fileName = resource.getName();
			
			//System.out.println("The file you want (Hope so, I got no IDE): " + fileName.toString());
			
			
				FileOutputStream out = new FileOutputStream(fileName);
				Properties props1 = new Properties();
				count = 1;
				props1.setProperty("User", userName);
				props1.setProperty("Password", psw);
				count = 1;
				props1.setProperty("Count", count+"");
				props1.setProperty("DbCount", count+"");
				
				props1.store(out, null);
				//System.out.println("Config Property Successfully Updated..");
				out.close();	
			
				flag=true;
			
	    } catch (Exception e) {
	      e.printStackTrace();
	      flag=false;
	    }
		
		return flag;
	}


}
