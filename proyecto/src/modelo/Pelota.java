package modelo;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Pelota extends SpriteMovimiento implements Colisionable {

	public static final String UBICACION = "img/pelota.png";
	public static final int VELOCIDAD_BASE = 4;
	public static final int VIDA_MAX = 5;

	private int vida;
	private Pelota izq;
	private Pelota der;

	public Pelota(int nivel) {
		super(0, 0, UBICACION);
		vida = VIDA_MAX;
		this.setX((int) (Math.random() * (Juego.ANCHO - getAncho() * 2)) + getAncho() / 2);
		this.setY(-this.getAlto());
		int velReal = (int) (VELOCIDAD_BASE * (Math.random() * 0.8 + 1));
		this.setDY(velReal+nivel/2);
		this.setDX(Math.random() < 0.5 ? velReal+nivel/2 : -velReal-nivel/2);
	}

	public void disminuirVida(Proyectil p) {
		vida -= p.getDanio();
		if (vida <= 0) {
			setVisible(false);
		}
	}

	public boolean esHoja() {
		return izq == null && der == null;
	}

	public boolean existenColisiones(Colisionable c) {
		boolean colIzq = false;
		boolean col;
		boolean colDer = false;
		if (izq != null) {
			colIzq = izq.existenColisiones(c);
		}
		col = hayColision(c);
		if (der != null) {
			colDer = der.existenColisiones(c);
		}

		return colIzq || col || colDer;
	}

	public void crearArreglo(ArrayList<Pelota> a) {
		if (izq != null) {
			izq.crearArreglo(a);
		}
		a.add(this);
		if (der != null) {
			der.crearArreglo(a);
		}
	}

	public boolean hayVivas() {
		boolean vIzq = false;
		boolean v = false;
		boolean vDer = false;
		if (izq != null) {
			vIzq = izq.hayVivas();
		}
		v = esVisible();
		if (der != null) {
			vDer = der.hayVivas();
		}

		return vIzq || v || vDer;
	}

	public void insertar(Pelota p) {
		if (this.getX() > p.getX()) {
			if (izq == null) {
				izq = p;
			} else {
				izq.insertar(p);
			}
		} else {
			if (der == null) {
				der = p;
			} else {
				der.insertar(p);
			}
		}
	}

	@Override
	public void colisionaCon(Colisionable c) {
		if (c instanceof Proyectil) {
			disminuirVida((Proyectil) c);
		}
	}

	@Override
	public boolean hayColision(Colisionable c) {
		return this.esVisible() && this.getHitbox().intersects(c.getHitbox());
	}

	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}

	@Override
	public void mover() {
		super.mover();
		if (getX() <= Math.abs(getDX()) || getX() + getAncho() >= Juego.ANCHO - Math.abs(getDX())) {
			setDX(-getDX());
		}
		if ((getY() <= Math.abs(getDY()) && getDY() < 0) || getY() + getAlto() >= Juego.ALTO - Math.abs(getDY())) {
			setDY(-getDY());
		}
	}
	
	public int darPeso() {
		int p1 = (izq==null) ? 0 : izq.darPeso();
		int p2 = (der==null) ? 0 : der.darPeso();
		return 1+p1+p2;
	}

}