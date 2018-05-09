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

		Font font = new Font("Calibri", Font.BOLD, 14);
		
		lblJugador = new JLabel("Jugador: ");
		lblPuntos = new JLabel("Puntos: ");
		lblNivel = new JLabel("Nivel: ");
		lblVidas = new JLabel("Vidas: ");

		lblJugador.setFont(font);
		lblPuntos.setFont(font);
		lblNivel.setFont(font);
		lblVidas.setFont(font);

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
