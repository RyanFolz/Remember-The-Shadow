package main;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import javax.swing.JPanel;

import org.w3c.dom.css.Rect;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.nio.Buffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import java.awt.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
public class PrintScreen extends JPanel implements KeyListener
{
	/**
	 *FINAL THINGS: 
	 */
	private static final long serialVersionUID = 7514116826406468440L;
	public static final int LEVEL_TEST = 0;
	public static final int LEVEL_ONE = 1;
	public static final double gravity = 4 * .13 / 5;
	public static final double movingSpeed = 4 * .13 / 3;
	public static final double friction = 4 * (2/3) /3;
	public static final double topSanicSpeed = 4 * 4.03  / 3;
	public static final double JUMP_SPEED = 7, BALL_SPEED = 7;
	public int frameCount = 0;

	BufferedImage ninjaLeft,ninjaRight, ball, background, hillBackground, hedgehogFacingRight, hedgehogFacingLeft,
	spriteWalkRight, spriteWalkLeft, sign, textBox, door, black;

	ArrayList<Integer> keyPressedList = new ArrayList<>();
	ArrayList<Image> imageList = new ArrayList<>();
	ArrayList<Platform> platformList = new ArrayList<>();
	ArrayList<Ball> ballList = new ArrayList<>();
	ArrayList<PlayerPoint> playerLocation = new ArrayList<>();
	ArrayList<PlayerPoint> tempPlayerLocation = new ArrayList<>();
	ArrayList<Hedgehog> hedgehogList = new ArrayList<>();
	ArrayList<Button> buttonList = new ArrayList<>();
	ArrayList<Sign> signList = new ArrayList<>();
	ArrayList<Door> doorList = new ArrayList<>();

	ThrowRectangle throwBoxBackground;
	ThrowRectangle throwBox;
	boolean showSign = false;
	Sign signToShow = null;

	int level = LEVEL_ONE;
	int previousLevel = -1;


	BufferedImage[] walkRight = new BufferedImage[5];
	BufferedImage[] walkLeft = new BufferedImage[5];

	boolean gameOver;

	public int screenHeight;
	int firstLevelFrames;
	public int screenWidth;
	public int shadowTimer;
	public int codeRun;
	public int drawRun;
	public int backgroundWidth;
	public int backgroundHeight;
	public int walkRightThingy;
	public int walkLeftThingy;
	double backgroundDisplacement = 0;
	double backgroundYDisplacement = 0;
	double screenCordX1, screenCordX2, hillBackgroundX, screenCordY, hillDisplacement, closeHillXDisplacement, closeHillYDisplacement, centerX, centerY, x1, y1;

	Player player;

