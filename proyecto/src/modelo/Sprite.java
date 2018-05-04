package modelo;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Sprite {

	private int x;
	private int y;
	private int ancho;
	private int alto;
	private boolean visible;
	private String imagen;

	public Sprite(int x, int y, String imagen) {
		this.x = x;
		this.y = y;
		this.imagen = imagen;
		visible = true;
		Image img = new ImageIcon(imagen).getImage();
		ancho = img.getWidth(null);
		alto = img.getHeight(null);
	}

	public Image getImagen() {
		return new ImageIcon(imagen).getImage();
	}
	
	public void setImagen(String imagen) {
		this.imagen=imagen;
		Image img = new ImageIcon(imagen).getImage();
		ancho = img.getWidth(null);
		alto = img.getHeight(null);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public boolean esVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}