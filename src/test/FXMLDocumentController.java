package test;

import physicssim.graphics.GraphicsContainer;
import physicssim.graphics.SHMContainer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import physicssim.*;
import physicssim.question.*;

public class FXMLDocumentController implements Initializable {
	
	private GraphicsContainer gc;
	private QuestionManager qm;
	private StatPersister sp;
	private StatManager sm;
	
	@FXML
	private ListView<String> basicsList;
	@FXML
	private SplitPane splitPane;
	@FXML
	private ListView<String> shmList;
	@FXML
	private Canvas canvas;
	@FXML
	private Label questionLabel;
	@FXML
	private TextField answerField;
	@FXML
	private Label correctLabel;
	@FXML
	private Label statistics;
	@FXML
	private Button checkButton;
	
	private String currentTab;
	@FXML
	private Button nextButton;
	@FXML
	private GridPane gridPane;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		sp = new StatPersister();
		sm = sp.load();
		
		basicsList.getItems().addAll("Newton's Laws", "1D Motion", "2D Motion", "Projectile Motion");
		shmList.getItems().addAll("Spring", "Pendulum");
		
		ListView[] listViews = {basicsList, shmList};
		
		for(ListView lv : listViews)
			lv.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> update((String) c));
		
		update("Newton's Laws");
		updateStatistics();
	}
	
	private void initQuestions() {
		if(currentTab.equals("Newton's Laws"))
			qm = new NewtonsLawsQuestion();
		else if(currentTab.equals("1D Motion"))
			qm = new OneDimensionalQuestion();
		
		if(qm != null)
			nextQuestion(null);
	}
	
	private void initCanvas() {
		gc = new SHMContainer(canvas, canvas.getGraphicsContext2D());
	}
	
	private void initButtonPane() {
		if(currentTab.equals("Newton's Laws")) {
			Slider slider = new Slider(0, 1, 0.5);
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			Label l = new Label("Test slider");
			gridPane.add(slider, 0, 0);
			gridPane.add(l, 1, 0);
		}
	}

	@FXML
	private void checkAnswer(ActionEvent event) {
		String s = answerField.getText();
		
		try {
			double ans = Double.parseDouble(s);
			
			if(ans == qm.getCurrentQuestion().getAnswer()) {
				correctLabel.setText("Correct!");
				checkButton.setDisable(true);
				nextButton.setDisable(false);
				sm.right();
			}
			
			else {
				correctLabel.setText("Incorrect");
				sm.wrong();
			}
			
			sp.save(sm);
			
		} catch(Exception e) {
			correctLabel.setText("Invalid input");
		}
		
		updateStatistics();
	}

	@FXML
	private void nextQuestion(ActionEvent event) {
		qm.nextQuestion(0);
		questionLabel.setText(qm.getCurrentQuestion().getText());
		correctLabel.setText("");
		answerField.setText("");
		checkButton.setDisable(false);
		nextButton.setDisable(true);
	}

	@FXML
	private void getHint(ActionEvent event) {
		
	}
	
	private void updateStatistics() {
		statistics.setText(sm.toString());
	}
	
	public void update(String id) {
		currentTab = id;
		initQuestions();
		initCanvas();
		initButtonPane();
	}

	@FXML
	private void resetStatistics(ActionEvent event) {
		sm.reset();
		sp.save(sm);
		updateStatistics();
	}

	@FXML
	private void startSimulation(ActionEvent event) {
		initCanvas();
		gc.start();
	}
}
