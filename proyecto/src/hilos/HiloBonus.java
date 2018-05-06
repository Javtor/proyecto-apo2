package hilos;

import gui.Ventana;
import modelo.Juego;
public class HiloBonus extends Thread{

	
	private Ventana principal;
	private Juego juego;
	
	public HiloBonus(Ventana principal, Juego juego) {
		// TODO Auto-generated constructor stub
		this.principal=principal;
		this.juego=juego;
		
		
	}

	
	@Override
	public void run() {
		while (principal.isJugando()) {
			try {
				juego.crearBonus();
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
