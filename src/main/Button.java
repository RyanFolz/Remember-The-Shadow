package main;

import java.awt.Color;

public class Button {
	double x, y, width, height;
	Color color;
	int timer, increment;
	String code;

	public Button(double x, double y, double width, double height, Color color, int timer, String code){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.timer = timer;
		this.code = code;
	}


	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public int getTimer() {
		return timer;
	}


	public void setTimer(int timer) {
		this.timer = timer;
	}


	public int getIncrement() {
		return increment;
	}


	public void setIncrement(int increment) {
		if(increment < timer){
			this.increment = increment;
		}else{
			increment = timer;
			this.color = Color.red;
		}
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

