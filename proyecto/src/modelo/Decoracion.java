package modelo;

import java.awt.geom.Rectangle2D;

public class Decoracion extends Sprite{
	public static final String IMAGEN = "img/icono.png";
	public static final String[] IMAGENES = { "img/marte.png", "img/jupiter.png", "img/mercurio.png",
			"img/planetaazul.png" };
	public static final int NUM_DECORACIONES = 4;
	private Decoracion siguiente;
	private Decoracion anterior;

	public Decoracion() {
		super(0, 0, null);
		setImagen(IMAGENES[(int)(Math.random()*4)]);
		setX((int)(Math.random()*(Juego.ANCHO-getAncho())));
		setY((int)(Math.random()*(Juego.ALTO-getAncho())));
	}

	public Decoracion darSiguiente() {
		return siguiente;
	}

	public Decoracion darAnterior() {
		return anterior;
	}

	public void setSiguiente(Decoracion s) {
		siguiente = s;
		s.anterior = this;
	}

	public void setAnterior(Decoracion a) {
		anterior = a;
	}
	
}