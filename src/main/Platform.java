package main;

import java.awt.Color;

public class Platform {
	double x, y, width, height, xVel, yVel, xDis, yDis, xStart, yStart, x1, x2, y1, y2;
	String code = "";
	Color color;
	
	public Platform(double x, double y, double width, double height, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.xStart = x;
		this.yStart = y;
	}
	
	public Platform(double x, double y, double width, double height, Color color, double xVel, double yVel, double xDis, double yDis, String code){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.xVel = xVel;
		this.yVel = yVel;
		this.xDis = xDis;
		this.yDis = yDis;
		this.code = code;
		this.xStart = x;
		this.yStart = y;
		x1 = x2 = x;
		y1 = y2 = y;
	}
	
	
	public void updatePosition(){
		System.out.println("ABS: " + Math.abs(xStart - x));
		System.out.println("xDis: " + xDis);
		if(Math.abs(xStart - x) < xDis){
			x += xVel;
			System.out.println("X CHANGED");
		}else{
			System.out.println("uhhh");
		}
		if(Math.abs(yStart - y) < yDis){
			y += yVel;
			System.out.println("X CHANGED");
		}else{
			System.out.println("uhhh");
		}
		
		x2 = x1;
		y2 = y1;
		x1 = x;
		y1 = y;
		
	}
	
	public void unUpdatePosition(){
		if(xVel > 0){
			if(xStart < x){
				x -= xVel;
			}
		}
		if(xVel < 0){
			if(xStart > x){
				x -= xVel;
			}
		}
		if(yVel > 0){
			if(yStart < y){
				y -= yVel;
			}
		}
		if(yVel < 0){
			if(yStart > y){
				y -= yVel;
			}
		}
	}
	

	public double getxDis() {
		return xDis;
	}

	public void setxDis(double xDis) {
		this.xDis = xDis;
	}

	public double getyDis() {
		return yDis;
	}

	public void setyDis(double yDis) {
		this.yDis = yDis;
	}

	public double getxStart() {
		return xStart;
	}

	public void setxStart(double xStart) {
		this.xStart = xStart;
	}

	public double getyStart() {
		return yStart;
	}

	public void setyStart(double yStart) {
		this.yStart = yStart;
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



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
}

