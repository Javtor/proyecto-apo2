package modelo;

import java.awt.geom.Rectangle2D;

public interface Colisionable {
	
	public boolean hayColision(Colisionable c);
	
	public void colisionaCon(Colisionable c);
	
	public Rectangle2D getHitbox();

}