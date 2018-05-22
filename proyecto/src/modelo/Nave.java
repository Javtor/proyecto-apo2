package modelo;

import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.Serializable;

/**
 * Clase Nave Esta es la plantilla para el objeto tipo Nave que será usado por
 * el usuario para moverse por la pantalla La nave mediante clics dispara un
 * proyectil que es uno de los atributos de esta Es la encargada de verificar
 * colisiones con pelotas, bonificaciones y disparar proyectiles.	
 * @author Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 */
public class Nave extends SpriteMovimiento implements Colisionable {
	// Constantes
	/**
	 * Velocidad base de la nave que es usada para definir que tanto se mueve para
	 * la izquiera, derecha, arriba o abajo dependiendo de la tecla presionada (W,
	 * A, S, D)
	 */
	public static final int VELOCIDAD_BASE = 13;
	/**
	 * Número de vidas que tiene la nave al iniciar el juego
	 */
	public static final int VIDAS_INICIAL = 4;
	/**
	 * Ruta de la imagen de la nave
	 */
	public static final String UBICACION = "./img" + File.separator + "nave.png";
	/**
	 * Ruta de la imagen de la nave cuando se encuentra en estado invulnerable
	 */
	public static final String UBICACION_INVULNERABLE = "./img" + File.separator + "invulnerable.png";

	// Atributos
	/**
	 * Número de vidas que tiene la nave
	 */
	private int vidas;
	/**
	 * Estado en el que se encuentra la nave False: si las pelotas pueden colisionar
	 * con este True: si las pelotas no pueden colisionar con este Pasa a estado
	 * invulnerable durante unos segundos después de alguna colision con una Pelota.
	 */
	private boolean invulnerable;
	/**
	 * Proyectil que dispara la nave para disminuir la vida y posteriormente
	 * eliminar a las pelotas
	 */
	private Proyectil proyectil;

	// Constructor
	/**
	 * Constructor de la clase Nave que ubica a la nave en el centro de la pantalla
	 * al iniciar el juego Asigna el valor de las vidas y crea el proyectil por
	 * defecto (de tipo Normal)<br>
	 */
	public Nave() {
		super(Juego.ANCHO / 2, Juego.ALTO - 100, UBICACION);
		setX(getX() - getAncho() / 2);
		setY(getY() - getAlto() / 2);
		vidas = VIDAS_INICIAL;
		proyectil = new ProyectilNormal();
	}

	/**
	 * Getter del atributo Proyectil <b>post:</b>Retorna el proyectil actual de la
	 * nave<br>
	 * @return El proyectil actual de la nave
	 */
	public Proyectil getProyectil() {
		return this.proyectil;
	}

	/**
	 * Getter del atributo vidas <b>post:</b>Devuelve el numero de vidas que tiene
	 * la nave actualmente<br>
	 * @return Numero de vidas que tiene la nave
	 */
	public int getVidas() {
		return vidas;
	}

	/**
	 * Disminuye en 1 la vida de la nave <b>pre:</b>Se ha inicializado el atributo
	 * vidas<br>
	 * <b>post:</b>El atributo vidas ha disminuido en 1<br>
	 */
	public void disminuirVida() {
		vidas--;
	}

	/**
	 * Valida si la nave sigue viva o no.<br>
	 * <b>post:</b>Se ha validado si sigue viva o no la nave<br>
	 * @return False si se quedo sin vidas. True si todavia tiene vidas.
	 */
	public boolean validarViva() {
		return vidas >= 0;
	}

