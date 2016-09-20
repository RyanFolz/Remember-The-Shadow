package main;

import java.awt.image.BufferedImage;

public class Ball {
	double x, y, xVel, yVel;
	BufferedImage image;
	boolean right;
	int timer = 0;
	
	public Ball(double x, double y, double xVel, double yVel, BufferedImage image, boolean right){
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
		this.image = image;
		this.right = right;
	}
	
	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
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
	
	public void updateY(){
		this.y += this.yVel;
		this.yVel += PrintScreen.gravity;
	}
	
	public void updateX(){
		this.x += this.xVel;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getxVel() {
		return xVel;
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
