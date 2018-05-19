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

public class PanelJuego extends JPanel implements KeyListener, MouseListener {

	public static final String GAME_OVER = "./img" + File.separator + "GameOver.jpg";
	public static final String BG_IMG = "./img/bg.jpg";

	private Ventana principal;
	private String nombre;
	private String puntos;
	private String nivel;
	private String vidas;

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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		if ((principal.isJugando())) {
			dibujarJuego(g2);
		} else {
			dibujarFin(g2);
		}

	}

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

	public void dibujarLuna(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		g2.fillOval(50, 50, 80, 80);
		g2.setColor(Color.DARK_GRAY);
		g2.fillOval(60, 60, 20, 20);
		g2.fillOval(90, 60, 15, 15);
		g2.fillOval(95, 90, 30, 30);
		g2.fillOval(60, 90, 12, 12);
	}
	
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

	public void dibujarDecoracion(Graphics2D g2) {
		ArrayList<Decoracion> a = principal.getPrimeraDeco();
		for (int i = 0; i < a.size(); i++) {
			Image img = a.get(i).getImagen();
			int x = a.get(i).getX();
			int y = a.get(i).getY();
			g2.drawImage(img, x, y, null);
		}
	}

	public void dibujarFin(Graphics2D g) {
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, Juego.ANCHO, Juego.ALTO);
		ImageIcon ii = new ImageIcon(GAME_OVER);
		g.drawImage(ii.getImage(), 0, 0, Juego.ANCHO, Juego.ALTO, null);
		g.setColor(Color.BLACK);
		g.fillRect(0, Juego.ALTO, Juego.ANCHO, 40);
	}

	public void dibujarNave(Graphics2D g2) {
		Image img = principal.getNave().getImagen();
		int x = principal.getNave().getX();
		int y = principal.getNave().getY();
		g2.drawImage(img, x, y, null);
	}

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

	public void dibujarProyectil(Graphics2D g2) {
		Proyectil a = principal.getNave().getProyectil();
		if (a.esVisible()) {
			Image img = a.getImagen();
			int x = a.getX();
			int y = a.getY();
			g2.drawImage(img, x, y, null);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		principal.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		principal.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		principal.dispararProyectil(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		requestFocus();

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setJugador(String s) {
		nombre = s;
	}

	public void setPuntos(String s) {
		puntos = s;
	}

	public void setNivel(String s) {
		nivel = s;
	}

	public void setVidas(String s) {
		vidas = s;
	}

}
