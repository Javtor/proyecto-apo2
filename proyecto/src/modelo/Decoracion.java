package modelo;
/**
 * Clase Decoracion, esta es la clase encargada de contener la informacion de los objetos que representan
 * decoraciones del juego.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class Decoracion extends Sprite{
	// Constantes
	/**
	 * IMAGENES es un arreglo de Strings que se encarga de contener la ruta de las imagenes
	 * que se van a utilizar de las decoraciones. Este sirve para que cuando se construya una
	 * decoracion esta reciba una ruta que le asiganara una imagen.
	 */
	public static final String[] IMAGENES = { "./img/marte.png", "./img/jupiter.png", "./img/mercurio.png",
			"./img/planetaazul.png" };
	/**
	 * Numero total de decoraciones que se van a crear en el  juego
	 */
	public static final int NUM_DECORACIONES = 4;
	/**
	 * Ruta que sirve para ir al siguiente objeto de la clase Decoracion
	 */
	private Decoracion siguiente;
	/**
	 * Ruta que sirve para ir al objeto anterior de la clase Deocracion
	 */
	private Decoracion anterior;
	// Constructor
	/**
	 * Constructor de la clase Decoracion, el cual se encarga de asignar la imagen disponible para la decoracion
	 * y se encarga de asignarle la ubicacion donde se ubicara en el panel de juego
	 */
	public Decoracion() {
		super(0, 0, null);
		setImagen(IMAGENES[(int)(Math.random()*4)]);
		setX((int)(Math.random()*(Juego.ANCHO-getAncho())));
		setY((int)(Math.random()*(Juego.ALTO-getAncho())));
	}
	/**
	 * Este metodo se encarga de dar el objeto del tipo Decoracion que le sigue al actual<br>
	 * <b>post:</b>Devuelve la siguiente decoración de la lista<br>
	 * @return Se retorna un objeto del tipo Decoracion
	 */
	public Decoracion darSiguiente() {
		return siguiente;
	}
	/**
	 * Este metodo se encarga de dar el objeto del tipo Decoracion que se encuntre antes del
	 * actual.<br>
	 * @return Se retorna un objeto del tipo Decoracion
	 * <b>pre:</b> No debe ser el primer objeto dentro de la lista<br>
	 */
	public Decoracion darAnterior() {
		return anterior;
	}
	/**
	 * Este metodo se encarga de modificar la ruta del objeto siguiente
	 * al mismo tiempo se implementa el objeto actual como el anterior del
	 * nuevo objeto.<br>
	 * <b> post:</b>Se crea una nueva ruta entre el objteo actual y el nuevo<br>
	 * @param s es un objeto del tipo decoracion. 
	 */
	public void setSiguiente(Decoracion s) {
		siguiente = s;
		s.anterior = this;
	}
	/**
	 * Este metodo se encarga de modificar el objeto anterior a este y se encarga de
	 * ubicar este metodo en el siguiente del nuevo.<br>
	 * @param a un objeto del tipo decoracion
	 */
	public void setAnterior(Decoracion a) {
		anterior = a;
		a.setSiguiente(this);
	}
	
}