package hilos;

import gui.Ventana;
import modelo.Proyectil;
import modelo.Juego;
import modelo.Nave;

public class HiloProyectil extends Thread {
	private Ventana principal;
	private Proyectil pro;

	public HiloProyectil(Ventana principal, Proyectil pro) {
		super();
		this.principal = principal;
		this.pro = pro;
	}

	@Override
	public void run() {
		while (principal.isJugando()) {
			pro.mover();
			pro = principal.getNave().getProyectil();
			try {
				Thread.sleep(1000 / Juego.FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
