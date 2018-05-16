package modelo;

import java.io.File;
/**
 * Clase ProyectilRapido
 * Esta clase sirve de plantilla para crear proyectiles de tipo r�pido 
 * que ser�n usados para eliminar las pelotas del juego.
 * La particularidad de estos proyectiles es que tienen menos da�o que los otros pero son m�s veloces.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class ProyectilRapido extends Proyectil{
	/**
	 * Velocidad base del proyectil r�pido (es mas veloz que los otros)
	 */
	public static final int VELOCIDAD = 25;
	/**
	 * Da�o base del proyectil r�pido (tiene menos da�o que los otros)
	 */
	public static final int DANIO = 2;
	/**
	 * Ruta de la imagen del proyectil r�pido 
	 */
	public static final String IMG = "img" + File.separator + "proyectil_r.png";
	//Constructor
	/**
	 * Constructor del proyectil r�pido
	 * Asigna su da�o, velocidad e imagen al valor de las constantes declaradas en esta clase 
	 */
	public ProyectilRapido() {
		super();
		setDanio(DANIO);
		setVelocidad(VELOCIDAD);
		setImagen(IMG);
	}

}