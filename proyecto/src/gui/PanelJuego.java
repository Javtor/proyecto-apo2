package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import modelo.Bonificacion;
import modelo.Decoracion;
import modelo.Juego;
import modelo.Pelota;
import modelo.Proyectil;
/**
 * Clase PanelJuego
 * Encargado de dar toda la imagen de donde se desarrolla el juego. 
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class PanelJuego extends JPanel implements KeyListener, MouseListener {
	/**
	 * Constante de la ruta del letrero de GameOver
	 */
	public static final String GAME_OVER = "./img" + File.separator + "GameOver.jpg";
	/**
	 * Constante de la ruta del fondo del juego. 
	 */
	public static final String BG_IMG = "./img/bg.jpg";
	/**
	 * Asociación con la ventana principal.
	 */
	private Ventana principal;
	/**
	 * Nombre del jugador actual.
	 */
	private String nombre;
	/**
	 * Puntos de la partida actual.
	 */
	private String puntos;
	/**
	 * Nivel de la partida actual
	 */
	private String nivel;
	/**
	 * Vidas de la partida actual 
	 */
	private String vidas;
	/**
	 * Constructor de la clase PanelJuego
	 * Inicializa la parte estética del panelJuego
	 * <b>post:</b>Inicializa los atributos nombre, puntos, nivel y vidas<br>
	 * @param p Asociación con la ventana principal de la interfaz. p!=null.
	 */
	public PanelJuego(Ventana p) {
		principal = p;
		setPreferredSize(new Dimension(Juego.ANCHO, Juego.ALTO + 40));
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		nombre = "";
		puntos = "";
		nivel = "";
		vidas = "";
	}
	/**
	 * Dependiendo si el juego está o no activo dibuja el juego como tal, 
	 * o la pantalla del GameOver.<br>
	 * <b>pre:</b>Se ha inicializado el atirbuto principal<br>
	 * <b>post:</b>Se ha pintado un juego en la pantalla o un GameOver<br>
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		if ((principal.isJugando()))
			dibujarJuego(g2);
		else 
			dibujarFin(g2);
	}
	/**
	 * Se encarga de dibujar los distintos elementos de la pantalla juego<br>
	 * <b>post:</b>Dibuja una luna en la pantalla<br>
	 * <b>post:</b>Sibuja las decoraciones de la pantalla<br>
	 * <b>post:</b>Dibuja los bonus activos<br>
	 * <b>post:</b>Dibuja el proyectil visible<br>
	 * <b>post:</b>Dibuja la nave usada<br>
	 * <b>post:</b>Dibuja las pelotas de la pantalla<br>
	 * <b>post:</b>Dibuja los datos del jugador (partida actual)<br>
	 * @param g2 Graphics 2D para dibujar. g2!=null.
	 */
	public void dibujarJuego(Graphics2D g2) {
		Image bg = new ImageIcon(BG_IMG).getImage();
		g2.drawImage(bg, 0, 0, Juego.ANCHO, Juego.ALTO, null);
		dibujarLuna(g2);
		dibujarDecoracion(g2);
		dibujarBonus(g2);
		dibujarProyectil(g2);
		dibujarNave(g2);
		dibujarPelotas(g2);
		dibujarDatos(g2);
	}
	/**
	 * Dibuja una luna con sus respectivos cráteres.<br>
	 * <b>post:</b>Se ha dibujado una luna con sus respectivos cráteres<br> 
	 * @param g2 Graphics 2D para dibujar. g2!=null.
	 */
	public void dibujarLuna(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		g2.fillOval(50, 50, 80, 80);
		g2.setColor(Color.DARK_GRAY);
		g2.fillOval(60, 60, 20, 20);
		g2.fillOval(90, 60, 15, 15);
		g2.fillOval(95, 90, 30, 30);
		g2.fillOval(60, 90, 12, 12);
	}
	/**
	 * Dibuja los datos de la partida actual<br>
	 * <b>post:</b>Se han dibujado los datos de la partida actual<br>
	 * @param g2 Graphics 2D para dibujar. g2!=null.
	 */
	public void dibujarDatos(Graphics2D g2) {
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(0, Juego.ALTO, Juego.ANCHO, 40);
		g2.setFont(new Font("Calibri", Font.PLAIN, 16));
		g2.setColor(Color.DARK_GRAY);
		g2.drawString("Jugador: " + nombre, 5, Juego.ALTO + 25);
		g2.drawString("Puntos: " + puntos, 200, Juego.ALTO + 25);
		g2.drawString("Nivel: " + nivel, 400, Juego.ALTO + 25);
		g2.drawString("Vidas: " + vidas, 600, Juego.ALTO + 25);
	}
	/**
	 * Dibuja las decoraciones de la pantalla<br>
	 * <b>post:</b>Dibuja las decoraciones de la pantalla<br>
	 * @param g2 Graphics 2D para dibujar. g2!=null.
	 */
	public void dibujarDecoracion(Graphics2D g2) {
		ArrayList<Decoracion> a = principal.getPrimeraDeco();
		for (int i = 0; i < a.size(); i++) {
			Image img = a.get(i).getImagen();
			int x = a.get(i).getX();
			int y = a.get(i).getY();
			g2.drawImage(img, x, y, null);
		}
	}
	/**
	 * Dibuja el letrero de GameOver de la partida<br>
	 * <b>post:</b>Se ha dibujado un letrero de GameOver<br>
	 * @param g Graphics 2D para dibujar. g!=null.
	 */
	public void dibujarFin(Graphics2D g) {
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, Juego.ANCHO, Juego.ALTO);
		ImageIcon ii = new ImageIcon(GAME_OVER);
		g.drawImage(ii.getImage(), 0, 0, Juego.ANCHO, Juego.ALTO, null);
		g.setColor(Color.BLACK);
		g.fillRect(0, Juego.ALTO, Juego.ANCHO, 40);
	}
	/**
	 * Dibuja la nave de la partida.<br>
	 * <b>post:</b>Se ha dibujado la nave de la partida actual. <br>
	 * @param g2  Graphics 2D para dibujar. g2!=null.
	 */
	public void dibujarNave(Graphics2D g2) {
		Image img = principal.getNave().getImagen();
		int x = principal.getNave().getX();
		int y = principal.getNave().getY();
		g2.drawImage(img, x, y, null);
	}
	/**
	 * Dibuja las pelotas activas en la pantalla <br>
	 * <b>post:</b>Se han dibujado las pelotas activas del nivel<br>
	 * @param g2  Graphics 2D para dibujar. g2!=null.
	 */
	public void dibujarPelotas(Graphics2D g2) {
		ArrayList<Pelota> a = principal.getPelotas();
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).esVisible()) {
				Image img = a.get(i).getImagen();
				int x = a.get(i).getX();
				int y = a.get(i).getY();
				g2.drawImage(img, x, y, null);
			}
		}
	}
	/**
	 * Dibuja los bonus activos de la partida<br>
	 * <b>post:</b>Se han dibujado los bonus activos de la partida<br>
	 * @param g2  Graphics 2D para dibujar. g2!=null.
	 */
	public void dibujarBonus(Graphics2D g2) {
		ArrayList<Bonificacion> b = principal.getBonus();
		for (int i = 0; i < b.size(); i++) {
			if (b.get(i).esVisible()) {
				Image img = b.get(i).getImagen();
				int x = b.get(i).getX();
				int y = b.get(i).getY();
				g2.drawImage(img, x, y, null);
			}
		}
	}
	/**
	 * Dibuja el proyectil disparado en la partida<br>
	 * <b>post:</b>Se ha dibujado el proyectil disparado<br>
	 * @param g2  Graphics 2D para dibujar. g2!=null.
	 */
	public void dibujarProyectil(Graphics2D g2) {
		Proyectil a = principal.getNave().getProyectil();
		if (a.esVisible()) {
			Image img = a.getImagen();
			int x = a.getX();
			int y = a.getY();
			g2.drawImage(img, x, y, null);
		}
	}
	/**
	 * Envia el evento obtenido por presionar una tecla<br>
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		principal.keyPressed(e);
	}
	/**
	 * Envia el evento obtenido por dejar de presionar una tecla<br>
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		principal.keyReleased(e);
	}
	/**
	 * Método implementado por la interfaz MouseListener<br>
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	/**
	 * Envia el evento obtenido por el clic el mouse para disparar el proyectil<br>
	 * <b>pre:</b>Se ha inicializado el atributo principal<br>
	 * <b>post:</b>Se ha disparado un proyectil<br>
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		principal.dispararProyectil(e.getX(), e.getY());
	}
	/**
	 * Método implementado por la interfaz MouseListener.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	/**
	 * Método implementado por la interfaz MouseListener
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		requestFocus();
	}
	/**
	 * Método implementado por la interfaz MouseListener
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	/**
	 * Método implementado por la interfaz MouseListener
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	/**
	 * Asigna el nombre del jugador al atributo nombre<br>
	 * <b>post:</b>Se ha inicializado el atributo nombre<br>
	 * @param s Nombre del jugador. s!=null, s!=""
	 */
	public void setJugador(String s) {
		nombre = s;
	}
	/**
	 * Asigna los puntos del jugador al atributo puntos<br>
	 * <b>post:</b>Se ha inicializado el atributo puntos<br>
	 * @param s Puntos del jugador. s!=null, s!=""
	 */
	public void setPuntos(String s) {
		puntos = s;
	}
	/**
	 * Asigna el nivel del jugador al atirbuto nivel<br>
	 * <b>post:</b>Se ha inicializado el atributo nivel<br>
	 * @param s Nivel del jugador. s!=null, s!=""
	 */
	public void setNivel(String s) {
		nivel = s;
	}
	/**
	 * Asigna la vidas del jugador al atirbuto vidas<br>
	 * <b>post:</b>Se ha inicializado el atributo vidas<br>
	 * @param s Vidas del jugador. s!=null, s!=""
	 */
	public void setVidas(String s) {
		vidas = s;
	}
}
