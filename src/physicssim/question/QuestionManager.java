package physicssim.question;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public abstract class QuestionManager {
	private String[] questions;
	
	public QuestionManager(String filename) {
		ArrayList<String> qs = new ArrayList<String>();
		
		try {
			Scanner scan = new Scanner(new File(filename));
			
			while(scan.hasNext())
				qs.add(scan.nextLine());
				
			questions = qs.toArray(new String[qs.size()]);
		}
		
		catch(Exception e) {
			System.err.printf("Error: %s", e);
		}
	}
	
	public String[] getQuestions() {
		return questions;
	}
	
	public abstract Question generateQuestion();
}