	/**
	 * Método implementado de la interfaz Colisionable Devuelve el HitBox de la nave.<br>
	 * <b>pre:</b>La nave ha sido instanciada<br>
	 * <b>pre:</b>Los atributos x, y, dX y dY han sido inicializados<br>
	 * <b>post:</b>Crea y retorna el HitBox de la nave<br>
	 * 
	 * @return El HitBox de la nave
	 */
	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}

	/**
	 * Método que devuelve el estado de la nave (vulnerable/invulnerable).<br>
	 * <b>pre:</b>Se ha instanciado una nave<br>
	 * <b>pre:</b>Se ha inicializado el atributo invulnerable<br>
	 * <b>post:</b>Devuelve el estado de invulnerabilidad de la nave<br>
	 * 
	 * @return True si la nave es invulnerable y False si la nave no es invulnerable
	 */
	public boolean esInvulnerable() {
		return invulnerable;
	}

	/**
	 * Método que asigna el estado de invulnerabilidad a la nave <b>pre:</b>Se ha
	 * instanciado una nave<br>
	 * <b>pre:</b>Se ha inicializado el atributo invulnerable<br>
	 * <b>post:</b>Asigna el estado de invulnerabilidad de la nave<br>
	 * <b>post:</b>Si es invulnerable cambia la imagen de la nave por la imagen del
	 * estado invulnerable<br>
	 * 
	 * @param invulnerable
	 *            Si es o no invulnerable. invulnerable!=null
	 */
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
		if (this.invulnerable) {
			setImagen(UBICACION_INVULNERABLE);
		}
	}

	/**
	 * Método implementado de la interfaz Colisionable<br>
	 * Se encarga de realizar la acción correspondiente dependiendo del objeto con el que colisionó.<br>
	 * Si es una pelota y no está en estado de invulnerabilidad disminuye si vida y pasa
	 * a estar invulnerable.<br>
	 * Si es una bonificación, dependiendo del tipo, realiza la acción correspondiente .<br>
	 * <b>pre:</b>Vidas ha sido inicializado<br>
	 * <b>pre:</b>El objeto colisionable es una Pelota o una Bonificación<br>
	 * <b>post:</b>Si el objeto colisionable es una pelota, disminuye la vida de la
	 * nave y pasa a estado de invulnerabilidad<br>
	 * <b>post:</b>Si el objeto colisionable es una bonificación y es de tipo Vida,
	 * incrementa en 1 la vida de la nave<br>
	 * <b>post:</b>Si el objeto colisionable es una bonificación y es de tipo
	 * PROYECTIL_F, cambia su proyectil a uno de tipo fuerte<br>
	 * <b>post:</b>Si el objeto colisionable es una bonificación y es de tipo
	 * PROYECTIL_N, cambia su proyectil a uno de tipo normal<br>
	 * <b>post:</b>Si el objeto colisionable es una bonificación y es de tipo
	 * PROYECTIL_R, cambia su proyectil a uno de tipo rápidp<br>
	 * 
	 * @param c
	 *            Objeto con el que colisionó la nave. c!=null, c instanceof Pelota
	 *            || c instanceof Bonificacion
	 */
	@Override
	public void colisionaCon(Colisionable c) {
		if (c instanceof Pelota && !esInvulnerable()) {
			disminuirVida();
			setInvulnerable(true);
		} else if (c instanceof Bonificacion) {
			Bonificacion b = (Bonificacion) c;
			if (b instanceof BonoVida) {
				vidas++;
			} else if (b instanceof BonoProyFuerte) {
				proyectil = new ProyectilFuerte();
			} else if (b instanceof BonoProyNormal) {
				proyectil = new ProyectilNormal();
			} else if (b instanceof BonoProyRapido) {
				proyectil = new ProyectilRapido();
			}
		}
	}

	/**
	 * Método implementado de la interfaz Movible<br>
	 * Llama al método mover de su superclase SpriteMovimiento y hace las respectivas verificaciones para que la
	 * nave no se salga de la pantalla 
	 * <b>pre:</b>Los atributos x, y , dX y dY han sido inicializados<br>
	 * <b>post:</b>Se aumenta el valor de x y y en dX y dY<br>
	 * <b>post:</b>Si se va a salir de la pantalla por la parte izquierda se fija su
	 * x a 0<br>
	 * <b>post:</b>Si se va a salir de la pantalla por la parte derecha se fija su x
	 * de manera que se queda viendo la imagen pero que no se salga de la
	 * pantalla<br>
	 * <b>post:</b>Si se va a salir de la pantalla por la parte de arrba se fija su
	 * y a 0<br>
	 * <b>post:</b>Si se va a salir de la pantalla por la parte de abajo se fija su
	 * y de manera que se queda viendo la imagen pero que no se salga de la
	 * pantalla<br>
	 */
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

	/**
	 * Método que recibe el evento de la tecla presionada <b>pre:</b>Los atributos
	 * dX y dY han sido inicializados<br>
	 * <b>post:</b>Si presionó la letra A cambia su movimiento en x hacia la
	 * derecha<br>
	 * <b>post:</b>Si presionó la letra D cambia su movimiento en x hacia la
	 * izquierda<br>
	 * <b>post:</b>Si presionó la letra W cambia su movimiento en y hacia la
	 * arriba<br>
	 * <b>post:</b>Si presionó la letra A cambia su movimiento en y hacia la
	 * abajo<br>
	 * 
	 * @param e
	 *            Evento de la tecla presionada. Ha presionado la tecla W, A, S o D
	 */
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

	/**
	 * Método que recibe el evento de la tecla que dejó de presionar <b>pre:</b>Los
	 * atributos dX y dY han sido inicializados<br>
	 * <b>post:</b>Detiene el movimiento de la nave en la dirección dada (W, A, S o
	 * D) fijando su dX o dY a 0<br>
	 * 
	 * @param e
	 *            Evento de la tecla que se ha dejado de presionar. Puede ser la
	 *            tecla W, A, S o D
	 */
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

	/**
	 * Método implementado de la interfaz Colisionable Recibe un objeto de tipo
	 * colisionable y verifica si este esta intersecó con la nave <br>
	 * <b>pre:</b>Se ha instanciado una nave<br>
	 * <b>post:</b>Retorna si la nave intersecó o no con el objeto Colisionable<br>
	 * @param c Objeto que puede haber o no colisionado con la nave
	 * @return True si intersecó con la nave, False si no intersecó con la nave
	 */
	@Override
	public boolean hayColision(Colisionable c) {
		return getHitbox().intersects(c.getHitbox());
	}

	/**
	 * Método que ejecuta el disparo del proyectil<br>
	 * <b>pre:</b>Se ha instanciado un proyectil<br>
	 * <b>post:</b>Se ha disparado un proyectil<br>
	 * @param x Ubicación x a la que tiene que llegar el proyectil. x!=null, x debe ser mayor o igual a 0.
	 * @param y Ubicación y a la que tiene que llegar el proyectil. y!=null, y debe ser mayor o igual a 0.
	 */
	public void disparar(int x, int y) {
		proyectil.disparar(getX() + getAncho() / 2, getY() + getAlto() / 2, x, y);
	}

}