import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.ibatis.common.jdbc.ScriptRunner;

public class RunSqlScript {

	public void runSQL(){
//		ScriptEngineManager manager = new ScriptEngineManager();
//	    ScriptEngine engine = manager.getEngineByName("sql");
	    //this.getClass().getResourceAsStream("/data/mysql-5.5.15-winx64.msi")
	    try {
//	    	Reader reader = new InputStreamReader(this.getClass().getResource("/lib/student.sql"));
//	      
//	    	engine.eval(reader);
//	    	reader.close();
	    	Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/", "root", "admin");
			Statement stmt = null;
	    	
	    	ScriptRunner sr = new ScriptRunner(con, false, false);
	    	//String aSQLScriptFilePath = "/data/studentDemo.sql";
	    	//String aSQLScriptFilePath = this.getClass().getClassLoader().getResourceAsStream("/data/studentDemo.sql");
	    	//System.out.println(aSQLScriptFilePath);
	    	InputStream in = getClass().getResourceAsStream("/data/studentDemo.sql"); 
	    	//BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	    	
	    	Reader reader = new BufferedReader(
	    			new InputStreamReader(in));

			// Exctute script
			sr.runScript(reader);
	    	
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		
	}
	public static void main(String[] args) {
		
		RunSqlScript runsql = new RunSqlScript();
		runsql.runSQL();
	}


}
