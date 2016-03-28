package physicssim.question;

public class Question {
	private String text;
	private double answer;
	
	public Question(String text, double answer) {
		setText(text);
		setAnswer(answer);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setAnswer(double answer) {
		this.answer = answer;
	}
	
	public String getText() {
		return text;
	}
	
	public double getAnswer() {
		return answer;
	}
	
	public boolean checkAnswer(int answer) {
		return answer == getAnswer();
	}
}