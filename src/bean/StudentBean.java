package bean;

import java.util.ArrayList;

public class StudentBean {
	private int id=0;
	private int groupId=0;
	private String studFirstName="";
	private String studLastName="";
	private int grade=0;
	private String dob="";
	private String stDate="";
	private String teacher="";
	ArrayList<StudentDecoding> listDecoding=null;
	ArrayList<StudentRate> listRate=null;
	private String age="";
	
	
	
	
	public ArrayList<StudentDecoding> getListDecoding() {
		return listDecoding;
	}
	public void setListDecoding(ArrayList<StudentDecoding> listDecoding) {
		this.listDecoding = listDecoding;
	}
	public ArrayList<StudentRate> getListRate() {
		return listRate;
	}
	public void setListRate(ArrayList<StudentRate> listRate) {
		this.listRate = listRate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getStDate() {
		return stDate;
	}
	public void setStDate(String stDate) {
		this.stDate = stDate;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getStudFirstName() {
		return studFirstName;
	}
	public void setStudFirstName(String studFirstName) {
		this.studFirstName = studFirstName;
	}
	public String getStudLastName() {
		return studLastName;
	}
	public void setStudLastName(String studLastName) {
		this.studLastName = studLastName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

}
