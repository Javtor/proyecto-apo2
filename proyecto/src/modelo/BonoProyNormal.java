package modelo;
/**
 * BonoProyNormal, esta bonificiacion se encarga de definir el disparo al normal
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class BonoProyNormal extends Bonificacion{
	// Constante
	/**
	 * La ruta del directorio donde se ubica la imagen de la bonificación del proyectil normal. 
	 */
	public static String DIREC = "./img/bonus/proyectil_n.png";
	// Constructor
	/**
	 * Constructor, el constructor se encarga de definir la imagen de la bonificacion.
	 */
	public BonoProyNormal() {
		super();
		setImagen(DIREC);
	}
}
