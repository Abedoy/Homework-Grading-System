package edu.csustan.gradingsystem.domain;

/*
 * Author: Thomas Falasco
 * 
 * This is our Course prototype class.
 */
public class Course {
	
	private String title, term, courseID;
	private int courseSec;
	
	
	public Course(String title, String term, String courseID, int courseSec) {
		super();
		this.title = title;
		this.term = term;
		this.courseID = courseID;
		this.courseSec = courseSec;
	}
	
	

	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getTerm() {
		return term;
	}



	public void setTerm(String term) {
		this.term = term;
	}



	public String getCourseID() {
		return courseID;
	}



	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}



	public int getCourseSec() {
		return courseSec;
	}



	public void setCourseSec(int courseSec) {
		this.courseSec = courseSec;
	}



	public void enrollStudent(Student student)
	{
		//This method will put the student in the class.
	}
	
	public void unenrollStudent(Student student)
	{
		//This method will remove the student from the class
	}
	
	public void addAssignment()
	{
		//This method will add the assignment to the course.
	}
	
	public void removeAssignment()
	{
		//This method will remove the assignment from the course.
	}
	
	public void getAllAssignments()
	{
		//This method will get all the assignments from this particular course.
	}
	
	public void getAverageScore(String assignmentTitle)
	{

		//This will get the score for the specified assignment.

	}
	
	public void getAverageScoreAll()
	{
		//This will get the average score of all of the graded assignments.

	}
	
    public static void main(String[] args)
    {

    }
    
}
