package main;

import java.awt.image.BufferedImage;

public class Door {

	double x, y;
	BufferedImage image;
	int codeTo, codeFrom;
	
	public Door(double x, double y, BufferedImage image, int codeTo, int codeFrom){
		this.x = x;
		this.y = y;
		this.image = image;
		this.codeTo = codeTo;
		this.codeFrom = codeFrom;
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

	public int getCodeTo() {
		return codeTo;
	}

	public void setCodeTo(int codeTo) {
		this.codeTo = codeTo;
	}

	public int getCodeFrom() {
		return codeFrom;
	}

	public void setCodeFrom(int codeFrom) {
		this.codeFrom = codeFrom;
	}

	
	
	
}
