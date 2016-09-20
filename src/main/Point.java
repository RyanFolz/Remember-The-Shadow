package main;

public class Point {
	double x, y;
	int timer;
	
	public Point(double x, double y, int timer){
		this.x = x;
		this.y = y;
		this.timer = timer;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
