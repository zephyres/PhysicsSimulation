package physicssim.graphics;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class ProjectileMotionContainer extends GraphicsContainer {

	public ProjectileMotionContainer(Canvas canvas, GraphicsContext gc, ArrayList<Slider> sliders) {
		super(canvas, gc, sliders);
	}
	
	public void init() {
		removeAllEntities();
		addEntity(new Projectile(20, 450));
		addEntity(new Cannon(20, 450));
	}
	
}
