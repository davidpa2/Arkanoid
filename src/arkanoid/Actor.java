package arkanoid;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Actor {
	
	protected int x, y; 
	protected int ancho = 30, alto = 30;
	protected int velocidadX = 0;
	protected BufferedImage spriteActual;
	protected boolean marcadoParaEliminacion = false;
	// Posibilidad de que el actor sea animado, a trav�s del siguiente array de sprites y las variables
	// velocidadDeCambioDeSprite y unidadDeTiempo
	protected List<BufferedImage> spritesDeAnimacion = new ArrayList<BufferedImage>();
	protected int velocidadDeCambioDeSprite = 0;
	private int unidadDeTiempo = 0;

	public Actor() {
	}

	//Constructor de Actor
	public Actor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	//Métodos abstractos de Actor que convierten la clase en abstracta:
	/*
	 * Método que permite que los subtipos de actor se pinten
	 */
	public void paint(Graphics g) {
		g.drawImage(this.spriteActual, this.x, this.y, null);
	}
	/*
	 * Método que permite que los subtipos de actor realicen sus acciones
	 */
	public void actua() {
		// En el caso de que exista un array de sprites el actor actual se tratar� de una animaci�n, para eso llevaremos a cabo los siguientes pasos
		if (this.spritesDeAnimacion != null && this.spritesDeAnimacion.size() > 0) {
			unidadDeTiempo++;
			if (unidadDeTiempo % velocidadDeCambioDeSprite == 0){
				unidadDeTiempo = 0;
				int indiceSpriteActual = spritesDeAnimacion.indexOf(this.spriteActual);
				int indiceSiguienteSprite = (indiceSpriteActual + 1) % spritesDeAnimacion.size();
				this.spriteActual = spritesDeAnimacion.get(indiceSiguienteSprite);
			}
		}
	}
	
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

	public BufferedImage getSpriteActual() {
		return spriteActual;
	}

	public void setSpriteActual(BufferedImage spriteActual) {
		this.spriteActual = spriteActual;
		this.ancho = this.spriteActual.getWidth();
		this.alto = this.spriteActual.getHeight();
	}

	public List<BufferedImage> getSpritesDeAnimacion() {
		return spritesDeAnimacion;
	}

	public void setSpritesDeAnimacion(List<BufferedImage> spritesDeAnimacion) {
		this.spritesDeAnimacion = spritesDeAnimacion;
	}

}
