package physicssim.question;

import java.util.Random;

public class OneDimensionalQuestion extends QuestionManager {
	
	public OneDimensionalQuestion() {
		super("src/physicssim/question/1d-questions.txt");
	}
	
	protected double generateValue(char c, int sizeType) {
		Random rand = new Random();
		
		if(sizeType == 0) switch(c) {
			case 's': case 'x':
				return rand.nextInt(35) + 10;
			case 'v': case 'u':
				return rand.nextInt(10) + 5;
			case 'a':
				return rand.nextInt(5) + 1;
			case 't':
				return rand.nextInt(15) + 5;
		}
		
		else switch(c) {
			case 's': case 'x':
				return rand.nextInt(5000) + 1000;
			case 'v': case 'u':
				return rand.nextInt(10000) + 5000;
			case 'a':
				return rand.nextInt(500) + 100;
			case 't':
				return rand.nextInt(50) + 10;
		}
		
		return 0;
	}
	
	@Override
	protected char[][] getMap() {
		char[][] map = {
			{'v', 'u', 'a', 't'},
			{'s', 'x', 'v', 't'},
			{'s', 'u', 'a', 't'},
			{'s', 'a', 'v', 'u'},
			{'s', 'u', 'v', 't'}
		};
		
		return map;
	}

	@Override
	protected String getSection() {
		return "1D Motion";
	}
}