package test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import physicssim.*;
import physicssim.question.OneDimensionalQuestion;
import physicssim.question.QuestionManager;

public class FXMLDocumentController implements Initializable {
	
	private GraphicsContainer gc;
	private QuestionManager qm;
	
	@FXML
	private ListView<String> basicsList;
	@FXML
	private SplitPane splitPane;
	@FXML
	private ListView<String> shmList;
	@FXML
	private Canvas canvas;
	@FXML
	private Button start;
	@FXML
	private Label questionLabel;
	@FXML
	private TextField answerField;
	@FXML
	private Label correctLabel;
	@FXML
	private Label statistics;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {		
		basicsList.getItems().addAll("Newton's Laws", "1D Motion", "2D Motion", "Projectile Motion");
		shmList.getItems().addAll("Spring", "Pendulum");
		
		initQuestions();
		initCanvas();
	}
	
	private void initQuestions() {
		qm = new OneDimensionalQuestion(); //deal with this later
		nextQuestion(null);
	}
	
	private void initCanvas() {
		gc = new GraphicsContainer(canvas, canvas.getGraphicsContext2D());
		gc.addEntity(new Pendulum(160, 600, 0.25));
	}

	@FXML
	private void startPendulumSimulation(ActionEvent event) {
		initCanvas();
		gc.start();
	}

	@FXML
	private void checkAnswer(ActionEvent event) {
		String s = answerField.getText();
		
		try {
			double ans = Double.parseDouble(s);
			
			if(ans == qm.getCurrentQuestion().getAnswer())
				correctLabel.setText("Correct!");
			else
				correctLabel.setText("Incorrect");
			
		} catch(Exception e) {
			correctLabel.setText("Invalid input");
		}
	}

	@FXML
	private void nextQuestion(ActionEvent event) {
		qm.nextQuestion(0);
		questionLabel.setText(qm.getCurrentQuestion().getText());
		correctLabel.setText("");
	}

	@FXML
	private void getHint(ActionEvent event) {
		
	}
}
