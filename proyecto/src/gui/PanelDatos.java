package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class PanelDatos extends JPanel {

	private JLabel lblJugador;
	private JLabel lblPuntos;
	private JLabel lblNivel;
	private JLabel lblVidas;

	public PanelDatos() {
		setLayout(new GridLayout(1,3));
		TitledBorder border = new TitledBorder("Info");
		setBorder(border);

		lblJugador = new JLabel("Jugador: ");
		lblJugador.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPuntos = new JLabel("Puntos: ");
		lblPuntos.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNivel = new JLabel("Nivel: ");
		lblNivel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblVidas = new JLabel("Vidas: ");
		lblVidas.setFont(new Font("Calibri", Font.BOLD, 14));

		add(lblJugador);
		add(lblPuntos);
		add(lblNivel);
		add(lblVidas);
	}

	public void setJugador(String s) {
		lblJugador.setText("Jugador: "+s);
	}

	public void setPuntos(String s) {
		lblPuntos.setText("Puntos: "+s);
	}

	public void setNivel(String s) {
		lblNivel.setText("Nivel: "+s);
	}
	
	public void setVidas(String s) {
		lblVidas.setText("Vidas: "+s);
	}
}
