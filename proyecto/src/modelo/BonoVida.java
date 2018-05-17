package modelo;
/**
 * Clase BonoVida es uno de los bonos, Esta clase hereda de bonificacion y se encarga
 * de definir la imagen de la bonificacion
 * @author  Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class BonoVida extends Bonificacion{
	// Constantes
	/**
	 *Este es el String que representa la ruta de la imagen de esta bonificacion
	 */
	public static String DIREC = "img/bonus/bonusvida.png";
	// Constructor
	/**
	 * Constructor que se encarga de definir la imagen de la bonificacion y el tipo
	 */
	public BonoVida() {
		super();
		setImagen(DIREC);
	}

}
