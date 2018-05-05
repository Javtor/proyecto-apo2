package modelo;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Pelota extends SpriteMovimiento implements Colisionable {

	public static final String UBICACION = "img/pelota.jpg";
	public static final int VELOCIDAD_BASE = 5;
	
	private int vida;
	private Pelota izq;
	private Pelota der;
	

	public Pelota() {
		super(0, 0, UBICACION);
		this.setX((int)(Math.random()*(Juego.ANCHO-this.getAncho())));
		this.setY(-this.getAlto());
		int velReal = (int) (VELOCIDAD_BASE*(Math.random()*0.1+1));
		this.setDY(velReal);
		this.setDX(Math.random()<0.5? velReal: -velReal);
	}

	public void disminuirvida(int Proyectil) {
		// TODO - implement Pelota.disminuirvida
		throw new UnsupportedOperationException();
	}

	public boolean verificarviva() {
		// TODO - implement Pelota.verificarviva
		throw new UnsupportedOperationException();
	}
	
	public boolean esHoja() {
		return izq == null && der == null;
	}
	
	public boolean existenColisiones(Colisionable c) {
		boolean colIzq = false;
		boolean col;
		boolean colDer = false;	
		if(izq != null) {
			colIzq = izq.existenColisiones(c);
		}
		col = hayColision(c);
		if(der != null) {
			colDer = der.existenColisiones(c);
		}
		
		return colIzq || col || colDer;
	}
	
	public void crearArreglo(ArrayList<Pelota> a) {
		if(izq != null) {
			izq.crearArreglo(a);
		}
		a.add(this);
		if(der != null) {
			der.crearArreglo(a);
		}
	}
	
	public void insertar(Pelota p) {
		if(this.getX()>p.getX()) {
			if(izq == null) {
				izq = p;
			} else {
				izq.insertar(p);
			}
		} else {
			if(der == null) {
				der = p;
			} else {
				der.insertar(p);
			}
		} 
	}

	@Override
	public void colisionaCon(Colisionable c) {
		
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
		if(getX()<=Math.abs(getDX()) || getX()+getAncho()>=Juego.ANCHO-Math.abs(getDX())) {
			setDX(-getDX());
		} if((getY()<=Math.abs(getDY()) && getDY()<0)|| getY()+getAlto() >=Juego.ALTO-Math.abs(getDY())) {
			setDY(-getDY());
		} 
	}

}