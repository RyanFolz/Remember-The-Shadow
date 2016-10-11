package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class LevelOne {
	
	BufferedImage sign, door, hedgehog, ball;
	double screenWidth, screenHeight;
	double x, y;
	
	public LevelOne(double screenWidth, double screenHeight, BufferedImage sign, BufferedImage door, BufferedImage hedgehog, BufferedImage ball){
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
		tempList.add(new Sign(-0, screenHeight - sign.getHeight() - screenHeight / 20 + 6, sign.getWidth(), sign.getHeight(), "Use A to move left and D to move right"));	
		tempList.add(new Sign(screenWidth / 4, screenHeight - sign.getHeight() - screenHeight / 20 + 6, sign.getWidth(), sign.getHeight(), "Press S to enter doors"));
		
		return tempList;
	}
	
	public ArrayList<Door> getDoor(){
		ArrayList<Door> tempList = new ArrayList<>();
		tempList.add(new Door(screenWidth / 2, screenHeight - door.getHeight() - screenHeight / 20 , door, PrintScreen.LEVEL_TWO, PrintScreen.LEVEL_ONE));
		return tempList;
	}
	
	public ArrayList<Platform> getPlatform(){
		ArrayList<Platform> platformList = new ArrayList<>();
		platformList.add(new Platform(-10000, (int)(19 * screenHeight / 20), 20000, (int)(screenHeight / 20),  new Color(0f,0f,0f, 1f )));
		platformList.add(new Platform((int)(-screenWidth / 10), (int)(screenHeight / 2), (int)(screenWidth / 40),
				(int)(screenHeight / 2 - screenHeight / 20),  new Color(0f,0f,0f, 1f )));
		platformList.add(new Platform((int)(screenWidth / 1.5), (int)(screenHeight / 2), (int)(screenWidth / 40),
				(int)(screenHeight / 2 - screenHeight / 20),  new Color(0f,0f,0f, 1f )));
		platformList.add(new Platform((int)(-screenWidth / 10), (int)(screenHeight / 2), (int)(screenWidth / 1.5 - -screenWidth / 10),
				(int)(screenWidth / 40), new Color(0f,0f,0f, 1f )));
		
		return platformList;
	}
	
	public ArrayList<Image> getImages() throws IOException{
		ArrayList<Image> tempList = new ArrayList<>();
		BufferedImage image1 =  PrintScreen.toCompatibleImage(PrintScreen.createResizedCopy(ImageIO.read(getClass().getResourceAsStream("housething.png")),
				(int)(screenWidth / 1.5 - -screenWidth / 10 + screenWidth / 26), (int)(screenHeight / 2 - screenWidth / 80), false));
		tempList.add(new Image(-screenWidth / 9.5, screenHeight / 2, image1));
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
