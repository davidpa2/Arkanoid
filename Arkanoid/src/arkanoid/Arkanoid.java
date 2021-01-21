package arkanoid;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

public class Arkanoid {

	private static int FPS = 60;
	
	public static void main(String[] args) {
		
		//Crear la ventana con su formato y un BorderLayout
		JFrame ventana = new JFrame("Space Invaders");
		ventana.setBounds(0, 0, 800, 600);
		ventana.getContentPane().setLayout(new BorderLayout());
		List<Actor> actores = creaActores();
		
		MiCanvas canvas = new MiCanvas(actores);
		
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		ventana.setIgnoreRepaint(true);
		ventana.setVisible(true);
		
		
		// Comienzo un bucle, que consistirá en el juego completo.
		int millisPorCadaFrame = 1000 / FPS;
		
		do {
			// Redibujo la escena tantas veces por segundo como indique la variable FPS
			// Tomo los millis actuales
			long millisAntesDeProcesarEscena = new Date().getTime();
					
			// Redibujo la escena
			canvas.repaint();
			
			for (Actor a : actores) {
				a.actua();
			}
					
			// Calculo los millis que debemos parar el proceso, generando 60 FPS.
			long millisDespuesDeProcesarEscena = new Date().getTime();
			int millisDeProcesamientoDeEscena = (int) (millisDespuesDeProcesarEscena - millisAntesDeProcesarEscena);
			int millisPausa = millisPorCadaFrame - millisDeProcesamientoDeEscena;
			millisPausa = (millisPausa < 0)? 0 : millisPausa;
			// "Duermo" el proceso principal durante los milllis calculados.
			try {
				Thread.sleep(millisPausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	 while (true);		
	}
	
	
	/**
	 * Método utilizado para crear actores
	 * @return
	 */
	private static List<Actor> creaActores () {
		List<Actor> actores = new ArrayList<Actor>();
		
		//Construyo los distintos actores que hay en el juego
		Pelota pelota = new Pelota(300, 300, "Manolo el peloto");
		actores.add(pelota);
		
		Ladrillo ladrillo = new Ladrillo(20, 20, "Bricky");
		actores.add(ladrillo);
		
		Nave nave = new Nave(600, 300, "Apolo XXXIII");
		actores.add(nave);
		
		// Devuelvo la lista con todos los actores del juego
		return actores;
	}

}
