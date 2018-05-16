package modelo;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.ImageIcon;
/**
 * Clase Sprite esta es la superclase que se encarga de contener todos los
 * elementos basicos de los objetos movibles, esta clase se encarga de almacenar 
 * las coordenadas del objeto, su tamaño en pixeles, si este es visible o no y la
 * la ruta que contiene la imagen
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public abstract class Sprite implements Serializable{
	// Atributos
	/**
	 * Es la coordenada del eje x
	 */
	private int x;
	/**
	 * Es la coordenada del eje Y
	 */
	private int y;
	/**
	 * Es el valor del ancho del objeto
	 */
	private int ancho;
	/**
	 * Es el valor de la altura del objeto
	 */
	private int alto;
	/**
	 * Es el valor que representa si el objeto es o no visible
	 */
	private boolean visible;
	/**
	 * Es la ruta de la imagen del objeto
	 */
	private String imagen;
	// Constructor
	/**
	 * Este es el constructor de la superclase que se encarga de 
	 * aplicar las coordenadas del objeto, la ruta de la imagen, definir la visibilidad
	 * del objeto y su altura y ancho.
	 * @param x
	 * 			Es el valor del eje X
	 * @param y
	 * 			Es el valor del eje Y
	 * @param imagen
	 * 			Es la ruta que contiene la imagen
	 */
	public Sprite(int x, int y, String imagen) {
		this.x = x;
		this.y = y;
		this.imagen = imagen;
		visible = true;
		Image img = new ImageIcon(imagen).getImage();
		ancho = img.getWidth(null);
		alto = img.getHeight(null);
	}
	/**
	 * Este metodo tiene la funcion de retornar un String
	 * @return Retorna un String que contiene la ruta de la imagen
	 */
	public Image getImagen() {
		return new ImageIcon(imagen).getImage();
	}
	/**
	 * Este metodo se encarga de definir el atributo imagen y la
	 * altura junto el ancho
	 * @param imagen
	 * 				Este es un String que contiene la ruta de una imagen
	 */
	public void setImagen(String imagen) {
		this.imagen=imagen;
		Image img = new ImageIcon(imagen).getImage();
		ancho = img.getWidth(null);
		alto = img.getHeight(null);
	}
	/**
	 * Este metodo retorna la coordenada del objeto en el eje X
	 * @return Retorna un int que representa la coordenada X del objeto
	 */
	public int getX() {
		return x;
	}
	/**
	 * Este modifica le coordenada del eje X
	 * @param x
	 * 			Este es un valor entero que sera utilizado para reemplazar
	 * 			el valor anterio
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Este metodo retorna la coordenada del eje Y
	 * @return Retorna un int que representa la coordenada del objeto en el eje Y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Este metodo modifica la coordenada del eje Y
	 * @param y
	 * 			Entero que sera la nueva coordenada del eje Y 
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Este metodo retorna el ancho del objeto
	 * @return Retorna un int que representa el ancho del objeto(imagen)
	 */
	public int getAncho() {
		return ancho;
	}
	/**
	 * Este metodo retorna la altura del objeto
	 * @return Retorna un valor entero que representa la altura del objeto(imagen)
	 */
	public int getAlto() {
		return alto;
	}
	/**
	 * Este metodo retorna si el objeto es visible o no
	 * @return Retorna un valor booleano que representa la visibilidad del 
	 * 			del objeto
	 */
	public boolean esVisible() {
		return visible;
	}
	/**
	 * Este metodo modifica el valor de la visibilidad
	 * <b> Pos: Se modifica la visibilidad del objeto
	 * @param visible
	 * 				Este valor booleano modifica la visibilidad del objeto
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}