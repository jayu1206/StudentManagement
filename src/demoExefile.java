import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class demoExefile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* String command = "H:\\WebDeveloper\\Software\\python-2.7.13.amd64.msi";
		  try {
			Process p = Runtime.getRuntime().exec("cmd /c "+command);
			
			System.out.println("done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*Pattern dateFrmtPtrn = 
	            Pattern.compile("(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)");
	     
		String str="12-24-2018";
		
		Matcher mtch = dateFrmtPtrn.matcher(str);
        if(mtch.matches()){
            System.out.println("true");
        }else{
        	
        	System.out.println("false");
        }*/
		//System.out.println(System.getProperty("os.name"));
		
		// System.getProperties().list(System.getProperty("os.name"));
		
		
		
		
		String cmd = "/Users/mrugalpanchal/Desktop/Jar/data/mysql-5.7.21-1-macos10.13-x86_64.dmg"; 
		Runtime r = Runtime.getRuntime(); 
		ProcessBuilder p = new ProcessBuilder(new String[] { "/usr/bin/open", cmd }); 
		Process pro; 
		try { 
			pro = p.start(); 
			InputStream is = pro.getInputStream(); 
			InputStreamReader isr = new InputStreamReader(is); 
			BufferedReader br = new BufferedReader(isr); 
			String line; 
			while ((line = br.readLine()) != null) { 
				System.out.println(line); 
				} 
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
