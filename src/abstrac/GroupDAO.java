package abstrac;

import java.util.ArrayList;

import bean.GroupBean;
import bean.StudentBean;

public abstract class GroupDAO {
	
	public abstract  ArrayList<GroupBean> getAllGroups();

	public abstract boolean insertGroups(GroupBean bean);

	public abstract boolean deleteGroups(String value);
	
	public abstract  GroupBean getGroup(int id);

	

}
