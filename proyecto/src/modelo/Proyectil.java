package modelo;

import java.awt.geom.Rectangle2D;
import java.io.File;

public class Proyectil extends SpriteMovimiento implements Colisionable {
	
	public static final int VELOCIDAD = 10;
	public static final String UBICACION = "img"+File.separator+"proyectil.jpg";

	public Proyectil(int x, int y) {
		super(x, y, UBICACION);
	}

	private int daño;
	private char tipo;
	
	public void disparar(int x, int y, int x2, int y2) {
		setX(x);
		setY(y);
		int difX = x2-getX()+getAncho()/2;
		int difY = y2-getY()+getAlto()/2;
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