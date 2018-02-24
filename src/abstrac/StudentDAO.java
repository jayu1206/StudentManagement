package abstrac;

import java.util.ArrayList;
import java.util.List;

import bean.StudentBean;
import bean.StudentDecoding;
import bean.StudentRate;

public abstract class StudentDAO {

	public abstract ArrayList<StudentBean> getAllStudents(String classId); 
	
	public abstract boolean insertStudent(StudentBean bean);

	public abstract boolean deleteStudent(String value);

	public abstract StudentBean getAllStudentsWithDecod_Rate_Data(int id);

	public abstract boolean updateStudentInfo(StudentBean bean);

	public abstract int getNextValue();
	
	public abstract int getNextValueRate();

	public abstract boolean insertDecoderData(StudentDecoding bean);
	
	public abstract boolean checkDecodingData(int decodId);

	public abstract boolean insertRateData(StudentRate bean);
	
	public abstract boolean checkRateData(int rateId);
	
	public abstract boolean deleteDecodingData(int decodId);
	
	public abstract boolean deleteRateData(int recordId);

	public abstract boolean deleteDecoding(String value);

	public abstract boolean deleteRate(String value);
	
	public abstract StudentBean getStudentById(int studentID);

	public abstract List getAvgofStud(List ids);

	public abstract StudentBean getStudentbyDecodingAndRatingByweek(int id, String txtBegin, String txtend);

	public abstract StudentBean getStudentbyDecodingAndRatingByText(int id, String txtBegin, String txtEnd);
	
}
