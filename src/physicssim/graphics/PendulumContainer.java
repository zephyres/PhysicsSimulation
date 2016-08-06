package physicssim.graphics;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class PendulumContainer extends GraphicsContainer {

	public PendulumContainer(Canvas canvas, GraphicsContext gc, ArrayList<Slider> sliders) {
		super(canvas, gc, sliders);
	}
	
	public void init() {
		addEntity(new Pendulum(2.45, 9.8, 0.25));
	}
	
}
