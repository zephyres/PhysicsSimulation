/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicssim;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;

/**
 *
 * @author Zephyr
 */
public class FXMLDocumentController implements Initializable {
	
	private GraphicsContainer gc;
	
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
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {		
		basicsList.getItems().addAll("Newton's Laws", "1D Motion", "2D Motion", "Projectile Motion");
		shmList.getItems().addAll("Spring", "Pendulum");
		
		initCanvas();
	}
	
	public void initCanvas() {
		gc = new GraphicsContainer(canvas, canvas.getGraphicsContext2D());
		gc.addEntity(new Pendulum(160, 600, 0.25));
	}

	@FXML
	private void startPendulumSimulation(ActionEvent event) {
		initCanvas();
		gc.start();
	}
}
