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
import modelo.Jugador;
/**
 * Clase DialogRanking
 * Ventana de dialogo que se encarga de mostrar la lista de jugadores con su respectivo
 * nickname, puntaje y nivel.
 * Permite además ordenar la lista al criterio escogido por el usuario, o buscar un puntaje o nickname
 * guardado.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class DialogRanking extends JDialog implements ActionListener {
	/**
	 *Constante usada para definir la acción buscar
	 *Esta acción busca a un jugador a partir de un nickname o un puntaje ingresado 
	 *por el usuario.
	 */
	public static final String BUSCAR = "Buscar";
	/**
	 * Constante usada para definir la acción generar
	 * Ordena la lista de acuerdo a un criterio dado.
	 */
	public static final String GENERAR = "Generar";
	/**
	 * Lista de jugadores (nickname, puntaje y nivel)
	 */
	private JList listjugadores;
	/**
	 * Scroll que permite desplazarse por la lista.
	 */
	private JScrollPane scroll;
	/**
	 * ButtonGroup de la opcion a elegir para generar el orden
	 * Puede ser nombre, puntaje o nivel
	 */
	private ButtonGroup grupochoice;
	/**
	 * ButtonGroup de la opción criterio para generar el orden dada su opción.
	 * Puede ser ascendente o descendente.
	 */
	private ButtonGroup grupocriterio;
	/**
	 * RadioButton de la opción nombre.
	 */
	private JRadioButton rbnombre;
	/**
	 * RadioButton de la opción puntaje.
	 */
	private JRadioButton rbpuntaje;
	/**
	 * RadioButton de la opción nivel.
	 */
	private JRadioButton rbnivel;
	/**
	 * RadioButton del criterio ascendente.
	 */
	private JRadioButton rbascendente;
	/**
	 * RadioButton del criterio descendente.
	 */
	private JRadioButton rbdescendente;
	/**
	 * Botón buscar
	 */
	private JButton butbuscar;
	/**
	 * Botón generar
	 */
	private JButton butgenerar;
	/**
	 * Panel donde se encuentra la lista y el scroll. 
	 */
	private JPanel panellista;
	/**
	 * Panel donde se encuentran la opciones del ButtonGroup choices
	 */
	private JPanel panelchoices;
	/**
	 * Panel donde se encuentran los criterior del ButtonGroup criterio
	 */
	private JPanel panelbotones;
	/**
	 * Asociación a la ventana principal de la interfaz
	 */
	private Ventana menu;
	/**
	 * Constructor del DialogRanking 
	 * Se encarga de acomodar la parte estética del dialogo.
	 * <b>post:</b>Inicializa todos los atributos de la clase DialogRanking.<br>
	 * @param menu Asociación a la clase principal de la interfaz. menu!=null
	 */
	public DialogRanking(Ventana menu) {
		this.menu = menu;
		setTitle("Ranking");
		setResizable(false);
		listjugadores = new JList();
		scroll = new JScrollPane(listjugadores);
		listjugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panellista = new JPanel();
		panellista.add(scroll);
		rbnombre = new JRadioButton("Nombre");
		rbpuntaje = new JRadioButton("Puntaje");
		rbnivel = new JRadioButton("Nivel");
		grupochoice = new ButtonGroup();
		grupochoice.add(rbnombre);
		grupochoice.add(rbpuntaje);
		grupochoice.add(rbnivel);
		rbnombre.setSelected(true);
		rbascendente = new JRadioButton("Ascendente");
		rbdescendente = new JRadioButton("Descendente");
		rbascendente.setSelected(true);
		grupocriterio = new ButtonGroup();
		grupocriterio.add(rbascendente);
		grupocriterio.add(rbdescendente);
		panelchoices = new JPanel();
		panelchoices.setLayout(new GridLayout(3, 2));
		panelchoices.add(rbnombre);
		panelchoices.add(rbascendente);
		panelchoices.add(rbpuntaje);
		panelchoices.add(rbpuntaje);
		panelchoices.add(rbdescendente);
		panelchoices.add(rbnivel);
		butbuscar = new JButton("Buscar");
		butbuscar.setActionCommand(BUSCAR);
		butbuscar.addActionListener(this);
		butbuscar.setBackground(Color.LIGHT_GRAY);
		butgenerar = new JButton("Generar");
		butgenerar.setActionCommand(GENERAR);
		butgenerar.addActionListener(this);
		butgenerar.setBackground(Color.LIGHT_GRAY);
		panelbotones = new JPanel();
		panelbotones.setLayout(new GridLayout(1, 2));
		panelbotones.add(butbuscar);
		panelbotones.add(butgenerar);
		setLayout(new BorderLayout());
		add(panellista, BorderLayout.NORTH);
		add(panelchoices, BorderLayout.CENTER);
		add(panelbotones, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(menu);
		setVisible(true);
	}
	/**
	 * Método implementado por el ActionListener
	 * Recibe el evento generado y llama a los respectivos métodos que cumplen la función buscada
	 * <b>pre:</b>El atributo menu ha sido inicializado<br>
	 * <b>pre:</b>Los atributos rbnombre, rbpuntaje, rbascendente han sido inicializados<br>
	 * <b>post:</b>Si presiona el boton buscar llama al método buscar de la clase principal<br>
	 * <b>post:</b>Si presiona el boton generar, de acuerdo a la opción y al criterio dado, ordena
	 * la lista de jugadores<br>
	 * @param e Evento generado por el clic a un botón. e!=null
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(BUSCAR)) {
			menu.buscar();
		}
		if (comando.equals(GENERAR)) {
			int tipo = 0;
			if (rbnombre.isSelected())
				tipo = 1;
			else if (rbpuntaje.isSelected())
				tipo = 2;
			else
				tipo = 3;
			int criterio = 0;
			if (rbascendente.isSelected())
				criterio = 1;
			else
				criterio = 2;
			menu.mostrarTablaOrdenada(tipo, criterio);
		}
	}
	/**
	 * Método que se encarga de actualiza la lista 
	 * @param jugadores ArrayList de los jugadores que existen guardados en el juego. jugadores!=null
	 */
	@SuppressWarnings("unchecked")
	public void actualizarLista(ArrayList<Jugador> jugadores) {
		listjugadores.setListData(jugadores.toArray());
	}
	/**
	 * Se encarga de cerrar la ventana de dialogo. 
	 */
	@Override
	public void dispose(){
		menu.cerrarRanking();
		super.dispose();
	}
}
