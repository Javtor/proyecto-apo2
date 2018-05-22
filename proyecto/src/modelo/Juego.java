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
/**
 * Clase Juego
 * Es la clase principal del modelo, donde se manejan los aspectos lógicos relacionados con los requerimientos
 * funcionales.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Juego implements Serializable {

	//Constantes
	/**
	 * Ancho de la ventana del juego
	 */
	public static final int ANCHO = 800;
	/**
	 * Alto de la ventana del juego
	 */
	public static final int ALTO = 600;

	/**
	 * Directorio del archivo .txt de la nave (cuando se vaya a serializar)
	 */
	public static final String DIREC_NAVE = "./data" + File.separator + "nave.txt";
	/**
	 * Directorio del archivo .txt del árbol de pelotas (cuando se vaya a serializar)
	 */
	public static final String DIREC_PELOTAS = "./data" + File.separator + "pelotas.txt";
	/**
	 * Directorio del archivo .txt de los datos del jugador
	 * En él se encuentra nombre, puntaje y nivel obtenidos en la partida previa.
	 */
	public static final String NOM_DATOS = "./data" + File.separator + "datospartida.txt";
	/**
	 * Directorio del archivo .txt del arbol de jugadores (cuando se vaya a serializar)
	 */
	public static final String DIREC_JUGADORES = "./data" + File.separator + "users.txt";
	/**
	 * Directorio del archivo .txt de la lista de bonificaciones (cuando se vaya a serializar)
	 */
	public static final String DIREC_BONUS = "./data" + File.separator + "bonus.txt";
	/**
	 * Directorio del archivo .txt de la lista de decoraciones (cuando se vaya a serializar)
	 */
	public static final String DIREC_DECO = "./data" + File.separator + "deco.txt";
	/**
	 * Ruta donde se encuentra la canción de fondo del juego. 
	 */
	public static final String SONG = "./img" + File.separator + "bgmusic.wav";
	/**
	 * Fotogramas por segundo del juego
	 */
	public static final int FPS = 35;
	/**
	 * Incremento de puntos obtenidos al disparar a una pelota
	 */
	public static final int INCREMENTO_PELOTA = 5;
	/**
	 * Incremento de puntos obtenidos al atrapar la bonificación de puntos
	 */
	public static final int INCREMENTO_BONUS = 10;
	/**
	 * Base para definir cuántas pelotas debe haber en la pantalla a medida que sube de nivel 
	 */
	public static final int CADA_CUANTO_PELOTA = 4;
	/**
	 * Numero de decoraciones que tiene la pantalla del juego
	 */
	public static final int NUMERO_DECORACIONES = 5;

	//Atributos
	/**
	 * Puntaje del juego
	 */
	private int puntaje;
	/**
	 * Nivel del juego
	 */
	private int nivel;
	/**
	 * Si el juego está activo o inactivo
	 */
	private boolean jugando;
	/**
	 * Numero de pelotas que hay en la pantalla
	 */
	private int numPelotas;
	/**
	 * Cancion usada como fondo para el juego
	 */
	private Clip cancionFondo;
	/**
	 * Nave que usa el jugador para desplazarse por la pantalla 
	 */
	private Nave nave;
	/**
	 * Jugador actual del juego
	 */
	private Jugador jugador;
	/**
	 * Primera bonificación de la lista enlazada Bonificación
	 */
	private Bonificacion primerBonus;
	/**
	 * Primera decoración de la lista enlazada Decoración
	 */
	private Decoracion primeraDeco;
	/**
	 * Raiz del árbol pelotas
	 */
	private Pelota raizPelota;
	/**
	 * Raiz del árbol jugador
	 */
	private Jugador raizjugador;

	/**
	 * Constructor de la clase juego<br>
	 * Asigna el nivel inicial como 1<br>
	 * Crea un jugador actual sin nombre<br>
	 */
	public Juego() {
		nivel = 1;
		jugador = new Jugador(null);
		raizjugador = null;
	}

	/**
	 * Getter de la raiz del árbol Jugador<br>
	 * <b>post:</b> Devuelve la raiz del arbol jugador <br> 
	 * @return raizjugador=null || raizjugador instanceOf Jugador
	 */
	public Jugador getRaizJugador() {
		return raizjugador;
	}

	/**
	 * Getter del jugador<br>
	 * <b>post:</b> Devuelve el jugador actual del juego <br>
	 * @return jugador instanceOf Jugador, jugador!=null
	 */
	public Jugador getJugador() {
		return jugador;
	}
	/**
	 * Setter del jugador<br>
	 * <b>post:</b> Devuelve el jugador actual del juego <br>
	 * @param jugador El nuevo jugador que se va a insertar como jugador actual. jugador!=null.
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	/**
	 * Getter del puntaje<br>
	 * @return retorna el puntaje actual del juego. puntaje!=null
	 */
	public int getPuntaje() {
		return puntaje;
	}
	/**
	 * Getter del atributo nave<br>
	 * <b>post:</b>Devuelve el atributo nave.<br>
	 * @return nave. nave!=null
	 */
	public Nave getNave() {
		return nave;
	}
	/**
	 * Getter del ArrayList de Pelotas, generado a partir de la raiz del árbol pelotas<br>
	 * @return un ArrayList con elementos de tipo Pelota. a!=null 
	 */
	public ArrayList<Pelota> getPelotas() {
		ArrayList<Pelota> a = new ArrayList<Pelota>();
		if (raizPelota != null) {
			raizPelota.crearArreglo(a);
		}
		return a;
	}
	/**
	 * Devuelve si la partida está activa<br>
	 * <b>post:</b>Devuelve si la partida está activa o no<br>
	 * @return true o false dependiendo si la partida está activa o no
	 */
	public boolean isJugando() {
		return jugando;
	}
	/**
	 * Setter para saber si la partida está activa o no.<br>
	 * <b>post:</b>El estado de la partida (activa/inactiva) ha cambiado<br>
	 * @param jugando si la partida está activa o no. jugador!=null
	 */
	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}
	/**
	 * Crea y retorna un ArrayList con la lista de los bonus.<br>
	 * <b>post:</b>Retorna un ArrayList a partir del primer bonus de la lista bonus<br>
	 * @return un ArrayList con la lista bonus, puede estar vacía.
	 */
	public ArrayList<Bonificacion> getBonus() {
		ArrayList<Bonificacion> b = new ArrayList<Bonificacion>();
		if (primerBonus != null) {
			primerBonus.aArrayList(b);
		}
		return b;
	}
	/**
	 * Inicia el juego poniendo a sonar la canción de fondo.<br>
	 * Añade un nuevo jugador y carga el numero de pelotas y la nave, dependiendo si es una partida nueva o no<br>
	 * Crea las decoraciones de la pantalla<br>
	 * Dependiendo si está cargado o no inicia las pelotas serializadas o nuevas<br>
	 * Dependiendo si está cargado o no si se lanza la Excepcion JugadorRepetidoException verifica si en realidad
	 * está repetido o es que está cargando uno ya existente. <br>
	 * @param cargado Dice si está cargado o no el juego. cargado !=null
	 * @throws JugadorRepetidoException si el jugador que se intenta instanciar para emepzar el juego ya existe 
	 */
	public void iniciarJuego(boolean cargado) throws JugadorRepetidoException {
		jugando = true;
		try {
			cancionFondo = AudioSystem.getClip();
			cancionFondo.open(AudioSystem.getAudioInputStream(new File(SONG)));
			cancionFondo.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			addJugador();
			nave = cargado ? nave : new Nave();
			numPelotas = nivel / 3 + 3;
			if (!cargado) {
				iniciarPelotas();
			}
			crearDecoraciones();
		} catch (JugadorRepetidoException e) {
			if (cargado) {
				jugador = e.getJugador();
				jugador.setPuntaje(puntaje);
				jugador.setNivel(nivel);
			} else {
				jugando = false;
				cancionFondo.stop();
				throw e;
			}
		}

	}
	/**
	 * Inserta una nueva pelota en el árbol de pelotas.<br>
	 * <b>post:</b> Se ha añadido un nuevo elemento al árbol de pelotas<br>
	 * @param p Es la nueva pelota a añadir. p!=null
	 */
	public void insertarPelota(Pelota p) {
		if (raizPelota == null) {
			raizPelota = p;
		} else {
			raizPelota.insertar(p);
		}
	}
	/**
	 * Inicia el numero de pelotas correspondientes con el atributo numPelotas.<br>
	 * <b>pre:</b>numPelotas!=null, numPelotas debe ser mayor a 0 <br>
	 * <b>post:</b>Se han instanciado n pelotas correspondientes con el atributo numPelotas <br>
	 */
	public void iniciarPelotas() {
		for (int i = 0; i < numPelotas; i++) {
			Pelota p = null;
			p = new Pelota(nivel);
			insertarPelota(p);
		}
	}
	/**
	 * Hace la serialización de todas las instancias de la pantalla en el momento<br>
	 * Serializa nave, pelotas, jugadores, bonificaciones y decoraciones.<br>
	 * <b>post:</b> Se ha creado 5 archivos serializables con las intancias de la pantalla.<br>
	 * @throws FileNotFoundException Si en alguno de los métodos llamados no se encuentra el File
	 * @throws IOException Si ocurre un error al momento de serializar
	 */
	public void guardarPartida() throws FileNotFoundException, IOException {
		guardarNave();
		guardarPelotas();
		guardarJugadores();
		guardarBonificaciones();
		guardarDeco();
	}
	/**
	 * Serializa el árbol de jugadores a partir de la raiz.<br>
	 * <b>post:</b>Se ha creado un archivo .txt con el serializado del árbol jugadores<br>
	 * @throws FileNotFoundException Si no se encuentra el File en el directorio
	 * @throws IOException Si ocurre un error al momento de serializar
	 */
	public void guardarJugadores() throws FileNotFoundException, IOException {
		File file = new File(DIREC_JUGADORES);
		if (file.exists())
			file.delete();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(raizjugador);
		oos.close();
	}
	/**
	 * Serializa la nave y por ende el proyectil que se tiene en el momento.<br> 
	 * <b>post:</b>Se ha creado un archivo .txt con el serializado de la nave<br>
	 * @throws FileNotFoundException Si no se encuentra el File en el directorio
	 * @throws IOException Si ocurre un error al momento de serializar
	 */
	public void guardarNave() throws FileNotFoundException, IOException {
		File file = new File(DIREC_NAVE);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(nave);
		oos.close();
	}
	/**
	 * Serializa el árbol de pelotas a partir de la raiz.<br>
	 * <b>post:</b>Se ha creado un archivo .txt con el serializado del árbol pelotas<br>
	 * @throws FileNotFoundException Si no se encuentra el File en el directorio
	 * @throws IOException Si ocurre un error al momento de serializar
	 */
	public void guardarPelotas() throws FileNotFoundException, IOException {
		File file = new File(DIREC_PELOTAS);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(raizPelota);
		oos.close();
	}
	/**
	 * Serializa la lista de bonificaciones a partir del primer elemento de la lista.<br>
	 * <b>post:</b>Se ha creado un archivo .txt con el serializado de la lista de bonificaciones<br>
	 * @throws FileNotFoundException Si no se encuentra el File en el directorio
	 * @throws IOException Si ocurre un error al momento de serializar
	 */
	public void guardarBonificaciones() throws FileNotFoundException, IOException {
		File file = new File(DIREC_BONUS);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(primerBonus);
		oos.close();
	}
	/**
	 * Serializa la lista de decoraciones a partir del primer elemento de la lista.<br>
	 * <b>post:</b>Se ha creado un archivo .txt con el serializado de las decoraciones<br>
	 * @throws FileNotFoundException Si no se encuentra el File en el directorio
	 * @throws IOException Si ocurre un error al momento de serializar
	 */
	public void guardarDeco() throws FileNotFoundException, IOException {
		File file = new File(DIREC_DECO);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(primeraDeco);
		oos.close();
	}
	/**
	 * Recupera los serializables de todos los aspectos relacionados con la pantalla y los jugadores 
	 * que han guardado el juego.<br>
	 * Recupera las pelotas, la nave, los jugadores, la lista de bonus y las decoraciones.<br>
	 * <b>post:</b> Se ha recuperado todos los aspectos del juego (asociaciones)<br>
	 * @throws FileNotFoundException Si no encuentra el archivo serializado
	 * @throws IOException Si ocurre un error al momento de recuperarlo
	 * @throws ClassNotFoundException Si no encuentra la clase de la que se quiere recuperar el objeto
	 */
	public void cargarPartida() throws FileNotFoundException, IOException, ClassNotFoundException {
		recuperarPelotas();
		recuperarNave();
		recuperarJugadores();
		recuperarBonus();
		recuperarDeco();
	}
	/**
	 * Recupera la raiz del árbol Pelotas.<br>
	 * <b>post:</b> el árbol de pelotas ha sido recuperado. raizPelota!=null<br>
	 * @throws IOException Si ocurre un error al momento de recuperarlo
	 * @throws ClassNotFoundException Si no encuentra la clase de la que se quiere recuperar el objeto
	 */
	public void recuperarPelotas() throws IOException, ClassNotFoundException {
		File file = new File(DIREC_PELOTAS);
		boolean existe = file.exists() && file.isFile();
		if (existe) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			try {
			raizPelota = (Pelota) ois.readObject();
			} finally {
				ois.close();
			}
		} else {
			throw new FileNotFoundException("No se ha encontrado el archivo");
		}
	}
	/**
	 * Recupera el primer elemento de la lista bonus.<br>
	 * <b>post:</b> la lista de bonus ha sido recuperada. primerBonus!=null<br>
	 * @throws IOException Si ocurre un error al momento de recuperarlo
	 * @throws ClassNotFoundException Si no encuentra la clase de la que se quiere recuperar el objeto
	 */
	public void recuperarBonus() throws IOException, ClassNotFoundException {
		File file = new File(DIREC_BONUS);
		boolean existe = file.exists() && file.isFile();
		if (existe) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			try {
			primerBonus = (Bonificacion) ois.readObject();
			}finally {
				ois.close();
			}
		} else {
			throw new FileNotFoundException("No se ha encontrado el archivo");
		}
	}
	/**
	 * Recupera el primer elemento de la lista de decoraciones.<br>
	 * <b>post:</b> La lista de decoraciones ha sido recuperada. primeraDeco!=null<br>
	 * @throws IOException Si ocurre un error al momento de recuperarlo
	 * @throws ClassNotFoundException Si no encuentra la clase de la que se quiere recuperar el objeto
	 */
	public void recuperarDeco() throws IOException, ClassNotFoundException {
		File file = new File(DIREC_DECO);
		boolean existe = file.exists() && file.isFile();
		if (existe) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			try {
			primeraDeco = (Decoracion) ois.readObject();
			} finally {
				ois.close();
			}
		} else {
			throw new FileNotFoundException("No se ha encontrado el archivo");
		}
	}
	/**
	 * Recupera la nave del juego, y por ende al proyectil actual.<br>
	 * <b>post:</b> la nave ha sido recuperada y su posicion pasa a ser la (0,0) de la pantalla<br>
	 * @throws IOException Si ocurre un error al momento de recuperarlo
	 * @throws ClassNotFoundException Si no encuentra la clase de la que se quiere recuperar el objeto
	 */
	public void recuperarNave() throws IOException, ClassNotFoundException {
		File file = new File(DIREC_NAVE);
		boolean existe = file.exists() && file.isFile();

		if (existe) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			try {
			nave = (Nave) ois.readObject();
			} finally {
				ois.close();
			}
			nave.setDX(0);
			nave.setDY(0);
		} else {
			throw new FileNotFoundException("No se ha encontrado el archivo");
		}
	}
	/**
	 * Recupera la raiz del árbol Jugadores.<br>
	 * <b>post:</b> el árbol de jugadores ha sido recuperado. raizjugador!=null<br>
	 * @throws IOException Si ocurre un error al momento de recuperarlo
	 * @throws ClassNotFoundException Si no encuentra la clase de la que se quiere recuperar el objeto
	 */
	public void recuperarJugadores() throws IOException, ClassNotFoundException {
		File file = new File(DIREC_JUGADORES);
		boolean existe = file.exists() && file.isFile();
		if (existe) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			try {			
				raizjugador = (Jugador) ois.readObject();
			} finally {
				ois.close();
			}
		} else {
			throw new FileNotFoundException("No se ha encontrado el archivo");
		}
	}
	/**
	 * Crea un archivo de texto con el nombre del jugador, el puntaje y el nivel actual del juego.<br>
	 * <b>post:</b> Se ha creado un archivo de texto con la información del jugador actual
	 * @throws FileNotFoundException Si no encuentra el directorio donde se crea el archivo
	 */
	public void guardarDatos() throws FileNotFoundException {
		jugador.setNivel(nivel);
		jugador.setPuntaje(puntaje);
		File archivo = new File(NOM_DATOS);
		PrintWriter pw = new PrintWriter(archivo);
		pw.println(jugador.getNickname());
		pw.println("Puntaje:" + jugador.getPuntaje());
		pw.println("Nivel:" + jugador.getNivel());
		pw.close();
	}
	/**
	 * Recupera los datos del jugador de la ultima partida guardada y asigna esa informacion al
	 * jugador, puntaje y nivel de la partida.<br>
	 * <b>post:</b>Se ha recuperado la informacion del jugador de la ultima partida guardada<br>
	 * @throws IOException Si ocurre algun error al momento de recuperar el archivo
	 */
	public void cargarDatos() throws IOException {
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
	 * Ejecuta la acccion correspondiente a la tecla presionada.<br>
	 * Si es la letra G, guarda la partida y los datos.<br>
	 * Si es diferente a G realiza algún movimiento de la nave.<br> 
	 * @param e KeyEvent de la tecla presionada 
	 * @throws IOException Si ocurre algun error al momento de guadar los datos o la partida.
	 */
	public void keyPressed(KeyEvent e) throws IOException {
		if (e.getKeyCode() != KeyEvent.VK_G) {
			nave.keyPressed(e);
		} else {
			guardarDatos();
			guardarPartida();
		}
	}
	/**
	 * Cuando se deja de presionar una tecla se envia el evento a la nave.<br>
	 * @param e Evento de la tecla presionada 
	 */
	public void keyReleased(KeyEvent e) {
		nave.keyReleased(e);
	}
	/**
	 * Añade un nuevo bonus a la lista bonificación.<br>
	 * <b>post:</b>Se ha añadido una nueva bonificación a la lista de bonus<br>
	 */
	public void crearBonus() {
		Bonificacion anadida;
		switch((int)(Math.random()*5)) {
		case 0:
			anadida = new BonoVida();
			break;
		case 1: 
			anadida = new BonoPuntos();
			break;
		case 2: 
			anadida = new BonoProyFuerte();
			break;
		case 3: 
			anadida = new BonoProyNormal();
			break;
		case 4: 
			anadida = new BonoProyRapido();
			break;
		default:
			anadida = new BonoVida();
		}
		if (primerBonus != null) {
			Bonificacion actual = localizarUltimoBonus();
			actual.setSiguiente(anadida);
		} else {
			primerBonus = anadida;
		}
	}
	/**
	 * Localiza el ultimo bonus de la lista de bonificaciones.<br>
	 * <b>post:</b>Devuelve el ultimo elemento de la lista de bonificaciones<br>
	 * @return actual es el ultimo elemento de la lista. actual puede ser null.
	 */
	public Bonificacion localizarUltimoBonus() {
		Bonificacion actual = primerBonus;
		if (actual != null) {
			while (actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
		}
		return actual;
	}
	/**
	 * Incrementa el puntaje actual en INCREMENTO_BONUS.<br>
	 * <b>pre:</b>Se ha instanciado un jugador<br>
	 * <b>post:</b>El atributo puntaje se ha incrementado<br>
	 */
	public void bonusPuntaje() {
		puntaje += INCREMENTO_BONUS;
		jugador.setPuntaje(puntaje);

	}
	/**
	 * Incrementa el puntaje actual en INCREMENTO_PELOTA.<br>	
	 * <b>pre:</b>Se ha instanciado un jugador<br>
	 * <b>post:</b>El atributo puntaje se ha incrementado<br>
	 */
	public void aumentarPuntaje() {
		puntaje += INCREMENTO_PELOTA;
		jugador.setPuntaje(puntaje);
	}
	/**
	 * Ordena la lista de jugadores de manera ascendente de acuerdo al nombre.<br>
	 * <b>post:</b>Retorna una lista de jugadores ordenada de manera ascendente de acuerdo a su nombre<br>
	 * @return Un ArrayList con elementos de tipo jugador ordenados de manera ascendente por nombre. 
	 */
	public ArrayList<Jugador> ordenarNombreAscendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador();
		// Seleccion
		for (int i = 0; i < listjugadores.size() - 1; i++) {
			Jugador menor = listjugadores.get(i);
			int cual = i;
			for (int j = i + 1; j < listjugadores.size(); j++) {
				if (menor.compararNombre(listjugadores.get(j).getNickname()) > 0) {
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
	/**
	 * Ordena la lista de jugadores de manera descendente de acuerdo al nombre.<br>
	 * <b>post:</b>Retorna una lista de jugadores ordenada de manera descendente de acuerdo a su nombre<br>
	 * @return Un ArrayList con elementos de tipo jugador ordenados de manera descente por nombre. 
	 */
	public ArrayList<Jugador> ordenarNombreDescendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador();
		// Seleccion
		for (int i = 0; i < listjugadores.size() - 1; i++) {
			Jugador mayor = listjugadores.get(i);
			int cual = i;
			for (int j = i + 1; j < listjugadores.size(); j++) {
				if (mayor.compararNombre(listjugadores.get(j).getNickname()) < 0) {
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
	/**
	 * Ordena la lista de jugadores de manera ascendente de acuerdo al puntaje.<br>
	 * <b>post:</b>Retorna una lista de jugadores ordenada de manera ascendente de acuerdo a su puntaje<br>
	 * @return Un ArrayList con elementos de tipo jugador ordenados de manera ascendente por puntaje. 
	 */
	public ArrayList<Jugador> ordenarPuntajeAscendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador();
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
	/**
	 * Ordena la lista de jugadores de manera descendente de acuerdo al puntaje.<br>
	 * <b>post:</b>Retorna una lista de jugadores ordenada de manera descendente de acuerdo a su puntaje<br>
	 * @return Un ArrayList con elementos de tipo jugador ordenados de manera descendente por puntaje. 
	 */
	public ArrayList<Jugador> ordenarPuntajeDescendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador();
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
	/**
	 * Ordena la lista de jugadores de manera ascendente de acuerdo al nivel.<br>
	 * <b>post:</b>Retorna una lista de jugadores ordenada de manera ascendente de acuerdo a su nivel<br>
	 * @return Un ArrayList con elementos de tipo jugador ordenados de manera ascendente por nivel. 
	 */
	public ArrayList<Jugador> ordenarNivelAscendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador();
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
	/**
	 * Ordena la lista de jugadores de manera descendente de acuerdo al nivel.<br>
	 * <b>post:</b>Retorna una lista de jugadores ordenada de manera descedente de acuerdo a su nivel<br>
	 * @return Un ArrayList con elementos de tipo jugador ordenados de manera descendente por nivel. 
	 */
	public ArrayList<Jugador> ordenarNivelDescendente() {
		ArrayList<Jugador> listjugadores = toArrayListJugador();
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
	/**
	 * Verifca todas las posibles colisiones del juego para realizar incremento de puntos,
	 * mejorar el proyectil dependiendo de las bonificaciones, o decrementar la vida de las pelotas si un 
	 * proyectil colisiona con ella.<br>
	 * <b>pre:</b>raizPelota!=null<br>
	 * <b>post:</b>Se ha verificado todos los aspectos del juego y se han ejecutado los incrementos,
	 * decrementos o mejoras correspondientes<br>
	 */
	public void cicloJuego() {
		verificarColisionNave();
		verificarColisionBonus();
		verificarColisionProyectil();
		verificarVidas();
		if (!(raizPelota.hayVivas())) {
			subirNivel();
		}
	}
	/**
	 * Verifica si la nave sigue con vida, de no ser así termina el juego.<br>
	 * <b>pre:</b>Se ha instanciado una nave<br>
	 * <b>post:</b>El juego para si la nave no tiene vidas<br>
	 * <b>post:</b>Si pierde el juego crea el serializable de los jugadores<br>
	 */
	public void verificarVidas() {
		if (!(nave.validarViva())) {
			jugando = false;
			cancionFondo.stop();
			try {
				guardarJugadores();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	/**
	 * Verifica si existe alguna colision entre la nave y un bonus de la lista.<br>
	 * <b>pre:</b>Se ha instanciado una nave<br>
	 * <b>post:</b>Recorre la lista de bonus verificando si existe colisión con la nave<br>
	 * <b>post:</b>Si existe colisión con la nave verifica si es de puntaje, o si es de tipo puntaje e 
	 * incrementa el puntaje, sino envia la el tipo de colisión a la nave para que realice su respectiva función<br>
	 */
	public void verificarColisionBonus() {
		Bonificacion actual = primerBonus;
		while (actual != null) {
			boolean colisiona = actual.hayColision(nave);
			if (actual.esVisible() && colisiona) {
				actual.colisionaCon(nave);
				if (actual instanceof BonoPuntos) {
					bonusPuntaje();
				} else {
					nave.colisionaCon(actual);
				}
			}
			actual = actual.getSiguiente();
		}
	}
	/**
	 * Verifica si alguna de las pelotas del árbol ha colisionado con la nave 
	 * <b>pre:</b>Se ha instanciado un objeto nave<br>
	 * <b>post:</b>Si existen colisiones se ejecuta el método colisiona con para bajar la vida de la nave<br>
	 */
	public void verificarColisionNave() {
		if (raizPelota != null && raizPelota.existenColisiones(nave) && nave.esVisible()) {
			nave.colisionaCon(new Pelota(0));
		}
	}
	/**
	 * Recorre el arreglo de pelotas verificando si existe alguna colisión entre la pelota y el proyectil
	 * Cuando encuentra una colision se sale  del ciclo.<br>
	 * <b>pre:</b>Se ha instanciado una nave<br>
	 * <b>post:</b>Si el proyectil colisionó con una pelota se vuelve invisible<br>
	 * <b>post:</b>Si el proyectil colisionó con una pelota se baja la vida de la pelota<br>
	 * <b>post:</b>Si la pelota es invisible (su vida es 0) se incrementa el puntaje<br>
	 */
	public void verificarColisionProyectil() {
		if (nave.getProyectil().esVisible()) {
			boolean sigue = true;
			ArrayList<Pelota> p = getPelotas();
			for (int i = 0; i < p.size() && sigue; i++) {
				if (p.get(i).hayColision(nave.getProyectil())) {
					p.get(i).colisionaCon(nave.getProyectil());
					nave.getProyectil().colisionaCon(p.get(i));
					if (!(p.get(i).esVisible())) {
						aumentarPuntaje();
					}
					sigue = false;
				}
			}
		}
	}
	/**
	 * Sube el nivel en 1 y ubica a la nave en el centro de la pantalla<br>
	 * Instancia el nuevo numero de pelotas que debe tener la pantalla de acuerdo al nivel alcanzado.<br>
	 * <b>pre:</b>Se ha instanciado una nave<br>
	 * <b>pre:</b>Se ha instanciado un jugador<br>
	 * <b>post:</b>Se ha incrementado el puntaje en 1<br>
	 * <b>post:</b>La nave se ha ubicado en el centro de la pantalla<br>
	 * <b>post:</b>Se ha cambiado el numero de pelotas de la pantalla por las correspondientes al nivel<br>
	 */
	public void subirNivel() {
		nivel++;
		nave.setX(ANCHO / 2 - nave.getAncho() / 2);
		nave.setY(Juego.ALTO - 100 - nave.getAlto() / 2);
		jugador.setNivel(nivel);
		numPelotas = nivel / CADA_CUANTO_PELOTA + 3;
		raizPelota = null;
		iniciarPelotas();
	}
	/**
	 * Añade el jugador actual de la partida al árbol de jugadores.<br>
	 * <b>post:</b>Se ha añadido un jugador al árbol de jugadores<br>
	 * @throws JugadorRepetidoException Si ya existe ese jugador en el árbol
	 */
	public void addJugador() throws JugadorRepetidoException {
		if (raizjugador == null) {
			raizjugador = jugador;
		} else {
			raizjugador.insertar(jugador);
		}
	}
	/**
	 * Crea y devuelve un ArrayList con los jugadores del árbol de jugadores.<br>
	 * <b>post:</b>Se ha creado un ArrayList con todos los jugadores del árbol<br>
	 * @return Un ArrayList con los jugadores (ordenado por defecto por nombre de manera ascendente)
	 */
	public ArrayList<Jugador> toArrayListJugador() {
		ArrayList<Jugador> alj = new ArrayList<Jugador>();
		if (raizjugador != null) {
			raizjugador.crearArreglo(alj);
		}
		return alj;

	}
	/**
	 * Busca en el árbol de jugadores un jugador dado su nombre.<br>
	 *  <b>post:</b>Se ha encontrado el jugador con dicho nombre<br>
	 * @param nombre El nombre que se usará para buscar al jugador. nombre!=null, nombre!=""
	 * @return Retorna el jugador buscado
	 * @throws NombreNoExisteException si no existe ningun jugador con ese nombre en el árbol
	 */
	public Jugador buscarJugadorNombre(String nombre) throws NombreNoExisteException {
		Jugador j = raizjugador == null ? null : raizjugador.buscarNombre(nombre);
		if (j == null)
			throw new NombreNoExisteException();

		return j;
	}
	/**
	 * Busca en un ArrayList con los jugadores del arbol un jugador dados sus puntos.<br>
	 * <b>post:</b>Se encontró un jugador con dichos puntos<br>
	 * @param puntos El criterio a buscar en el ArrayList. puntos!=null, puntos debe ser mayor o igual a 0.
	 * @return El primer jugador encontrado con dicho puntaje
	 * @throws PuntajeNoExisteException Si no existe ningun jugador con ese puntaje 
	 */
	public Jugador buscarJugadorPuntos(int puntos) throws PuntajeNoExisteException {
		//busqueda binaria
		ArrayList<Jugador> array = ordenarPuntajeAscendente();
		Jugador j = null;
		boolean resultado = false;
		int inicio = 0;
		int fin = array.size() - 1;
		while (inicio <= fin && !resultado) {
			int medio = (inicio + fin) / 2;
			if (array.get(medio).getPuntaje() == puntos) {
				j = array.get(medio);
				resultado = true;
			} else if (array.get(medio).getPuntaje() > puntos) {
				fin = medio - 1;
			} else {
				inicio = medio + 1;
			}
		}
		if (j == null)
			throw new PuntajeNoExisteException();
		return j;
	}
	/**
	 * Manda las coordenadas x y y de la posición a la que debe dirigir el disparo del proyectil.<br>
	 * <b>pre:</b>Se ha instanciado una nave<br>
	 * <b>post:</b>El proyectil ha sido disparado<br>
	 * @param x coordenada x. x!=null, x debe ser mayor o igual a 0.
	 * @param y coordenada y. y!=null, y debe ser mayor o igual a 0.
	 */
	public void disparar(int x, int y) {
		nave.disparar(x, y);
	}
	/**
	 * Crea el numero de decoraciones correspondiente a la constante NUMERO_DECORACIONES.<br>
	 * <b>post:</b>Se han creado las decoraciones correspondientes en la lista de decoraciones<br>
	 */
	public void crearDecoraciones() {
		for (int i = 0; i < NUMERO_DECORACIONES; i++) {
			agregarDecoracion(new Decoracion());
		}
	}
	/**
	 * Añade una decoración al final de la lista.<br>
	 * <b>post:</b>Añade una decoración al final de la lista<br>
	 * @param d La nueva decoración a añadir. d!=null
	 */
	public void agregarDecoracion(Decoracion d) {
		if (primeraDeco == null) {
			primeraDeco = d;
		} else {
			Decoracion actual = primeraDeco;
			while (actual.darSiguiente() != null) {
				actual = actual.darSiguiente();
			}
			actual.setSiguiente(d);
		}
	}
	/**
	 * Crea y devuelve un ArrayList con las decoraciones que hay en la lista decoraciones.<br>
	 * <b>post:</b>Se ha recorrido y añadido todas las decoraciones de la lista al ArrayList<br>
	 * @return Un ArrayList con las decoraciones que hay en la lista decoraciones 
	 */
	public ArrayList<Decoracion> darDecoraciones() {
		ArrayList<Decoracion> decos = new ArrayList<Decoracion>();
		if (primeraDeco != null) {
			Decoracion actual = primeraDeco;
			while (actual.darSiguiente() != null) {
				decos.add(actual);
				actual = actual.darSiguiente();
			}
			decos.add(actual);
		}
		return decos;
	}

}