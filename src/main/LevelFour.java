package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class LevelFour {
	
	BufferedImage sign, door, hedgehog, ball;
	double screenWidth, screenHeight;
	double x, y;
	
	public LevelFour(double screenWidth, double screenHeight, BufferedImage sign, BufferedImage door, BufferedImage hedgehog, BufferedImage ball){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.sign = sign;
		this.door = door;
		this.hedgehog = hedgehog;
		this.ball = ball;
		this.x = screenWidth / 4;
		this.y = -4.2 * screenHeight;
	}
	
	public ArrayList<Button> getButton(){
		ArrayList<Button> tempList = new ArrayList<Button>();
		tempList.add(new Button(3 * screenWidth / 4 + screenWidth / 35.6 - screenHeight / 100, .3 * screenHeight, screenHeight / 50, screenHeight / 50, Color.RED, 50, "ERIN"));
		return tempList;
	}
	
	public ArrayList<Sign> getSign(){
		ArrayList<Sign> tempList = new ArrayList<>();
		tempList.add(new Sign(8.75 * screenWidth / 6, - screenHeight / 11 + screenHeight, sign.getWidth(), sign.getHeight(), "Press the down arrow to create a shadow.\nShadows mimic your actions from the past\nthree seconds"));
		return tempList;
	}
	
	public ArrayList<Door> getDoor(){
		ArrayList<Door> tempList = new ArrayList<>();
		tempList.add(new Door(screenWidth / 4, (-4 * screenHeight) - door.getHeight(), door, PrintScreen.LEVEL_THREE, PrintScreen.LEVEL_FOUR));
		return tempList;
	}
	
	public ArrayList<Platform> getPlatform(){
		ArrayList<Platform> platformList = new ArrayList<>();
		platformList.add(new Platform(-10000, (int)(19 * screenHeight / 20), 20000, (int)(screenHeight), Color.BLUE));
		platformList.add(new Platform(-screenWidth / 35.6, -5 * screenHeight, screenWidth / 35.6, 6 * screenHeight, Color.blue));
		platformList.add(new Platform(0, (int)(-4 * screenHeight), (int)(screenWidth / 2), screenHeight / 20, Color.blue));
		platformList.add(new Platform(screenWidth / 2, - 3.8 * screenHeight, .75 * screenWidth / 4, screenHeight / 20, Color.blue));
		platformList.add(new Platform(screenWidth / 2 + .4 * screenWidth / 4, - 3.6 * screenHeight, .6 * screenWidth / 4, screenHeight / 20, Color.blue));
		platformList.add(new Platform(screenWidth / 2, -4 * screenHeight, screenWidth / 35.6, 5 * screenHeight, Color.BLUE));
		platformList.add(new Platform(screenWidth * 3 / 4, - 5 * screenHeight, screenWidth / 35.6, 5.5 * screenHeight, Color.blue));
		platformList.add(new Platform(screenWidth, .78 * screenHeight , screenWidth / 4, screenWidth / 4, Color.blue));
		platformList.add(new Platform(screenWidth + screenWidth / 12, .73 * screenHeight - screenWidth / 12, screenWidth / 12, screenWidth / 8, Color.blue));
		platformList.add(new Platform(screenWidth * 3 / 4 + screenWidth / 35.6, .2 * screenHeight, screenWidth, screenHeight / 20, Color.blue));
		platformList.add(new Platform(11 * screenWidth / 6, -screenHeight, screenWidth / 35.6, screenHeight * 2, Color.blue)); 
		platformList.add(new Platform(9.5 * screenWidth / 6, -screenHeight, 1.5 * screenWidth / 6, screenHeight * 1.55, Color.blue));
		platformList.add(new Platform(9.5 * screenWidth / 6, .55 * screenHeight, screenWidth / 35.6, .4 * screenHeight, Color.cyan, 0, -6, 0, .4 * screenHeight, "ERIN"));
		platformList.add(new Platform(11 * screenWidth / 6, -screenHeight, screenWidth / 35.6, screenHeight * 2, Color.blue));
		return platformList;
	}
	
	public ArrayList<Image> getImages() throws IOException{
		ArrayList<Image> tempList = new ArrayList<>();
		return tempList;
	}
	
	public ArrayList<Hedgehog> getHedgehog(){
		ArrayList<Hedgehog> hedgehogList = new ArrayList<>();
		hedgehogList.add(new Hedgehog(hedgehog, screenWidth / 2, -3.8 * screenHeight - hedgehog.getHeight(), 2, 0, 3 * screenWidth / 4 + screenWidth / 35.6, 3 * screenWidth / 4 + .75 * screenWidth / 4, null));
		return hedgehogList;
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
