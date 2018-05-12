package modelo;

public class JugadorRepetidoException extends Exception {
	
	private Jugador j;

	public JugadorRepetidoException(Jugador j) {
		super();
		this.j = j;
	}
	
	public Jugador getJugador() {
		return j;
	}
}
