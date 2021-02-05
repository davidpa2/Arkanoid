package arkanoid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Arkanoid {

	//Declaración de variables
	private static int FPS = 60;
	public static final int ANCHO = 795;
	public static final int ALTO = 600;
	private JFrame ventana = null;
	private List<Actor> actores = new ArrayList<Actor>();
	private MiCanvas canvas = null;
	Nave nave = null;
	private List<Actor> actoresParaIncorporar = new ArrayList<Actor>();
	private List<Actor> actoresParaEliminar = new ArrayList<Actor>();
	
	// Para utilizar un patrón singleton necesitamos la siguiente propiedad estática
	private static Arkanoid instance = null;
	
	/**
	 * Este método representa la principal funcionalidad de un patrón Singleton.
	 * Devuelve la única instancia que puede existir de esta clase. Si no se ha 
	 * inicializado, en la primera llamada a este método se realiza dicha 
	 * inicialización.
	 */
	public static Arkanoid getInstance () {
		if (instance == null) { // Si no está inicializada, se inicializa
			instance = new Arkanoid();
		}
		return instance;
	}

	public static void main(String[] args) {
		ResourcesCache.getInstance().cargarRecursosEnMemoria();
		Arkanoid.getInstance().juego();
	}
	
	public Arkanoid() {
		
		//Crear la ventana con su formato y un BorderLayout
		ventana = new JFrame("Arkanoid");
		ventana.setBounds(0, 0, ANCHO, ALTO);
		ventana.getContentPane().setLayout(new BorderLayout());
		//Crear una lista con los actores que aparecerán
		actores = creaActores();
		//Crear un canvas pasándole los actores
		canvas = new MiCanvas(actores);
		//Adaptador de Mouse Motion Listener que permitirá mover la nave con el ratón
		canvas.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				nave.mover(e.getX());
			}
		});
		
		//Adaptador de Key Listener que permitirá mover la nave con las teclas
		canvas.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				nave.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				nave.keyReleased(e);
			}
		});
		
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		//Conseguir que la ventana no se redibuje por los eventos de Windows
		ventana.setIgnoreRepaint(true);
		//Hacer la ventana visible
		ventana.setVisible(true);
		
