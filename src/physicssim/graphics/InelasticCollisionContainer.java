package physicssim.graphics;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class InelasticCollisionContainer extends GraphicsContainer {
	
	private Ball ball1, ball2;
	
	public InelasticCollisionContainer(Canvas canvas, GraphicsContext gc, ArrayList<Slider> sliders) {
		super(canvas, gc, sliders);
	}

	@Override
	public void init() {
		removeAllEntities();
		ball1 = new Ball(10, 300, 10, 50, 10, 0);
		ball2 = new Ball(400, 300, 10, -60, 20, 0);
		addEntity(ball1);
		addEntity(ball2);
	}
	
}
