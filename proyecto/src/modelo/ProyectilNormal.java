package modelo;

import java.io.File;
/**
 * Clase ProyectilNormal
 * Esta clase sirve de plantilla para crear proyectiles de tipo normal 
 * que serán usados para eliminar las pelotas del juego.
 * Estos proyectiles son un punto medio entre los proyectiles fuertes y los rápidos
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class ProyectilNormal extends Proyectil{
	/**
	 * Velocidad base del proyectil normal 
	 */
	public static final int VELOCIDAD = 20;
	/**
	 * Daño base del proyectil normal
	 */
	public static final int DANIO = 3;
	/**
	 * Ruta de la imagen del proyectil normal 
	 */
	public static final String IMG = "./img" + File.separator + "proyectil_n.png";
	//Constructor
	/**
	 * Constructor del proyectil normal
	 * Asigna su daño, velocidad e imagen al valor de las constantes declaradas en esta clase 
	 */
	public ProyectilNormal() {
		super();
		setDanio(DANIO);
		setVelocidad(VELOCIDAD);
		setImagen(IMG);
	}

}