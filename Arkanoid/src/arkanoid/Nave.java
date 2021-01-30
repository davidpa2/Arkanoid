package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Nave extends Actor {
	
	private String nombre;
	private boolean up, down, left, right;
	protected static final int PLAYER_SPEED = 6;

	public Nave() {

	}

	//Constructor con todos los parámetros de Nave
	public Nave(int x, int y, int ancho, int alto, String nombre) {
		super(x, y, ancho, alto);
		this.nombre = nombre;
		this.velocidadX = 0;
	}
	
	//Sobreescritura del método pain para permitir que la nave se pinte
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
		
	}
	
	@Override
	/**
	 * Método actúa con el que haremos que la Nave no salga de los límites del Canvas
	 */
	public void actua() {
		// Por comodidad obtengo un puntero al canvas
		MiCanvas canvas = Arkanoid.getInstance().getCanvas();
				
		// Movimiento en el eje horizontal
		this.x += this.velocidadX; // En cada iteración del bucle principal, el monstruo cambiará su posición en el eje X, sumándole a esta el valor de vx
		// si la nave intenta salir por la derecha no se lo permitimos
		if (this.x <  0) {
			this.x = 0;
		}
		// si la nave intenta salir por la izquierda no se lo permitimos
		if (this.x >  (canvas.getWidth() - this.ancho)) {
			this.x = (canvas.getWidth() - this.ancho);
		}
				
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
			this.velocidadX = -PLAYER_SPEED;
		}
			
		if (right) {
			this.velocidadX = PLAYER_SPEED;
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

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

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
		return PLAYER_SPEED;
	}

	
}
