package modelo;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * La clase Bonificacion es la super clase utilizada para modelar las bonificaciones del juego que seran utilizadas
 * por el usuario cuando este las atrape. Esta clase tiene como funcion servir como esqueleto base para las clases 
 * que heredan de la misma y asi mantener una uniformidad con pocas variaciones dentro de las mismas.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 *@version 1.0
 */
public abstract class Bonificacion extends Sprite implements Colisionable{
//	Asociaciones
	/**
	 *  Bonificación que le sigue
	 */
	private Bonificacion siguiente;
	/**
	 * 	Bonificación que le antecede 
	 */
	private Bonificacion anterior;
	/**
	 * Constructor de la superclase Bonificacion, este se encarga de aplicar las caracteristicas a la bonificacion y al mismo tiempo le envia
	 * informacion a la clase sprite para que genere toda la informacion de la bonificacion. Esta clase se encarga principalmente de ubicar la
	 * bonificacion dentro de la lista y se encarga de enviarle las coordenadas en las cuales esta ubicada en el panel de juego
	 */
	public Bonificacion() {
		super(0, 0, null);
		setX((int)(Math.random()*(Juego.ANCHO-getAncho()*2)));
		setY((int)(Math.random()*(Juego.ALTO-getAlto()*2)));
		siguiente=null;
		anterior=null;
	}
	/**
	 * Consiste en agregar la bonificacion a un ArrayList de bonificaciones <br>
	 * <b>post:</b>Se agrega la bonificacion al arreglo que contiene las otras bonificaciones y se busca agregar la siguiente, si es que existe<br>
	 * @param a Es el arreglo que contiene todas las bonificaciones
	 */
	public void aArrayList(ArrayList<Bonificacion> a) {
		a.add(this);
		if(siguiente != null) {
			siguiente.aArrayList(a);
		}
	}
	/**
	 * Este metodo retorna un objeto del tipo bonificacion que le sigue al actual<br>
	 * @return se retorna la bonificacion que le sigue a la actual, puede ser un objeto o puede ser del tipo null
	 */
	public Bonificacion getSiguiente() {
		return siguiente;
	}
	/**
	 * Este metodo se encarga de modificar la bonificacion que le sigue a la bonificacion actual y se le agrega 
	 * esta clase como anterior al objeto agregado <br>
	 * <b>post:</b> Se añade el nuevo en la variable siguiente, y se le agrega al objeto nuevo este objeto como anterior<br>
	 * @param siguiente Objeto que se recibe para reemplazar el objeto del tipo bonificacion en la variable siguiente
	 */
	public void setSiguiente(Bonificacion siguiente) {
		this.siguiente = siguiente;
		siguiente.anterior = this;
	}
	/**
	 * Se retorna un objeto del tipo bonificacion que esta antes del objeto actual<br>
	 * @return se retorna la bonificacion que esta antes de la actual
	 */
	public Bonificacion getAnterior() {
		return anterior;
	}
	/**
	 * Se elimina la bonificacion siguiente, y se reemplaza con la que le sigue<br>
	 * <b>post:</b> se elimina la bonificacion que seguia y se reemplaza con la que le seguia<br>
	 */
	public void desconectarSiguiente() {
		siguiente = siguiente.siguiente;
	}
	/**
	 * Se elimina la bonificacion anterior y se reemplaza con la anterior de la que sera eliminada<br>
	 * <b>pre:</b> Se asume que el primer objeto no es el que se busca.<br>
	 * <b>post:</b> Se elimina el objeto anterior ya que es reemplazado por su anterior.<br>
	 */
	public void desconectarAnterior() {
		anterior = anterior.anterior;
	}
	/**
	 * Este metodo sirve para verificar si el objeto del tipo bonificacion colisiona con un objeto 
	 * que implementa la interfaz Colisionable, Y cambio el valor de su estado visible<br>
	 * <b>post:</b> Se cambia a falso el valor de la variable visible<br>
	 */
	@Override
	public void colisionaCon(Colisionable c) {
		setVisible(false);
	}
	/**
	 *Este metodo verifica si existe una colision entre un objeto y la bonificacion<br>
	 * @return Se retorna un valor booleano que representa si existe una colision entre el objeto actual y el recibido por el metodo
	 */
	@Override
	public boolean hayColision(Colisionable c) {
		return getHitbox().intersects(c.getHitbox());
	}
	/**
	 * Este metodo retorna una figura rectangular que sirve como una hitbox para el objeto de bonificacion<br>
	 * @return Se retorna un objeto rectangular
	 */
	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}

}