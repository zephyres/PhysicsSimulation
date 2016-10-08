package physicssim.graphics;

import javafx.scene.canvas.GraphicsContext;
import static java.lang.Math.*;
import javafx.scene.paint.Color;

public class Ball extends Entity {
	
	private double radius;
	private double velocity;
	private double mass;
	private boolean hasCollided;
	private double cr;
	
	public Ball(double x, double y, double r, double v, double m, double cr) {
		this.cr = cr;
		setX(x);
		setY(y);
		setRadius(r);
		setVelocity(v);
		setMass(m);
		hasCollided = false;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillOval(getX() - radius, getY() - radius, radius*2, radius*2);
		gc.setStroke(Color.GRAY);
		gc.strokeLine(0, 300 + radius, 500, 300 + radius);
	}

	@Override
	public void update(double delta) {
		setX(getX() + velocity * delta);
		
		for(Entity e : getContainer().getActiveEntities()) {
			if(e != this && e.getClass().equals(Ball.class)) {
				Ball b = (Ball) e;
				if(isTouching(b) && !hasCollided) {
					b.setHasCollided(true);
					setHasCollided(true);
					double v1 = (getMass() * getVelocity() + b.getMass() * b.getVelocity() + b.getMass() * cr * (b.getVelocity() - getVelocity())) / (getMass() + b.getMass());
					double v2 = (b.getMass() * b.getVelocity() + getMass() * getVelocity() + getMass() * cr * (getVelocity() - b.getVelocity())) / (getMass() + b.getMass());
					setVelocity(v1);
					setVelocity(v2);
					
				}
			}
		}
	}
	
	private boolean isTouching(Ball b) {
		return getRadius() + b.getRadius() >= sqrt(pow(getX() - b.getX(), 2) + pow(getY() - b.getY(), 2));
	}
	
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public double getMass() {
		return mass;
	}
	
	public void setHasCollided(boolean a) {
		this.hasCollided = a;
	}
}
