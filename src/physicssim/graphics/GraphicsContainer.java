package physicssim.graphics;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public abstract class GraphicsContainer extends AnimationTimer {
	
	private boolean isRunning, isRunningBuffer;
	private boolean isDifferent;
	private boolean simulatedEnded;
	private ArrayList<Slider> sliders;
	private ArrayList<Entity> activeEntities;
	private GraphicsContext gc;
	private Canvas canvas;
	private Color backgroundColor;
	private long previousTime;
	
	public GraphicsContainer(Canvas canvas, GraphicsContext gc, ArrayList<Slider> sliders) {
		this.sliders = sliders;
		this.canvas = canvas;
		this.gc = gc;
		this.isDifferent = true;
		this.isRunning = false;
		this.simulatedEnded = false;
		this.isRunningBuffer = false;
		
		this.activeEntities = new ArrayList<Entity>();
		this.previousTime = System.nanoTime();
		
		setBackgroundColor(Color.BLACK);
		init();
	}
	
	public void handle(long currentTime) {
		double delta = (currentTime - previousTime) * 1e-9;
		previousTime = currentTime;
		
		render(gc);
		if(isRunning()) {
			update(delta);
		}
		
		isRunning = isRunningBuffer;
		if(isRunning && simulatedEnded) {
			init();
			this.simulatedEnded = false;
		}
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
	
	public void removeAllEntities() {
		for(int i = activeEntities.size()-1; i > 0; i--)
			activeEntities.get(i).setContainer(null);
		activeEntities.clear();
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
	
	public ArrayList<Slider> getSliders() {
		return sliders;
	}
	
	public boolean isDifferent() {
		return isDifferent;
	}
	
	public boolean isRunning() {
		return isRunningBuffer;
	}
	
	public void setSimulationEnded(boolean s) {
		this.simulatedEnded = s;
	}
	
	public boolean simulationEnded() {
		return this.simulatedEnded;
	}
	
	public void setIsDifferent(boolean isDifferent) {
		this.isDifferent = isDifferent;
	}
	
	public void setRunning(boolean isRunning) {
		this.isRunningBuffer = isRunning;
	}
	
	public abstract void init();
}