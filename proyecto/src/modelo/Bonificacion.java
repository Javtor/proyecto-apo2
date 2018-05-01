package modelo;

import java.awt.geom.Rectangle2D;

public class Bonificacion extends Sprite implements Colisionable {

	public static String DIREC_N_PROYECTIL = "img/bonus/nproyectiles.png";
	public static String DIREC_T_PROYECTIL = "img/bonus/tproyectil.png";
	public static String DIREC_BONUS_VIDA = "img/bonus/bonusvida.png";
	public static String DIREC_BONUS_PUNTOS = "img/bonus/bonuspuntos.png";
	
	private int tipo;
	private Bonificacion siguiente;
	private Bonificacion anterior;
	
	public Bonificacion(int x, int y, String imagen, int tipo) {
		super(x, y, imagen);
		this.tipo=tipo;
		switch (tipo){
			case 1:
				setImagen(DIREC_N_PROYECTIL);
			break;
			case 2:
				setImagen(DIREC_T_PROYECTIL);
			break;
			case 3:
				setImagen(DIREC_BONUS_VIDA);
			break;
			case 4:
				setImagen(DIREC_BONUS_PUNTOS);
			break;
		}
		
		siguiente=null;
		anterior=null;
	}
	
	public int getTipo() {
		return tipo;
	}

	public Bonificacion getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Bonificacion siguiente) {
		this.siguiente = siguiente;
	}

	public Bonificacion getAnterior() {
		return anterior;
	}

	public void setAnterior(Bonificacion anterior) {
		this.anterior = anterior;
	}

	public void desconectarSiguiente() {
		siguiente = siguiente.siguiente;
	}
	
	public void desconectarAnterior() {
		anterior = anterior.anterior;
	}
	
	@Override
	public void colisionaCon(Colisionable c) {
		// TODO Auto-generated method stub
		
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