package net.ard.lowpixrender;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
	
	Vec3 pos;
	Vec3 rot;
	
	float speed = 0.5f;
	float sensitivity = 0.1f;
	
	Player() {
		pos = new Vec3();
		rot = new Vec3();
	}
	
	public void move() {
		Vec3 move = new Vec3();
		
		if (W)
			move.translate(0, 0, 1);
		if (A)
			move.translate(-1, 0, 0);
		if (S)
			move.translate(0, 0, -1);
		if (D)
			move.translate(1, 0, 0);
		if (SPACE)
			move.translate(0, 1, 0);
		if (CTRL)
			move.translate(0, -1, 0);
		
		move.normalize(speed).rotate(rot.scaled(-1.0f));
		pos.translate(move);
		
		Vec3 look = new Vec3();
		
		if (LEFT)
			look.y -= sensitivity;
		if (RIGHT)
			look.y += sensitivity;
		if (UP)
			look.x -= sensitivity;
		if (DOWN)
			look.x += sensitivity;
		if (Q)
			look.z += sensitivity;
		if (E)
			look.z -= sensitivity;
		
		rot.translate(look);
	}
	
	private boolean W;
	private boolean A;
	private boolean S;
	private boolean D;
	private boolean SPACE;
	private boolean CTRL;
	private boolean LEFT;
	private boolean RIGHT;
	private boolean UP;
	private boolean DOWN;
	private boolean Q;
	private boolean E;

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			W = true;
		if (e.getKeyCode() == KeyEvent.VK_A)
			A = true;
		if (e.getKeyCode() == KeyEvent.VK_S)
			S = true;
		if (e.getKeyCode() == KeyEvent.VK_D)
			D = true;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			SPACE = true;
		if (e.getKeyCode() == KeyEvent.VK_CONTROL)
			CTRL = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			LEFT = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			RIGHT = true;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			UP = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			DOWN = true;
		if (e.getKeyCode() == KeyEvent.VK_Q)
			Q = true;
		if (e.getKeyCode() == KeyEvent.VK_E)
			E = true;
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			W = false;
		if (e.getKeyCode() == KeyEvent.VK_A)
			A = false;
		if (e.getKeyCode() == KeyEvent.VK_S)
			S = false;
		if (e.getKeyCode() == KeyEvent.VK_D)
			D = false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			SPACE = false;
		if (e.getKeyCode() == KeyEvent.VK_CONTROL)
			CTRL = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			LEFT = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			RIGHT = false;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			UP = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			DOWN = false;
		if (e.getKeyCode() == KeyEvent.VK_Q)
			Q = false;
		if (e.getKeyCode() == KeyEvent.VK_E)
			E = false;
	}

}
