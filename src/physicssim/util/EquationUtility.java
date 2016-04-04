package util;

public class EquationUtility {
	public static double eq(int n, double x, double y, double z) {
		switch(n) {
			case 0: return eq0(x, y, z);
			case 1: return eq1(x, y, z);
			case 2: return eq2(x, y, z);
			case 3: return eq3(x, y, z);
			default: return eq4(x, y, z);
		}
	}
	
	public static double eq0(double u, double a, double t) {
		return u + a*t;
	}
	
	public static double eq1(double x, double v, double t) {
		return x + v*t;
	}
	
	public static double eq2(double u, double a, double t) {
		return u*t + 0.5*a*t*t;
	}
	
	public static double eq3(double a, double v, double u) {
		return (v*v - u*u) / (2*a);
	}
	
	public static double eq4(double u, double v, double t) {
		return (u + v) * t / 2;
	}
}