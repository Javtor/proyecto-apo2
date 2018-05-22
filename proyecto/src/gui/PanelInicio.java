package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.Juego;
/**
 * Clase PanelInicio
 * Panel que muestra el menu principal del juego, es decir las opciones del inicio:
 * Nuevo juego, cargar juego, ir al ranking o salir del juego.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class PanelInicio extends JPanel implements ActionListener {
	/**
	 * Constante de la ruta del fondo del panel
	 */
	public static final String FONDO = "img"+File.separator+"Fondo.jpeg";
	/**
	 * Constante usada para definir la acción del nuevo juego
	 */
	public static final String NUEVO = "Nuevo";
	/**
	 * Constante usada para definir la acción de cargar la última partida guardada.
	 */
	public static final String CARGAR= "Cargar";
	/**
	 * Constante usada para definir la acción de ir al Ranking.
	 */
	public static final String RANKING = "Ranking";
	/**
	 * Constante usada para definir la acción de salir.
	 */
	public static final String SALIR = "Salir";
	/**
	 * Botón para iniciar nueva partida
	 */
	private JButton butnuevo;
	/**
	 * Botón para cargar la última partida guardada.
	 */
	private JButton butcargar;
	/**
	 * Botón para ir al DialogRanking
	 */
	private JButton butranking;
	/**
	 * Botón para salir del juego.
	 */
	private JButton butsalir;
	/**
	 * Asociación a la ventana principal de la interfaz.
	 */
	private Ventana principal;	
	/**
	 * Constructor del PanelInicio
	 * Inicializa toda la parte estética del panel
	 * <b>post:</b>Inicializa todos los atributos de la clase PanelInicio<br>
	 * @param principal Asociación con la ventana principal. principal!=null.
	 */
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
	/**
	 * Método implementado por el ActionListener<br>
	 * Dependiendo del evento recibido llama a la interfaz para que cumpla con el requerimiento<br>
	 * <b>pre:</b>El atributo principal ha sido inicializado<br>
	 * <b>post:</b>LLama a los métodos de la ventana principal que cumplen con el requerimiento
	 * dado por el evento<br>
	 * @param e Evento generado por el clic a un botón. e!=null
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(NUEVO))
			principal.nuevaPartida();
		if (comando.equals(CARGAR))
			principal.cargarDatos();
		if (comando.equals(RANKING)) 
			principal.abrirRanking();
		if (comando.equals(SALIR)) 
			principal.dispose();
	}
	/**
	 * Método que pinta el fondo del PanelInicio<br>
	 * <b>post:</b>Se ha pintado el fondo del panel inicio.<br>
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Image bg = new ImageIcon(FONDO).getImage();
		g2.drawImage(bg,0,0,Juego.ANCHO, Juego.ALTO,null);
	}
	
}
