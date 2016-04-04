package physicssim.question;

import java.util.Random;
import static util.EquationUtility.*;

public class OneDimensionalQuestion extends QuestionManager {
	
	private char[][] map = {
		{'v', 'u', 'a', 't'},
		{'s', 'x', 'v', 't'},
		{'s', 'u', 'a', 't'},
		{'s', 'a', 'v', 'u'},
		{'s', 'u', 'v', 't'}
	};
	
	public static void main(String[] args) {
		System.out.println(new OneDimensionalQuestion().generateQuestion(0));
	}
	
	public OneDimensionalQuestion() {
		super("src/physicssim/question/1d-questions.txt");
	}
	
	@Override
	public Question generateQuestion(int id) {
		/*
		 * 0: v=u+at
		 * 1: s=x+vt
		 * 2: s=ut+0.5at^2
		 * 3: 2as=v^2-u^2
		 * 4: s=t*(u+v)/2
		 */
		
		Question question = null;
		double[] values = new double[4];
		
		for(int i = 1; i < 4; i++)
			values[i] = generateValue(map[id][i]);
			
		values[0] = eq(id, values[1], values[2], values[3]);
		
		int unknown = (int) generateValue(' ');
		String key = String.format("%d%c", id, map[id][unknown]);
		
		for(String s : getQuestions()) {
			String[] splitQuestion = s.split(";");
			char[] knowns = splitQuestion[2].toCharArray();
			
			if(key.equals(splitQuestion[1])) {
				question = new Question(
					String.format(
						splitQuestion[0],
						values[getLocation(id, knowns[0])],
						values[getLocation(id, knowns[1])],
						values[getLocation(id, knowns[2])]
					), values[unknown]
				);
			}
		}
		
		return question;
	}
	
	private double generateValue(char c) {
		Random rand = new Random();
		
		switch(c) {
			case 's': case 'x':
				return rand.nextInt(35) + 10;
			case 'v': case 'u':
				return rand.nextInt(10) + 5;
			case 'a':
				return rand.nextInt(5) + 1;
			case 't':
				return rand.nextInt(15) + 5;
			default:
				return rand.nextInt(4);
		}
	}
	
	private int getLocation(int id, char c) {
		for(int i = 0; i < map[id].length; i++)
			if(map[id][i] == c) return i;
		return -1;
	}
}