package physicssim.graphics;

import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;
import javafx.scene.paint.Color;

public class Spring extends Entity {
	final private double TOP = 10, MID = 215;
	final private double LINE_LENGTH = 10;
	
	private double radius;
	private double displacement;
	private double mass;
	private double k;
	private double ex, ey;
	private double a, v;
	private int lines;
	
	public Spring(double m, double k, double d, double ey) {
		setMass(m);
		this.ey = ey;
		setX(MID);
		
		updateValues();
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.setStroke(Color.AQUA);
		gc.strokeLine(0, ey, 500, ey);
		
		double dy = getY() - TOP - radius + 10;
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
		gc.fillOval(MID - radius, getY() - radius, radius*2, radius*2);
		
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(2);
		gc.fillText(getStatistics(), 20, 375);
		
		if(getContainer().isDifferent())
			updateValues();
	}

	@Override
	public void update(double delta) {
		a = (k / mass) * getDisplacement();
		v += a * delta;
		setDisplacement(getDisplacement() - v * delta);
		System.out.println(getY());
		setY(ey + getDisplacement());
	}
	
	public void setDisplacement(double d) {
		this.displacement = d;
	}
	
	public double getDisplacement() {
		return displacement;
	}
	
	public void setMass(double m) {
		this.mass = m;
		radius = 10*Math.exp(0.05*mass);
	}
	
	public double getMass() {
		return mass;
	}
	
	public void setSpringConstant(double k) {
		this.k = k;
		lines = (int) (5 * Math.sqrt(5*k));
	}
	
	public double getSpringContant() {
		return k;
	}
	
	private String getStatistics() {
		return String.format(
			"Displacement: %.2fm\nVelocity: %.2fm/s\nAcceleration: %.2fm/s²\nPeriod: %.2fs", 
			getDisplacement(), v, a, 2*Math.PI*Math.sqrt(getMass()/k)
		);
	}
	
	private void updateValues() {
		if(getContainer() != null) {
			setDisplacement(getContainer().getSliders().get(0).getValue());
			setY(ey + getDisplacement());
			setMass(getContainer().getSliders().get(1).getValue());
			setSpringConstant(getContainer().getSliders().get(2).getValue());
			
			a = (k / getMass()) * getDisplacement();
			v = 0;
			
			getContainer().setIsDifferent(false);
			getContainer().setRunning(false);
		}
	}
}
