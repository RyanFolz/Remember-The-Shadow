package main;

import java.awt.image.BufferedImage;

public class Image {
	double x, y;
	BufferedImage image;
	
	public Image(double x, double y, BufferedImage image){
		this.x = x;
		this.y = y;
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

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
