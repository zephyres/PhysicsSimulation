package physicssim.question;

import java.util.Random;

public class NewtonsLawsQuestion extends QuestionManager {

	public NewtonsLawsQuestion() {
		super("src/physicssim/question/newtons-laws-questions.txt");
	}
	
	@Override
	protected char[][] getMap() {
		char[][] map = {
			{'f', 'm', 'a'}
		};
		
		return map;
	}

	@Override
	protected double generateValue(char c) {
		Random rand = new Random();
		
		switch(c) {
			case 'm':
				return rand.nextInt(75) + 5;
			case 'a':
				return rand.nextInt(10) + 1;
		}
		
		return 0;
	}

	@Override
	protected String getSection() {
		return "Newton's Laws";
	}
}
