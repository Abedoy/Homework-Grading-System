package edu.csustan.gradingsystem.domain;

/**
 * Author: Brandon Halpin,
 * Edited by: Joacim Soto
 * 
 * 
 * Defines the class for an assignment submitted by a student.
 * 
 **/


import java.io.File;
import java.sql.Blob;
import java.sql.Date;


public class StudentSubmission {
	
	//Attributes
	private int submissionID;
	private int submissionCount;
	private Date submissionDate;
	private File instructorFeedback;
	private int studentID;
	private int facultyID;
	private int assignmentNo;  
	
	
	
	
	//Default Constructor
	
	public StudentSubmission(){
		submissionID = 0;
		submissionCount = 0;         
		submissionDate = new Date(0);
		instructorFeedback = new File("");
		studentID = 0;
		facultyID = 0;
		assignmentNo = 0;		
	}
	
	//Set Constructor  
	public StudentSubmission(int stuid,int  facid, int assigno){
		
		studentID = stuid;
		facultyID = facid;
		assignmentNo = assigno;
		
	}
	
	//Methods
	
	//Setters and Getters

	public int getSubmissionID() {
		return submissionID;
	}

	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}

	public int getSubmissionCount() {
		return submissionCount;
	}

	public void setSubmissionCount(int submissionCount) {
		this.submissionCount = submissionCount;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public File getInstructorFeedback() {
		return instructorFeedback;
	}

	
	public void setInstructorFeedback(File instructorFeedback) {
		this.instructorFeedback = instructorFeedback;
	}
	

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getFacultyID() {
		return facultyID;
	}

	public void setFacultyID(int facultyID) {
		this.facultyID = facultyID;
	}

	public int getAssignmentNo() {
		return assignmentNo;
	}

	public void setAssignmentNo(int assignmentNo) {
		this.assignmentNo = assignmentNo;
	}
	
	
	public String toString(){
		String s = "SID: " + submissionID + " sCount: " + submissionCount + " date: " +
				submissionDate + " Feedback: " + instructorFeedback +  " stuidentID: " + studentID + " facultyID: " + facultyID + 
				" assignment#: " + assignmentNo;
		return s;
	}
}
