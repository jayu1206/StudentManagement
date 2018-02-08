package abstrac;

import bean.adminBean;

public abstract class RegistrationDAO {

	public abstract boolean insertUser(adminBean bean);

	public abstract boolean checkUserAlreadyExist(String text);

	public abstract boolean updateNewPassword(adminBean bean);

	
}
