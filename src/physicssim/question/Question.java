package physicssim.question;

public class Question {
	private String text;
	private String id;
	private double answer;
	
	public Question(String text, double answer) {
		setText(text);
		setAnswer(answer);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	//public void setEquation(String id)
	
	public void setAnswer(double answer) {
		this.answer = answer;
	}
	
	public String getText() {
		return text;
	}
	
	public double getAnswer() {
		return answer;
	}
	
	public String toString() {
		return String.format("Question: %s; Answer: %f", text, answer);
	}
}