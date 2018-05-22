package modelo;
/**
 * NombreNoExisteException, Esta excepcion se encarga de manejar la 
 * falla cuando no se encuentra un nombre especificado
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 *
 */
public class NombreNoExisteException extends Exception {
	//Constructor
	/**
	 * Se encarga de crear la Excepcion
	 */
	public NombreNoExisteException() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Se encarga de crear la excepcion, utilizando un string para modificar el mensaje.<br>
	 * @param s Es un String que contiene el nombre no ubicado
	 */
	public NombreNoExisteException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

}
