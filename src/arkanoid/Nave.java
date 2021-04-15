package arkanoid;

import java.awt.event.KeyEvent;

public class Nave extends Actor {
	
	private boolean left, right;
	protected static int VELOCIDAD = 6;

	public Nave() {

	}

	//Constructor con todos los parámetros de Nave
	public Nave(int x, int y) {
		super(x, y);
		this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_NAVE));

	}
	
	@Override
	public void actua() {
		super.actua();
		// Compruebo las variables booleanas que determinan el movimiento
		if (left) this.x -= VELOCIDAD;
		if (right) this.x += VELOCIDAD;
		
		// Compruebo si el player sale del canvas por cualquiera de los cuatro márgenes
		mover(this.x);
	}
	
	/**
	 * Método mover con el que podrá moverse en el eje X
	 * @param x
	 */
	public void mover(int x) {
		this.x = x;
		// Controlo los casos en los que el jugador pueda salir del Canvas
		MiCanvas canvas = Arkanoid.getInstance().getCanvas(); // Referencia al objeto Canvas usado

		// Compruebo si el ratón sale por la derecha
		if (this.x > (canvas.getWidth() - this.ancho)) {
			this.x = canvas.getWidth() - this.ancho;
		}
		// Compruebo si el jugador sale por la izquierda
				if (this.x < 0) {
					this.x = 0;
				}			
	}
	
	/**
	 * Método con el que se detecta si las teclas derecha e izquierda han sido presionadas
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		}
		//Llamar al método updateSpeed para cambiar la velocidad y movimiento de la nave
		updateSpeed();
	}

	/**
	 * Método con el que se detecta si las teclas derecha e izquierda han sido soltadas
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		}
		//Llamar al método updateSpeed para cambiar la velocidad y movimiento de la nave
		updateSpeed();
	}
	
	/**
	 * Método con el que hacer que se mueva la nave a izquierda o derecha
	 */
	protected void updateSpeed() {
		this.velocidadX = 0;
		if (left) {
			this.velocidadX = VELOCIDAD;
		}
			
		if (right) {
			this.velocidadX = VELOCIDAD;
		}
			
	}
	
	/**
	 * Getters y setters
	 * @return
	 */
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public static int getPlayerSpeed() {
		return VELOCIDAD;
	}

	
}
