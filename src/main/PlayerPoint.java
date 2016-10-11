
package main;

public class PlayerPoint {
	double x, y, speed;
	boolean right, isStanding;
	int timer, action;
	
	public PlayerPoint(double x, double y, int timer, boolean right, int action, double speed, boolean isStanding){
		this.x = x;
		this.y = y;
		this.timer = timer;
		this.right = right;
		this.action = action;
		this.speed = speed;
		this.isStanding = isStanding;
	}
	
	public double getSpeed() {
		return speed;
	}

	public boolean isStanding() {
		return isStanding;
	}

	public void setStanding(boolean isStanding) {
		this.isStanding = isStanding;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
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

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	
	
}

