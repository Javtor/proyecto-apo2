package modelo;
/**
 * PuntajeNoExisteException es una exception que se encarga de controlar la falla 
 * si no existe un puntaje especificado
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 *
 */
public class PuntajeNoExisteException extends Exception {
	//Constructor
	/**
	 * Constructor que crea la excepcion
	 */
	public PuntajeNoExisteException() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor que crea la excepcion con un String para redefinirla
	 * @param arg0
	 * 			Es un String que contiene el puntaje no encontrado
	 */
	public PuntajeNoExisteException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
