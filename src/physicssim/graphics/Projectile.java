package physicssim.graphics;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class Projectile extends Entity {
	
	private final double WIDTH = 5, HEIGHT = 5;
	private double vx, vy, g;
	
	public Projectile(double x, double y) {
		setX(x);
		setY(y);
		double v = 50;
		double angle = 70;
		
		g = 9;
		this.vx = v * Math.cos(Math.toRadians(angle));
		this.vy = v * Math.sin(Math.toRadians(angle));
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillOval(getX() - WIDTH / 2, getY() - HEIGHT / 2, WIDTH, HEIGHT);
	}

	@Override
	public void update(double delta) {
		delta *= 10;
		
		setX(getX() + vx * delta);
		setY(getY() - vy * delta);
		vy -= g * delta;
		
		if(getY() >= 453) {
			getContainer().setSimulatedEnded(true);
			getContainer().setRunning(false);
			setY(453);
		}
	}
	
}
