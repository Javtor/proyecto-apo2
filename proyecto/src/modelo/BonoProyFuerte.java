package modelo;
/**
 * Clase BonoProyFuerte es uno de los bonos, Esta clase hereda de bonificacion y se encarga
 * de definir la imagen de la bonificacion
 * @author  Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class BonoProyFuerte extends Bonificacion {
	/**
	 * Constante de la ruta de la bonificación del proyectil fuerte.
	 */
	public static String DIREC = "./img/bonus/proyectil_f.png";
	/**
	 * Constructor, el constructor se encarga de definir la imagen de la bonificación.
	 */
	public BonoProyFuerte() {
		super();
		setImagen(DIREC);
	}
}
