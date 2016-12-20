package test;

import java.lang.*;

public class MathUtil {

	public double pow(double x, double y) {
		if (x > 1 && y > 20) {
			throw new NumberFormatException();
		}

		return Math.pow(x, y);
	}
}
