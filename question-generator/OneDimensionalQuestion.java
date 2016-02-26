import java.util.Random;
import java.util.HashMap;
import static java.lang.Math.*;

public class OneDimensionalQuestion extends QuestionManager {
	public static void main(String[] args) {
		System.out.println(new OneDimensionalQuestion().generateQuestion().getText());
	}
	
	public OneDimensionalQuestion() {
		super("1d.txt");
	}
	
	@Override
	public Question generateQuestion() {
		// first value is equation, last is unknown
		
		Random random = new Random();
		String q = getQuestions()[random.nextInt(getQuestions().length)];
		
		String[] separated = q.split(";");
		String[] values = new String[separated[1].length() - 2];
		int[] intvalues = new int[values.length];
			
		for(int i = 0; i < values.length; i++) {
			int v = generateNumber(separated[1].toCharArray()[i + 1]);
			values[i] = "" + v;
			intvalues[i] = v;
		}
		System.out.println(getAnswer(q, intvalues));
		return new Question(
			String.format(separated[0] + "\n", (Object[]) values), getAnswer(q, intvalues)
		);
	}
	
	public int getAnswer(String question, int[] values) {
		long answer = 0;
		String[] separated = question.split(";");
		char[] meta = separated[1].toCharArray();
		int eq = meta[0] - 48;
		char last = meta[meta.length - 1];
		
		HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
		
		for(int i = 0; i < values.length; i++)
			dict.put(meta[i + 1], values[i]);
		
		if(eq == 1) {
			if(last == 'v')
				answer = dict.get('u') + dict.get('a') * dict.get('t');
			
			if(last == 'u')
				answer = dict.get('a') * dict.get('t') - dict.get('v');
				
			if(last == 'a')
				answer = (int) ((dict.get('v') - dict.get('u')) / (double) dict.get('t') + 0.5);
				
			if(last == 't')
				answer = (int) ((dict.get('v') - dict.get('u')) / (double) dict.get('a') + 0.5);
		}
		
		else if(eq == 2) {
			int s = -1, u = -1, t = -1, a = -1;
			
			try {
				s = dict.get('s');
			} catch(Exception e) {}
			
			try {
				u = dict.get('u');
			} catch(Exception e) {}
			
			try {
				t = dict.get('t');
			} catch(Exception e) {}
			
			try {
				a = dict.get('a');
			} catch(Exception e) {}
			
			switch(last) {
				case 's':
					answer = round(u * t + 0.5 * a * t * t);
					break;
				case 'u':
					answer = round((0.5 * a * t * t - s) / t);
					break;
				case 'a':
					answer = round((s - u * t) / (0.5 * t * t));
					break;
				case 't':
					answer = round((sqrt(u * u + 2 * a * s) - u) / a);
					break;
			}
		}
		
		else if(eq == 3) {
			
		}
		
		else if(eq == 4) {
			
		}
		
		else {
			
		}
		
		return (int) answer;
	}
	
	private int generateNumber(char unknown) {
		Random r = new Random();
		
		switch(unknown) {
			case 'v': case 'u': return r.nextInt(15) + 5;
			case 't': return r.nextInt(10) + 5;
			case 'a': return r.nextInt(10) + 1;
			default : return r.nextInt(40) + 10;
		}
	}
}