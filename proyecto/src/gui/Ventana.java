package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import hilos.HiloInvulnerabilidad;
import hilos.HiloJuego;
import hilos.HiloNave;
import hilos.HiloPelotas;
import modelo.Juego;
import modelo.Nave;
import modelo.Pelota;

public class Ventana extends JFrame{
	
	private PanelJuego panelJuego;
	private Juego juego;
	
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
	}
	
	public void moverNave() {
		HiloJuego hJ = new HiloJuego(this, juego);
		HiloNave hN = new HiloNave(this, getNave());
		HiloInvulnerabilidad hI = new HiloInvulnerabilidad(this, getNave());
		hJ.start();
		hN.start();
		hI.start();
	}
	
	public void generarPelotas() {
		HiloPelotas hP = new HiloPelotas(this,getPelotas());
		hP.start();
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