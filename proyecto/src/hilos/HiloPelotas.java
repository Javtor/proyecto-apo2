package hilos;

import java.util.ArrayList;

import gui.Ventana;
import modelo.Juego;
import modelo.Pelota;
/**
 * Clase HiloPelotas
 * Esta clase controla el movimiento de las pelotas.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class HiloPelotas extends Thread{
	/**
	 * Asociación con la ventana principal del juego
	 */
	Ventana principal;
	/**
	 * Asociación con el ArrayList de pelotas que tiene el juego
	 */
	ArrayList<Pelota> pelotas;

	/**
	 * Constructor del HiloPelotas
	 * Inicializa los atributos principal y pelotas
	 * <b>post:</b>Pelotas será un ArrayList generado a partir del árbol de pelotas 
	 * @param principal Ventana principal del juego. principal!=null
	 */
	public HiloPelotas(Ventana principal) {
		super("Pelotas");
		this.principal = principal;
		this.pelotas = principal.getPelotas();
	}
	/**
	 * Lo que realiza el hilo cuando inicia
	 * Mientras el juego se encuentre activo se ejecuta el método de movimiento para cada pelota
	 * en el ArrayList de pelotas cada segundo/FPS del juego
	 */
	@Override
	public void run() {
		while (principal.isJugando()) {
			pelotas = principal.getPelotas();
			for (int i = 0; i < pelotas.size(); i++) {
				pelotas.get(i).mover();
			}
			try {
				Thread.sleep(1000 / Juego.FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
