package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Nave extends Actor {
	
	private String nombre;

	public Nave() {

	}

	public Nave(int x, int y, String nombre) {
		super(x, y);
		this.nombre = nombre;
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

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
		
	}

	@Override
	public void actua() {
		
	}

	
}
