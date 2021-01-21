package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actor {

	private String nombre; 

	public Ladrillo() {
	}

	public Ladrillo(int x, int y, String nombre) {
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
		g.setColor(Color.RED);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
		
	}

	@Override
	public void actua() {
		// TODO Auto-generated method stub
		
	}

	
	
}
