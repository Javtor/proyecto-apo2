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
<<<<<<< HEAD
	public void colisionaCon(Colisionable c) {
||||||| merged common ancestors
	public void colisionaCon(int Colisionable) {
=======
	public boolean colisionaCon(Colisionable Colisionable) {
>>>>>>> Bonificacion
		// TODO Auto-generated method stub
		return false;
	}
<<<<<<< HEAD

	@Override
	public boolean hayColision(Colisionable c) {
		return getHitbox().intersects(c.getHitbox());
	}

	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}

||||||| merged common ancestors

=======
	
>>>>>>> Bonificacion
}