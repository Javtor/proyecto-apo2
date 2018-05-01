package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

import modelo.Juego;
import modelo.Pelota;

public class PanelJuego extends JPanel implements KeyListener{

	private Ventana principal;
	
	public PanelJuego(Ventana p) {
		principal = p;
		setPreferredSize(new Dimension(Juego.ANCHO, Juego.ALTO));
		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Image bg = new ImageIcon("img/bg.jpg").getImage();
		g2.drawImage(bg,0,0,Juego.ANCHO, Juego.ALTO,null);
		//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, Juego.ANCHO, Juego.ALTO);
		dibujarNave(g2);
		dibujarPelotas(g2);
		dibujarBonus(g2);
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
			Image img = a.get(i).getImagen();
			int x = a.get(i).getX();
			int y = a.get(i).getY();				
			g2.drawImage(img, x, y, null);
		}
		
	}

	public void dibujarBonus(Graphics2D g2) {
		if (principal.getBonus()!=null) {
			Image img = principal.getBonus().getImagen();
			int x = principal.getBonus().getX();
			int y = principal.getBonus().getY();
			g2.drawImage(img, x, y , null);
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
	
}
