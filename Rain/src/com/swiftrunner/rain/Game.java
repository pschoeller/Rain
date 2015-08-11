package com.swiftrunner.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.swiftrunner.rain.entity.mob.Player;
import com.swiftrunner.rain.graphics.Font;
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
	private Font font;
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
		TileCoordinate playerSpawn = new TileCoordinate(17, 60);
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
		level.add(player);
		font = new Font();
		
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
		double xScroll = (player.getX() - screen.getWidth()/2) + (spriteWidth);
		double yScroll = (player.getY() - screen.getHeight()/2) + (spriteWidth);
		level.render((int)xScroll, (int)yScroll, screen);
		font.render(50, 50, -3, "Hey\nbro!", screen);
		
		for(int i=0; i<pixels.length; i++){
			pixels[i] = screen.getPixels()[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
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
