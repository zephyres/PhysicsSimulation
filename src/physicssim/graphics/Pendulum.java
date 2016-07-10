package physicssim.graphics;

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
		double x2 = x1 - length * 100 * Math.sin(theta);
		double y2 = y1 + length * 100 * Math.cos(theta);
		
		gc.setStroke(Color.WHITE);
		gc.strokeLine(x1, y1, x2, y2);
		
		gc.setFill(Color.GRAY);
		gc.fillOval(x2 - R/2, y2 - R/2, R, R);
		
		if(getContainer().isDifferent()) {
			setLength(getContainer().getSliders().get(0).getValue());
			setGravity(getContainer().getSliders().get(1).getValue());
			setAngle(Math.toRadians(getContainer().getSliders().get(2).getValue()));
			getContainer().setIsDifferent(false);
			getContainer().setRunning(false);
		}
		
		gc.setFill(Color.WHITE);
		gc.strokeText(String.format("Length: %.2fm", length), 10, 366);
		gc.strokeText(String.format("Gravity: %.2fms⁻²", gravity), 10, 386);
		gc.strokeText(String.format("Angle: %.2f rad", theta), 10, 406);
		gc.strokeText(String.format("Speed: %.2fms⁻¹", Math.abs(w * length)), 10, 426);
	}
	
	public void update(double delta) {
		a = -gravity / length * Math.sin(theta);
		w += a * delta;
		theta += w * delta;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public void setGravity(double g) {
		this.gravity = g;
	}
	
	public void setAngle(double a) {
		this.theta = a;
		this.w = 0;
	}
}
