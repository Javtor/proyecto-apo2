package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import hilos.HiloBonus;
import hilos.HiloJuego;
import hilos.HiloNave;
import modelo.Juego;
import modelo.Nave;

public class Ventana extends JFrame{
	
	private PanelJuego panelJuego;
	private Juego juego;
	
	private HiloBonus hB;
	private HiloJuego hJ;
	private HiloNave hN;
	
	public Ventana() {
		juego = new Juego();
		
		setTitle("Proyecto");
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelJuego = new PanelJuego(this);
		add(panelJuego, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		moverNave();
	}
	
	public Juego getJuego() {
		return juego;
	}
	
	public void moverNave() {
		hJ = new HiloJuego(this, juego);
		hN = new HiloNave(this, getNave());
		hB = new HiloBonus (this, juego);
		hJ.start();
		hN.start();
		hB.start();
	}
	
	public Nave getNave() {
		return juego.getNave();
	}
	
	public void keyPressed(KeyEvent e) {
		juego.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
    	juego.keyReleased(e);
    }
    
    public boolean isJugando() {
    	return juego.isJugando();
    }
    
    public void refrescarTablero() {
    	panelJuego.repaint();
    }
    	
	public static void main(String[] args) {
		Ventana v = new Ventana();
	}
	
}