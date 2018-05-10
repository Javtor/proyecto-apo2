package modelo;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.Serializable;

public class Proyectil extends SpriteMovimiento implements Colisionable, Serializable {

	public static final int RAPIDO = 0;
	public static final int NORMAL = 1;
	public static final int FUERTE = 2;

	public static final int VELOCIDAD_FUERTE = 15;
	public static final int VELOCIDAD_NORMAL = 20;
	public static final int VELOCIDAD_RAPIDO = 25;
	public static final int DANIO_RAPIDO = 2;
	public static final int DANIO_NORMAL = 3;
	public static final int DANIO_FUERTE = 5;
	public static final String IMG_NORMAL = "img" + File.separator + "proyectil_n.png";
	public static final String IMG_RAPIDO = "img" + File.separator + "proyectil_r.png";
	public static final String IMG_FUERTE = "img" + File.separator + "proyectil_f.png";
	
	private int danio;
	private int velocidad;

	public Proyectil(int tipo) {
		super(0, 0, IMG_NORMAL);
		setVisible(false);
		setElements(tipo);
	}

	public void disparar(int x, int y, int x2, int y2) {
		if (!esVisible()) {
			setX(x);
			setY(y);
			setVisible(true);
			int difX = x2 - getX() + getAncho() / 2;
			int difY = y2 - getY() + getAlto() / 2;
			double hip = Math.sqrt(difX * difX + difY * difY);
			double prop = velocidad / hip;
			setDX((int) (prop * (x2 - getX())));
			setDY((int) (prop * (y2 - getY())));
		}
	}

	@Override
	public void mover() {
		super.mover();
		if (getX() < 0 || getX() + getAncho() > Juego.ANCHO || getY() < 0 || getY() + getAlto() > Juego.ALTO) {
			setVisible(false);
			setDX(0);
			setDY(0);
		}
	}

	@Override
	public void colisionaCon(Colisionable c) {
		if (c instanceof Pelota) {
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
	
	public void setElements(int tipo) {
		switch (tipo) {
		case RAPIDO:
			setImagen(IMG_RAPIDO);
			danio = DANIO_RAPIDO;
			velocidad = VELOCIDAD_RAPIDO;
			break;
		case NORMAL:
			setImagen(IMG_NORMAL);
			danio = DANIO_NORMAL;
			velocidad = VELOCIDAD_NORMAL;
			break;
		case FUERTE:
			setImagen(IMG_FUERTE);
			danio = DANIO_FUERTE;
			velocidad = VELOCIDAD_FUERTE;
			break;
		}
	}

}