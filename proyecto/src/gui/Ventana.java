package gui;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hilos.HiloInvulnerabilidad;
import hilos.HiloBonus;
import hilos.HiloJuego;
import hilos.HiloNave;
import hilos.HiloPelotas;
import hilos.HiloProyectil;
import modelo.Bonificacion;
import modelo.Decoracion;
import modelo.Juego;
import modelo.Jugador;
import modelo.JugadorRepetidoException;
import modelo.Nave;
import modelo.NombreNoExisteException;
import modelo.Pelota;
import modelo.PuntajeNoExisteException;
/**
 * Clase Ventana
 * Ventana principal de la interfaz, encargada de crear conexión entre el modelo, los hilos, y el usuario como tal
 * para usar el juego. 
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class Ventana extends JFrame {
	/**
	 * Constante de la ruta de la musica de fondo
	 */
	public static final String MUSICA_INICIO = "./img" + File.separator + "inicio.wav";
	/**
	 * Asociación con el panel del juego (parte gráfica del juego)
	 */
	private PanelJuego panelJuego;
	/**
	 * Asociación con el menú de inicio del juego
	 */
	private PanelInicio panelInicio;
	/**
	 * Asociacón con la ventana de Ranking del juego
	 */
	private DialogRanking ranking;
	/**
	 * Asocación con la clase principal del modelo
	 */
	private Juego juego;
	/**
	 * Canción de fondo
	 */
	private Clip cancionFondo;
	/**
	 * Hilo que controla la creación de bonus
	 */
	private HiloBonus hB;
	/**
	 * Hilo que controla el ciclo del juego.
	 */
	private HiloJuego hJ;
	/**
	 * Hilo que controla el movimiento de la nave.
	 */
	private HiloNave hN;
	/**
	 * Hilo que controla la invulnerabilidad de la nave
	 */
	private HiloInvulnerabilidad hI;
	/**
	 * Hilo que controla el movimiento de las pelotas
	 */
	private HiloPelotas hPel;
	/**
	 * Hilo que controla el movimiento del proyectil.
	 */
	private HiloProyectil hPro;
	/**
	 * Constructor de la ventana principal de la interfaz<br>
	 * Se encarga de inicializar el menu de inicio del juego, y las partidas guardadas.<br>
	 * <b>post:</b>La lista de usuarios que se han registrado en el juego ha sido recuperada<br>
	 * <b>post:</b>Se muestra el menu de inicio del juego<br>
	 */
	public Ventana() {
		try {
			cancionFondo = AudioSystem.getClip();
			cancionFondo.open(AudioSystem.getAudioInputStream(new File(MUSICA_INICIO)));
			cancionFondo.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		juego = new Juego();
		try {
			juego.recuperarJugadores(Juego.DIREC_JUGADORES);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error al recuperar jugadores");
		}
		setTitle("Space War");
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("img/icono.png");
		if (icon.getIconWidth() != -1) {
			setIconImage(icon.getImage());
		}
		panelInicio = new PanelInicio(this);
		add(panelInicio, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	/**
	 * Getter de la lista de bonus del juego<br>
	 * @return un arrayList de la lista de bonus del juego
	 */
	public ArrayList<Bonificacion> getBonus() {
		return juego.getBonus();
	}
	/**
	 * Getter de la nave del juego<br>
	 * @return el atributo nave de la clase Juego 
	 */
	public Nave getNave() {
		return juego.getNave();
	}
	/**
	 * Getter de la lista de pelotas del juego<br>
	 * @return un arrayList de la lista de pelotas del juego
	 */
	public ArrayList<Pelota> getPelotas() {
		return juego.getPelotas();
	}
	/**
	 * Getter del estado del juego<br>
	 * @return el estado del juego. true: si está activo, false: si está inactivo.
	 */
	public boolean isJugando() {
		return juego.isJugando();
	}
	/**
	 * Getter de la lista de jugadores que han sido registrados en el juego<br>
	 * @return un arrayList de la lista de jugadores que se han registrado en el juego
	 */
	public ArrayList<Jugador> getJugadores() {
		return juego.toArrayListJugador();
	}
	/**
	 * Getter de la lista de decoraciones del juego<br>
	 * @return un arrayList de la lista decoraciones del juego
	 */
	public ArrayList<Decoracion> getPrimeraDeco() {
		return juego.darDecoraciones();
	}
	/**
	 * Inicia una partida, dependiendo si es nueva o la ultima que se ha guardado.<br>
	 * Muestra el panel de juego e inicia el juego.<br>
	 * <b>pre:</b>juego ha sido inicializado<br>
	 * <b>post:</b>panelJuego ha sido inicializado<br>
	 * <b>post:</b>Se ha iniciado una nueva partida<br>
	 * <b>post:</b>Se ha cargado la última partida guardada<br>
	 * <b>post:</b>Se lanza Escepción si se intanta inciar una partida nueva con un nombre que 
	 * ya se ha guardado antes<br>
	 * @param cargado Estado del juego true: si es una partida guardada, false: si es una partida nueva
	 */
	public void iniciarPartida(boolean cargado) {
		try {
			juego.iniciarJuego(cargado);
			remove(panelInicio);
			cancionFondo.stop();
			panelJuego = new PanelJuego(this);
			add(panelJuego, BorderLayout.CENTER);
			pack();
			setLocationRelativeTo(null);
			moverNave();
			generarPelotas();
			generarBonificaciones();
		} catch (JugadorRepetidoException e) {
			JOptionPane.showMessageDialog(this, "El jugador con ese nombre ya existe");
		}
	}
	/**
	 * Se encarga de mostrar los datos de la partida en la pantalla<br>
	 * <b>pre:</b>Se ha inicialiado el panelJuego<br>
	 * <b>post:</b>Se muestran los datos de la partida actual: nickname, puntos,
	 * nivel y vidas<br>
	 */
	public void mostrarDatos() {
		panelJuego.setJugador(juego.getJugador().getNickname());
		panelJuego.setPuntos("" + juego.getJugador().getPuntaje());
		panelJuego.setNivel("" + juego.getJugador().getNivel());
		panelJuego.setVidas("" + juego.getNave().getVidas());
	}
	/**
	 * Inicia todos los hilos relacionados con el juego y el movimiento de la nave<br>
	 * <b>post:</b>El hilo del juego ha empezado<br>
	 * <b>post:</b>El hilo de la nave ha empezado<br>
	 * <b>post:</b>EL hilo del proyectil ha empezado<br>
	 * <b>post:</b>El hilo invulnerabilidad ha empezado<br>
	 */
	public void moverNave() {
		hI = new HiloInvulnerabilidad(this, getNave());
		hJ = new HiloJuego(this, juego);
		hN = new HiloNave(this, getNave());
		hPro = new HiloProyectil(this, getNave().getProyectil());
		hJ.start();
		hN.start();
		hI.start();
		hPro.start();
	}
	/**
	 * Inicia el hilo del movimiento de las pelotas<br>
	 * <b>post:</b>El hilo de las pelotas ha empezado<br>
	 */
	public void generarPelotas() {
		hPel = new HiloPelotas(this);
		hPel.start();
	}
	/**
	 * Inicia el hilo que genera las bonificaciones<br>
	 * <b>post:</b>El hilo que genera las bonificaciones ha empezado<br>
	 */
	public void generarBonificaciones() {
		hB = new HiloBonus(this, juego);
		hB.start();
	}
	/**
	 * Dispara un proyectil a la posicion x,y<br>
	 * <b>pre:</b>Se ha inicializado el atributo juego<br>
	 * <b>post:</b>Se ha disparado un proyectil a la posicion x,y de la pantalla<br>
	 * @param x Posición en x donde debe llegar el proyectil. x!=null, x debe ser positivo.
	 * @param y Posición en y donde debe llegar el proyectil. y!=null, y debe ser positivo.
	 */
	public void dispararProyectil(int x, int y) {
		juego.disparar(x, y);
	}	
	/**
	 * Método que se encarga de recibir el evento de la tecla presionada<br>
	 * <b>pre:</b>Se ha inicializado el atributo juego<br>
	 * <b>post:</b>Se ha realizado la acción correspondiente a la tecla presionada 
	 * solo si es: W,A,S,D o G<br>
	 * <b>post:</b>Si ha ocurrido algún error al guardar el juego, al presionar la tecla G
	 * se atrapa la excepción lanzada<br>
	 * @param e Evento de la tecla presionada. e!=null
	 */
	public void keyPressed(KeyEvent e) {
		try {
			juego.keyPressed(e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Método que se encarga de recibir el evento de la tecla que se ha dejado de presionar.<br>
	 * <b>pre:</b>Se ha inicializado el atributo juego<br>
	 * <b>post:</b>Se ha realizado la acción correspondiente a la tecla que se ha dejado de presionar 
	 * solo si es: W,A,S,D<br>
	 * @param e Evento de la tecla presionada. e!=null
	 */
	public void keyReleased(KeyEvent e) {
		juego.keyReleased(e);
	}
	/**
	 * Método que se encarga de repintar el tablero.<br>
	 * <b>pre:</b>Se ha repintado el juego<br>
	 */
	public void refrescarTablero() {
		panelJuego.repaint();
	}
	/**
	 * Método que recibe el nuevo nickname del jugador.<br>
	 * <b>pre:</b> Se ha inicializado el atributo juego<br>
	 * <b>post:</b> Ha iniciado una partida nueva con ese nickname<br>
	 */
	public void nuevaPartida() {
		String nick = JOptionPane.showInputDialog("Nuevo Jugador: ");
		if (nick != null) {
			if (nick.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe ingresar un nombre", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				juego.getJugador().setNickname(nick);
				iniciarPartida(false);
			}
		}
	}
	/**
	 * Se encarga de ordenar la lista de jugadores de acuerdo al tipo de ordenamiento y al criterio dado
	 * <b>pre:</b>Se ha inicializado el atributo ranking<br>
	 * <b>pre:</b>Se ha inicializado el atributo juego<br>
	 * <b>post:</b>Se ha actualizado la lista de jugadores de acuerdo al tipo de ordenamiento y al 
	 * criterio dado<br>
	 * @param tipo Tipo de ordenamiento. t!=null, t debe estar entre 1-4.
	 * @param criterio Criterio de ordenamiento. criterio!=null, criterio==1 || criterio==2.
	 */
	public void mostrarTablaOrdenada(int tipo, int criterio) {
		switch (tipo) {
		case 1:
			// Nombre
			if (criterio == 1)
				ranking.actualizarLista(juego.ordenarNombreAscendente());
			else
				ranking.actualizarLista(juego.ordenarNombreDescendente());
			break;
		case 2:
			if (criterio == 1)
				ranking.actualizarLista(juego.ordenarPuntajeAscendente());
			else
				ranking.actualizarLista(juego.ordenarPuntajeDescendente());
			break;
		case 3:
			if (criterio == 1)
				ranking.actualizarLista(juego.ordenarNivelAscendente());
			else
				ranking.actualizarLista(juego.ordenarNivelDescendente());
			break;
		}
	}
	/**
	 * Método que dirige la busqueda de acuerdo al criterio seleccionado por el usuario.<br>
	 * <b>pre:</b>Se ha inicializado el atributo ranking.
	 * <b>post:</b> Se llama al método buscarNombre
	 * <b>post:</b> Se llama al método buscarPuntaje
	 */
	public void buscar() {
		int respuesta = JOptionPane.showOptionDialog(ranking, "Escoge el criterio de busqueda", "Buscar",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Nombre", "Puntaje" },
				null);
		if (respuesta == JOptionPane.YES_OPTION) {
			buscarNombre();
		} else if (respuesta == JOptionPane.NO_OPTION) {
			buscarPuntaje();
		}
	}
	/**
	 * Busca el puntaje ingresado por el usuario y muestra los datos del jugador que tiene el puntaje ingresado<br>
	 * <b>pre:</b>El atributo ranking se ha sido inicializado<br>
	 * <b>pre:</b>El atributo juego se ha inicializado<br>
	 * <b>post:</b> Busca el puntaje ingresado por el usuario
	 * <b>post:</b> Muestra los datos del jugador que tiene dicho puntaje
	 * <b>post:</b> Si no existe ese puntaje lanza un mensaje de alerta.  
	 */
	public void buscarPuntaje() {
		try {
			String ans = JOptionPane.showInputDialog(ranking, "Ingresa el puntaje a buscar");
			if (ans != null) {
				int puntos = Integer.parseInt(ans);
				String nombre = juego.buscarJugadorPuntos(puntos).getNickname();
				JOptionPane.showMessageDialog(ranking, nombre + " tiene " + puntos + " puntos");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(ranking, "Ingrese un número valido", "Error", JOptionPane.WARNING_MESSAGE);
		} catch (PuntajeNoExisteException e) {
			JOptionPane.showMessageDialog(ranking, "No existe un jugador con ese puntaje", "Not Found",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	/**
	 * Busca el nombre ingresado por el usuario y muestra los datos del jugador que tiene el nombre ingresado<br>
	 * <b>pre:</b>El atributo ranking se ha sido inicializado<br>
	 * <b>pre:</b>El atributo juego se ha inicializado<br>
	 * <b>post:</b> Busca el nombre ingresado por el usuario
	 * <b>post:</b> Muestra los datos del jugador que tiene dicho nombre
	 * <b>post:</b> Si no existe ese nombre lanza un mensaje de alerta.  
	 */
	public void buscarNombre() {
		try {
			String nombre = JOptionPane.showInputDialog(ranking, "Ingresa el nombre a buscar");
			if (nombre != null) {
				Jugador j = juego.buscarJugadorNombre(nombre);
				JOptionPane.showMessageDialog(ranking,
						nombre + " tiene " + j.getPuntaje() + " puntos, y esta en el nivel " + j.getNivel());
			}
		} catch (NombreNoExisteException e) {
			JOptionPane.showMessageDialog(ranking, "No existe un jugador con ese nombre", "Not Found",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	/**
	 * Deja de mostrar el juego y vuelve al menú de inicio<br>
	 * <b>pre:</b>El atributo juego ha sido inicializado<br>
	 * <b>post:</b>El paneljuego se ha eliminado de la pantalla<br>
	 * <b>post:</b>Se muestra el menú de inicio del juego<br>
	 */
	public void mostrarInicio() {
		juego = new Juego();
		try {
			juego.recuperarJugadores(Juego.DIREC_JUGADORES);
		} catch (ClassNotFoundException | IOException e) {

		}
		try {
			cancionFondo = AudioSystem.getClip();
			cancionFondo.open(AudioSystem.getAudioInputStream(new File(MUSICA_INICIO)));
			cancionFondo.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		remove(panelJuego);
		panelInicio = new PanelInicio(this);
		add(panelInicio, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	/**
	 * Abre el dialogo de ranking.<br>
	 * <b>post:</b>Inicializada el atributo ranking.<br>
	 * <b>post:</b>Abre el dialogo de ranking<br>
	 */
	public void abrirRanking() {
		ranking = new DialogRanking(this);
		ranking.actualizarLista(juego.toArrayListJugador());
	}
	/**
	 * Cierra el dialogo ranking.<br>
	 * <b>post:</b>El dialogo ranking ha sido cerrado<br>
	 */
	public void cerrarRanking() {
		ranking = null;
	}
	/**
	 * Carga la última partida guardada en el juego.<br>
	 * <b>pre:</b>El atributo juego ha sido inicializado.<br>
	 * <b>post:</b>Se ha cargado la última partida guardada y empieza el juego desde donde se guardó
	 * <b>post:</b>Si ocurre un error al momento de recuperar la partida se muestra un mensaje de alerta
	 */
	public void cargarDatos() {
		try {
			juego.cargarDatos(Juego.NOM_DATOS);
			juego.cargarPartida();
			JOptionPane.showMessageDialog(this, "El juego se ha cargado correctamente");
			iniciarPartida(true);
			mostrarDatos();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "No se ha encontrado una partida previa", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	/**
	 * Hilo principal de la aplicación.<br>
	 * <b>post:</b>Crea la ventana principal<br>
	 * @param args Parámetro del método main 
	 */
	public static void main(String[] args) {
		Ventana v = new Ventana();
	}
}