package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
import modelo.Juego;
import modelo.Jugador;
import modelo.Nave;
import modelo.Pelota;
import modelo.PuntajeNoExisteException;

public class Ventana extends JFrame {

	private PanelJuego panelJuego;
	private PanelInicio panelInicio;
	private DialogRanking ranking;
	private PanelDatos panelDatos;
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
		ImageIcon icon = new ImageIcon("img/icono.png");
		if(icon.getIconWidth()!=-1) {
			setIconImage(icon.getImage());
		}

		panelInicio = new PanelInicio(this);
		add(panelInicio, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void iniciarPartida() {
		juego.iniciarJuego();
		remove(panelInicio);
		panelJuego = new PanelJuego(this);
		add(panelJuego, BorderLayout.CENTER);
		panelDatos = new PanelDatos();
		add(panelDatos, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);

		moverNave();
		generarPelotas();
		generarBonificaciones();
	}
	
	public void mostrarDatos() {
		panelDatos.setJugador(juego.getJugador().getNickname());
		panelDatos.setPuntos(""+juego.getJugador().getPuntaje());
		panelDatos.setNivel(""+juego.getJugador().getNivel());
		panelDatos.setVidas(""+juego.getNave().getVidas());
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
		hPel = new HiloPelotas(this);
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
		try {
			juego.keyPressed(e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
				JOptionPane.showMessageDialog(this, "Debe ingresar un nombre", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				juego.getJugador().setNickname(nick);
				iniciarPartida();
			}
		}
	}

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
				ranking.actualizarLista(juego.ordenarPuntajeAscendente());
			break;
		case 3:
			if (criterio == 1)
				ranking.actualizarLista(juego.ordenarNivelAscendente());
			else
				ranking.actualizarLista(juego.ordenarNivelDescendente());
			break;
		}
	}

	public void buscarPuntaje() {
		try {
			int puntos = Integer.parseInt(JOptionPane.showInputDialog(ranking,"Ingrese puntaje a buscar"));
			String nombre = juego.buscarJugadorPuntos(puntos).getNickname();
			JOptionPane.showMessageDialog(ranking, nombre + " tiene "+puntos+" puntos");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(ranking, "Ingrese un número valido", "Error", JOptionPane.WARNING_MESSAGE);
		} catch (PuntajeNoExisteException e) {
			JOptionPane.showMessageDialog(ranking, "No existe un jugador con ese puntaje", "Not Found",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void mostrarInicio() {
		remove(panelJuego);
		remove(panelDatos);
		panelInicio = new PanelInicio(this);
		add(panelInicio, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void abrirRanking() {
		ranking = new DialogRanking(this);
		ranking.actualizarLista(juego.toArrayListJugador());
	}

	public ArrayList<Jugador> getJugadores() {
		return juego.toArrayListJugador();
	}

	public void cargarDatos() {
		try {
			juego.cargarPartida();
			juego.guardarDatos();
			
			panelDatos.setJugador(juego.getJugador().getNickname());
			panelDatos.setNivel(""+juego.getJugador().getNivel());
			panelDatos.setPuntos(""+juego.getJugador().getPuntaje());
			
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(this, "No se ha encontrado una partida previa", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		Ventana v = new Ventana();
	}

}