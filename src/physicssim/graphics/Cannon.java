package physicssim.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;

public class Cannon extends Entity {
	
	private final double WIDTH = 10, HEIGHT = 10;
	private double angle, length;
	
	public Cannon(double x, double y) {
		setX(x);
		setY(y);
		length = 15;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		angle = getContainer().getSliders().get(1).getValue();
		
		gc.setFill(Color.WHITE);
		gc.fillOval(getX() - WIDTH / 2, getY() - HEIGHT / 2, WIDTH, HEIGHT);
		gc.setLineWidth(5);
		gc.setStroke(Color.WHITE);
		gc.strokeLine(getX(), getY(), getX() + length * Math.cos(Math.toRadians(angle)), getY() - length * Math.sin(Math.toRadians(angle)));
	}

	@Override
	public void update(double delta) {}
	
}
