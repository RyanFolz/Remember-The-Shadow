package main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hedgehog {
	BufferedImage image;
	double x, y, xVel, yVel;
	double rangeXOne, rangeXTwo;
	ArrayList<Double> jumpPoints;
	
	public Hedgehog(BufferedImage image, double x, double y, double xVel, double yVel, double rangeXOne, double rangeXTwo, ArrayList<Double> jumpPoints){
		this.image = image;
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
		this.rangeXOne = rangeXOne;
		this.rangeXTwo = rangeXTwo;
		this.jumpPoints = jumpPoints;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
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

	public double getRangeXOne() {
		return rangeXOne;
	}

	public void setRangeXOne(double rangeXOne) {
		this.rangeXOne = rangeXOne;
	}

	public double getRangeXTwo() {
		return rangeXTwo;
	}

	public void setRangeXTwo(double rangeXTwo) {
		this.rangeXTwo = rangeXTwo;
	}

	public ArrayList<Double> getJumpPoints() {
		return jumpPoints;
	}

	public void setJumpPoints(ArrayList<Double> jumpPoints) {
		this.jumpPoints = jumpPoints;
	}

	public void updateX() {
		if(xVel > 0){
			if(x + image.getWidth() < rangeXTwo){
				x += xVel;
			}else{
				xVel *= -1;
				x += xVel;
				
			}
		}else{
			if(x > rangeXOne){
				x += xVel;
			}else{
				xVel *= -1;
				x += xVel;
			}
		}
		
	}
	
	
}
