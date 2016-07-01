package edu.csustan.gradingsystem.domain;

/*
 * Author: Brandon Halpin
 * Modified by: Thomas Falasco
 * 
 * Defines the class for an Assignment
 * 
 */


import java.io.File;
import java.sql.Date;
import java.sql.Time;

public class Assignment {

	// Attributes
	private String Title;
	private int AssignmentNo;
	private String Term;
	private boolean IsAvailable;
	private String AssignDesc;
	private Date DueDate;
	private Time TimeDue;
	private double MaximumScore;
	private double FcnScore;
	private double StyleScore;
	private int FacultyID;
	private int CourseSec;
	private String CourseID;

	// Constructors
	public Assignment() {
	}
	

	public Assignment(String Title, int AssignmentNo, String Term, boolean IsAvailable,
			String AssignDesc, Date DueDate, Time TimeDue,
			double MaximumScore, double FcnScore,
			double StyleScore, int FacultyID, int CourseSec,
			String CourseID) {
		this.Title = Title;
		this.AssignmentNo = AssignmentNo;
		this.Term = Term;
		this.IsAvailable = IsAvailable;
		this.AssignDesc = AssignDesc;
		this.DueDate = DueDate;
		this.TimeDue = TimeDue;
		this.MaximumScore = MaximumScore;
		this.FcnScore = FcnScore;
		this.StyleScore = StyleScore;
		this.FacultyID = FacultyID;
		this.CourseSec = CourseSec;
		this.CourseID = CourseID;
	}


	public Assignment(String Title) {
		this.Title = Title;
	}

	// Methods


	// Accessors and Modifiers
	public String getTitle() {
		return Title;
	}

	public String getTerm() {
		return Term;
	}
	public void setTerm(String Term) {
		this.Term = Term;
	}


	public void setTitle(String Title) {
		this.Title = Title;
	}

	public int getAssignmentNo() {
		return AssignmentNo;
	}

	public void setAssignmentNo(int AssignmentNo) {
		this.AssignmentNo = AssignmentNo;
	}

	public boolean isAvailable() {
		return IsAvailable;
	}

	public void setIsAvailable(boolean IsAvailable) {
		this.IsAvailable = IsAvailable;
	}

	public String getAssignDesc() {
		return AssignDesc;
	}

	public void setAssignDesc(String AssignDesc) {
		this.AssignDesc = AssignDesc;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date DueDate) {
		this.DueDate = DueDate;
	}
	
	public Time getTimeDue() {
		return TimeDue;
	}
	
	public void setTimeDue(Time TimeDue){
		this.TimeDue = TimeDue;
	}
	
/**
 * This isn't currently in the assignment database, even though it was
 * previously discussed, so I commented it out.  If we end up having
 * unit tests in the assignment database, then we would have to create
 * variables at the top.
	public File getUnitTest() {
		return unitTest;
	}

	public void setUnitTest(File unitTest) {
		this.unitTest = unitTest;
	}
	*/

	public double getMaximumScore() {
		return MaximumScore;
	}

	public void setMaximumScore(double MaximumScore) {
		this.MaximumScore = MaximumScore;
	}

	public double getFcnScore() {
		return FcnScore;
	}

	public void setFcnScore(double FcnScore) {
		this.FcnScore = FcnScore;
	}

	public double getStyleScore() {
		return StyleScore;
	}

	public void setStyleScore(double StyleScore) {
		this.StyleScore = StyleScore;
	}

	public int getFacultyID() {
		return FacultyID;
	}

	public void setFacultyID(int FacultyID) {
		this.FacultyID = FacultyID;
	}

	public int getCourseSec() {
		return CourseSec;
	}

	public void setCourseSec(int CourseSec) {
		this.CourseSec = CourseSec;
	}

	public String getCourseID() {
		return CourseID;
	}

	public void setCourseID(String CourseID) {
		this.CourseID = CourseID;
	}


}