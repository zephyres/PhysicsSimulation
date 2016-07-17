package test;

import physicssim.graphics.GraphicsContainer;
import physicssim.graphics.PendulumContainer;
import physicssim.graphics.ProjectileMotionContainer;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
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
	
	private ArrayList<Slider> sliders;
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
	@FXML
	private Button runButton;
	
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
	
	private void initButtonPane() {
		
		gridPane.getChildren().remove(0, gridPane.getChildren().size());
		sliders = new ArrayList<Slider>();
		gridPane.setVgap(20);
		gridPane.getColumnConstraints().get(0).setMaxWidth(80);
		gridPane.getColumnConstraints().get(0).halignmentProperty().set(HPos.LEFT);
		
		if(currentTab.equals("Pendulum")) {
			addSlider("Length", 0.1, 4, 1);
			addSlider("Gravity", 0.1, 30, 9.8);
			addSlider("Angle", 10, 90, 45);
		}
	}
	
	private void initSimulation() {
		if(currentTab.equals("Pendulum")) {
			gc = new PendulumContainer(canvas, canvas.getGraphicsContext2D(), sliders);
			gc.start();
		}
		
		if(currentTab.equals("Projectile Motion")) {
			gc = new ProjectileMotionContainer(canvas, canvas.getGraphicsContext2D(), sliders);
			gc.start();
		}
	}
	
	private void addSlider(String name, double min, double max, double val) {
		Slider s = new Slider(min, max, val);
		Label l = new Label(name);
		
		s.setShowTickLabels(true);
		s.setShowTickMarks(true);
		
		s.valueProperty().addListener( new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				gc.setIsDifferent(true);
			}
		});
		
		gridPane.addRow(sliders.size(), l, s);
		sliders.add(s);
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
		initButtonPane();
		initSimulation();
	}

	@FXML
	private void resetStatistics(ActionEvent event) {
		sm.reset();
		sp.save(sm);
		updateStatistics();
	}

	@FXML
	private void startSimulation(ActionEvent event) {
		gc.setRunning(!gc.isRunning());
	}
}
