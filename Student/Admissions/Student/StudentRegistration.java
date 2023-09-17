package Admissions.Student;

import java.time.LocalDate;
public class StudentRegistration {
	
	private  int RegID;
	private Name Studentname;
	private Name fathername;
	
	private LocalDate DOB;
	public LocalDate getDOB() {
		return DOB;
	}
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	private double Intermediate_Marks;
	private String Gender;
	private long Phone_No;
	private String mail;
	private Address address;
	private long Adhar_no;
	private CoursePriority priority;
	
	public int getRegID() {
		return RegID;
	}
	public void setRegID(int regID) {
		RegID = regID;
	}
	public Name getStudentname() {
		return Studentname;
	}
	public void setStudentname(Name student_Name) {
		Studentname = student_Name;
	}
	public Name getFathername() {
		return fathername;
	}
	public void setFathername(Name fathername) {
		this.fathername = fathername;
	}
	
	public double getIntermediate_Marks() {
		return Intermediate_Marks;
	}
	public void setIntermediate_Marks(double intermediate_Marks) {
		Intermediate_Marks = intermediate_Marks;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public long getPhone_No() {
		return Phone_No;
	}
	public void setPhone_No(long phone_No) {
		Phone_No = phone_No;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long getAdhar_no() {
		return Adhar_no;
	}
	public void setAdhar_no(long adhar_no) {
		Adhar_no = adhar_no;
	}
	public CoursePriority getPriority() {
		return priority;
	}
	public void setPriority(CoursePriority priority) {
		this.priority = priority;
	}

}
