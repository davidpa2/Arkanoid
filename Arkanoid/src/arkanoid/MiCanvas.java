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
	
	public void paint(Graphics g) {
		this.setBackground(Color.GRAY);
		
		for (Actor a : this.actores) {
			a.paint(g);
		}
	}

}
