package hilos;
import gui.Ventana;
import modelo.Juego;
/**
 * Clase HiloBonus
 * Esta clase controla la creación de los bonus.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class HiloBonus extends Thread{
	/**
	 * Asociación con la ventana principal del juego
	 */
	private Ventana principal;
	/**
	 * Asociación con la clase principal del modelo
	 */
	private Juego juego;
	/**
	 * Constructor del HiloBonus
	 * Inicializa los atributos principal y juego.
	 * @param principal Ventana principal de la interfaz
	 * @param juego Clase principal del modelo
	 */
	public HiloBonus(Ventana principal, Juego juego) {
		this.principal=principal;
		this.juego=juego;	
	}
	/**
	 * Lo que el hilo realiza cuando se inicie
	 * Si la ventana principal esta jugando, en un intervalo de tiempo random entre 0-7 segundos crea
	 * un nuevo bonus.
	 * <b>pre:</b>Se ha instanciado una ventana principal<br>
	 * <b>pre:</b>Se ha instanciado un juego<br>
	 * <b>post:</b>Se ha creado una nueva bonificación cada 0-7 segundos<br>
	 */
	@Override
	public void run() {
		while (principal.isJugando()) {
			try {
				Thread.sleep((int)(Math.random()*6+5)*1000);
				juego.crearBonus();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
