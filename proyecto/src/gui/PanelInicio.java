package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.Juego;

public class PanelInicio extends JPanel implements ActionListener {
	
	public static final String FONDO = "img/Fondo.jpeg";
	public static final String NUEVO = "Nuevo";
	public static final String CARGAR= "Cargar";
	public static final String RANKING = "Ranking";
	public static final String SALIR = "Salir";
	
	private JButton butnuevo;
	private JButton butcargar;
	private JButton butranking;
	private JButton butsalir;
	
	private Ventana principal;	

	public PanelInicio(Ventana principal) {
		
		this.principal = principal;
		
		setPreferredSize(new Dimension(Juego.ANCHO, Juego.ALTO));
		setFocusable(true);
		setVisible(true);
		
		butnuevo = new JButton ("Iniciar nueva partida");
		butnuevo.setActionCommand(NUEVO);
		butnuevo.addActionListener(this);
		butnuevo.setBackground(Color.LIGHT_GRAY);
		
		butcargar = new JButton ("Cargar partida");
		butcargar.setActionCommand(CARGAR);
		butcargar.addActionListener(this);
		butcargar.setBackground(Color.LIGHT_GRAY);
		
		butranking = new JButton ("Ranking");
		butranking.setActionCommand(RANKING);
		butranking.addActionListener(this);
		butranking.setBackground(Color.LIGHT_GRAY);
		
		butsalir = new JButton ("Salir");
		butsalir.setActionCommand(SALIR);
		butsalir.addActionListener(this);
		butsalir.setBackground(Color.LIGHT_GRAY);
		
		setLayout(new GridLayout(13,3));
		
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(butnuevo);
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(butcargar);
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(butranking);
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(butsalir);
		add(new JLabel(""));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals(NUEVO)) {
			principal.nuevaPartida();
		}
		if (comando.equals(CARGAR)) {
			
		}
		if (comando.equals(RANKING)) {
			principal.abrirRanking();
		}
		if (comando.equals(SALIR)) {
			principal.dispose();
		}
		
	}
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Image bg = new ImageIcon(FONDO).getImage();
		g2.drawImage(bg,0,0,Juego.ANCHO, Juego.ALTO,null);
	}
	
}
