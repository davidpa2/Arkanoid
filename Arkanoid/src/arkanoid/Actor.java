package arkanoid;

import java.awt.Graphics;

public abstract class Actor {
	
	protected int x, y; 
	protected int ancho = 30, alto = 30;

	public Actor() {
	}

	public Actor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public abstract void paint(Graphics g);
	public abstract void actua();

	
	/**
	 * Getters y setters
	 * @return
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	

}
