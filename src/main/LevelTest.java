package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LevelTest {
	
	BufferedImage sign, door, hedgehog, ball;
	double screenWidth, screenHeight;
	double x, y;
	
	public LevelTest(double screenWidth, double screenHeight, BufferedImage sign, BufferedImage door, BufferedImage hedgehog, BufferedImage ball){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.sign = sign;
		this.door = door;
		this.hedgehog = hedgehog;
		this.ball = ball;
		this.x = screenWidth / 2;
		this.y = screenHeight / 2;
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

	public ArrayList<Image> getImages(){
		ArrayList<Image> tempList = new ArrayList<>();
		
		return tempList;
	}
	
	public ArrayList<Button> getButton(){
		ArrayList<Button> tempList = new ArrayList<Button>();
		tempList.add(new Button(50, 500, 20, 20, Color.RED, 300, "TEST"));
		
		return tempList;
	}
	
	public ArrayList<Sign> getSign(){
		ArrayList<Sign> tempList = new ArrayList<>();
		tempList.add(new Sign(-500, screenHeight - (11 * sign.getHeight() / 6	), sign.getWidth(), sign.getHeight(), "eep"));	
		tempList.add(new Sign(screenWidth / 2, screenHeight - (11 * sign.getHeight() / 6), sign.getWidth(), sign.getHeight(), "FUCKAAAAA YOU"));
		
		return tempList;
	}
	
	public ArrayList<Door> getDoor(){
		ArrayList<Door> tempList = new ArrayList<>();
		tempList.add(new Door(300, screenHeight - door.getHeight() - screenHeight / 24 , door, PrintScreen.LEVEL_ONE, PrintScreen.LEVEL_TEST));
		return tempList;
	}
	
	public ArrayList<Platform> getPlatform(){
		ArrayList<Platform> platformList = new ArrayList<>();
		platformList.add(new Platform(-10000, (int)(23 * screenHeight / 24), 20000, (int)(screenHeight / 24), Color.BLUE));
		platformList.add(new Platform(screenWidth / 2, 19 * screenHeight / 27, screenWidth / 4, screenHeight / 60, Color.BLUE));
		platformList.add(new Platform((int)(screenWidth / 4), screenHeight / 2, screenWidth / 4, screenHeight / 60, Color.BLUE));
		platformList.add(new Platform((int)(3 *screenWidth / 4), screenHeight / 4, screenWidth / 4, screenHeight / 60, Color.BLUE));
		platformList.add(new Platform(screenWidth / 4, 22 * screenHeight / 27, screenWidth / 4, screenHeight / 60, Color.BLUE));
		platformList.add(new Platform(60, 5 * screenHeight / 6, 200, 20, Color.cyan, 1, 8, 100, 800, "TEST"));
		return platformList;
	}

	public ArrayList<Hedgehog> getHedgehog(){
		ArrayList<Hedgehog> hedgehogList = new ArrayList<>();
		hedgehogList.add(new Hedgehog(hedgehog, 0, screenHeight - hedgehog.getHeight() - screenHeight / 22, 5, 5, 0, screenWidth, null));
		return hedgehogList;
	}
	
	
	
	
}
