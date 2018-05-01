package modelo;

import java.awt.geom.Rectangle2D;

public class Proyectil extends SpriteMovimiento implements Colisionable {

	public Proyectil(int x, int y, String imagen) {
		super(x, y, imagen);
		// TODO Auto-generated constructor stub
	}

	private int daño;
	private char tipo;
	
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