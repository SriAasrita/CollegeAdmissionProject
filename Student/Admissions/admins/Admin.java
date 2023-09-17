package Admissions.admins;

import Admissions.Student.StudentRegistration;

public class Admin {
	private int RegID;
	private double InterMark;
	private String Priority_1;
	private String Priority_2;
	private String Priority_3;
	private StudentRegistration stdRegForm;
	private String AllocatedSeat;
	
	
	
	public StudentRegistration getStdRegForm() {
		return stdRegForm;
	}
	public void setStdRegForm(StudentRegistration stdRegForm) {
		this.stdRegForm = stdRegForm;
	}
	public String getAllocatedSeat() {
		return AllocatedSeat;
	}
	public void setAllocatedSeat(String allocatedSeat) {
		this.AllocatedSeat = allocatedSeat;
	}
	
	public int getRegID() {
		return RegID;
	}
	public void setRegID(int regID) {
		RegID = regID;
	}
	public double getInterMark() {
		return InterMark;
	}
	public void setInterMark(double interMark) {
		InterMark = interMark;
	}
	public String getPriority_1() {
		return Priority_1;
	}
	public void setPriority_1(String priority_1) {
		Priority_1 = priority_1;
	}
	public String getPriority_2() {
		return Priority_2;
	}
	public void setPriority_2(String priority_2) {
		Priority_2 = priority_2;
	}
	public String getPriority_3() {
		return Priority_3;
	}
	public void setPriority_3(String priority_3) {
		Priority_3 = priority_3;
	}
	
}
