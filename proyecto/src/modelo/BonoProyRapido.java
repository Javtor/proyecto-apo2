package modelo;
/**
 * BonoProyRapido, esta clase es una de la bonificaciones del juego y se
 * encarga d edefinir la imagen de la bonificacion
 * @author  Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class BonoProyRapido extends Bonificacion {
	// Constante
	/**
	 * Este String contiene la ruta del directorio donde se ubica la imagen
	 */
	public static String DIREC = "./img/bonus/proyectil_r.png";
	// Constructor
	/**
	 * El constructor se encarga de definir la imagen de la bonificacion y su tipo
	 */
	public BonoProyRapido() {
		super();
		setImagen(DIREC);
	}
}
