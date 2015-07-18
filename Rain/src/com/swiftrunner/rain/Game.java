package com.swiftrunner.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.swiftrunner.rain.entity.mob.Dummy;
import com.swiftrunner.rain.entity.mob.Player;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.input.Keyboard;
import com.swiftrunner.rain.input.Mouse;
import com.swiftrunner.rain.level.Level;
import com.swiftrunner.rain.level.SpawnLevel;
import com.swiftrunner.rain.level.TileCoordinate;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int width = 300;
	private static int height = width/16*9;
	private static int scale = 3;
	private static int swidth = width * scale;
	private static int sheight = height * scale;
	private static String title = "Rain";
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	
	public Game(){
		Dimension size = new Dimension(swidth, sheight);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = new SpawnLevel("/levels/spawn_level_map.png");
		TileCoordinate playerSpawn = new TileCoordinate(19, 62);
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
		level.add(player);
		
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	
	public static int getWindowWidth() { return swidth; }
	public static int getWindowHeight() { return sheight; }
	
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		
		int spriteWidth = player.getSprite().getSIZE()/2;
		int spriteHeight = player.getSprite().getSIZE()/2;
		int xScroll = (player.getX() - screen.getWidth()/2) + (spriteWidth);
		int yScroll = (player.getY() - screen.getHeight()/2) + (spriteWidth);
		level.render(xScroll, yScroll, screen);
		//screen.renderSheet(40, 40, SpriteSheet.player_down, false, 0);
		
		for(int i=0; i<pixels.length; i++){
			pixels[i] = screen.getPixels()[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		//g.fillRect(Mouse.getX()-32, Mouse.getY()-32, 64, 64);
		//if(Mouse.getB() != -1) g.drawString("Button: " + Mouse.getB(), 80, 80);
		
		g.dispose();
		bs.show();
	}
	
	
	public void update(){
		key.update();
		level.update();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.00/60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		requestFocus();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	
	public static void main(String[] args){
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}
