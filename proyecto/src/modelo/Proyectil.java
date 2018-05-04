package modelo;

import java.awt.geom.Rectangle2D;

public class Proyectil extends SpriteMovimiento implements Colisionable {
	
	public static final int VELOCIDAD = 10;
	private Proyectil siguiente;

	public Proyectil(int x, int y, String imagen, int x2, int y2) {
		super(x, y, imagen);
		calcularVelocidades(x2,y2);
	}

	private int daño;
	private char tipo;
	
	public void calcularVelocidades(int x2, int y2) {
		int difX = x2-getX();
		int difY = y2-getY();
		double hip = Math.sqrt(difX*difX+difY*difY);
		double prop = VELOCIDAD/hip;
		setDX((int)(prop*(x2-getX())));
		setDY((int)(prop*(y2-getY())));
	}
	
	@Override
	public void colisionaCon(Colisionable c) {

	}

	@Override
	public boolean hayColision(Colisionable c) {
		return getHitbox().intersects(c.getHitbox());
	}

	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}
}