//		//Hacer que el foco de la aplicación se centre en el Canvas
//		canvas.requestFocus();
		
		// Control del evento de cierre de ventana
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarAplicacion();
			}
		});	
	}
		
	/**
	 * Método del juego principal
	 */
	public void juego() {
		// Comienzo un bucle, que consistirá en el juego completo.
		int millisPorCadaFrame = 1000 / FPS;
			
		do {
			// No sé cuando se va a mostar la ventana y hasta entonces no puedo utilizar la instrucción canvas.requestFocus();
			// Por tanto, en este bucle compruebo constantemente si el canvas tiene el foco y, si no lo tiene, se lo doy
			if (ventana.getFocusOwner() != null && !ventana.getFocusOwner().equals(canvas)) {
				canvas.requestFocus();
			}
				
			// Redibujo la escena tantas veces por segundo como indique la variable FPS
			// Tomo los millis actuales
			long millisAntesDeProcesarEscena = new Date().getTime();
				
			//Redibujar la escena 60 veces por segundo ya que está en el bucle
			canvas.pintaEscena();
				
			for (Actor a : actores) {
				a.actua();
			}
				
			// Tras hacer que cada actor actúe y antes de agregar y eliminar actores, detecto colisiones
			detectaColisiones();
				
			// Acualizo los actores, incorporando los nuevos y eliminando los que ya no se quieren
			actualizaActores();
						
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
	private List<Actor> creaActores () {
		List<Actor> actores = new ArrayList<Actor>();
		
		//Construyo los distintos actores que hay en el juego
		Pelota pelota = new Pelota(395, 525, 20, 20);
		actores.add(pelota);
			
		//Construir una nave en unas coordenadas y con un tamaño
		nave = new Nave(400, 540);
		actores.add(nave);
		
		//Array de colores, con el cual cada fila será del color de cada elemento del array
		Color colores[] = new Color[] {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
		
		//for con el que crear todos los ladrillos:
		//Inicializar yLadrillo a 5 para que se empiecen a crear con un margen en la vertical
		int yLadrillo = 5;
		for(int i = 0; i < 6; i++) {
			//Inicializar xLadrillo a 5 para se empiecen a crear con un margen en la horizontal
			int xLadrillo = 5;
			for(int j = 0; j < 14; j++){	
				//Por cada fila que tiene 14 ladrillos le pasamos la posición de i del array colores[]
				Ladrillo ladrillo = new Ladrillo(xLadrillo, yLadrillo, 35, 50, i, colores[i]);
				//Por cada ladrillo sumarle a xLadrillo 55 ya que el ladrillo mide de ancho 50, quedarán distanciados 5px
				xLadrillo += 55;
				//Añadir el ladrillo creado a la lista
				actores.add(ladrillo);
			}
			/*Al salir del bucle que genera las filas, se le suma a yLadrillo 40 ya que cada 
			 * ladrillo mide de alto 35, se bajará la altua y quedarán con un margen de 5px entre todos
			 */
			yLadrillo += 40;
		}
			
		// Devuelvo la lista con todos los actores del juego
		return actores;
	}
	
	/**
	 * Método encargado de gestionar cuando la ventana se cierre
	 */
	private void cerrarAplicacion() {
		String [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(ventana,"¿Desea cerrar la aplicación?","Salir de la aplicación",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	/**
	 * Método llamado para incorporar nuevos actores
	 * @param a
	 */
	public void incorporaNuevoActor (Actor a) {
		this.actoresParaIncorporar.add(a);
	}
	
	public void eliminaActor(Actor a) {
		this.actoresParaEliminar.add(a);
		
	}

	/**
	 * Incorpora los actores nuevos al juego y elimina los que corresponden
	 */
	private void actualizaActores () {
		// Incorporo los nuevos actores
		for (Actor a : this.actoresParaIncorporar) {
			this.actores.add(a);
		}
		this.actoresParaIncorporar.clear(); // Limpio la lista de actores a incorporar, ya están incorporados
		
		// Elimino los actores que se deben eliminar
		for (Actor a : this.actoresParaEliminar) {
			this.actores.remove(a);
		}
		this.actoresParaEliminar.clear(); // Limpio la lista de actores a eliminar, ya los he eliminado
	}
	
	/**
	 * Detecta colisiones entre actores e informa a los dos
	 */
	private void detectaColisiones() {
		// Una vez que cada actor ha actuado, intento detectar colisiones entre los actores y notificarlas. Para detectar
		// estas colisiones, no nos queda más remedio que intentar detectar la colisión de cualquier actor con cualquier otro
		// sólo con la excepción de no comparar un actor consigo mismo.
		// La detección de colisiones se va a baser en formar un rectángulo con las medidas que ocupa cada actor en pantalla,
		// De esa manera, las colisiones se traducirán en intersecciones entre rectángulos.
		for (Actor actor1 : this.actores) {
			// Creo un rectángulo para este actor.
			Rectangle rect1 = new Rectangle(actor1.getX(), actor1.getY(), actor1.getAncho(), actor1.getAlto());
			// Compruebo un actor con cualquier otro actor
			for (Actor actor2 : this.actores) {
				// Evito comparar un actor consigo mismo, ya que eso siempre provocaría una colisión y no tiene sentido
				if (!actor1.equals(actor2)) {
					// Formo el rectángulo del actor 2
					Rectangle rect2 = new Rectangle(actor2.getX(), actor2.getY(), actor2.getAncho(), actor2.getAlto());
					// Si los dos rectángulos tienen alguna intersección, notifico una colisión en los dos actores
					if (rect1.intersects(rect2)) {
						actor1.colisionaCon(actor2); // El actor 1 colisiona con el actor 2
						actor2.colisionaCon(actor1); // El actor 2 colisiona con el actor 1
					}
				}
			}
		}
	}

	
	//Getter y setters
	public MiCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(MiCanvas canvas) {
		this.canvas = canvas;
	}


}
