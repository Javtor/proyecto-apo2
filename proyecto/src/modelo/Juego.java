package modelo;

import java.awt.event.KeyEvent;
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
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import hilos.HiloJuego;

public class Juego implements Serializable{
	public static final int ANCHO = 800;
	public static final int ALTO = 600;

	public static final String DIREC_DATOS = "data/ultimapartida.txt";
	public static final String NOM_DATOS = "data/datospartida.txt";
	public static final String DIREC_JUGADORES = "data/users.txt";
	public static final int FPS = 45;
	
	public static final int INCREMENTO = 5;

	private int puntaje;
	private int nivel;
	private Nave nave;
	private boolean jugando;
	private int numPelotas;
	private Clip cancionFondo;

	private Jugador jugador;
	private Bonificacion primerbonus;

	private Pelota raizPelota;
	private Jugador raizjugador;

	public Juego() {
		nivel = 1;
		jugador = new Jugador(null);
		raizjugador=null;
//		iniciarNivel();
		
	}
	
	public Jugador getRaizJugador() {
		return raizjugador;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador=jugador;
	}

	public void iniciarNivel() {
		jugador.setNivel(nivel);
		jugando = true;
		try {
			cancionFondo = AudioSystem.getClip();
			cancionFondo.open(AudioSystem.getAudioInputStream(new File("img/bgmusic.wav")));
			cancionFondo.loop(Clip.LOOP_CONTINUOUSLY);
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
//			boolean generar = true;
			Pelota p = null;
//			while (generar) {
//				generar = false;
				p = new Pelota();
//				if (raizPelota != null && raizPelota.existenColisiones(p)) {
//					if(ANCHO/(numPelotas-2)>raizPelota.getAncho()) {
//						generar = true;
//					}
//				}
//			}
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

	
	public ArrayList<Jugador> ordenarNombreAscendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador ();
		// Seleccion
		
		for (int i = 0; i < listjugadores.size() - 1; i++) {
			Jugador menor = listjugadores.get(i);
			int cual = i;
			for (int j = i + 1; j < listjugadores.size(); j++) {
				if (menor.compararNombre(listjugadores.get(j).getNickname()) < 0) {
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

	public ArrayList<Jugador> ordenarNombreDescendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador ();
		// Seleccion
		for (int i = 0; i < listjugadores.size() - 1; i++) {
			Jugador mayor = listjugadores.get(i);
			int cual = i;
			for (int j = i + 1; j < listjugadores.size(); j++) {
				if (mayor.compararNombre(listjugadores.get(j).getNickname()) > 0) {
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

	public ArrayList<Jugador> ordenarPuntajeAscendente() {
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

	public ArrayList<Jugador> ordenarPuntajeDescendente() {
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

	public ArrayList<Jugador> ordenarNivelAscendente() {
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

	public ArrayList<Jugador> ordenarNivelDescendente() {
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
		verificarColisionProyectil();
		verificarVidas();
	}

	public void verificarVidas() {
		if(nave.getVidas()==0) {
			jugando=false;
			cancionFondo.stop();
		}
	}

	public void verificarColisionNave() {
		if (raizPelota != null && raizPelota.existenColisiones(nave) && nave.esVisible()) {
			nave.colisionaCon(new Pelota());
		}
	}
	
	public void aumentarPuntaje() {
		puntaje+=INCREMENTO;
		jugador.setPuntaje(puntaje);
	}
	
	public void verificarColisionProyectil() {
		if (nave.getProyectil().esVisible()) {
			boolean sigue = true;
			ArrayList<Pelota> p = getPelotas();
			for (int i = 0; i < p.size() && sigue; i++) {
				if (p.get(i).hayColision(nave.getProyectil())) {
					p.get(i).colisionaCon(nave.getProyectil());
					nave.getProyectil().colisionaCon(p.get(i));
					if(!(p.get(i).esVisible())) {
						aumentarPuntaje();
					}
					sigue = false;
				}
			} 
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
	
	public Jugador buscarJugadorNombre(String nombre) throws NombreNoExisteException {
		Jugador j = raizjugador==null? null: raizjugador.buscarNombre(nombre);
		if (j==null)
			throw new NombreNoExisteException ();
		
		return j;
	}
	
	public Jugador buscarJugadorPuntos(int puntos) throws PuntajeNoExisteException {
		ArrayList<Jugador> array = ordenarPuntajeAscendente();
		Jugador j = null;
		boolean resultado = false;
		int inicio = 0;
		int fin = array.size()-1;
		
		while (inicio<=fin && !resultado) {
			
			int medio = (inicio+fin)/2;
			
			if (array.get(medio).getPuntaje()==puntos) {
				j = array.get(medio);
				resultado = true;
		
			}else if (array.get(medio).getPuntaje()>puntos) {
				fin = medio - 1;
			}else {
				inicio = medio +1 ;
			}
		}
		
		if (j==null)
			throw new PuntajeNoExisteException();
		
		return j;
	}

	public void disparar(int x, int y) {
		nave.disparar(x, y);
	}
	
	
}