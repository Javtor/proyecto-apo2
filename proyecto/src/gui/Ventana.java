package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hilos.HiloInvulnerabilidad;
import hilos.HiloBonus;
import hilos.HiloJuego;
import hilos.HiloNave;
import hilos.HiloPelotas;
import hilos.HiloProyectil;
import modelo.Bonificacion;
import modelo.Juego;
import modelo.Jugador;
import modelo.Nave;
import modelo.Pelota;

public class Ventana extends JFrame {

	private PanelJuego panelJuego;
	private PanelInicio panelInicio;
	private Juego juego;

	private HiloBonus hB;
	private HiloJuego hJ;
	private HiloNave hN;
	private HiloInvulnerabilidad hI;
	private HiloPelotas hPel;
	private HiloProyectil hPro;

	public Ventana() {
		juego = new Juego();

		setTitle("Space War");
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelInicio = new PanelInicio(this);
		add(panelInicio, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	

	public void iniciarPartida() {
		juego.iniciarNivel();
		remove(panelInicio);
		panelJuego = new PanelJuego(this);
		add(panelJuego, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);

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
		hPro = new HiloProyectil(this, getNave().getProyectil());

		hJ.start();
		hN.start();
		hI.start();
		hPro.start();
	}

	public void generarPelotas() {
		hPel = new HiloPelotas(this, getPelotas());
		hPel.start();
	}

	public void generarBonificaciones() {
		hB = new HiloBonus(this, juego);
		hB.start();
	}

	public void dispararProyectil(int x, int y) {
		juego.disparar(x, y);
	}

	public Nave getNave() {
		return juego.getNave();
	}

	public ArrayList<Pelota> getPelotas() {
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

	public void nuevaPartida() {
		juego = new Juego();
		registrarNickname();	
	}
	
	public void registrarNickname() {
			String nick = JOptionPane.showInputDialog("Nuevo Jugador: ");
			if (nick != null) {
				if (nick.equals("")) {
					JOptionPane.showMessageDialog(this, "Debe ingresar un nombre", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					juego.getJugador().setNickname(nick);
					iniciarPartida();
				} 
			}
	}
	
	public void mostrarInicio() {
		remove(panelJuego);
		panelInicio = new PanelInicio(this);
		add(panelInicio, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void abrirRanking() {
//		ranking = new DialogRanking(this);
	}
	
	public ArrayList<Jugador> getJugadores(){
		return juego.toArrayListJugador();
	}

	public static void main(String[] args) {
		Ventana v = new Ventana();
	}
	
}