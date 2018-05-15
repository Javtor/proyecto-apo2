package modelo;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.Serializable;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class Proyectil extends SpriteMovimiento implements Colisionable, Disparable{

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
	public static final String DISPARO = "img/disparo.wav";
	
	private int danio;
	private int velocidad;

	public Proyectil() {
		super(0, 0, IMG_NORMAL);
		setVisible(false);
	}

	@Override
	public void disparar(int x, int y, int x2, int y2) {
		if (!esVisible()) {
			try {
				Clip disparo = AudioSystem.getClip();
				disparo.open(AudioSystem.getAudioInputStream(new File(DISPARO)));
				disparo.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

}