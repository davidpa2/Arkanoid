package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actor {

	//Antes era String nombre;
	private int numLadrillo; 
	private Color colores;

	public Ladrillo() {
	}

	/**
	 * Constructor con todos los parámetros de Ladrillo
	 * @param x
	 * @param y
	 * @param alto
	 * @param ancho
	 * @param numLadrillo
	 * @param colores
	 */
	public Ladrillo(int x, int y, int alto, int ancho, int numLadrillo, Color colores) {
		super(x, y);
		this.numLadrillo = numLadrillo;
		this.colores = colores;
		this.alto = alto;
		this.ancho = ancho;
	}

	
	//Sobreescritura del método paint para que los ladrillos puedan ser pintados
	@Override
	public void paint(Graphics g) {
		g.setColor(colores);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
		
	}

	//Sobreescritura del método actúa, para que los ladrillos puedan actuar
	@Override
	public void actua() {
		
	}
	
	/**
	 * Este método se disparará cuando un actor colisione con el monstruo
	 */
	@Override
	public void colisionaCon(Actor a) {
		super.colisionaCon(a);
		// Si colisionamos con un player o un disparo, eliminamos al monstruo
		if (a instanceof Pelota) {
			Arkanoid.getInstance().eliminaActor(this);
			ResourcesCache.getInstance().playSonido("explosion.wav");
			Arkanoid.getInstance().incorporaNuevoActor(new Explosion(this.x, this.y));
		}
	}
	
	/**
	 * Getters y setters
	 * @return
	 */
	public int getnumLadrillo() {
		return numLadrillo;
	}

	public void setnumLadrillo(int numLadrillo) {
		this.numLadrillo = numLadrillo;
	}

	public int getNumLadrillo() {
		return numLadrillo;
	}

	public void setNumLadrillo(int numLadrillo) {
		this.numLadrillo = numLadrillo;
	}

	public Color getColores() {
		return colores;
	}

	public void setColores(Color colores) {
		this.colores = colores;
	}
	
}
