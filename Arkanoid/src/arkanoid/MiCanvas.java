package arkanoid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class MiCanvas extends Canvas {
	
	List<Actor> actores = null;
	
	public MiCanvas (List<Actor> actores) {
		this.actores = actores;
	}
	
	/**
	 * Sobreescribir el método paint para tener el control sobre lo que se va a pintar en pantalla.
	 */
	@Override
	public void paint(Graphics g) {
		//Asignar un color al fondo
		this.setBackground(Color.GRAY);
		//Pintar cad uno de los actores
		for (Actor a : this.actores) {
			a.paint(g);
		}
	}

}
