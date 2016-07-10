package physicssim.graphics;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class SHMContainer extends GraphicsContainer {

	public SHMContainer(Canvas canvas, GraphicsContext gc, ArrayList<Slider> sliders) {
		super(canvas, gc, sliders);
		addEntity(new Pendulum(2.45, 9.8, 0.25));
	}
	
}
