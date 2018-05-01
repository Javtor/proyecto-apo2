package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

import modelo.Juego;

public class MenuInicio extends JFrame {
	
	private Juego juego;
	private PanelInicio panelinicio;
	
	
	public MenuInicio(){
		
		setPreferredSize(new Dimension(Juego.ANCHO, Juego.ALTO));
		juego = new Juego();		
		setLayout(new BorderLayout());
		
		setTitle("Space War");
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelinicio = new PanelInicio(this);
		add(panelinicio, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
		
	

	public static void main(String[] args) {
		MenuInicio mi = new MenuInicio();

	}


}
