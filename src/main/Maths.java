package main;

public class Maths {
	public static int pow(int x, int y){
		if (x > 1 && y > 20){
			return -1;
		}
		return x*y;
	}
}
