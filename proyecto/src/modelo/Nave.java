package modelo;

import java.awt.event.KeyEvent;

public class Nave extends SpriteMovimiento implements Colisionable {
	public static final String VISUALNAVE="img/lennon.png";
	public static final int VELOCIDAD_BASE = 5;
	public static final int VIDAS = 3;
	
	private int vidas;
	
	public Nave() {
		super(Juego.ANCHO/2, Juego.ALTO/2,VISUALNAVE );
		setX(getX()-getAncho()/2);
		setY(getY()-getAlto()/2);
		vidas = VIDAS;
	}

	public void disminuirvida() {
		// TODO - implement Nave.disminuirvida
		throw new UnsupportedOperationException();
	}

	public boolean validarvida() {
		// TODO - implement Nave.validarvida
		throw new UnsupportedOperationException();
	}

	@Override
	public void colisionaCon(int Colisionable) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mover() {
		super.mover();
		if(getX()<0) {
			setX(0);
		} else if(getX()+getAncho()>Juego.ANCHO) {
			setX(Juego.ANCHO-getAncho());
		} else if(getY()<0) {
			setY(0);
		} else if(getY()+getAlto()>Juego.ALTO) {
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

}