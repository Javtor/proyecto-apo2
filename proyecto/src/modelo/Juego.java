package modelo;

import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import hilos.HiloJuego;

public class Juego implements Serializable{
	public static final int ANCHO = 800;
	public static final int ALTO = 600;

	public static final String DIREC_DATOS = "data/ultimapartida.txt";
	public static final String NOM_DATOS = "data/datospartida.txt";
	public static final String DIREC_JUGADORES = "data/users.txt";
	public static final int FPS = 45;

	private int puntaje;
	private int nivel;
	private Nave nave;
	private boolean jugando;
	private int numPelotas;

	private Jugador jugador;
	private Bonificacion primerbonus;

	private Pelota raizPelota;
	private Jugador raizjugador;

	public Juego() {
		nivel = 1;
		jugando = true;
		iniciarNivel();
	}

	public void iniciarNivel() {
		try {
			Clip sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File("img/bgmusic.wav")));
			sonido.loop(sonido.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nave = new Nave();
		numPelotas = nivel / 2 + 3;
		iniciarPelotas();
	}

	public void insertarPelota(Pelota p) {
		if (raizPelota == null) {
			raizPelota = p;
		} else {
			raizPelota.insertar(p);
		}
	}

	public void iniciarPelotas() {
		for (int i = 0; i < numPelotas; i++) {
			boolean generar = true;
			Pelota p = null;
			while (generar) {
				generar = false;
				p = new Pelota();
				if (raizPelota != null && raizPelota.existenColisiones(p)) {
					generar = true;
				}
			}
			insertarPelota(p);
		}
	}

	public ArrayList<Pelota> getPelotas() {
		ArrayList<Pelota> a = new ArrayList<Pelota>();
		if (raizPelota != null) {
			raizPelota.crearArreglo(a);
		}
		return a;
	}

	public void iniciarjuego(String nombre) {
		jugador = new Jugador(nombre);
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

	public void guardarpartida() throws FileNotFoundException, IOException{
		guardarJugadores();
	}
	
	public void guardarJugadores() throws FileNotFoundException, IOException {
		File file = new File (DIREC_JUGADORES);
		
		if (file.exists())
			file.delete();
		
		ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream (file));
		oos.writeObject(raizjugador);
		oos.close();
		
	}

	public void cargarpartida() {
		
	}
	
	public void recuperarJugadores() throws IOException, ClassNotFoundException {
		File file = new File(DIREC_JUGADORES);
		boolean existe = file.exists() && file.isFile();
		
		if (existe) {
			ObjectInputStream ois = new ObjectInputStream (new FileInputStream (file));
			raizjugador = (Jugador) ois.readObject();
		}else {
			throw new FileNotFoundException("No se ha encontrado el archivo");
		}
	}

	public void guardardatos() throws FileNotFoundException {
		jugador.setNivel(nivel);
		jugador.setPuntaje(puntaje);
		addJugador();

		File archivo = new File(NOM_DATOS);
		PrintWriter pw = new PrintWriter(archivo);

		if (archivo.exists())
			archivo.delete();

		pw.write(jugador.getNickname());
		pw.write("Puntaje:" + jugador.getPuntaje());
		pw.write("Nivel:" + jugador.getNivel());
		pw.close();
	}

