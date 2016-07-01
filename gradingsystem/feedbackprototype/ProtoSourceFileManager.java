package edu.csustan.gradingsystem.feedbackprototype;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import edu.csustan.gradingsystem.domain.SourceFile;
 
public class ProtoSourceFileManager
{
public List<SourceFile> loadedSourceFiles;

    public ProtoSourceFileManager() {
        this.loadedSourceFiles = new ArrayList<>();
    }

    public void instantiateFromCSV() throws FileNotFoundException
    {
		int submissionID;
		String fileName;
		File sFile;
    try (Scanner scanner = new Scanner(new File("sourcefiles.csv"))) {
        scanner.useDelimiter(",");
         
        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext())
        {
                        submissionID = Integer.parseInt(scanner.next().trim());
                        fileName= scanner.next();
                        sFile = new File(fileName);
            loadedSourceFiles.add(new SourceFile(submissionID ,sFile, fileName));
        }
    }
    }
	//right now for prototype purposes submission count is submission ID
	public SourceFile getSourceFileByID(int tID)
		{
			for (SourceFile S: loadedSourceFiles)
			{
				if (S.getSubmissionID() == tID)
				{
					return S;
				}
			}
			
			return null;
		}
}
 