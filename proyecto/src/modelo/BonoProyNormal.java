package modelo;
/**
 * BonoProyNormal, esta bonificiacion se encarga de definir el disparo al normal
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class BonoProyNormal extends Bonificacion{
	// Constante
	/**
	 * Este String contiene la ruta del directorio donde se ubica la imagen
	 */
	public static String DIREC = "img/bonus/proyectil_n.png";
	// Constructor
	/**
	 * Constructor, el constructor se encar de definir la imagen de la bonificacion
	 */
	public BonoProyNormal() {
		super();
		setImagen(DIREC);
	}
}
