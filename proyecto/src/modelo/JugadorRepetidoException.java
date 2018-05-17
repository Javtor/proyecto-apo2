package modelo;
/**
 * JugadorRepetidoException, es una excepcion que se encarga de controlar el fallo si existe un jugador repetido
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 *
 */
public class JugadorRepetidoException extends Exception {
	// Atributo
	/**
	 * Este atributo contiene el objeto Jugador
	 */
	private Jugador j;
	// Constructor
	/**
	 *  Se encarga de crear la excepcion, y modifica el atributo j
	 * @param j
	 * 		Objeto del tipo Jugador
	 */
	public JugadorRepetidoException(Jugador j) {
		super();
		this.j = j;
	}
	/**
	 * Este metodo se encarga de dar el objeto del tipo Jugador almacenado  en el
	 * atributo j
	 * @return Retorna un Objto del tipo Jugador
	 */
	public Jugador getJugador() {
		return j;
	}
}
