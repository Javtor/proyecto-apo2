package hilos;

import gui.Ventana;
import modelo.Juego;
import modelo.Nave;

public class HiloInvulnerabilidad extends Thread {
	private Ventana principal;
	private Nave nave;

	public HiloInvulnerabilidad(Ventana principal, Nave nave) {
		super();
		this.principal = principal;
		this.nave = nave;
	}

	@Override
	public void run() {
		while (principal.isJugando()) {
			if (nave.esInvulnerable()) {
				try {
					Thread.sleep(1500);
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
