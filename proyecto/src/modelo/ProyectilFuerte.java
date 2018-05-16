package modelo;

import java.io.File;
/**
 * Clase ProyectilFuerte
 * Esta clase sirve de plantilla para crear proyectiles de tipo fuerte 
 * que serán usados para eliminar las pelotas del juego.
 * La particularidad de estos proyectiles es que tienen más daño que los otros pero son menos veloces.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class ProyectilFuerte extends Proyectil{
	/**
	 * Velocidad base del proyectil fuerte (Es mas lento que los demás)
	 */
	public static final int VELOCIDAD = 15;
	/**
	 * Daño del proyectil fuerte (Es el que más daño tiene)
	 */
	public static final int DANIO = 5;
	/**
	 * Ruta de la imagen del proyectil fuerte 
	 */
	public static final String IMG = "img" + File.separator + "proyectil_f.png";
	//Constructor
	/**
	 * Constructor del proyectil fuerte
	 * Asigna su daño, velocidad e imagen al valor de las constantes declaradas en esta clase 
	 */
	public ProyectilFuerte() {
		super();
		setDanio(DANIO);
		setVelocidad(VELOCIDAD);
		setImagen(IMG);
	}

}