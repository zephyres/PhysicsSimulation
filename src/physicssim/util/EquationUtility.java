package physicssim.util;

public class EquationUtility {
	public static double eq(String sec, int n, double[] values) {
		if(sec.equals("1D Motion")) switch(n) {
			case 0: return eq0(values[1], values[2], values[3]);
			case 1: return eq1(values[1], values[2], values[3]);
			case 2: return eq2(values[1], values[2], values[3]);
			case 3: return eq3(values[1], values[2], values[3]);
			default: return eq4(values[1], values[2], values[3]);
		}
		
		if(sec.equals("Newton's Laws")) switch(n) {
			default: return eq5(values[1], values[2]);
		}
		
		else return 0;
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
	
	public static double eq5(double m, double a) {
		return m * a;
	}
}