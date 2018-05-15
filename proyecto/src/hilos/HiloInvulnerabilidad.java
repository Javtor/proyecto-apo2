package hilos;

import gui.Ventana;
import modelo.Juego;
import modelo.Nave;

/**
 * Este hilo se encarga de darle un tiempo a la duración de la invulnerabilidad
 * de la nave.
 * 
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class HiloInvulnerabilidad extends Thread {
	
	/**
	 * Constante que representa el tiempo (en milisegundos) que dura la invulnerabilidad de la nave
	 */
	public static final int TIEMPO_INVULNERABILIDAD = 1500;
	/**
	 * La ventana principal del juego
	 */
	private Ventana principal;
	/**
	 * La nave a la que se le aplica la invulnerabilidad
	 */
	private Nave nave;

	/**
	 * Constructor del hilo de la duracion de la invulnerabilidad de la nave
	 * @param principal La ventana principal del juego. principal != null
	 * @param nave La nave a la que se le aplica la invulnerabilidad. nave != null
	 */
	public HiloInvulnerabilidad(Ventana principal, Nave nave) {
		super();
		this.principal = principal;
		this.nave = nave;
	}

	/**
	 * Mientras se esté en juego, verifica si la nave es invulnerable (se llega a este estado tras
	 * ser impactado por una pelota). Si lo es, espera un tiempo fijo, tras el cual hace que la nave
	 * ya no sea invulnerable<br>
	 * 
	 * <b>pos:</b> Si la nave era invulnerable, deja de serlo después de un tiempo fijo.
	 */
	@Override
	public void run() {
		while (principal.isJugando()) {
			if (nave.esInvulnerable()) {
				try {
					Thread.sleep(TIEMPO_INVULNERABILIDAD);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nave.setInvulnerable(false);
				nave.setImagen(Nave.UBICACION);
			}
			try {
				Thread.sleep(1000 / Juego.FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
