
public class ChkOS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//System.out.println(System.getProperty("os.name"));
		String osname = System.getProperty("os.name");
		if (osname.contains("Windows")){
			System.out.println("Windows PC");
		}else if (osname.contains("Linux")){
			System.out.println("Linux PC");
		}else{
			System.out.println("MAC PC");
		}
	}

}
