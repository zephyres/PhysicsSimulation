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
	protected double generateValue(char c, int sizeType) {
		Random rand = new Random();
		
		if(sizeType == 0) switch(c) {
			case 'm':
				return rand.nextInt(75) + 5;
			case 'a':
				return rand.nextInt(10) + 1;
		}
		
		else switch(c) {
			case 'm':
				return rand.nextInt(10000) + 1000;
			case 'a':
				return rand.nextInt(500) + 500;
		}
		
		return 0;
	}

	@Override
	protected String getSection() {
		return "Newton's Laws";
	}
}
