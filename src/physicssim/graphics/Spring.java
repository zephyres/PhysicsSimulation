package physicssim.graphics;

import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;
import javafx.scene.paint.Color;

public class Spring extends Entity {
	final private double TOP = 10, MID = 215;
	final private double RADIUS = 12;
	final private double LINE_LENGTH = 10;
	
	private double displacement;
	private double mass;
	private double k;
	private double ex, ey;
	private double a, v;
	private int lines;
	
	public Spring(double m, double k, double d, double ey) {
		this.mass = m;
		this.ey = ey;
		
		setSpringConstant(k);
		setX(MID);
		setDisplacement(d);
		setY(ey + getDisplacement());
		
		a = (k / m) * getDisplacement();
		v = 0;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		double dy = getY() - TOP + RADIUS;
		double sep = (dy - 2*LINE_LENGTH) / (lines-1);
		
		double[] xCoords = new double[lines+3];
		double[] yCoords = new double[lines+3];
		
		xCoords[0] = MID;
		yCoords[0] = 0;
		
		xCoords[1] = MID;
		yCoords[1] = LINE_LENGTH;
		
		double currentY = LINE_LENGTH;
		
		for(int i = 2; i < xCoords.length-2; i++) {
			if(i == 2)
				currentY += sep / 2;
			else
				currentY += sep;
			
			xCoords[i] = MID + Math.pow(-1, i) * 5;
			yCoords[i] = currentY;
		}
		
		xCoords[xCoords.length-2] = MID;
		yCoords[xCoords.length-2] = currentY + sep / 2;
		
		xCoords[xCoords.length-1] = MID;
		yCoords[xCoords.length-1] = currentY + sep / 2 + LINE_LENGTH;
		
		gc.setStroke(Color.WHITE);
		for(int i = 0; i < xCoords.length-1; i++) {
			gc.strokeLine(xCoords[i], yCoords[i], xCoords[i+1], yCoords[i+1]);
		}
		
		gc.setFill(Color.GRAY);
		gc.fillOval(MID - RADIUS, getY(), RADIUS*2, RADIUS*2);
	}

	@Override
	public void update(double delta) {
		a = (k / mass) * getDisplacement();
		v += a * delta;
		setDisplacement(getDisplacement() - v * delta);
		System.out.println(getY());
		setY(ey + getDisplacement());
	}
	
	private void setDisplacement(double d) {
		this.displacement = d;
	}
	
	private double getDisplacement() {
		return displacement;
	}
	
	private void setSpringConstant(double k) {
		this.k = k;
		lines = (int) (15 * Math.sqrt(k));
	}
}
