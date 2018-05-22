package modelo;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Clase Pelota
 * Esta clase sirve de plantilla para crear Pelotas que seran las enemigas del objeto Nave
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class Pelota extends SpriteMovimiento implements Colisionable {
	/**	 * Ruta de la imagen de la pelota
	 */
	public static final String UBICACION = "./img/pelota.png";
	/**
	 * Velocidad base de la pelota
	 */
	public static final int VELOCIDAD_BASE = 4;
	/**
	 * Vida m�xima de la pelota
	 */
	public static final int VIDA_MAX = 5;
	/**
	 * Cantidad de vida de la pelota
	 */
	private int vida;
	/**
	 * Raiz izquierda de la pelota
	 */
	private Pelota izq;
	/**
	 * Raiz derecha de la pelota
	 */
	private Pelota der;
	/**
	 * Constructor de la clase Pelota<br>
	 * Ubica a la pelota en una posici�n random (en x) de manera en que no se salga de los bordes
	 * izquierdo y derecho de la pantalla.<br>
	 * Ubica a la pelota en la parte superior de la pantalla (en y)<br>
	 * Asigna la velocidad real a un valor multiplo de la velocidad base en una escala de 0.8-1 para
	 * que esta sirva para asignar el dY Y dX<br>
	 * Asigna el dY a a la velocidad real + la mitad del nivel actual<br>
	 * Asigna el dX a un valor random que al ser menor a 0.5 ira en direccion a la izquierda y si no a la derecha<br>
 	 * @param nivel Nivel actual del juego 
	 */
	public Pelota(int nivel) {
		super(0, 0, UBICACION);
		vida = VIDA_MAX;
		this.setX((int) (Math.random() * (Juego.ANCHO - getAncho() * 2)) + getAncho() / 2);
		this.setY(-this.getAlto());
		int velReal = (int) (VELOCIDAD_BASE * (Math.random() * 0.8 + 1));
		this.setDY(velReal+nivel/2);
		this.setDX(Math.random() < 0.5 ? velReal+nivel/2 : -velReal-nivel/2);
	}
	/**
	 * Disminuye la vida de la pelota dependiendo del da�o del Proyetil
	 * <b>pre:</b>El atributo vida ha sido inicializado<br>
	 * <b>post:</b>Se ha disminuido la vida dependiendo del da�o del proyectil<br>
	 * <b>post:</b>Si la vida es menor o igual a cero el proyectil se hace invisible<br>
	 * @param p Poryectil que colision� con la pelota
	 */
	public void disminuirVida(Proyectil p) {
		vida -= p.getDanio();
		if (vida <= 0) {
			setVisible(false);
		}
	}
	/**
	 * Verifica si la pelota es una hoja, es decir no tiene subarbol izquierdo y derecho<br>
	 * <b>post:</b>Verifica si la pelota es una hoja<br>
	 * @return False si tiene subarbol izquiero o derecho, True si ambos subarboles son null.
	 */
	public boolean esHoja() {
		return izq == null && der == null;
	}
	/**
	 * Recorre todo el arbol de pelotas en inorden verificando si existe alguna colisi�n entre las pelotas<br>
	 * <b>post:</b>Se ha recorrido todo el �rbol de pelotas en inorden<br>
	 * <b>post:</b>Se ha retornado si existe alguna colisi�n en el arbol<br>
	 * @param c Objeto Colisionable con el que posiblemente colisin� alguna pelota
	 * @return True si alguna pelota ha colisionado con el objeto Colisionable, False si ninguna ha colisionado
	 * con el objeto colisionable
	 */
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
	/**
	 * Crea el arreglo de pelotas en inorden<br>
	 * @param a ArrayList donde se van acomulando las pelotas del �rbol
	 */
	public void crearArreglo(ArrayList<Pelota> a) {
		if (izq != null) {
			izq.crearArreglo(a);
		}
		a.add(this);
		if (der != null) {
			der.crearArreglo(a);
		}
	}
	/**
	 * Recorre todo el �rbol de pelotas en inorden verificando si existe alguna pelota viva.<br>
	 * <b>post:</b>Se ha recorrido todo el �rbol de pelotas<br>
	 * <b>post:</b>Retorna true si eixste por lo menos una pelota viva en el �rbol<br>
	 * @return True si existe por lo menos una pelota viva en el �rbol, False si no hay pelotas vivas en el �rbol
	 */
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
	/**
	 * Inserta una pelota en el �rbol ordenado de acuerdo a su posici�n en X.<br>
	 * <b>pre:</b>Se ha instanciado la ra�z del �rbol<br>
	 * <b>post:</b>Se ha a�adido una pelota en su posici�n correspondiente de acuero a su posicion en x<br>
	 * <b>post:</b>El peso del �rbol se increment� en +1<br>
	 * @param p Nueva pelota que se va a insertar en el �rbol. p!=null 
	 */
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
	/**
	 * M�todo implementado de la interfaz Colisionable.<br>
	 * Realiza su respectiva acci�n si ha colisionado con un Proyectil.<br>
	 * <b>pre:</b>Se ha inicializado el atributo vida<br>
	 * <b>post:</b>Si ha colisionado con un poryectil se llama al m�todo que disminuye la vida de la pelota<br>
	 * @param c Objeto con el que colision� la Pelota. c!=null
	 */
	@Override
	public void colisionaCon(Colisionable c) {
		if (c instanceof Proyectil) {
			disminuirVida((Proyectil) c);
		}
	}
	/**
	 * M�todo implementado de la interfaz Colisionable.<br>
	 * Verifica si existe colisi�n entre el Objeto Colisionable y la pelota.<br>
	 * <b>post:</b>Retorna si existe colisi�n entre ambos objetos<br>
	 * @return True si existe colisi�n, false si no eixste colisi�n.
	 * @param c Objeto que posiblemente colision� con la pelota. c!=null.
	 */
	@Override
	public boolean hayColision(Colisionable c) {
		return this.esVisible() && this.getHitbox().intersects(c.getHitbox());
	}
	/**
	 * M�todo implementado de la interfaz Colisionable.<br>
	 * Retorna el HitBox de la pelota.<br>
	 * <b>pre:</b>Los atributos x, y, ancho y alto han sido inicializados<br>
	 * <b>post:</b>Retorna el HitBox de la pelota<br>
	 * @return el HitBox Rectangular de la pelota. 
	 */
	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}
	/**
	 * M�todo implementado de la interfaz Movible.<br>
	 * Realiza el movimiento de la pelota aumentando su posicion en x y y en dX y dY.<br>
	 * Verifica si la nueva posici�n en x y en y de la pelota hace que se salga de la pantalla y fija su 
	 * dX y dY en direcci�n contraria.<br>
	 * <b>pre:</b>Los atributos dX, dY, X y Y han sido inicializados<br>
	 * <b>post:</b>Se ha cambiado la posicion en x y en y en dX y dY<br>
	 * <b>post:</b>Si se sale de los bordes izquierdo o derecho cambia su direccion en x a la opuesta<br>
	 * <b>post:</b>Si se sale de los bordes superior e inferior cambia su direccion en y a la opuesta<br>
	 */
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
	/**
	 * Retorna el peso del �rbol, es decir cu�ntos elementos tiene el �rbol.<br>
	 * <b>post:</b>Recorre el �rbol y retorna el peso<br>
	 * @return el peso del �rbol, sumando el peso del sub�rbol izquiero y del subarbol derecho m�s la raiz 
	 */
	public int darPeso() {
		int p1 = (izq==null) ? 0 : izq.darPeso();
		int p2 = (der==null) ? 0 : der.darPeso();
		return 1+p1+p2;
	}

}