package edu.csustan.gradingsystem.domain;

/**
 * @author Brandon Halpin
 * Edited by: Joacim Soto 
 * Container class for SourceFiles table
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class SourceFile {
	
	int sourceFileID;
	int submissionID;
	File sourceFile;
	String fileName = "N/A";

	/**
	 * Constructor for pulling out of the database
	 * @param sourceFileID
	 * @param submissionID
	 * @param fileName
	 * @param srcFile
	 */
	public SourceFile(int sourceFileId, int submissionId, File srcF, String fName) {
		super();
		this.sourceFileID = sourceFileId;
		this.submissionID = submissionId;
		this.sourceFile = srcF;
		this.fileName = fName;
	}
	
	/**
	 * Constructor for inserting into the database
	 * @param sourceFileID
	 * @param submissionID
	 * @param fileName
	 * @param srcFile
	 */
	public SourceFile(int submissionId, File srcF, String fName){
		this.submissionID = submissionId;
		this.sourceFile = srcF;
		this.fileName = fName;
	}
	
	public int getSourceFileID(){
		return sourceFileID;
	}

	public void setSourceFileID(int sourceFileId){
		this.sourceFileID = sourceFileId;
	}
	public int getSubmissionID() {
		return submissionID;
	}

	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fName){
		this.fileName = fName;
	}

	public String toString(){
		String out = "SourceFileId: " + sourceFileID + "SubmissionId : " + submissionID + " SourceFile: " +
				printFileContents() + " File Name: " + fileName;
		return out;
	}
	
	public String printFileContents(){
		
		Reader fis;
		String buffer, result = "";
		try {
			
			fis = new FileReader(sourceFile);
			BufferedReader br = new BufferedReader(fis);
			while((buffer = br.readLine()) != null){
				result = result + buffer;
			}
			fis.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
