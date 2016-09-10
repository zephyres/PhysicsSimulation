package physicssim.graphics;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class SpringContainer extends GraphicsContainer {
	
	public SpringContainer(Canvas canvas, GraphicsContext gc, ArrayList<Slider> sliders) {
		super(canvas, gc, sliders);
	}
	
	@Override
	public void init() {
		addEntity(new Spring(1, 1, 60, 40));
	}
	
}
