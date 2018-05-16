package modelo;

import java.io.File;
/**
 * Clase ProyectilRapido
 * Esta clase sirve de plantilla para crear proyectiles de tipo rápido 
 * que serán usados para eliminar las pelotas del juego.
 * La particularidad de estos proyectiles es que tienen menos daño que los otros pero son más veloces.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class ProyectilRapido extends Proyectil{
	/**
	 * Velocidad base del proyectil rápido (es mas veloz que los otros)
	 */
	public static final int VELOCIDAD = 25;
	/**
	 * Daño base del proyectil rápido (tiene menos daño que los otros)
	 */
	public static final int DANIO = 2;
	/**
	 * Ruta de la imagen del proyectil rápido 
	 */
	public static final String IMG = "img" + File.separator + "proyectil_r.png";
	//Constructor
	/**
	 * Constructor del proyectil rápido
	 * Asigna su daño, velocidad e imagen al valor de las constantes declaradas en esta clase 
	 */
	public ProyectilRapido() {
		super();
		setDanio(DANIO);
		setVelocidad(VELOCIDAD);
		setImagen(IMG);
	}

}