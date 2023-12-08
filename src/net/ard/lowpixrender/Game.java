package net.ard.lowpixrender;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	
	int width;
	int height;
	int scale;
	
	int bWidth;
	int bHeight;
	
	String name;
	
	JFrame frame;
	
	BufferedImage buffer;

	Game(int width, int height, String name, int scale) {
		this.width = width - (width % scale);
		this.height = height - (height % scale);
		this.scale = scale;
		this.name = name;
		bWidth = this.width / scale;
		bHeight = this.height / scale;
	}
	
	public void start() {
		init();
		mainloop();
	}
	
	private void init() {
		Point.setAspect(bWidth, bHeight);
		buffer = new BufferedImage(width/scale, height/scale, BufferedImage.TYPE_INT_RGB);
		frame = new JFrame(name);
		JPanel panel = new JPanel() {
			public void paint(Graphics g) {
				Graphics2D b = (Graphics2D) g;
				b.scale(scale, scale);
				b.drawImage(buffer, 0, 0, null);
			}
		};
		panel.setPreferredSize(new Dimension(width, height));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ComponentListener resize = new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				width = frame.getContentPane().getWidth();
				width -= width % scale;
				height = frame.getContentPane().getHeight();
				height -= height % scale;
				panel.setPreferredSize(new Dimension(width, height));
				bWidth = width / scale;
				bHeight = height / scale;
				buffer = new BufferedImage(bWidth, bHeight, BufferedImage.TYPE_INT_RGB);
				Point.setAspect(bWidth, bHeight);
				frame.pack();
			}
			public void componentMoved(ComponentEvent e) {}
			public void componentShown(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		};
		frame.addComponentListener(resize);
		frame.setVisible(true);
	}
	
	private void mainloop() {
		while (true) {
			draw();
			frame.repaint();
		}
	}
	
	private void draw() {
		Graphics g = buffer.getGraphics();
		
		Cube m = new Cube(0, 0, 2.5f);
		
		g.setColor(Color.YELLOW);
		
		m.draw(g);
	}
	
	public static void main(String[] args) {
		new Game(800, 600, "d", 4).start();
	}
}
