package physicssim.graphics;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class Projectile extends Entity {
	
	private final double WIDTH = 5, HEIGHT = 5;
	private double vx, vy, g;
	private boolean vNotDefined;
	
	public Projectile(double x, double y) {
		setX(x);
		setY(y);
		
		vNotDefined = true;
		g = 9;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillOval(getX() - WIDTH / 2, getY() - HEIGHT / 2, WIDTH, HEIGHT);
	}

	@Override
	public void update(double delta) { // bad code
		if(vNotDefined) {
			double v = getContainer().getSliders().get(0).getValue() * 1.5;
			double angle = getContainer().getSliders().get(1).getValue();
			this.vx = v * Math.cos(Math.toRadians(angle));
			this.vy = v * Math.sin(Math.toRadians(angle));
			vNotDefined = false;
		}
		
		if(getY() > 453) {
			getContainer().setSimulationEnded(true);
			getContainer().setRunning(false);
			setY(453);
		}
		
		if(getY() < 453) {
			delta *= 10;

			setX(getX() + vx * delta);
			setY(getY() - vy * delta);
			vy -= g * delta;
		}
	}
	
}
