package modelo;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import hilos.HiloJuego;

public class Juego {
	
	public static final String DIREC_DATOS = "data/ultimapartida.txt";
	public static final String NOM_DATOS = "data/datospartida.txt";
	
	public static final int ANCHO = 750;
	public static final int ALTO = 550;
	public static final int FPS = 45;

	private int puntaje;
	private int nivel;
	private Nave nave;
	private boolean jugando;
	
	private Jugador jugador;
	private Bonificacion primerbonus;
	
	//se supone que iba a ser un arbol 
	private ArrayList<Jugador> listjugadores;

	public Juego() {
		nave = new Nave();
		jugando = true;
		
	}
	
	public void iniciarjuego(String nombre) {
		jugador = new Jugador (nombre);
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

	public void guardardatos() throws FileNotFoundException {
		// TODO - implement Juego.guardardatos
		
		//Falta manejo de archivos de texto
		jugador.setNivel(nivel);
		jugador.setPuntaje(puntaje);
		
		File archivo = new File (NOM_DATOS);
		PrintWriter pw = new PrintWriter (archivo);
		
		if (archivo.exists())
			archivo.delete();
		
		pw.write(jugador.getNickname());
		pw.write("Puntaje:"+jugador.getPuntaje());
		pw.write("Nivel:"+jugador.getNivel());
		pw.close();
		
	}

	public void cargardatos() throws IOException {
		File archivo = new File (NOM_DATOS);
		boolean existe = archivo.exists()&&archivo.isFile();
		if (existe) {
			BufferedReader read = new BufferedReader(new FileReader (archivo));
			String usuario = read.readLine();
			String[] level = read.readLine().split(":");
			String[] puntuacion = read.readLine().split(":");
			jugador.setNickname(usuario);
			puntaje = Integer.parseInt(level[1]);
			nivel = Integer.parseInt(puntuacion[1]);
			read.close();
		}else {
			throw new FileNotFoundException ("No se ha encontrado el archivo");
		}
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
	
	public Bonificacion getBonus() {
		return primerbonus;
	}
	
	public void crearBonus() {
		
		borrarbonusinvisibles();
		
		int cx, cy, tipo;
		
		tipo = (int) ((Math.random() *3)+1);
		cx = (int) ((Math.random() * ANCHO)+10);
		cy = (int) ((Math.random() * ALTO)+10);
		
		Bonificacion anadida = new Bonificacion (cx, cy, null, tipo);
		
		if (primerbonus!=null) {
			Bonificacion actual = localizarultimo();
			actual.setSiguiente(anadida);
			anadida.setAnterior(actual);
		}else {
			primerbonus=anadida;
		}
	}
	
	public Bonificacion localizarultimo() {
		Bonificacion actual = primerbonus;
		if (actual!=null) {
		while (actual.getSiguiente()!=null) {
			actual = actual.getSiguiente();
		}
		}
		return actual;
	}
	
	public void borrarbonusinvisibles() {
		Bonificacion actual = primerbonus;
		
		while (actual!=null) {
			if (!actual.esVisible()) {
				actual.getAnterior().desconectarsiguiente();
				actual.getSiguiente().desconectaranterior();
			}
			actual=actual.getSiguiente();
		}
	}
	
	public void verificarcolisiones() {
		Bonificacion actual = primerbonus;
		if (actual!=null) {
		while(actual!=localizarultimo()) {
			boolean colisiona = actual.colisionaCon(nave);
			System.out.println("Entra");
			if (colisiona) {
				System.out.println("Colisiona");
				actual.setVisible(false);
				dobonus( actual.getTipo());
			}
			actual = actual.getSiguiente();
		}
		}
	}
	
	public void dobonus(int tipo) {
		switch (tipo) {
		case 1:
			//n proyectiles
			break;
		case 2:
			//tipo proyectiles
			
			break;
		case 3:
			nave.aumentarvida();
			//bonus vida
			break;
		case 4: 
			puntaje+=100;
			//bonus puntos
			break;
		}
	}
	
	
	public void ordernarNombreAscendente() {
		//Seleccion
		for (int i=0; i<listjugadores.size()-1; i++) {
			Jugador menor = listjugadores.get(i);
			int cual = 0;
			for (int j = i+1; j<listjugadores.size(); j++) {
				if (listjugadores.get(j).compararNombre(menor)<0) {
					menor = listjugadores.get(j);
					cual = j; 
				}
			}
			Jugador tmp = listjugadores.get(i);
			listjugadores.set(i, menor);
			listjugadores.set(cual, tmp);
		}		
	}
	
	public void ordernarNombreDescencente() {
		//Seleccion
		for (int i=0; i<listjugadores.size()-1; i++) {
			Jugador mayor = listjugadores.get(i);
			int cual = 0;
			for (int j = i+1; j<listjugadores.size(); j++) {
				if (listjugadores.get(j).compararNombre(mayor)>0) {
					mayor = listjugadores.get(j);
					cual = j; 
				}
			}
			Jugador tmp = listjugadores.get(i);
			listjugadores.set(i, mayor);
			listjugadores.set(cual, tmp);
		}		
	}
	
	public void ordernarPuntajeAscendente() {
		//Burbuja
		for (int i=listjugadores.size(); i>0; i--) {
			for (int j=0; j<i-1; j++) {
				if (listjugadores.get(j).compareTo(listjugadores.get(j+1))>0) {
					Jugador tmp = listjugadores.get(j);
					listjugadores.set(j, listjugadores.get(j+1));
					listjugadores.set(j+1, tmp);
				}
			}
		}
	}
	
	public void ordernarPuntajeDescendente() {
		//Burbuja
		for (int i=listjugadores.size(); i>0; i--) {
			for (int j=0; j<i-1; j++) {
				if (listjugadores.get(j).compareTo(listjugadores.get(j+1))<0) {
					Jugador tmp = listjugadores.get(j);
					listjugadores.set(j, listjugadores.get(j+1));
					listjugadores.set(j+1, tmp);
				}
			}
		}
	}
	
	public void ordernarNivelAscendente() {
		//Insercion 
		for (int i=1; i<listjugadores.size();i++) {
			for (int j=i; j>0 && listjugadores.get(j-1).compararNivel(listjugadores.get(j))>0; j--) {
					Jugador tmp = listjugadores.get(j);
					listjugadores.set(j, listjugadores.get(j-1));
					listjugadores.set(j-1, tmp);
			}
		}
	}
	
	public void ordernarNivelDescendente() {
		//Insercion 
		for (int i=1; i<listjugadores.size();i++) {
			for (int j=i; j>0 && listjugadores.get(j-1).compararNivel(listjugadores.get(j))<0; j--) {
					Jugador tmp = listjugadores.get(j);
					listjugadores.set(j, listjugadores.get(j-1));
					listjugadores.set(j-1, tmp);
			}
		}
	}
	
	
	
	
}