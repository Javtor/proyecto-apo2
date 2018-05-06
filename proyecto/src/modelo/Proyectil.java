package modelo;

import java.awt.geom.Rectangle2D;
import java.io.File;

public class Proyectil extends SpriteMovimiento implements Colisionable {
	
	public static final int VELOCIDAD = 15;
	public static final String UBICACION = "img"+File.separator+"proyectil.jpg";
	public static final int DANIO = 1;
	
	private int danio;
	private char tipo;

	public Proyectil(int x, int y) {
		super(x, y, UBICACION);
		danio = DANIO;
		setVisible(false);
	}

	public void disparar(int x, int y, int x2, int y2) {
		if ( !esVisible() ) {
			setX(x);
			setY(y);
			setVisible(true);
			int difX = x2 - getX() + getAncho() / 2;
			int difY = y2 - getY() + getAlto() / 2;
			double hip = Math.sqrt(difX * difX + difY * difY);
			double prop = VELOCIDAD / hip;
			setDX((int) (prop * (x2 - getX())));
			setDY((int) (prop * (y2 - getY())));
		}
	}
	
	@Override
	public void mover() {
		super.mover();
		if(getX()<0 
				|| getX()+getAncho()>Juego.ANCHO
				|| getY()<0
				|| getY()+getAlto()>Juego.ALTO) {
			setVisible(false);
			setDX(0);
			setDY(0);
		} 
	}
	
	@Override
	public void colisionaCon(Colisionable c) {
		if(c instanceof Pelota) {
			setVisible(false);
		}
	}

	@Override
	public boolean hayColision(Colisionable c) {
		return getHitbox().intersects(c.getHitbox());
	}

	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}

	public int getDanio() {
		return danio;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}
	
}