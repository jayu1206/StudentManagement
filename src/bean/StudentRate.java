package bean;

public class StudentRate {
	private int rateId;
	private int studId;
	private String date;
	private int text;
	private int time;
	private int cwpm;
	private int errors;
	private int week;
	
	
	public int getRateId() {
		return rateId;
	}
	public void setRateId(int rateId) {
		this.rateId = rateId;
	}
	public int getStudId() {
		return studId;
	}
	public void setStudId(int studId) {
		this.studId = studId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getText() {
		return text;
	}
	public void setText(int text) {
		this.text = text;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getCwpm() {
		return cwpm;
	}
	public void setCwpm(int cwpm) {
		this.cwpm = cwpm;
	}
	public int getErrors() {
		return errors;
	}
	public void setErrors(int errors) {
		this.errors = errors;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}

}
