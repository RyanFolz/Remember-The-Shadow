package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class LevelTwo {
	
	BufferedImage sign, door, hedgehog, ball;
	double screenWidth, screenHeight;
	double x, y;
	
	public LevelTwo(double screenWidth, double screenHeight, BufferedImage sign, BufferedImage door, BufferedImage hedgehog, BufferedImage ball){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.sign = sign;
		this.door = door;
		this.hedgehog = hedgehog;
		this.ball = ball;
		this.x = 0;
		this.y = screenHeight / 1.1;
	}
	
	public ArrayList<Button> getButton(){
		ArrayList<Button> tempList = new ArrayList<Button>();
		return tempList;
	}
	
	public ArrayList<Sign> getSign(){
		ArrayList<Sign> tempList = new ArrayList<>();
		return tempList;
	}
	
	public ArrayList<Door> getDoor(){
		ArrayList<Door> tempList = new ArrayList<>();
		tempList.add(new Door(screenWidth / 4, (-10 * screenHeight / 20) - door.getHeight(), door, PrintScreen.LEVEL_ONE, PrintScreen.LEVEL_TWO));
		tempList.add(new Door(screenWidth / 2, screenHeight - screenHeight / 20 - door.getHeight(), door, PrintScreen.LEVEL_TEST, PrintScreen.LEVEL_TWO));
		tempList.add(new Door(2.1 * screenWidth, -3 * screenHeight / 20 - door.getHeight(), door, PrintScreen.LEVEL_THREE, PrintScreen.LEVEL_TWO));
		return tempList;
	}
	
	public ArrayList<Platform> getPlatform(){
		ArrayList<Platform> platformList = new ArrayList<>();
		platformList.add(new Platform(-10000, (int)(19 * screenHeight / 20), 20000, (int)(screenHeight / 10), Color.BLUE));
		platformList.add(new Platform(0, (int)(-10 * screenHeight / 20), (int)(screenWidth / 2), (int)(30 * screenHeight / 20), Color.blue));
		platformList.add(new Platform(- screenHeight / 20, -30 * screenHeight / 20, screenHeight / 20, 50 * screenHeight / 20, Color.BLUE));
		platformList.add(new Platform(4 * screenWidth / 5, -8 * screenHeight / 20, screenWidth / 3, 28 * screenHeight / 20, Color.BLUE));
		platformList.add(new Platform(19 * screenWidth / 15, - 12 * screenHeight / 20, screenWidth / 6, 35 * screenHeight / 20, Color.BLUE));
		platformList.add(new Platform(1.9 * screenWidth, - 3 * screenHeight / 20, screenWidth / 3, 35 * screenHeight / 20, Color.BLUE));
		platformList.add(new Platform(1.9 * screenWidth + screenWidth / 3, -2 * screenHeight, screenHeight / 20, 3 * screenHeight, Color.BLUE));
		platformList.add(new Platform(-1000, screenHeight - 2 * screenHeight / 20, 6 * screenWidth, screenHeight / 20, Color.red));
		return platformList;
	}
	
	public ArrayList<Image> getImages() throws IOException{
		ArrayList<Image> tempList = new ArrayList<>();
		return tempList;
	}
	
	public ArrayList<Hedgehog> getHedgehog(){
		ArrayList<Hedgehog> hedgehogList = new ArrayList<>();
		
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
