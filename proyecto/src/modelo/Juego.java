package modelo;

import java.awt.event.KeyEvent;

import hilos.HiloJuego;

public class Juego {
	
	public static final int ANCHO = 750;
	public static final int ALTO = 550;
	public static final int FPS = 45;	
	private int puntaje;
	private int nivel;
	private Nave nave;
	private boolean jugando;
	private int cantidadpelotas;
	

	public Juego() {
		nave = new Nave();
		jugando = true;
		cantidadpelotas=4;
	}
	public void crearPelotas() {
	}
	public void eliminarpelota() {
		// TODO - implement Juego.eliminarpelota
		throw new UnsupportedOperationException();
	}

	public void aumentarpuntaje() {
		// TODO - implement Juego.aumentarpuntaje
		throw new UnsupportedOperationException();
	}

	public void subirnivel() {
		// TODO - implement Juego.subirnivel
		throw new UnsupportedOperationException();
	}

	public void guardarpartida() {
		// TODO - implement Juego.guardarpartida
		throw new UnsupportedOperationException();
	}

	public void cargarpartida() {
		// TODO - implement Juego.cargarpartida
		throw new UnsupportedOperationException();
	}

	public void guardardatos() {
		// TODO - implement Juego.guardardatos
		throw new UnsupportedOperationException();
	}

	public void cargardatos() {
		// TODO - implement Juego.cargardatos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 */
	public Jugador buscarjugador(String nombre) {
		// TODO - implement Juego.buscarjugador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param puntaje
	 */
	public Jugador buscarpuntaje(int puntaje) {
		// TODO - implement Juego.buscarpuntaje
		throw new UnsupportedOperationException();
	}

	public Nave getNave() {
		return nave;
	}
	
	public void keyPressed(KeyEvent e) {
		nave.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
    	nave.keyReleased(e);
    }

	public boolean isJugando() {
		return jugando;
	}

	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}
	
}