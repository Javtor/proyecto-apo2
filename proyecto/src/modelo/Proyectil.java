package modelo;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.Serializable;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * Clase Abstracta Proyectil
 * Esta clase abstracta es la base para el objeto Proyectil que será usado para eliminar las pelotas del juego.
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public abstract class Proyectil extends SpriteMovimiento implements Colisionable, Disparable{
	//Constantes
	/**
	 * Sonido del disparo
	 */
	public static final String DISPARO = "img/disparo.wav";
	/**
	 * Nivel de daño que tiene frente a las pelotas
	 */
	private int danio;
	/**
	 * Velocidad con la que se desplazará por la pantalla
	 */
	private int velocidad;
	
	//Contructor
	/**
	 * Crea un la base de un proyectil que por el momento es invisible
	 */
	public Proyectil() {
		super(0, 0, null);
		setVisible(false);
	}
	/**
	 * Getter del daño
	 * <b>post:</b>Devuelve el daño del proyectil<br>
	 * @return
	 */
	public int getDanio() {
		return danio;
	}
	/**
	 * Setter del daño
	 * <b>post:</b>Asigna el daño al poryectil<br>
	 * @param danio
	 */
	public void setDanio(int danio) {
		this.danio = danio;
	}
	/**
	 * Setter de la velocidad 
	 * <b>post:</b>Asigna la velocidad del poryectil<br>
	 * @param danio
	 */
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	/**
	 * Método implementado de la interfaz Disparable
	 * <b>pre:</b>El atributo velocidad ha sido inicializado<br>
	 * <b>post:</b>Se ha reproducido el sonido del disparo<br>
	 * <b>post:</b>Se ha cambiado en dX y el dY del proyectil<br>
	 * @param x Posición en x inicial del proyectil. x!=null, x>=0
	 * @param y Posición en y inicial del proyectil. y!=null, y>=0
	 * @param x2 Posición en x final del proyectil. x2!=null, x2>=0
	 * @param y2 Posición en y final del proyectil. y2!=null, y2>=0 
	 */
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
	/**
	 * Método implementado de la interfaz Movible
	 * Realiza el movimiento del proyectil y si alcanza los bordes de la pantalla se vuelve invisible
	 * <b>pre:</b>Se ha intanciado un proyectil<br>
	 * <b>post:</b>Se ha cambiado su posición en x y en y <br>
	 * <b>post:</b>Si ha llegado a los bordes, se ha vuelto invisible<br>
	 */
	@Override
	public void mover() {
		super.mover();
		if (getX() < 0 || getX() + getAncho() > Juego.ANCHO || getY() < 0 || getY() + getAlto() > Juego.ALTO) {
			setVisible(false);
			setDX(0);
			setDY(0);
		}
	}
	/**
	 * Método implementado de la interfaz Colisionable
	 * Vuelve el proyectil invisible si el objeto Colisionable con el que intersecó es una Pelota
	 * <b>post:</b>Si c es una Pelota, el proyectil se vuelve invisible<br>
	 * @param c Objeto colisionable que intersecó con el proyectil. 
	 */
	@Override
	public void colisionaCon(Colisionable c) {
		if (c instanceof Pelota) {
			setVisible(false);
		}
	}
	/**
	 * Método implementado de la interfaz Colisionable
	 * Verifica si existe una colisión entre un objeto Colisionable y otro
	 * <b>post:</b>Devuelve si el proyectil ha intersecado o no con otro objeto Colisionable<br>
	 * @return True si ha colisionado, False si no ha colisionado
	 */
	@Override
	public boolean hayColision(Colisionable c) {
		return getHitbox().intersects(c.getHitbox());
	}
	/**
	 * Método implementado de la interfaz Colisionable
	 * Retorna el HitBox del proyectil
	 * <b>pre:</b>Los atributos x, y, ancho y alto han sido inicializados<br>
	 * <b>post:</b>Retorna el HitBox del proyectil<br>
	 * @return el HitBox Rectangular del proyectil. 
	 */
	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}

	

}