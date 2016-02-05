package physicssim;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
	
	private GraphicsContainer container;
	private double x, y;
	
	public GraphicsContainer getContainer() {
		return container;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setContainer(GraphicsContainer container) {
		this.container = container;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public abstract void render(GraphicsContext gc);
	public abstract void update(double delta);
}