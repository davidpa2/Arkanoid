package arkanoid;

import java.awt.Graphics;

public abstract class Actor {
	
	protected int x, y; 
	protected int ancho, alto;
	protected int velocidadX = 0;

	public Actor() {
	}

	//Constructor de Actor
	public Actor(int x, int y, int ancho, int alto) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	//Métodos abstractos de Actor que convierten la clase en abstracta:
	/*
	 * Método que permite que los subtipos de actor se pinten
	 */
	public abstract void paint(Graphics g);
	/*
	 * Método que permite que los subtipos de actor realicen sus acciones
	 */
	public abstract void actua();
	
	/**
	 * Método que se podrá sobrescribir en los subtipos para decidir la acción a realizar al colisionar
	 * con otro actor
	 * @param a
	 */
	public void colisionaCon(Actor a) {
	}

	
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

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}
	

}
