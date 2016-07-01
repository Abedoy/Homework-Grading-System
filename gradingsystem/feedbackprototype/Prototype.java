package edu.csustan.gradingsystem.feedbackprototype;

import java.io.FileNotFoundException;

public class Prototype
{
	private static ProtoFacultyManager pFM = new ProtoFacultyManager();
	private static ProtoStudentManager pSM = new ProtoStudentManager();
	private static ProtoSubmissionsManager pSSM = new ProtoSubmissionsManager();
	private static ProtoAssignmentsManager pAM = new ProtoAssignmentsManager();
	private static ProtoSourceFileManager pSFM = new ProtoSourceFileManager();
	
	public static void mains(String[] args) {
		//Load data is the only part of main the rest is just examples to show how to pull data.
		loadData();
		
		System.out.println((pAM.getAssignmentByID(2)).getTitle());
		
		//this gets the associated assignment ID for a given submission
		int tempID = (pSSM.getSubmissionByID(3)).getAssignmentNo();
		
		//this gets the description for the assignment ID from earlier
		System.out.println((pAM.getAssignmentByID(tempID)).getAssignDesc());
		
		System.out.println("Student Email:" + (pSM.getStudentByID(4)).getEmail());
		System.out.println("Faculty Name:" + (pFM.getFacultyByID(6)).getFirstName());
		
		System.out.println(pSFM.getSourceFileByID(2).getFileName());
		
	}
	
	public static void loadData() {
		try {
			pFM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try {
			pSM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try{
			pSSM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try {
			pAM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try {
			pSFM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
	}
}