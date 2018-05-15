package modelo;
import java.awt.geom.Rectangle2D;
/**
 * Interfaz Colisionable
 * Esta interfaz es implementada por los objetos Sprite que pueden colisionar con otros.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public interface Colisionable {
	/**
	 * M�todo que verifica si existe colision entre un objeto colisionable y otro
	 * <b>post:</b>Si existe colisi�n entre un objeto y otro<br>
	 * @param c Objecto con el que podria colisionar. c!=null
	 * @return True si colisionaron, False si no colisionaron
	 */
	public boolean hayColision(Colisionable c);
	/**
	 * M�todo que realiza la acci�n correspondiente al objeto con el que colision�
	 * @param c Objeto con el que colision�. c!=null
	 */
	public void colisionaCon(Colisionable c);
	/**
	 * M�todo que devulve el HitBox del objeto colisionable
	 * <b>post:</b>Devuelve el HitBox del objeto colisionable<br>
	 * @return Devuelve el hitBox del objeto colisionable 
	 */
	public Rectangle2D getHitbox();

}