package physicssim.graphics;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class ProjectileMotionContainer extends GraphicsContainer {

	public ProjectileMotionContainer(Canvas canvas, GraphicsContext gc, ArrayList<Slider> sliders) {
		super(canvas, gc, sliders);
		this.addEntity(new Cannon(100, 400, 70));
	}
	
}
