package hilos;
import gui.Ventana;
import modelo.Juego;
import modelo.Nave;
/**
 * Clase HiloNave
 * Esta clase controla el movimiento de la nave.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class HiloNave extends Thread {
	/**
	 * Asociación con la ventana principal del juego
	 */
	private Ventana principal;
	/**
	 * Asociación con la nave del Juego
	 */
	private Nave nave;
	/**
	 * Constructor del HiloNave
	 * Inicializa los atributos principal y nave.
	 * @param principal Ventana principal de la interfaz. principal!=null
	 * @param nave Nave que se está usando en el juego. nave!=null
	 */
	public HiloNave(Ventana principal, Nave nave) {
		super();
		this.principal = principal;
		this.nave = nave;
	}
	/**
	 * Lo que realiza el hilo cuando se inicia
	 * Mientras se el juego se encuentre activo se ejecuta el método mover de la nave
	 * cada segundo/FPS del juego
	 */
	@Override
	public void run() {
		while (principal.isJugando()) {
			nave.mover();
			try {
				Thread.sleep(1000 / Juego.FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
