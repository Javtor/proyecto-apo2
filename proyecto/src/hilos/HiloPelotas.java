package hilos;

import java.util.ArrayList;

import gui.Ventana;
import modelo.Juego;
import modelo.Pelota;

public class HiloPelotas extends Thread{
	
	Ventana principal;
	ArrayList<Pelota> pelotas;

	public HiloPelotas(Ventana principal) {
		super("Pelotas");
		this.principal = principal;
		this.pelotas = principal.getPelotas();
	}
	
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
