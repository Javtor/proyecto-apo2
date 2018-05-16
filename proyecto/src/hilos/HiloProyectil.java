package hilos;

import gui.Ventana;
import modelo.Proyectil;
import modelo.Juego;
import modelo.Nave;
/**
 * Clase HiloProyectil
 * Esta clase controla el movimiento del proyectil para que vaya con la nave.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class HiloProyectil extends Thread {
	/**
	 * Asociación con la ventana principal del juego
	 */
	private Ventana principal;
	/**
	 * Asociación con el proyectil del juego
	 */
	private Proyectil pro;
	/**
	 * Constructor del HiloProyectil 
	 * Inicializa los atributos principal y pro
	 * @param principal Ventana principal del juego. principal!=null
	 * @param pro Proyectil del juego asociado con la nave. pro!=null
	 */
	public HiloProyectil(Ventana principal, Proyectil pro) {
		super();
		this.principal = principal;
		this.pro = pro;
	}
	/**
	 * Lo que realiza el hilo cuando se inicia
	 * Mientras el juego esté activo ejecuta el método mover el proyectil para que vaya en dirección de la nave
	 * sin importar si es visible o no
	 * Actualiza el proyectil cada segundo/ FPS del juego
	 * <b>post:</b>Se ha movido el proyectil<br>
	 * <b>post:</b>Se ha actualizado el atributo pro, por el poryectil actual de la nave<br>
	 */
	@Override
	public void run() {
		while (principal.isJugando()) {
			pro.mover();
			pro = principal.getNave().getProyectil();
			try {
				Thread.sleep(1000 / Juego.FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
