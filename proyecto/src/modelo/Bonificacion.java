package modelo;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;

public class Bonificacion extends Sprite implements Colisionable{

//	public static String DIREC_PROYECTIL = "img/bonus/nproyectiles.png";
//	public static String DIREC_PROYECTIL = "img/bonus/tproyectil.png";
	public static String DIREC_BONUS_VIDA = "img/bonus/bonusvida.png";
	public static String DIREC_BONUS_PUNTOS = "img/bonus/bonuspuntos.png";
	public static String DIREC_BONUS_PROYECTIL_R = "img/bonus/proyectil_r.png";
	public static String DIREC_BONUS_PROYECTIL_N = "img/bonus/proyectil_n.png";
	public static String DIREC_BONUS_PROYECTIL_F = "img/bonus/proyectil_f.png";
	
	public static final int VIDA = 0;
	public static final int PUNTOS = 1;
	public static final int PROYECTIL_R = 2;
	public static final int PROYECTIL_N = 3;
	public static final int PROYECTIL_F = 4;
	
	private int tipo;
	private Bonificacion siguiente;
	private Bonificacion anterior;
	
	public Bonificacion() {
		super(0, 0, null);
		tipo = (int) (Math.random() * 5);
		setX((int)(Math.random()*(Juego.ANCHO-getAncho()*2)));
		setY((int)(Math.random()*(Juego.ALTO-getAlto()*2)));
		switch (tipo){
			case VIDA:
				setImagen(DIREC_BONUS_VIDA);
			break;
			case PUNTOS:
				setImagen(DIREC_BONUS_PUNTOS);
			break;
			case PROYECTIL_R:
				setImagen(DIREC_BONUS_PROYECTIL_R);
			break;
			case PROYECTIL_N:
				setImagen(DIREC_BONUS_PROYECTIL_N);
			break;
			case PROYECTIL_F:
				setImagen(DIREC_BONUS_PROYECTIL_F);
			break;
		}
		
		siguiente=null;
		anterior=null;
	}
	
	public void aArrayList(ArrayList<Bonificacion> a) {
		a.add(this);
		if(siguiente != null) {
			siguiente.aArrayList(a);
		}
	}
	
	public int getTipo() {
		return tipo;
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