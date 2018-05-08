package modelo;

import java.awt.geom.Rectangle2D;

public class Decoracion extends SpriteMovimiento{
	public static final String IMAGEN="img/icono.png";
	private String imagen;
	private Decoracion siguiente;
	private Decoracion anterior;
	
	// se utiliza la constante como prueba
	public Decoracion(int x, int y, String i) {
		super(x, y, IMAGEN);
		this.imagen=IMAGEN;
	}
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}
	public Decoracion darSiguiente() {
		return siguiente;
	}
	public Decoracion darAnterior() {
		return anterior;
	}
	public void setSiguiente(Decoracion s) {
		siguiente=s;
	}
	public void setAnterior(Decoracion a) {
		anterior=a;
	}
}