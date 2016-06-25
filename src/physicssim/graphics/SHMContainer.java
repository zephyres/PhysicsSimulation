package physicssim.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SHMContainer extends GraphicsContainer {

	public SHMContainer(Canvas canvas, GraphicsContext gc) {
		super(canvas, gc);
		addEntity(new Pendulum(160, 600, 0.25));
	}
	
}
