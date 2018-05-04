package modelo;

import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Nave extends SpriteMovimiento implements Colisionable {
	
	public static final int VELOCIDAD_BASE = 10;
	public static final int VIDAS_INICIAL = 3;
	public static final String UBICACION = "img/nave.png";
	public static final String UBICACION_INVULNERABLE = "img/invulnerable.jpg";
	
	private int vidas;
	private boolean invulnerable;
	
	public Nave() {
		super(Juego.ANCHO/2, Juego.ALTO/2, "img/nave.png");
		setX(getX()-getAncho()/2);
		setY(getY()-getAlto()/2);
		vidas = VIDAS_INICIAL;
	}

	public void disminuirvida() {
		vidas--;
	}

	public boolean validarvida() {
		// TODO - implement Nave.validarvida
		throw new UnsupportedOperationException();
	}

	@Override
	public void colisionaCon(Colisionable c) {
		if(c instanceof Pelota && !esInvulnerable()) {
			disminuirvida();
			System.out.println("pum");
			setInvulnerable(true);
		}
	}
	
	@Override
	public void mover() {
		super.mover();
		if(getX()<Math.abs(getDX())) {
			setX(0);
		} else if(getX()+getAncho()>Juego.ANCHO-Math.abs(getDX())) {
			setX(Juego.ANCHO-getAncho());
		} else if(getY()<Math.abs(getDY())) {
			setY(0);
		} else if(getY()+getAlto()>Juego.ALTO-Math.abs(getDY())) {
			setY(Juego.ALTO-getAlto());
		}
	}
	
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

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
        	setDX(0);
        }

        if (key == KeyEvent.VK_D) {
        	setDX(0);
        }

        if (key == KeyEvent.VK_W) {
        	setDY(0);
        }

        if (key == KeyEvent.VK_S) {
        	setDY(0);
        }
    }

	@Override
	public boolean hayColision(Colisionable c) {
		return getHitbox().intersects(c.getHitbox());
	}

	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D.Double(getX(), getY(), getAncho(), getAlto());
	}

	public boolean esInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
		if(this.invulnerable) {
			setImagen(UBICACION_INVULNERABLE);
		}
	}
	
	public void aumentarVida() {
		if (vidas<3) {
			vidas++;
		}
	}

	public int getVidas() {
		return vidas;
	}

}