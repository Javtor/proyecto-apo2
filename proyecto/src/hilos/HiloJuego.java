package hilos;

import gui.Ventana;
import modelo.Juego;
/**
 * Clase HiloJuego
 * Esta clase controla el ciclo del juego.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class HiloJuego extends Thread{
	/**
	 * Asociación con la ventana principal del juego
	 */
	private Ventana principal;
	/**
	 * Asociación con la clase principal del modelo
	 */
	private Juego juego;
	/**
	 * Constructor del HiloJuego
	 * Inicializa los atributos principal y juego.
	 * @param principal Ventana principal de la interfaz. principal!=null
	 * @param juego Clase principal del modelo. juego!=null
	 */
	public HiloJuego(Ventana principal, Juego juego) {
		super();
		this.principal = principal;
		this.juego = juego;
	}
	/**
	 * Lo que realiza el hilo una vez inicie
	 * Mientras este jugando va a refrescar el tablero cada segundo/Los FPS del juego
	 * Mientras este jugando va a controlar el ciclo del juego.
	 * Si deja de jugar vuelve a la pantalla de inicio
	 * <b>pre:</b>Se ha instanciado una ventana principal<br>
	 * <b>pre:</b>Se ha instanciado un juego<br>
	 * <b>post:</b>Se refresca el tablero mientras se este jugando<br>
	 * <b>post:</b>Se devuelve al menu de inicio si deja de jugar<br>
	 */
	@Override
	public void run() {
		while (principal.isJugando()) {
			try {
				principal.refrescarTablero();
				principal.mostrarDatos();
				juego.cicloJuego();
				Thread.sleep(1000 / Juego.FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		principal.refrescarTablero();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		principal.mostrarInicio();
	}
}
