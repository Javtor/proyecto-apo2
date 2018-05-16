package modelo;
/**
 * Interfaz Disparable
 * Esta interfaz es implementada por los objetos Sprite que puedan ser disparados por otros.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public interface Disparable {
	/**
	 * M�todo que dispara el objeto desde una posicion (x,y) a una posicion (x2,y2)
	 * @param x Posici�n en x inicial del objeto Disparable. x!=null, x>=0
	 * @param y Posici�n en y inicial del objeto Disparable. y!=null, y>=0
	 * @param x2 Posici�n en x final del objeto Disparable. x2!=null, x2>=0
	 * @param y2 Posici�n en y final del objeto Disparable. y2!=null, y2>=0 
	 */
	public void disparar(int x, int y, int x2, int y2);

}
