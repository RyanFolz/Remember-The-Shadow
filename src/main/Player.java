package main;

import java.awt.image.BufferedImage;

public class Player {
	double x, y, xVel, yVel;
	BufferedImage image;
	boolean isStanding, facingRight;
	int screenHeight, floor;

	public Player(BufferedImage image, double x, double y, double xVel, double yVel, int screenHeight){
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
		this.screenHeight = screenHeight;
		this.image = image;
		this.facingRight = true;
	}

	public boolean isStanding() {
		return isStanding;
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	public void setStanding(boolean isStanding) {
		this.isStanding = isStanding;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void updateX(){
		this.x = x + xVel;
	}

	public void updatexVel(int a){
		if(a == 0){

			xVel += PrintScreen.movingSpeed;
			if(xVel >= PrintScreen.topSanicSpeed){
				xVel = PrintScreen.topSanicSpeed;
			}

		}else if(a == 1){
			xVel -= PrintScreen.movingSpeed;
			if(xVel <= -PrintScreen.topSanicSpeed){
				xVel = -PrintScreen.topSanicSpeed;
			}

		}else if (a == 2){
			if(Math.abs(xVel) < 0.001){
				xVel = 0;
			}
			else if(xVel > 0){
				xVel -= PrintScreen.movingSpeed;
			}else if(xVel < 0){
				xVel += PrintScreen.movingSpeed;
			} 
		}
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getxVel() {
		return xVel;
	}

	public void updateyVel(){
		this.yVel -= PrintScreen.gravity;
	}

	public void updateY(){
		y -= yVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}


