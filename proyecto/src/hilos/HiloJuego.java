package hilos;

import gui.Ventana;
import modelo.Juego;

public class HiloJuego extends Thread{
	private Ventana principal;
	private Juego juego;

	public HiloJuego(Ventana principal, Juego juego) {
		super();
		this.principal = principal;
		this.juego = juego;
	}

	@Override
	public void run() {
		while (principal.isJugando()) {
			try {
				principal.refrescarTablero();
				principal.mostrarDatos();
				juego.cicloJuego();
				Thread.sleep(1000 / Juego.FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		principal.refrescarTablero();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		principal.mostrarInicio();
	}
}
