package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import hilos.HiloInvulnerabilidad;
import hilos.HiloBonus;
import hilos.HiloJuego;
import hilos.HiloNave;
import hilos.HiloPelotas;
import modelo.Bonificacion;
import modelo.Juego;
import modelo.Nave;
import modelo.Pelota;

public class Ventana extends JFrame{
	
	private PanelJuego panelJuego;
	private Juego juego;
	
	private HiloBonus hB;
	private HiloJuego hJ;
	private HiloNave hN;
	private HiloInvulnerabilidad hI;
	
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
		generarPelotas();
		generarBonificaciones();
	}

	public Bonificacion getBonus() {
		return juego.getBonus();
	}

	public void moverNave() {
		hI = new HiloInvulnerabilidad(this, getNave());
		hJ = new HiloJuego(this, juego);
		hN = new HiloNave(this, getNave());
		
		hJ.start();
		hN.start();
		hI.start();
	}
	
	public void generarPelotas() {
		HiloPelotas hP = new HiloPelotas(this,getPelotas());
		hP.start();
	}
	
	public void generarBonificaciones() {
		hB = new HiloBonus (this, juego);
		hB.start();
	}
	
	public Nave getNave() {
		return juego.getNave();
	}
	
	public ArrayList<Pelota> getPelotas(){
		return juego.getPelotas();
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