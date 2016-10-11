package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class LevelThree {
	
	BufferedImage sign, door, hedgehog, ball;
	double screenWidth, screenHeight;
	double x, y;
	
	public LevelThree(double screenWidth, double screenHeight, BufferedImage sign, BufferedImage door, BufferedImage hedgehog, BufferedImage ball){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.sign = sign;
		this.door = door;
		this.hedgehog = hedgehog;
		this.ball = ball;
		this.x = 0;
		this.y = -screenHeight;
	}
	
	public ArrayList<Button> getButton(){
		ArrayList<Button> tempList = new ArrayList<Button>();
		tempList.add(new Button(4.96 * screenWidth / 6, -screenHeight, screenHeight / 50, screenHeight / 50, Color.RED, 350, "LEVEL_THREE_ELEVATOR"));
		return tempList;
	}
	
	public ArrayList<Sign> getSign(){
		ArrayList<Sign> tempList = new ArrayList<>();
		tempList.add(new Sign(3 * screenHeight / 4, -screenHeight / 2 - sign.getHeight(), sign.getWidth(), sign.getHeight(), "Hold the up arrow to throw a ball"));
		return tempList;
	}
	
	public ArrayList<Door> getDoor(){
		ArrayList<Door> tempList = new ArrayList<>();
		tempList.add(new Door(screenWidth / 4, (-10 * screenHeight / 20) - door.getHeight(), door, PrintScreen.LEVEL_TWO, PrintScreen.LEVEL_THREE));
		tempList.add(new Door(6 * screenWidth / 6, - 2 * screenHeight - door.getHeight(), door, PrintScreen.LEVEL_FOUR, PrintScreen.LEVEL_THREE));
		return tempList;
	}
	
	public ArrayList<Platform> getPlatform(){
		ArrayList<Platform> platformList = new ArrayList<>();
		platformList.add(new Platform(-10000, (int)(19 * screenHeight / 20), 20000, (int)(screenHeight / 20), Color.BLUE));
		platformList.add(new Platform(0, (int)(-10 * screenHeight / 20), (int)(screenWidth / 2), (int)(30 * screenHeight / 20), Color.black));
		platformList.add(new Platform(- screenHeight / 20, -30 * screenHeight / 20, screenHeight / 20, 50 * screenHeight / 20, Color.BLUE));
		platformList.add(new Platform(3 * screenWidth / 5, - 5.5 * screenHeight / 20, screenWidth / 6, screenHeight / 20, Color.CYAN, 0, -6, 0, -1.8 * screenHeight, "LEVEL_THREE_ELEVATOR"));
		platformList.add(new Platform(5 * screenWidth / 6, - 2 * screenHeight , screenHeight / 20, screenWidth * 3, Color.blue));
		platformList.add(new Platform(5 * screenWidth / 6, - 2 * screenHeight , screenWidth / 4, screenHeight / 20, Color.blue));
		platformList.add(new Platform(6.5 * screenWidth / 6, -4 * screenHeight, screenHeight / 20, screenHeight * 3, Color.blue));
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
