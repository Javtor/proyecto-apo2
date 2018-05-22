package modelo;
/**
 * Interfaz Disparable
 * Esta interfaz es implementada por los objetos Sprite que puedan ser disparados por otros.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public interface Disparable {
	/**
	 * Método que dispara el objeto desde una posicion (x,y) a una posicion (x2,y2)<br>
	 * @param x Posición en x inicial del objeto Disparable. x!=null, x debe ser mayor o igual a 0
	 * @param y Posición en y inicial del objeto Disparable. y!=null, y debe ser mayor o igual a 0
	 * @param x2 Posición en x final del objeto Disparable. x2!=null, x2 debe ser mayor o igual a 0
	 * @param y2 Posición en y final del objeto Disparable. y2!=null, y2 debe ser mayor o igual a 0 
	 */
	public void disparar(int x, int y, int x2, int y2);

}