	public void cargardatos() throws IOException {
		File archivo = new File(NOM_DATOS);
		boolean existe = archivo.exists() && archivo.isFile();
		if (existe) {
			BufferedReader read = new BufferedReader(new FileReader(archivo));
			String usuario = read.readLine();
			String[] level = read.readLine().split(":");
			String[] puntuacion = read.readLine().split(":");
			jugador.setNickname(usuario);
			puntaje = Integer.parseInt(level[1]);
			nivel = Integer.parseInt(puntuacion[1]);
			read.close();
		} else {
			throw new FileNotFoundException("No se ha encontrado el archivo");
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

		tipo = (int) ((Math.random() * 3) + 1);
		cx = (int) ((Math.random() * ANCHO) + 10);
		cy = (int) ((Math.random() * ALTO) + 10);

		Bonificacion anadida = new Bonificacion(cx, cy, null, tipo);

		if (primerbonus != null) {
			Bonificacion actual = localizarultimo();
			actual.setSiguiente(anadida);
			anadida.setAnterior(actual);
		} else {
			primerbonus = anadida;
		}

	}

	public Bonificacion localizarultimo() {
		Bonificacion actual = primerbonus;
		if (actual != null) {
			while (actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
		}
		return actual;
	}

	public void borrarbonusinvisibles() {
		Bonificacion actual = primerbonus;

		while (actual != null) {
			if (!actual.esVisible()) {
				if(actual.getAnterior() != null) {
					actual.getAnterior().desconectarSiguiente();
				}
				if(actual.getSiguiente() != null) {
					actual.getSiguiente().desconectarAnterior();
				}
			}
			actual = actual.getSiguiente();
		}
	}

	public void verificarColisionBonus() {
		Bonificacion actual = primerbonus;
		while (actual != null) {
			boolean colisiona = actual.hayColision(nave);
			if (actual.esVisible() && colisiona) {
				actual.colisionaCon(nave);
			}
			actual = actual.getSiguiente();
		}
	}

	public void doBonus(int tipo) {
		switch (tipo) {
		case 1:
			// n proyectiles
			break;
		case 2:
			// tipo proyectiles

			break;
		case 3:
			nave.aumentarVida();
			// bonus vida
			break;
		case 4:
			puntaje += 100;
			// bonus puntos
			break;
		}
	}

	
	public ArrayList<Jugador> ordernarNombreAscendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador ();
		// Seleccion
		for (int i = 0; i < listjugadores.size() - 1; i++) {
			Jugador menor = listjugadores.get(i);
			int cual = 0;
			for (int j = i + 1; j < listjugadores.size(); j++) {
				if (listjugadores.get(j).compararNombre(menor) < 0) {
					menor = listjugadores.get(j);
					cual = j;
				}
			}
			Jugador tmp = listjugadores.get(i);
			listjugadores.set(i, menor);
			listjugadores.set(cual, tmp);
		}
		return listjugadores;
	}

	public ArrayList<Jugador> ordernarNombreDescencente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador ();
		// Seleccion
		for (int i = 0; i < listjugadores.size() - 1; i++) {
			Jugador mayor = listjugadores.get(i);
			int cual = 0;
			for (int j = i + 1; j < listjugadores.size(); j++) {
				if (listjugadores.get(j).compararNombre(mayor) > 0) {
					mayor = listjugadores.get(j);
					cual = j;
				}
			}
			Jugador tmp = listjugadores.get(i);
			listjugadores.set(i, mayor);
			listjugadores.set(cual, tmp);
		}
		return listjugadores;
	}

	public ArrayList<Jugador> ordernarPuntajeAscendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador ();
		// Burbuja
		for (int i = listjugadores.size(); i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				if (listjugadores.get(j).compareTo(listjugadores.get(j + 1)) > 0) {
					Jugador tmp = listjugadores.get(j);
					listjugadores.set(j, listjugadores.get(j + 1));
					listjugadores.set(j + 1, tmp);
				}
			}
		}
		return listjugadores;
	}

	public ArrayList<Jugador> ordernarPuntajeDescendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador ();
		// Burbuja
		for (int i = listjugadores.size(); i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				if (listjugadores.get(j).compareTo(listjugadores.get(j + 1)) < 0) {
					Jugador tmp = listjugadores.get(j);
					listjugadores.set(j, listjugadores.get(j + 1));
					listjugadores.set(j + 1, tmp);
				}
			}
		}
		return listjugadores;
	}

	public ArrayList<Jugador> ordernarNivelAscendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador ();
		// Insercion
		for (int i = 1; i < listjugadores.size(); i++) {
			for (int j = i; j > 0 && listjugadores.get(j - 1).compararNivel(listjugadores.get(j)) > 0; j--) {
				Jugador tmp = listjugadores.get(j);
				listjugadores.set(j, listjugadores.get(j - 1));
				listjugadores.set(j - 1, tmp);
			}
		}
		return listjugadores;
	}

	public ArrayList<Jugador> ordernarNivelDescendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador ();
		// Insercion
		for (int i = 1; i < listjugadores.size(); i++) {
			for (int j = i; j > 0 && listjugadores.get(j - 1).compararNivel(listjugadores.get(j)) < 0; j--) {
				Jugador tmp = listjugadores.get(j);
				listjugadores.set(j, listjugadores.get(j - 1));
				listjugadores.set(j - 1, tmp);
			}
		}
		return listjugadores;
	}


	public void cicloJuego() {
		verificarColisionNave();
		verificarColisionBonus();
	}

	public void verificarColisionNave() {
		if (raizPelota != null && raizPelota.existenColisiones(nave) && nave.esVisible()) {
			nave.colisionaCon(new Pelota());
		}
	}
	
	public void addJugador() {
		if (raizjugador == null) {
			raizjugador = jugador;
		}else {
			raizjugador.insertar(jugador);
		}
	}
	
	public ArrayList<Jugador> toArrayListJugador(){
		ArrayList<Jugador> alj = new ArrayList<Jugador>();
		if (raizjugador != null) {
			raizjugador.crearArreglo(alj);
		}
		return alj;
		
	}
	
	public Jugador buscarJugadorPuntos(int puntos) {
		return raizjugador==null? null: raizjugador.buscar(puntos);
	}
	
	
}