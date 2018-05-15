package modelo;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Bonificacion extends Sprite implements Colisionable{
	
	private Bonificacion siguiente;
	private Bonificacion anterior;
	
	public Bonificacion() {
		super(0, 0, null);
		setX((int)(Math.random()*(Juego.ANCHO-getAncho()*2)));
		setY((int)(Math.random()*(Juego.ALTO-getAlto()*2)));
		siguiente=null;
		anterior=null;
	}
	
	public void aArrayList(ArrayList<Bonificacion> a) {
		a.add(this);
		if(siguiente != null) {
			siguiente.aArrayList(a);
		}
	}

	public Bonificacion getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Bonificacion siguiente) {
		this.siguiente = siguiente;
		siguiente.anterior = this;
	}

	public Bonificacion getAnterior() {
		return anterior;
	}

	public void desconectarSiguiente() {
		siguiente = siguiente.siguiente;
	}
	
	public void desconectarAnterior() {
		anterior = anterior.anterior;
	}
	
	@Override
	public void colisionaCon(Colisionable c) {
		setVisible(false);
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