package physicssim.graphics;

import javafx.scene.canvas.GraphicsContext;

public class Projectile extends Entity {
	
	private final double WIDTH = 25, HEIGHT = 25;
	private double vx, vy, g;
	
	public Projectile(double x, double y, double vx, double vy) {
		setX(x);
		setY(y);
		this.vx = vx;
		this.vy = vy;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.fillOval(getX() - WIDTH / 2, getY() - HEIGHT / 2, WIDTH, HEIGHT);
	}

	@Override
	public void update(double delta) {
		setX(getX() + vx * delta);
		setY(getY() - vx * delta);
		vy -= g * delta;
	}
	
}
