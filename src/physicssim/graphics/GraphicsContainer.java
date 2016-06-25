package physicssim.graphics;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GraphicsContainer extends AnimationTimer {
	
	private ArrayList<Entity> activeEntities;
	private GraphicsContext gc;
	private Canvas canvas;
	private Color backgroundColor;
	private long previousTime;
	
	public GraphicsContainer(Canvas canvas, GraphicsContext gc) {
		this.canvas = canvas;
		this.gc = gc;
		
		this.activeEntities = new ArrayList<Entity>();
		this.previousTime = System.nanoTime();
		
		setBackgroundColor(Color.BLACK);
	}
	
	public void handle(long currentTime) {
		double delta = (currentTime - previousTime) * 1e-9;
		previousTime = currentTime;
		
		render(gc);
		update(delta);
	}
	
	public void render(GraphicsContext gc) {
		clearScreen();
		
		for(int i = 0; i < activeEntities.size(); i++)
			activeEntities.get(i).render(gc);
	}
	
	public void update(double delta) {
		for(int i = 0; i < activeEntities.size(); i++)
			activeEntities.get(i).update(delta);
	}
	
	public void addEntity(Entity e) {
		activeEntities.add(e);
		e.setContainer(this);
	}
	
	public void removeEntity(Entity e) {
		activeEntities.remove(e);
		e.setContainer(null);
	}
	
	public void setBackgroundColor(Color c) {
		backgroundColor = c;
	}
	
	public void clearScreen() {
		gc.setFill(backgroundColor);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public ArrayList<Entity> getActiveEntities() {
		return activeEntities;
	}
}