	public PrintScreen() throws IOException
	{
		super(true);

		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		//kfm.addKeyEventDispatcher(this);
		kfm.setGlobalCurrentFocusCycleRoot(this);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		screenWidth = (int) gd.getDefaultConfiguration().getBounds().getWidth();
		screenHeight = (int)gd.getDefaultConfiguration().getBounds().getHeight();
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		addKeyListener(this);
		this.setOpaque(true);
		this.setBackground(Color.WHITE);

		try{

			ninjaLeft = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("ninja.png")), (int)(.03 * screenWidth), (int)(.03 * screenWidth), false));
			ninjaRight = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("rightninja.png")), (int)(.03 * screenWidth), (int)(.03 * screenWidth), false));
			ball = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("ball.png")), (int)(.012 * screenWidth), (int)(.012 * screenWidth), false));
			background = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("starbackground.jpg")), (int)(.1 * screenWidth), (int)(.1 * screenWidth), false));
			hillBackground = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("hill.png")), (int)(1 * screenWidth), (int)(.3 * screenWidth), false));
			//hillBackgroundClose = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("closshill.png")), (int)(1 * screenWidth), (int)(.3 * screenWidth), false));
			hedgehogFacingLeft = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("hedgehogfacingleft.png")), (int)(.027 * screenWidth), (int)(.021 * screenWidth), false));
			hedgehogFacingRight = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("hedgehogfacingright.png")), (int)(.027 * screenWidth), (int)(.021 * screenWidth), false));			
			spriteWalkRight = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("playerwalkright.png")), (int)(.03 * 5 * screenWidth), (int)(.03 * screenWidth), false));			
			spriteWalkLeft = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("playerwalkleft.png")), (int)(.03 * 5 * screenWidth), (int)(.03 * screenWidth), false));			
			sign = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("sign.png")), (int)(.025 * screenWidth), (int)(.025 * screenWidth), false));			
			textBox = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("textbox.png")), (int)(sign.getWidth() + 400), 200, false));			
			door = toCompatibleImage(createResizedCopy(ImageIO.read(getClass().getResourceAsStream("door.png")), (int)(.035 * screenWidth), (int)(.07 * screenWidth), false));
			black = toCompatibleImage(PrintScreen.createResizedCopy(ImageIO.read(getClass().getResourceAsStream("black.gif")), screenWidth + 20, screenHeight + 20, false));

		}catch(Exception e){
			System.out.println("ERR");
			e.printStackTrace();
		}

		for(int i = 0; i < walkRight.length; i ++){
			BufferedImage temp = spriteWalkRight.getSubimage(i * spriteWalkRight.getWidth() / 5 + 1, 0, spriteWalkRight.getWidth() / 5 - 1, spriteWalkRight.getHeight());
			walkRight[i] = temp;
		}
		for(int i = 0; i < walkLeft.length; i ++){
			BufferedImage temp = spriteWalkLeft.getSubimage(i * spriteWalkLeft.getWidth() / 5 + 1, 0, spriteWalkLeft.getWidth() / 5 - 1, spriteWalkLeft.getHeight());
			walkLeft[i] = temp;
		}


		backgroundWidth = background.getWidth();
		backgroundHeight = background.getHeight();

		player = new Player(ninjaLeft, screenWidth / 2 , screenHeight /2, 0, 0, screenHeight);


		x1 = player.getX();
		y1 = player.getY();

		centerX = screenWidth / 2;
		centerY = screenHeight / 2;


		screenCordX1 = 0;
		screenCordX2 = screenWidth;
		screenCordY = screenHeight;

		for(int i = 0; i < 500; i ++){
			playerLocation.add(new PlayerPoint(player.getX(), player.getY(), 0, player.isFacingRight(), 0, 0));
		}

		hillDisplacement = 0;

		throwBoxBackground = new ThrowRectangle((int)player.getX(),
				(int)(player.getY() - walkRight[0].getWidth() / 2),
				walkRight[0].getWidth() + 20,
				walkRight[0].getWidth() / 6,
				100, Color.WHITE);
		throwBox = new ThrowRectangle((int)player.getX() + 3,
				(int)(player.getY() - walkRight[0].getWidth() / 2 + 3),
				(int)(walkRight[0].getWidth() - 3 + 20),
				walkRight[0].getWidth() /6 - 6,
				0, Color.RED);

		InitializeLevel(level, previousLevel);
		
		

		Thread gameThread = new Thread()
		{
			public void run()
			{
				while(true){

					repaint();
					code();
					try{
						Thread.sleep((long)(1000/Main.UPDATE_RATE));
					} catch(InterruptedException e){
						e.printStackTrace();
					}


				}
			}
		};
		gameThread.start();
		//read pictures
	}

	public static BufferedImage createResizedCopy(BufferedImage originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha)
	{
		System.out.println("resizing...");
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
		g.dispose();
		return scaledBI;
	}

	private void drawString(Graphics2D g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	public static BufferedImage toCompatibleImage(BufferedImage image)
	{
		// obtain the current system graphical settings
		GraphicsConfiguration gfx_config = GraphicsEnvironment.
				getLocalGraphicsEnvironment().getDefaultScreenDevice().
				getDefaultConfiguration();

		/*
		 * if image is already compatible and optimized for current system 
		 * settings, simply return it
		 */
		if (image.getColorModel().equals(gfx_config.getColorModel()))
			return image;

		// image is not optimized, so create a new image that is
		BufferedImage new_image = gfx_config.createCompatibleImage(
				image.getWidth(), image.getHeight(), image.getTransparency());

		// get the graphics context of the new image to draw the old image on
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();

		// actually draw the image and dispose of context no longer needed
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		// return the new optimized image
		return new_image; 
	}

	@Override
	public synchronized void paintComponent(Graphics g)
	{
		//System.out.println("PAINT");
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.BLACK);
		Graphics2D g2d = (Graphics2D)g;

		drawRun ++;
		//System.out.println("DRAW: " + drawRun);

		if(level == LEVEL_TEST){
			for(int y = 0 - getBackgroundYDisplacement() - background.getHeight(); y < screenHeight; y += backgroundHeight){
				for(int x = 0 - getBackgroundDisplacement() - backgroundWidth; x < screenWidth; x += backgroundWidth){
					g2d.drawImage(background, x, y, null)  ;

				}
			}

			for(int x = (int) (0 - getHillBackgroundDisplacement() - hillBackground.getWidth()); x < screenWidth; x +=  hillBackground.getWidth()){
				g2d.drawImage(hillBackground, x,(int)( hillBackground.getHeight() - hillDisplacement), null);
			}
		}
		
		if(level == LEVEL_ONE){
			g2d.drawImage(black, -10, -10, null);
		}




		for(Image i: imageList){
			g2d.drawImage(i.getImage(), (int)i.getX(), (int)i.getY(), null);
		}


		/*System.out.println("background printed " + backgroundPrint + " times!");*/


		for(Platform p : platformList){
			g2d.setColor(p.getColor());
			g2d.fillRect((int)p.getX(), (int)p.getY(), (int)p.getWidth(), (int)p.getHeight());
			g2d.setColor(Color.white);
			g2d.drawRect((int)p.getX(), (int)p.getY(), (int)p.getWidth(), (int)p.getHeight());
		}
		for(Button b: buttonList){
			g2d.setColor(b.getColor());
			g2d.fillRect((int)b.getX(), (int)b.getY(), (int)b.getWidth(), (int)b.getHeight());
			g2d.setColor(Color.white);
			g2d.drawRect((int)b.getX(), (int)b.getY(), (int)b.getWidth(), (int)b.getHeight());
		}

		for(Door d: doorList){
			g2d.drawImage(d.getImage(), (int)d.getX(), (int)d.getY(), null);
		}

		for(Ball b : ballList){
			g2d.drawImage(b.getImage(), (int)(b.getX()), (int)(b.getY()), null);
			// ************* BALL HITBOXES ************
			/*			g2d.setColor(Color.WHITE);
			g2d.drawRect((int)(b.getX() + b.getImage().getWidth() / 2 - 3), (int) (b.getY() + b.getImage().getHeight()), 6, 6);
			g2d.drawRect((int)(b.getX() + b.getImage().getWidth() / 2 - 3), (int) (b.getY() - 6), 6, 6);
			g2d.drawRect((int) b.getX() + b.getImage().getWidth(), (int)b.getY() + b.getImage().getHeight() / 2 - 3, 6, 6);
			g2d.drawRect((int) b.getX() - 6, (int)b.getY() + b.getImage().getHeight() / 2 - 3, 6, 6);

			g2d.drawRect((int)b.getX() + 5 * b.getImage().getWidth() / 6, (int)b.getY() + 5 * b.getImage().getHeight() / 6, 6, 6);
			g2d.drawRect((int)b.getX() + 1 * b.getImage().getWidth() / 6 - 6, (int)b.getY() + 5 * b.getImage().getHeight() / 6, 6, 6);
			g2d.drawRect((int)b.getX() + 5 * b.getImage().getWidth() / 6, (int)b.getY() + b.getImage().getHeight() / 6 - 6, 6, 6);
			g2d.drawRect((int)b.getX() + 1 * b.getImage().getWidth() / 6 - 6, (int)b.getY() +  b.getImage().getHeight() / 6 - 6, 6, 6);*/
		}

		for(Sign s: signList){
			g2d.drawImage(sign, (int)s.getX(), (int)s.getY(), null);
		}

		if(shadowTimer > 0){
			if(tempPlayerLocation.size() > 0){
				if(tempPlayerLocation.get(0).isRight()){
					g2d.drawImage(ninjaLeft, (int)playerLocation.get(0).getX(), (int)playerLocation.get(0).getY(), null);
				}else{
					g2d.drawImage(ninjaRight, (int)playerLocation.get(0).getX(), (int)playerLocation.get(0).getY(), null);
				}


			}
		}


		/*		try{
			for(Point p : pointList){

				g2d.setColor(Color.white);
				g2d.drawLine((int)p.getX(), (int)p.getY(), (int)p.getX(), (int)p.getY());

			}
		}catch(Exception e){
			System.out.println("ERROR");

		}
		 */

		for(Hedgehog h : hedgehogList){
			g2d.drawImage(h.getImage(), (int)h.getX(), (int)h.getY(), null);
		}

		if(player.isFacingRight()){
			g2d.drawImage(walkRight[walkRightThingy % 5], (int)(player.getX()), (int)(player.getY()), null);
		}else{
			g2d.drawImage(walkLeft[walkLeftThingy % 5], (int)(player.getX()), (int)(player.getY()), null);
		}

		g2d.setColor(throwBoxBackground.getColor());
		g2d.fillRect((int)throwBoxBackground.getX(), (int)throwBoxBackground.getY(), (int)(100 * throwBoxBackground.getWidth() / throwBoxBackground.getHealth()), (int)throwBoxBackground.getHeight());

		g2d.setColor(Color.red);
		g2d.fillRect((int)throwBox.getX(), (int)throwBox.getY(), (int)((throwBox.getHealth() - 20) * throwBox.getWidth() / 100), (int)throwBox.getHeight());

		if(showSign){
			g2d.drawImage(textBox, (int)(signToShow.getX() - 200), (int)(signToShow.getY() - 250), null);
			g2d.setFont(getFont().deriveFont(20.0f));
			drawString(g2d, signToShow.getText(), (int)signToShow.getX() - 180, (int)(signToShow.getY() - 220));

		}



		/*		g2d.fillRect(playerOneHP.getX(), playerOneHP.getY(), 310, playerOneHP.getHeight());
		g2d.fillRect(playerTwoHP.getX(), playerTwoHP.getY(), playerTwoHP.getWidth(), playerTwoHP.getHeight());
		 */


/*
		g2d.setColor(Color.WHITE);
		g2d.drawRect((int)(player.getX() + player.getImage().getWidth() / 2 - 3	),
				(int)(player.getY() + player.getImage().getHeight() + 1), 
				2, 6);
		g2d.drawRect((int)(player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5 - 10),
				(int)(player.getY() + player.getImage().getHeight()), 
				2, 5);
		g2d.drawRect((int)(player.getX() + player.getImage().getWidth() / 5 + 10),
				(int)(player.getY() + player.getImage().getHeight()), 
				2, 5);
		g2d.drawRect((int)(player.getX() + player.getImage().getWidth() / 2 - 3),
				(int)(player.getY() - 6), 
				2, 6);
		g2d.drawRect((int)(player.getX() + player.getImage().getWidth() / 5 + 10),
				(int)(player.getY() - 6), 
				2, 5);
		g2d.drawRect((int)(player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5 - 10),
				(int)(player.getY() - 6), 
				2, 5);


		g2d.drawRect((int) player.getX() + player.getImage().getWidth() / 5 - 3, 
				(int) player.getY() + player.getImage().getHeight() / 2 - 3,
				3, 6);
		g2d.drawRect((int) player.getX() + player.getImage().getWidth() / 5 - 3, 
				(int) player.getY(),
				3, 6);
		g2d.drawRect((int) player.getX() + player.getImage().getWidth() / 5 - 3, 
				(int) player.getY() +  19 * player.getImage().getHeight() / 20 - 3,
				3, 6);
		g2d.drawRect((int) player.getX() + player.getImage().getWidth() / 5 - 3, 
				(int) player.getY() + 3 * player.getImage().getHeight() / 4 - 3,
				3, 6);
		g2d.drawRect((int) player.getX() + player.getImage().getWidth() / 5 - 3, 
				(int) player.getY() + player.getImage().getHeight() / 4 - 3,
				3, 6);


		g2d.drawRect((int) (player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
				(int) player.getY() + player.getImage().getHeight() / 2 - 3,
				3, 6);
		g2d.drawRect((int) (player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
				(int) player.getY(),
				3, 6);
		g2d.drawRect((int) (player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
				(int) player.getY() +  19 * player.getImage().getHeight() / 20 - 3,
				3, 6);
		g2d.drawRect((int) (player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
				(int) player.getY() + 3 * player.getImage().getHeight() / 4 - 3,
				3, 6);
		g2d.drawRect((int) (player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
				(int) player.getY() + player.getImage().getHeight() / 4 - 3,
				3, 6);

		g2d.setColor(Color.MAGENTA);
		g2d.fillRect((int) player.getX() + player.getImage().getWidth() / 5 - 3, 
				(int) player.getY(),
				3, 6);*/


	}




	public void code(){
		//System.out.println("CODE");
		boolean collision = false;
		if(!gameOver){

			codeRun ++;

			playerLocation.add(new PlayerPoint(player.getX(), player.getY(), 0, player.facingRight, 0, 0));



			while(playerLocation.size() > 500){
				playerLocation.remove(0);
			}

			if(shadowTimer > 0){
				shadowTimer --;
			}

			throwBoxBackground.setX((int)player.getX());
			throwBoxBackground.setY((int)(player.getY() - player.getImage().getWidth() / 2));

			throwBox.setX((int)player.getX() + 3);
			throwBox.setY((int)(player.getY() - player.getImage().getWidth() / 2) + 3);
			try{
				for(Integer i : keyPressedList){
					if(i == KeyEvent.VK_UP){
						throwBox.setHealth(throwBox.getHealth() - 19);
					}
				}
			}catch(NoSuchElementException e){

			}

			for(int i = ballList.size() - 1; i >= 0; i --){
				if(ballList.get(i).timer > 500){
					ballList.remove(i);
				}else{
					ballList.get(i).timer ++;
				}
			}

			for(Hedgehog h : hedgehogList){				
				h.updateX();

				if(h.getxVel() > 0){
					if(h.getImage() != hedgehogFacingRight){
						h.setImage(hedgehogFacingRight);
					}
				}else{
					if(h.getImage() != hedgehogFacingLeft){
						h.setImage(hedgehogFacingLeft);
					}
				}

			}

			if(player.getxVel() < 0 && codeRun % 10 == 0){
				walkRightThingy ++;
			}else if(player.getxVel() > 0 && codeRun % 10 == 0){
				walkLeftThingy ++;
			}

			for(Button b: buttonList){
				b.setIncrement(b.getIncrement() + 1);
			}

			if(tempPlayerLocation.size() > 0){
				if(tempPlayerLocation.get(0).getAction() == 1){

					if(tempPlayerLocation.get(0).isRight()){
						System.out.println("NEW RIGHT SHADOW BALL, X: " + tempPlayerLocation.get(0).getX() + " Y: " + tempPlayerLocation.get(0).getY());
						ballList.add(new Ball((int)(tempPlayerLocation.get(0).getX() + 4 * player.getImage().getWidth() / 5),
								(int)(tempPlayerLocation.get(0).getY() - ball.getHeight()),
								BALL_SPEED * tempPlayerLocation.get(0).getSpeed() / 100, -BALL_SPEED * tempPlayerLocation.get(0).getSpeed() / 100, ball, true));
					}else{
						ballList.add(new Ball((int)(tempPlayerLocation.get(0).getX() - player.getImage().getWidth() / 5),
								(int)(tempPlayerLocation.get(0).getY() - ball.getHeight()), 
								-BALL_SPEED * tempPlayerLocation.get(0).getSpeed() / 100, -BALL_SPEED * tempPlayerLocation.get(0).getSpeed() / 100, ball, false));
					}
				}
				tempPlayerLocation.remove(0);
			}


			boolean dontMoveRight = false;
			boolean dontMoveLeft = false;


			try{
				for(Ball b : ballList){
					b.setX(b.getX() + player.getxVel());
				}
			}catch(ConcurrentModificationException e){

			}
			backgroundDisplacement += player.getxVel() / 4;
			hillBackgroundX += player.getxVel() / 2;
			for(Platform p : platformList){
				p.setxStart(p.getxStart() + player.getxVel());
				p.setX(p.getX() + player.getxVel());
			}
			for(Button b: buttonList){
				b.setX(b.getX() + player.getxVel());
			}
			try{
				for(PlayerPoint p: playerLocation){
					try{
						p.setX(p.getX() + player.getxVel());
					}catch(NullPointerException e){

					}
				}
			}catch(ConcurrentModificationException e){

			}
			for(Hedgehog h : hedgehogList){
				h.setX(h.getX() + player.getxVel());
				h.setRangeXOne(h.getRangeXOne() + player.getxVel());
				h.setRangeXTwo(h.getRangeXTwo() + player.getxVel());
			}
			for(Sign s: signList){
				s.setX(s.getX() + player.getxVel());
			}
			for(Door d: doorList){
				d.setX(d.getX() + player.getxVel());
			}
			
			if(platformList.size() > 0){
				if(platformList.get(0).getY() > screenHeight - screenHeight / 20){
					System.out.println("Bottom Platform lower than thing");
				}else{
					System.out.println("Bottom Platform not lower than thing");
				}
				if(player.getY() < centerY){
					System.out.println("Player y is less than centerY");
				}else{
					System.out.println("Player y is not less than centerY");
				}
				System.out.println(player.getX());
				System.out.println(player.getY());
				if(platformList.get(0).getY() > screenHeight - screenHeight / 20 || player.getY() < centerY){
					try{
						for(Ball b : ballList){
							b.setY(b.getY() + player.getyVel());
						}
					}catch(ConcurrentModificationException e){

					}
					for(Platform p : platformList){
						p.setY(p.getY() + player.getyVel());
						p.setyStart(p.getyStart() + player.getyVel());
					}
					for(Button b: buttonList){
						b.setY(b.getY() + player.getyVel());
					}
					try{
						for(PlayerPoint p: playerLocation){
							p.setY(p.getY() + player.getyVel());
						}
					}catch(ConcurrentModificationException e){

					}
					for(Hedgehog h : hedgehogList){
						h.setY(h.getY() + player.getyVel());
					}
					for(Sign s: signList){
						s.setY(s.getY() + player.getyVel());
					}
					for(Door d: doorList){
						d.setY(d.getY() + player.getyVel());
					}

					hillDisplacement -= player.getyVel() / 2;
					backgroundYDisplacement += player.getyVel() / 4;

					

					if(player.getY() != centerY){
						if(firstLevelFrames > 0){
							firstLevelFrames --;
						}else{
							double distance = player.getY() - centerY;
							
							hillDisplacement += distance / 2;
							backgroundYDisplacement += distance / 4;
							for(Platform p: platformList){
								p.setY(p.getY() - distance);
								p.setyStart(p.getyStart() - distance);
							}

							try{
								for(Ball b : ballList){
									b.setY(b.getY() - distance);
								}
							}catch(ConcurrentModificationException e){

							}
							for(Button b: buttonList){
								b.setY(b.getY() - distance);
							}
							try{
								for(PlayerPoint p: playerLocation){
									p.setY(p.getY() - distance);
								}
							}catch(ConcurrentModificationException e){

							}
							for(Hedgehog h : hedgehogList){
								h.setY(h.getY() - distance);
							}
							for(Sign s: signList){
								s.setY(s.getY() - distance);
							}
							for(Door d: doorList){
								d.setY(d.getY() - distance);
							}


							player.setY(centerY);
						}
					}
				}else{
					player.setY(player.getY() - player.getyVel());
					if(platformList.get(0).getY() < screenHeight - screenHeight / 20){
						double distance = platformList.get(0).getY() - (screenHeight - screenHeight / 20);
						hillDisplacement += distance / 2;
						backgroundYDisplacement += distance / 4;
						for(Platform p: platformList){
							p.setY(p.getY() - distance);
							p.setyStart(p.getyStart() - distance);
						}

						try{
							for(Ball b : ballList){
								b.setY(b.getY() - distance);
							}
						}catch(ConcurrentModificationException e){

						}
						for(Button b: buttonList){
							b.setY(b.getY() - distance);
						}
						try{
							for(PlayerPoint p: playerLocation){
								p.setY(p.getY() - distance);
							}
						}catch(ConcurrentModificationException e){

						}
						for(Hedgehog h : hedgehogList){
							h.setY(h.getY() - distance);
						}
						for(Sign s: signList){
							s.setY(s.getY() - distance);
						}
						for(Door d: doorList){
							d.setY(d.getY() - distance);
						}
					}
				}


				if(player.getX() != centerX){
					double distance = player.getX() - centerX;

					for(Platform p: platformList){
						p.setX(p.getX() - distance);
						p.setxStart(p.getxStart() - distance);
					}

					backgroundDisplacement -= distance;
					hillBackgroundX -= distance;
					try{
						for(Ball b : ballList){
							b.setX(b.getX() - distance);
						}
					}catch(ConcurrentModificationException e){

					}
					for(Button b: buttonList){
						b.setX(b.getX() - distance);
					}
					try{
						for(PlayerPoint p: playerLocation){
							p.setX(p.getX() - distance);
						}
					}catch(ConcurrentModificationException e){

					}
					for(Hedgehog h : hedgehogList){
						h.setX(h.getX() - distance);
					}
					for(Sign s: signList){
						s.setX(s.getX() - distance);
					}
					for(Door d: doorList){
						d.setX(d.getX() - distance);
					}


					player.setX(centerX);
				}
			}





			if(keyPressedList.contains(KeyEvent.VK_D) && keyPressedList.contains(KeyEvent.VK_A)){
				if(!dontMoveRight && !dontMoveLeft){
					player.updatexVel(2);
					//player.updateX();
				}else{
					player.updatexVel(2);
				}
			}

			else if(keyPressedList.contains(KeyEvent.VK_A)){

				if(!player.getImage().equals(ninjaRight)){
					player.setFacingRight(false);
					player.setImage(ninjaRight);
				}

				if(!dontMoveRight && !dontMoveLeft){
					player.updatexVel(0);
					//player.updateX();
				}else{
					player.updatexVel(0);
				}
			}

			else if(keyPressedList.contains(KeyEvent.VK_D)){
				if(!player.getImage().equals(ninjaLeft)){
					player.setFacingRight(true);
					player.setImage(ninjaLeft);
				}
				if(!dontMoveLeft && !dontMoveRight){
					player.updatexVel(1);
					//player.updateX();
				}else{
					player.updatexVel(1);
				}
			}

			else if(!keyPressedList.contains(KeyEvent.VK_D) && !keyPressedList.contains(KeyEvent.VK_A)){
				if(!dontMoveLeft && !dontMoveRight){
					player.updatexVel(2);
					//player.updateX();
				}else{
					player.updatexVel(2);
				}
			}	

			try{
				player.updateyVel();
				//player.updateY();
			}catch(ConcurrentModificationException e){

			}




			try{
				for(Ball b : ballList){

					b.updateX();
					b.updateY();


				}

			}catch(ConcurrentModificationException e){

			}


			for(Button butt: buttonList){
				Rectangle buttonRect = new Rectangle((int)butt.getX(), (int)butt.getY(), (int)butt.getWidth(), (int)butt.getHeight());

				for(Platform p: platformList){
					if(butt.getCode().equals(p.getCode()) && butt.getColor().equals(Color.green)){
						p.updatePosition();
					}else{
						p.unUpdatePosition();
					}
				}

				try{
					for(Ball b : ballList){
						Rectangle bottomRectangle = new Rectangle((int)(b.getX() + b.getImage().getWidth() / 2 - 3), (int) (b.getY() + b.getImage().getHeight()), 6, 6);


						Rectangle topRectangle = new Rectangle((int)(b.getX() + b.getImage().getWidth() / 2 - 3), (int) (b.getY() - 6), 6, 6);



						if(buttonRect.contains(bottomRectangle)){

							b.setY(butt.getY() - b.getImage().getHeight());
							b.setyVel(0);
							if(b.right){
								if(b.getxVel() > 0){
									b.setxVel(b.getxVel() - movingSpeed);
								}else{
									b.setxVel(0);
								}
							}else{
								if(b.getxVel() < 0){
									b.setxVel(b.getxVel() + movingSpeed);
								}else{
									b.setxVel(0);
								}
							}
							butt.setColor(Color.GREEN);
							butt.setIncrement(0);

						}

						Rectangle rightRectangle = new Rectangle((int) b.getX() + b.getImage().getWidth(), (int)b.getY() + b.getImage().getHeight() / 2 - 3, 6, 6);
						Rectangle rightBotRectangle = new Rectangle((int)b.getX() + 5 * b.getImage().getWidth() / 6, (int)b.getY() + 5 * b.getImage().getHeight() / 6, 6, 6);
						Rectangle rightTopRectangle = new Rectangle((int)b.getX() + 5 * b.getImage().getWidth() / 6, (int)b.getY() + b.getImage().getHeight() / 6 - 6, 6, 6);

						Rectangle leftRectangle = new Rectangle((int) b.getX() - 6, (int)b.getY() + b.getImage().getHeight() / 2 - 3, 6, 6);
						Rectangle leftBotRectangle = new Rectangle((int)b.getX() + 1 * b.getImage().getWidth() / 6 - 6, (int)b.getY() + 5 * b.getImage().getHeight() / 6, 6, 6);
						Rectangle leftTopRectangle = new Rectangle((int)b.getX() + 1 * b.getImage().getWidth() / 6 - 6, (int)b.getY() +  b.getImage().getHeight() / 6 - 6, 6, 6);

						if(buttonRect.contains(topRectangle)){
							butt.setColor(Color.GREEN);
							butt.setIncrement(0);
							b.setY(butt.getY() + butt.getHeight() + 2);
							b.setyVel(0);
						}else

							if(buttonRect.contains(rightRectangle) || buttonRect.contains(rightBotRectangle) || buttonRect.contains(rightTopRectangle)){
								b.setX(buttonRect.getX() - b.getImage().getWidth());
								b.setxVel(-1 * b.getxVel() / 3);
								butt.setColor(Color.GREEN);
								butt.setIncrement(0);

								if(b.isRight()){
									b.setRight(false);
								}else{
									b.setRight(true);
								}
							} else

								if(buttonRect.contains(leftRectangle) || buttonRect.contains(leftBotRectangle) || buttonRect.contains(leftTopRectangle)){
									b.setX(buttonRect.getX() + buttonRect.getWidth());
									b.setxVel(-1 * b.getxVel() / 3);
									butt.setColor(Color.GREEN);
									butt.setIncrement(0);

									if(b.isRight()){
										b.setRight(false);
									}else{
										b.setRight(true);
									}

								}					

					}
				}catch(ConcurrentModificationException e){

				}
			}



			try{
				for(Platform p : platformList){
					if(!collision){					

						Rectangle platRect = new Rectangle((int)p.getX(), (int)p.getY(), (int)p.getWidth(), (int)p.getHeight());
						Rectangle playerBotMidRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() / 2 - 3	),
								(int)(player.getY() + player.getImage().getHeight() + 1), 
								2, 6);
						Rectangle playerBotRightRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5 - 10),
								(int)(player.getY() + player.getImage().getHeight()), 
								2, 5);
						Rectangle playerBotLeftRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() / 5 + 5),
								(int)(player.getY() + player.getImage().getHeight()), 
								1, 5);


						try{
							for(Ball b : ballList){
								Rectangle bottomRectangle = new Rectangle((int)(b.getX() + b.getImage().getWidth() / 2 - 3), (int) (b.getY() + b.getImage().getHeight()), 6, 6);


								Rectangle topRectangle = new Rectangle((int)(b.getX() + b.getImage().getWidth() / 2 - 3), (int) (b.getY() - 6), 6, 6);



								if(platRect.contains(bottomRectangle)){

									b.setY(p.getY() - b.getImage().getHeight());
									b.setyVel(0);
									if(b.right){
										if(b.getxVel() > 0){
											b.setxVel(b.getxVel() - movingSpeed);
										}else{
											b.setxVel(0);
										}
									}else{
										if(b.getxVel() < 0){
											b.setxVel(b.getxVel() + movingSpeed);
										}else{
											b.setxVel(0);
										}
									}

								}

								Rectangle rightRectangle = new Rectangle((int) b.getX() + b.getImage().getWidth(), (int)b.getY() + b.getImage().getHeight() / 2 - 3, 6, 6);
								Rectangle rightBotRectangle = new Rectangle((int)b.getX() + 5 * b.getImage().getWidth() / 6, (int)b.getY() + 5 * b.getImage().getHeight() / 6, 6, 6);
								Rectangle rightTopRectangle = new Rectangle((int)b.getX() + 5 * b.getImage().getWidth() / 6, (int)b.getY() + b.getImage().getHeight() / 6 - 6, 6, 6);

								Rectangle leftRectangle = new Rectangle((int) b.getX() - 6, (int)b.getY() + b.getImage().getHeight() / 2 - 3, 6, 6);
								Rectangle leftBotRectangle = new Rectangle((int)b.getX() + 1 * b.getImage().getWidth() / 6 - 6, (int)b.getY() + 5 * b.getImage().getHeight() / 6, 6, 6);
								Rectangle leftTopRectangle = new Rectangle((int)b.getX() + 1 * b.getImage().getWidth() / 6 - 6, (int)b.getY() +  b.getImage().getHeight() / 6 - 6, 6, 6);

								if(platRect.contains(topRectangle)){

									b.setY(p.getY() + p.getHeight() + 2);
									b.setyVel(0);
								}else

									if(platRect.contains(rightRectangle) || platRect.contains(rightBotRectangle) || platRect.contains(rightTopRectangle)){
										b.setX(platRect.getX() - b.getImage().getWidth());
										b.setxVel(-1 * b.getxVel() / 3);
										if(b.isRight()){
											b.setRight(false);
										}else{
											b.setRight(true);
										}
									} else

										if(platRect.contains(leftRectangle) || platRect.contains(leftBotRectangle) || platRect.contains(leftTopRectangle)){
											b.setX(platRect.getX() + platRect.getWidth());
											b.setxVel(-1 * b.getxVel() / 3);
											if(b.isRight()){
												b.setRight(false);
											}else{
												b.setRight(true);
											}

										}					

							}
						}catch(ConcurrentModificationException e){

						}


						if(platRect.contains(playerBotMidRect) || platRect.contains(playerBotRightRect) || platRect.contains(playerBotLeftRect)){

							player.setY(p.getY() - player.getImage().getHeight());
							player.setyVel(0);
							player.isStanding = true;
						}

						Rectangle playerTopMidRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() / 2 - 3),
								(int)(player.getY() - 6), 
								2, 6);
						Rectangle playerTopLeftRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() / 5 + 10),
								(int)(player.getY() - 6), 
								2, 5);
						Rectangle playerTopRightRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5 - 10),
								(int)(player.getY() - 6), 
								2, 5);

						Rectangle playerLeftMidRect = new Rectangle((int) player.getX() + player.getImage().getWidth() / 5 - 1, 
								(int) player.getY() + player.getImage().getHeight() / 2 - 3,
								1, 6);					
						Rectangle playerLeftTopRect = new Rectangle((int) player.getX() + player.getImage().getWidth() / 5 - 1, 
								(int) player.getY() + player.getImage().getHeight() / 4 - 3,
								1, 6);
						Rectangle playerLeftTopTopRect = new Rectangle((int) player.getX() + player.getImage().getWidth() / 5 - 1, 
								(int) player.getY(),
								1, 6);
						Rectangle playerLeftBotRect = new Rectangle((int) player.getX() + player.getImage().getWidth() / 5 - 1, 
								(int) player.getY() + 3 * player.getImage().getHeight() / 4 - 3,
								1, 6);
						Rectangle playerLeftBotBotRect = new Rectangle((int) player.getX() + player.getImage().getWidth() / 5 - 1, 
								(int) player.getY() +  19 * player.getImage().getHeight() / 20 - 3,
								1, 6);

						Rectangle playerRightMidRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
								(int) player.getY() + player.getImage().getHeight() / 2 - 3,
								1, 6);					
						Rectangle playerRightTopRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
								(int) player.getY() + player.getImage().getHeight() / 4 - 3,
								1, 6);
						Rectangle playerRightTopTopRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
								(int) player.getY(),
								1, 6);
						Rectangle playerRightBotRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
								(int) player.getY() + 3 * player.getImage().getHeight() / 4 - 3,
								1, 6);
						Rectangle playerRightBotBotRect = new Rectangle((int) (player.getX() + player.getImage().getWidth() - player.getImage().getWidth() / 5), 
								(int) player.getY() +  19 * player.getImage().getHeight() / 20 - 3,
								1, 6);

						if(platRect.contains(playerTopMidRect) || platRect.contains(playerTopRightRect) || platRect.contains(playerTopLeftRect)){
							player.setyVel(0);
							player.setY(p.getY() + p.getHeight() + 2);

						}else{
							if(true){
								if(platRect.contains(playerLeftMidRect) || platRect.contains(playerLeftTopRect) || platRect.contains(playerLeftTopTopRect)
										|| platRect.contains(playerLeftBotRect) || platRect.contains(playerLeftBotBotRect))	{
									player.setxVel(0);
									player.setX(p.getX() + 1 + p.getWidth() - player.getImage().getWidth() / 5);
								}

								if(platRect.contains(playerRightMidRect) || platRect.contains(playerRightTopRect) || platRect.contains(playerRightTopTopRect)
										|| platRect.contains(playerRightBotRect) || platRect.contains(playerRightBotBotRect))	{
									player.setxVel(0);
									player.setX(p.getX() - 2 - 4 *player.getImage().getWidth() / 5);
								}
							}
						}


					}
				}
			}catch(ConcurrentModificationException e){

			}

			boolean isNear = false;

			for(Sign s: signList){
				double signX = s.getX() + sign.getWidth() / 2;
				double signY = s.getY() + sign.getHeight() / 2;

				double charX = player.getX() + player.getImage().getHeight() / 2;
				double charY = player.getY() + player.getImage().getWidth() / 2;

				double distance = Math.sqrt(Math.pow(charX - signX, 2) + Math.pow(charY - signY , 2));

				if(distance < 100){
					signToShow = s;
					showSign = true;
					isNear = true;
				}

			}
			if(!isNear){
				showSign = false;
			}


		}

	}

	private int getBackgroundDisplacement() {
		return Math.abs((int)backgroundDisplacement % background.getWidth() - background.getWidth()) ;

	}

	private int getBackgroundYDisplacement() {
		return Math.abs((int)backgroundYDisplacement % background.getHeight() - background.getHeight());
	}

	private int getHillBackgroundDisplacement(){
		return Math.abs((int)hillBackgroundX % hillBackground.getWidth() - hillBackground.getWidth());
	}



	public void InitializeLevel(int level, int levelFrom) throws IOException{
		ballList.clear();
		buttonList.clear();
		doorList.clear();
		hedgehogList.clear();
		platformList.clear();
		playerLocation.clear();
		signList.clear();
		imageList.clear();

		screenCordX1 = screenCordX2 = hillBackgroundX = screenCordY = hillDisplacement = closeHillXDisplacement = closeHillYDisplacement = 0;

		Door tempDoor = null;

		switch(level){
		case LEVEL_TEST:
			LevelTest lvlTest = new LevelTest(screenWidth, screenHeight, sign, door, hedgehogFacingRight, ball);			
			buttonList = lvlTest.getButton();
			doorList = lvlTest.getDoor();
			System.out.println("LEVEL: " + level);
			System.out.println("LEVEL FROM: " + level);
			for(Door d: doorList){
				System.out.println("FOR DOOR CODE TO: " + d.getCodeTo());
				System.out.println("FOR DOOR CODE FROM: " + d.getCodeFrom());
				if(d.getCodeTo() == levelFrom && d.getCodeFrom() == level){
					System.out.println("FOUND DOOR");
					System.out.println("DOOR Y: " + d.getY());
					tempDoor = d;
				}

			}
			hedgehogList = lvlTest.getHedgehog();
			platformList = lvlTest.getPlatform();
			signList = lvlTest.getSign();	
			imageList = lvlTest.getImages();
			if(tempDoor != null){
				player.setX(tempDoor.getX());
				player.setY(tempDoor.getY());
			}else{
				player.setX(lvlTest.getX());
				player.setY(lvlTest.getY());
			}
			System.out.println("TEMP DOOR Y: " + tempDoor.getY());
			this.level = LEVEL_TEST;/*
			firstLevelFrames = 50;*/
			break;

		case LEVEL_ONE:
			LevelOne lvlOne = new LevelOne(screenWidth, screenHeight, sign, door, hedgehogFacingRight, ball);
			buttonList = lvlOne.getButton();
			doorList = lvlOne.getDoor();
			for(Door d: doorList){
				System.out.println("FOR DOOR CODE TO: " + d.getCodeTo());
				System.out.println("FOR DOOR CODE FROM: " + d.getCodeFrom());
				if(d.getCodeTo() == levelFrom && d.getCodeFrom() == level){
					tempDoor = d;
				}
			}
			hedgehogList = lvlOne.getHedgehog();
			platformList = lvlOne.getPlatform();
			signList = lvlOne.getSign();	
			imageList = lvlOne.getImages();
			if(tempDoor != null){
				player.setX(tempDoor.getX());
				player.setY(tempDoor.getY());
				System.out.println("WORK WORK WORK");
			}else{
				player.setX(lvlOne.getX());
				player.setY(lvlOne.getY());
			}
			this.level = LEVEL_ONE;/*
			firstLevelFrames = 50;*/
			break;

		}
	}



	@Override
	public void keyPressed(KeyEvent keyEvent) 
	{
		switch(keyEvent.getKeyCode())
		{
		case KeyEvent.VK_D:
			boolean hasKey = false;
			for(Integer i: keyPressedList){
				if(i.equals(KeyEvent.VK_D)){
					hasKey = true;
				}
			}
			if(!hasKey){
				keyPressedList.add(KeyEvent.VK_D);
			}
			break;

		case KeyEvent.VK_UP:
			boolean hasKey6 = false;
			for(Integer i: keyPressedList){
				if(i.equals(KeyEvent.VK_UP)){
					hasKey6 = true;
				}
			}
			if(!hasKey6){
				keyPressedList.add(KeyEvent.VK_UP);



			}


			break;	

		case KeyEvent.VK_W:
			boolean hasKey1 = false;
			for(Integer i: keyPressedList){
				if(i.equals(KeyEvent.VK_W)){
					hasKey1 = true;
				}
			}
			if(!hasKey1){
				keyPressedList.add(KeyEvent.VK_W);
			}
			if(player.isStanding){
				if(Math.abs(player.getyVel()) <= 4 * .18 / 3){
					player.setyVel(JUMP_SPEED);
					player.updateY();
					player.isStanding = false;
				}
			}
			break;

		case KeyEvent.VK_S:
			boolean hasKey9 = false;
			for(Integer i: keyPressedList){
				if(i.equals(KeyEvent.VK_S)){
					hasKey9 = true;
				}
			}
			if(!hasKey9){
				keyPressedList.add(KeyEvent.VK_S);
				Rectangle playerCenterRect = new Rectangle((int)(player.getX() + player.getImage().getWidth() / 2 -2),
						(int)(player.getY() + player.getImage().getHeight() / 2 - 2),
						4, 4);
				try{
					for(Door d: doorList){
						Rectangle tempRect = new Rectangle((int)d.getX(), (int)d.getY(), d.getImage().getWidth(), d.getImage().getHeight());
						if(tempRect.contains(playerCenterRect)){
							previousLevel = level;
							InitializeLevel(d.getCodeTo(), d.getCodeFrom());
						}
					}
				}catch(ConcurrentModificationException | IOException e){

				}
			}
			break;

		case KeyEvent.VK_A:
			boolean hasKey2 = false;
			for(Integer i: keyPressedList){
				if(i.equals(KeyEvent.VK_A)){
					hasKey2 = true;
				}
			}
			if(!hasKey2){
				keyPressedList.add(KeyEvent.VK_A);
			}
			break;

		case KeyEvent.VK_DOWN:
			boolean hasKey7 = false;
			for(Integer i: keyPressedList){
				if(i.equals(KeyEvent.VK_DOWN)){
					hasKey7 = true;
				}
			}
			if(!hasKey7){
				keyPressedList.add(KeyEvent.VK_DOWN);
				if(shadowTimer <= 0){
					tempPlayerLocation.clear();
					for(int i = 0; i < playerLocation.size(); i++){
						tempPlayerLocation.add(playerLocation.get(i));
					}

					shadowTimer = playerLocation.size();
				}else{
					System.out.println("Screen Cord Y1: " + screenCordY);
					double tempX = tempPlayerLocation.get(0).getX();
					double tempY = tempPlayerLocation.get(0).getY();

					double uh = tempX - player.getX();
					double uh2 = tempY - player.getY();

					for(Ball b : ballList){
						b.setX(b.getX() - uh);
						//b.setY(b.getY() - uh2);
					}
					for(Platform p : platformList){
						p.setX(p.getX() - uh);
						p.setxStart(p.getxStart() - uh);
						//p.setY(p.getY() - uh2);
					}
					for(Door d: doorList){
						d.setX(d.getX() - uh);
					}
					//screenCordY -= uh2;
					backgroundDisplacement -= uh / 4;
					//backgroundYDisplacement -= uh2 / 2;

					//player.setY(player.getY() - uh2);

					hillBackgroundX -= uh / 2;

					System.out.println(hillDisplacement);
					try{
						for(PlayerPoint p: playerLocation){
							p.setX(p.getX() - uh);
							//p.setY(p.getY() - uh2);
						}
					}catch(ConcurrentModificationException e){

					}
					try{
						tempPlayerLocation.clear();
					}catch(ConcurrentModificationException e){

					}
					for(Hedgehog h : hedgehogList){
						h.setX(h.getX() - uh);
						h.setRangeXOne(h.getRangeXOne() - uh);
						h.setRangeXTwo(h.getRangeXTwo() - uh);
						//h.setY(h.getY() - uh2);
					}
					for(Button b : buttonList){
						b.setX(b.getX() - uh);
						//b.setY(b.getY() - uh2);
					}
					for(Sign s: signList){
						s.setX(s.getX() - uh);
						//s.setY(s.getY() - uh2);
					}
					//player.setX(tempX - uh);
					player.setY(tempY);

					tempPlayerLocation.clear();

					shadowTimer = 0;







					System.out.println("Screen Cord Y2: " + screenCordY);

				}

			}
			break;

		case KeyEvent.VK_RIGHT:

			break;

		case KeyEvent.VK_LEFT:

			break;
		}
	}


	@Override
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		for(int i = keyPressedList.size() - 1; i >=0; i --){
			if(key == keyPressedList.get(i)){
				if(key == KeyEvent.VK_UP){
					if(player.facingRight){
						ballList.add(new Ball((int)(player.getX() + 4 * player.getImage().getWidth() / 5),
								(int)(player.getY() - ball.getHeight()), BALL_SPEED * throwBox.getHealth() / 100, -BALL_SPEED * throwBox.getHealth() / 100, ball, true));
						playerLocation.add(new PlayerPoint(player.getX(), player.getY(), 0, true, 1, throwBox.getHealth()));
					}else{
						ballList.add(new Ball((int)(player.getX() - player.getImage().getWidth() / 5),
								(int)(player.getY() - ball.getHeight()), -BALL_SPEED * throwBox.getHealth() / 100, -BALL_SPEED * throwBox.getHealth() / 100, ball, false));
						playerLocation.add(new PlayerPoint(player.getX(), player.getY(), 0, false, 1, throwBox.getHealth()));

					}

					throwBox.setHealth(0);


				}
				keyPressedList.remove(i);
			}
		}
	}

	//  Unimportant/unused
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
}
