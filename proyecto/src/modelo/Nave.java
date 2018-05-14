package modelo;

import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.Serializable;

public class Nave extends SpriteMovimiento implements Colisionable {

	public static final int VELOCIDAD_BASE = 13;
	public static final int VIDAS_INICIAL = 4;
	public static final String UBICACION = "img" + File.separator + "nave.png";
	public static final String UBICACION_INVULNERABLE = "img" + File.separator + "invulnerable.png";

	private int vidas;
	private boolean invulnerable;

	private Proyectil proyectil;

	public Nave() {
		super(Juego.ANCHO / 2, Juego.ALTO - 100, UBICACION);
		setX(getX() - getAncho() / 2);
		setY(getY() - getAlto() / 2);
		vidas = VIDAS_INICIAL;
		proyectil = new Proyectil(Proyectil.NORMAL);
	}
	
	public Proyectil getProyectil() {
		return this.proyectil;
	}

	public int getVidas() {
		return vidas;
	}
	
	public void disminuirVida() {
		vidas--;
	}

	public boolean validarViva() {
		return vidas >= 0;
	}

	@Override
	public void colisionaCon(Colisionable c) {
		if (c instanceof Pelota && !esInvulnerable()) {
			disminuirVida();
			setInvulnerable(true);
		} else if (c instanceof Bonificacion) {
			Bonificacion b = (Bonificacion) c;
			switch (b.getTipo()) {
			case Bonificacion.VIDA:
				vidas++;
				break;
			case Bonificacion.PROYECTIL_F:
				proyectil = new Proyectil(Proyectil.FUERTE);
				break;
			case Bonificacion.PROYECTIL_N:
				proyectil = new Proyectil(Proyectil.NORMAL);
				break;
			case Bonificacion.PROYECTIL_R:
				proyectil = new Proyectil(Proyectil.RAPIDO);
				break;
			}
		}
	}

	@Override
	public void mover() {
		super.mover();
		if (getX() < Math.abs(getDX())) {
			setX(0);
		} else if (getX() + getAncho() > Juego.ANCHO - Math.abs(getDX())) {
			setX(Juego.ANCHO - getAncho());
		}
		if (getY() < Math.abs(getDY())) {
			setY(0);
		} else if (getY() + getAlto() > Juego.ALTO - Math.abs(getDY())) {
			setY(Juego.ALTO - getAlto());
		}
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			setDX(-VELOCIDAD_BASE);
		}

		if (key == KeyEvent.VK_D) {
			setDX(VELOCIDAD_BASE);
		}

		if (key == KeyEvent.VK_W) {
			setDY(-VELOCIDAD_BASE);
		}

		if (key == KeyEvent.VK_S) {
			setDY(VELOCIDAD_BASE);
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A && getDX() < 0) {
			setDX(0);
		}

		if (key == KeyEvent.VK_D && getDX() > 0) {
			setDX(0);
		}

		if (key == KeyEvent.VK_W && getDY() < 0) {
			setDY(0);
		}

		if (key == KeyEvent.VK_S && getDY() > 0) {
			setDY(0);
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

	public boolean esInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
		if (this.invulnerable) {
			setImagen(UBICACION_INVULNERABLE);
		}
	}

	public void disparar(int x, int y) {
		proyectil.disparar(getX() + getAncho() / 2, getY() + getAlto() / 2, x, y);
	}



}