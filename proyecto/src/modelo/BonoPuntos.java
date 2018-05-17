package modelo;
/**
 * Clase BonoPuntos, es una de los tipos de bonificacion; esta se encarga de 
 * definir la imagen de la bonificacion de bonus vida
 * @author  Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class BonoPuntos extends Bonificacion{
	// Constante
	/**
	 * Es el string que contiene la ruta del directorio que contiene la imagen
	 */
	public static String DIREC = "./img/bonus/bonuspuntos.png";
	// Constructor
	/**
	 * El Constructor de la clase se encarga de definir la imagen del bono y su tipo
	 */
	public BonoPuntos() {
		super();
		setImagen(DIREC);
	}
}
