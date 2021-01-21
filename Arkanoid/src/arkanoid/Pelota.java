package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Actor {
	
	private String nombre; 
	private int velocidadX = -5;
	private int velocidadY = -5;

	public Pelota() {
		
	}

	public Pelota(int x, int y, String nombre) {
		super(x, y);
		this.nombre = nombre;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(this.x, this.y, this.ancho, this.alto);
	}
	
	@Override
	/**
	 * 
	 */
	public void actua() {
		// El monstruo se mueve de manera horizontal, en cada FPS
		this.x += this.velocidadX;
		// Si el monstruo abandona la escena por la izquierda o la derecha, rebota
		if (this.x < 0 || this.x > 800) {
			this.velocidadX = -this.velocidadX;
		}
		
		// Copiamos el esquema anterior para el movimiento vertical
		this.y += this.velocidadY;
		// Si el monstruo abandona la escena por la izquierda o la derecha, rebota
		if (this.y < 0 || this.y > 600) {
			this.velocidadY = -this.velocidadY;
		}
		
	}

	
	/**
	 * Getters y setters
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

}
