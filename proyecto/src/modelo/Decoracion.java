package modelo;

import java.awt.geom.Rectangle2D;

public class Decoracion extends SpriteMovimiento{
	public static final String IMAGEN="img/icono.png";
	private static String[] imagenes= {"img/marte.png","img/jupiter.png","img/mercurio.png","img/planetaazul.png"};
	private String imagen;
	private Decoracion siguiente;
	private Decoracion anterior;
	
	// se utiliza la constante como prueba
	public Decoracion(int x, int y, int n) {
		super(x, y, imagenes[n]);
		imagen=imagenes[n];
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