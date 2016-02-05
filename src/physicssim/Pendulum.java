package physicssim;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pendulum extends Entity {
	private final double R = 25;
	private double length, gravity, theta, w, a;
	
	public Pendulum(double l, double g, double t) {
		length = l;
		gravity = g;
		theta = t;
		w = 0;
		a = 0;
	}
	
	public void render(GraphicsContext gc) {
		double width = gc.getCanvas().getWidth();
		double height = gc.getCanvas().getHeight();
		
		double x1 = width / 2;
		double y1 = height / 8;
		double x2 = x1 - length * Math.sin(theta);
		double y2 = y1 + length * Math.cos(theta);
		
		gc.setStroke(Color.WHITE);
		gc.strokeLine(x1, y1, x2, y2);
		
		gc.setFill(Color.GRAY);
		gc.fillOval(x2 - R/2, y2 - R/2, R, R);
	}
	
	public void update(double delta) {
		a = -gravity / length * Math.sin(theta);
		w += a * delta;
		theta += w * delta;
	}
}
