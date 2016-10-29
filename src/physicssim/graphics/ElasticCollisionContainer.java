package physicssim.graphics;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public class ElasticCollisionContainer extends GraphicsContainer {
	
	private Ball ball1, ball2;
	
	public ElasticCollisionContainer(Canvas canvas, GraphicsContext gc, ArrayList<Slider> sliders) {
		super(canvas, gc, sliders);
	}

	@Override
	public void init() {
		double m1 = getSliders().get(0).getValue();
		double m2 = getSliders().get(2).getValue();
		double v1 = getSliders().get(1).getValue();
		double v2 = getSliders().get(3).getValue();
		
		removeAllEntities();
		ball1 = new Ball(10, 300, 10, v1, m1, 1, Color.GREEN);
		ball2 = new Ball(400, 300, 10, -v2, m2, 1, Color.RED);
		addEntity(ball1);
		addEntity(ball2);
	}
	
}
