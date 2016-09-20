package main;

import java.awt.Color;
import java.awt.Rectangle;

public class ThrowRectangle {

	int x, y, width, height, health;
	Color color;

	public ThrowRectangle(int x, int y, int width, int height, int health, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}


	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getHealth() {
		return health + 20;
	}

	public void setHealth(double health) {
		if(health > 80){
			health = 80;
		}else{
			if(health >=0){
				this.health = (int) health;
			}else {
				health = 0;
			}
		}
	}	
}

