package com.swiftrunner.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.swiftrunner.rain.entity.mob.Player;
import com.swiftrunner.rain.event.Event;
import com.swiftrunner.rain.event.EventListener;
import com.swiftrunner.rain.graphics.Font;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.UI.UIManager;
import com.swiftrunner.rain.graphics.layers.Layer;
import com.swiftrunner.rain.input.Keyboard;
import com.swiftrunner.rain.input.Mouse;
import com.swiftrunner.rain.level.Level;
import com.swiftrunner.rain.level.SpawnLevel;
import com.swiftrunner.rain.level.TileCoordinate;
import com.swiftrunner.rain.net.player.NetPlayer;
import com.swiftrunner.raincloud.serialization.RCDatabase;
import com.swiftrunner.raincloud.serialization.RCField;
import com.swiftrunner.raincloud.serialization.RCObject;


public class Game extends Canvas implements Runnable, EventListener {

	private static final long serialVersionUID = 1L;
	private static int width = 300-80;
	private static int height = 168;
	private static int scale = 3;
	private static int swidth = width * scale;
	private static int sheight = height * scale;
	private static String title = "Rain";
	private static  UIManager uiManager;
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private Font font;
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image;
	private int[] pixels;
	
	private List<Layer> layerStack = new ArrayList<Layer>();
	
	
	public Game(){
		setSize();
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		uiManager = new UIManager();
		level = new SpawnLevel("/levels/spawn_level_map.png");
		addLayer(level);
		TileCoordinate playerSpawn = new TileCoordinate(17, 60);
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), key, "Alden'Kai");
		level.add(player);
		level.addPlayer(new NetPlayer());
		font = new Font();
		
		addKeyListener(key);
		
		Mouse mouse = new Mouse(this);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		save();
	}
	
	
	public static int getWindowWidth() { return swidth; }
	public static int getWindowHeight() { return sheight; }
	public static UIManager getUIManager() { return uiManager; }
	public void addLayer(Layer layer){ layerStack.add(layer); }
	
	
	private void setSize(){
		RCDatabase db = RCDatabase.DeserializeFromFile("res/data/screen.bin");
		
		if(db != null){
			RCObject obj = db.findObject("Resolution");
			width = obj.findField("width").getInt();
			height = obj.findField("height").getInt();
			scale = obj.findField("scale").getInt();
		}

		Dimension size = new Dimension(width * scale + 80*3, height * scale);
		setPreferredSize(size);
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	}
	
	
	private void save(){
		RCDatabase db = new RCDatabase("Screen");
		
		RCObject obj = new RCObject("Resolution");
		obj.addField(RCField.Integer("width", width));
		obj.addField(RCField.Integer("height", height));
		obj.addField(RCField.Integer("scale", scale));
		db.addObject(obj);
		
		db.serializeToFile("res/data/screen.bin");
	}
	
	
	private void load(){
		
	}
	
	
	public void onEvent(Event event) {
		for(int i=layerStack.size()-1; i>=0; i--){
			layerStack.get(i).onEvent(event);
		}
	}
	
	
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
		
		level.setScroll((int)xScroll, (int)yScroll);
		
		// Render level layers
		for(int i=0; i<layerStack.size(); i++){
			layerStack.get(i).render(screen);
		}

		for(int i=0; i<pixels.length; i++){
			pixels[i] = screen.getPixels()[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, (swidth), (sheight), null);
		uiManager.render(g);
		
		g.dispose();
		bs.show();
	}
	
	
	public void update(){
		key.update();
		uiManager.update();
		
		// Render level layers
		for(int i=0; i<layerStack.size(); i++){
			layerStack.get(i).update();
		}
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
