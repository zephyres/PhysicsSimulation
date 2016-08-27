package physicssim.graphics;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.lang.Math;

public class Projectile extends Entity {
	
	private ArrayList<Double> xList, yList;
	private final double WIDTH = 5, HEIGHT = 5;
	private double vx, vy, g;
	private boolean vNotDefined;
	
	public Projectile(double x, double y) {
		setX(x);
		setY(y);
		
		xList = new ArrayList<Double>();
		yList = new ArrayList<Double>();
		xList.add(getX() - WIDTH / 2);
		yList.add(getY() - HEIGHT / 2);
		vNotDefined = true;
		g = 9;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		// draw trajectory
		gc.setStroke(Color.AQUA);
		gc.setLineWidth(1.5);
		for(int i = 1; i < xList.size(); i++)
			gc.strokeLine(xList.get(i-1), yList.get(i-1), xList.get(i), yList.get(i));
		
		// draw ball
		gc.setFill(Color.WHITE);
		gc.fillOval(getX() - WIDTH / 2, getY() - HEIGHT / 2, WIDTH, HEIGHT);
		
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(1);
		gc.strokeText(String.format("v_x: %.2fms⁻¹", vx), 10, 20);
		gc.strokeText(String.format("v_y: %.2fms⁻¹", vy), 10, 40);
		gc.strokeText(String.format("Speed: %.2fms⁻¹", Math.sqrt(vx*vx+vy*vy)), 10, 60);
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
		
		boolean isOutside = getY() > 453 || getY() < 0 || getX() > 429;
		
		if(isOutside) {
			getContainer().setSimulationEnded(true);
			getContainer().setRunning(false);
			if(getY() > 453)
				setY(453);
			if(getY() < 0)
				setY(0);
			if(getX() > 429)
				setX(429);
		}
		
		if(!isOutside) {
			delta *= 10;

			setX(getX() + vx * delta);
			setY(getY() - vy * delta);
			vy -= g * delta;
			
			xList.add(getX());
			yList.add(getY());
		}
	}
	
}
