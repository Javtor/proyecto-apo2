package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import modelo.Juego;

public class DialogRanking extends JDialog implements ActionListener{

	public static final String BUSCAR = "Buscar";
	public static final String GENERAR = "Generar";
	
	private JList listjugadores;
	private JScrollPane scroll;
	
	private ButtonGroup grupochoice;
	private ButtonGroup grupocriterio;
	
	private JRadioButton rbnombre;
	private JRadioButton rbpuntaje;
	private JRadioButton rbnivel;
	private JRadioButton rbascendente;
	private JRadioButton rbdescendente;
	
	private JButton butbuscar;
	private JButton butgenerar;
	private JPanel panellista;
	private JPanel panelchoices;
	private JPanel panelbotones;
	
	private Ventana menu;
	
	public DialogRanking(Ventana menu) {
		
		this.menu=menu;
		
		setTitle("Ranking");
		setSize(Juego.ANCHO, Juego.ALTO);
		setResizable(false);
		setVisible(true);
		
		listjugadores = new JList();
		scroll = new JScrollPane(listjugadores);
		
		listjugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(Juego.ANCHO,300));
		
		panellista = new JPanel();
		panellista.add(scroll);
		
		rbnombre = new JRadioButton ("Nombre");
		rbpuntaje = new JRadioButton ("Puntaje");
		rbnivel = new JRadioButton ("Nivel");
		
		grupochoice = new ButtonGroup();
		grupochoice.add(rbnombre);
		grupochoice.add(rbpuntaje);
		grupochoice.add(rbnivel);
		
		rbascendente = new JRadioButton ("Ascendente");
		rbdescendente = new JRadioButton ("Descendente");
		
		grupocriterio = new ButtonGroup();
		grupocriterio.add(rbascendente);
		grupocriterio.add(rbdescendente);
		
		panelchoices = new JPanel();
		panelchoices.setLayout(new GridLayout (3,2));
		panelchoices.add(rbnombre);
		panelchoices.add(rbascendente);
		panelchoices.add(rbpuntaje);
		panelchoices.add(rbpuntaje);
		panelchoices.add(rbdescendente);
		panelchoices.add(rbnivel);
		panelchoices.setPreferredSize(new Dimension(Juego.ANCHO,200));
		
		butbuscar = new JButton ("Buscar");
		butbuscar.setActionCommand(BUSCAR);
		butbuscar.addActionListener(this);
		butbuscar.setBackground(Color.LIGHT_GRAY);
		
		butgenerar = new JButton ("Generar");
		butgenerar.setActionCommand(GENERAR);
		butgenerar.addActionListener(this);
		butgenerar.setBackground(Color.LIGHT_GRAY);
		
		panelbotones = new JPanel ();
		panelbotones.setLayout(new GridLayout(1,2));
		panelbotones.add(butbuscar);
		panelbotones.add(butgenerar);
		panelbotones.setPreferredSize(new Dimension(Juego.ANCHO,100));
		
		setLayout(new BorderLayout());
		
		add(panellista, BorderLayout.NORTH);
		add(panelchoices, BorderLayout.CENTER);
		add(panelbotones, BorderLayout.SOUTH);
		
		listjugadores.setListData(menu.getJuego().toArrayListJugador().toArray());
		
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals(BUSCAR)) {
			buscar();
		}
		if (comando.equals(GENERAR)) {
			generar();
		}
		
	}
	
	
	public void actualizarlista(int tipo, int criterio) {
		switch (tipo) {
		case 1:
			//Nombre
				if (criterio==1)
					listjugadores.setListData(menu.getJuego().ordenarNombreAscendente().toArray());
				else
					listjugadores.setListData(menu.getJuego().ordenarNombreDescendente().toArray());
			break;
		case 2:
			if (criterio==1)
				listjugadores.setListData(menu.getJuego().ordenarPuntajeAscendente().toArray());
			else
				listjugadores.setListData(menu.getJuego().ordenarPuntajeAscendente().toArray());
			break;
		case 3:
			if (criterio==1)
				listjugadores.setListData(menu.getJuego().ordenarNivelAscendente().toArray());
			else
				listjugadores.setListData(menu.getJuego().ordenarNivelDescendente().toArray());
			break;
		}
	}
	 
	
	public void buscar() {
		try {
			int puntos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese puntaje a buscar"));
			String nombre = menu.getJuego().buscarJugadorPuntos(puntos).getNickname();
			
			if (nombre.equals(null)) {
				JOptionPane.showMessageDialog(this, "No existe un jugador con ese puntaje", "Not Found", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, nombre+" tiene ese puntaje");
			}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Ingrese un número valido", "Error", JOptionPane.WARNING_MESSAGE);
		}	
	}
	
	public void generar() {
		int tipo = 0;
		if (rbnombre.isSelected())
			tipo=1;
		else if (rbpuntaje.isSelected())
			tipo=2;
		else
			tipo=3;
		
		int criterio=0;
		if (rbascendente.isSelected())
			criterio=1;
		else
			criterio=2;
		
		actualizarlista(tipo, criterio);
			
	}
}
