package physicssim.graphics;

import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;
import javafx.scene.paint.Color;

public class Spring extends Entity {
	final private double TOP = 10, MID = 215;
	final private int LINES = 10;
	
	private double displacement;
	private double mass;
	private double k;
	private double ex, ey;
	
	public Spring(double m, double k, double iy, double ey) {
		this.mass = mass;
		this.k = k;
		this.ey = ey;
		
		setX(MID);
		setY(ey + iy);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		double dy = getY() - TOP;
		double sep = dy / LINES;
		
		double[] xCoords = new double[LINES+4];
		double[] yCoords = new double[LINES+4];
		
		xCoords[0] = MID;
		yCoords[0] = 0;
		
		xCoords[1] = MID;
		yCoords[1] = TOP * 1.5;
		
		xCoords[xCoords.length-2] = MID;
		yCoords[xCoords.length-2] = sep * (xCoords.length-1) + TOP;
		
		xCoords[xCoords.length-1] = MID;
		yCoords[xCoords.length-1] = sep * (xCoords.length-1) + 2 * TOP;
		
		for(int i = 2; i < xCoords.length-2; i++) {
			yCoords[i] = sep * i + TOP;
			xCoords[i] = Math.pow(-1, i) * 10 + MID;
		}
		
		
		gc.setStroke(Color.WHITE);
		for(int i = 0; i < xCoords.length-1; i++) {
			gc.strokeLine(xCoords[i], yCoords[i], xCoords[i+1], yCoords[i+1]);
		}
	}

	@Override
	public void update(double delta) {
		
	}
}
