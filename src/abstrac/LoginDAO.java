package abstrac;

import bean.adminBean;

public abstract class LoginDAO {

	public abstract boolean getAuthentication(String userId, String psw) ;
	
	public abstract adminBean getUserName();

}
