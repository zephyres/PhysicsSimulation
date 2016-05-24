package physicssim.question;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import static physicssim.util.EquationUtility.eq;

public abstract class QuestionManager {
	private Question current;
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
	
	public Question getCurrentQuestion() {
		return current;
	}
	
	public void nextQuestion(int id) {
		current = generateQuestion(id);
	}
	
	public Question generateQuestion(int id) {
		Question question = null;
		char[][] map = getMap();
		double[] values = new double[questions[id].split(";")[2].length()+1];
		
		// Generate known values for given question
		for(int i = 1; i < values.length; i++)
			values[i] = generateValue(map[id][i]);
		
		// Finds final value according to equation
		values[0] = eq(getSection(), id, values);
		
		// Choose unknown
		int unknown = (int) generateValue(' ');
		String key = String.format("%d%c", id, map[id][unknown]);
		
		// Loops through all questions
		for(String s : getQuestions()) {
			String[] splitQuestion = s.split(";");
			
			// Check if question type matches unknown
			if(key.equals(splitQuestion[1])) {
				char[] knowns = splitQuestion[2].toCharArray();
				String[] vals = new String[knowns.length];
				
				// Makes values appear in correct order
				for(int i = 0; i < vals.length; i++)
					vals[i] = "" + values[getLocation(id, knowns[i])];
				
				question = new Question(String.format(splitQuestion[0], (Object[]) vals), values[unknown]);
			}
		}
		
		return question;
	}
	
	private int getLocation(int id, char c) {
		char[][] map = getMap();
		
		for(int i = 0; i < map[id].length; i++)
			if(map[id][i] == c) return i;
		return -1;
	}
	
	protected abstract char[][] getMap();
	protected abstract double generateValue(char c);
	protected abstract String getSection();
}