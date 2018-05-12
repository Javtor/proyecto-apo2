package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
	
	public static final String GAME_OVER = "img"+File.separator+"GameOver.jpg";

	private Ventana principal;

	public PanelJuego(Ventana p) {
		principal = p;
		setPreferredSize(new Dimension(Juego.ANCHO, Juego.ALTO));
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (principal.isJugando()) {
			Image bg = new ImageIcon("img/bg.jpg").getImage();
			g2.drawImage(bg, 0, 0, Juego.ANCHO, Juego.ALTO, null);
			dibujarDecoracion(g2);
			dibujarBonus(g2);
			dibujarProyectil(g2);
			dibujarNave(g2);
			dibujarPelotas(g2);
		} else {
			dibujarFin(g2);
		}

	}

	private void dibujarDecoracion(Graphics2D g2) {
		ArrayList<Decoracion> a=principal.getPrimeraDeco();
			for(int i=0;i<a.size();i++) {
				Image img=a.get(i).getImagen();
				int x = a.get(i).getX();
				int y = a.get(i).getY();
				g2.drawImage(img,x,y,null);
			}
	}

	public void dibujarFin(Graphics2D g) {
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, Juego.ANCHO, Juego.ALTO);
//		g.setColor(Color.WHITE);
//		g.drawString("Game Over", Juego.ANCHO/2, Juego.ALTO/2);
		ImageIcon ii = new ImageIcon(GAME_OVER);
		g.drawImage(ii.getImage(), 0, 0, Juego.ANCHO, Juego.ALTO, null);
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

}
