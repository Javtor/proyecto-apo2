package hilos;

import gui.Ventana;
import modelo.Juego;
import modelo.Nave;

public class HiloNave extends Thread {
	private Ventana principal;
	private Nave nave;

	public HiloNave(Ventana principal, Nave nave) {
		super();
		this.principal = principal;
		this.nave = nave;
	}

	@Override
	public void run() {
		while (principal.isJugando()) {
			nave.mover();
			try {
				Thread.sleep(1000 / Juego.FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
