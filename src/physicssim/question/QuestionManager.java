package physicssim.question;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Random;
import static physicssim.util.EquationUtility.eq;

/*
 * When implementing:
 * 1. Update EquationUtility
 * 2. Link to question text file in super
 * 3. Implement getMap() (1 column = calculated value, others are generated)
 * 4. Implement getSection()
 * 5. Implement generateValue()
 * 6. Update init methods in Document Controller
 */

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
			values[i] = generateValue(map[id][i]) * (questions[id].split(";")[3].equals("1") ? 10 : 1);
		
		// Finds final value according to equation
		values[0] = eq(getSection(), id, values);
		
		// Choose unknown
		int unknown = new Random().nextInt(map[id].length);
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