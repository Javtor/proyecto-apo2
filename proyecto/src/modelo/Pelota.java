package modelo;

public class Pelota extends SpriteMovimiento implements Colisionable {
	public static final int INICIO_Y=0;
	private static final String IMAGEN="img/yoko.png";
	private boolean viva;
	private int vidas;
	

	public Pelota(int x) {
		super(x,INICIO_Y,IMAGEN);
		//Sujeto a cambiar, dependiendo de lo que diga el resto del grupo
		vidas=2;
	}

	/**
	 * 
	 * @param Proyectil
	 */
	public void disminuirvida(int Proyectil) {
		// TODO - implement Pelota.disminuirvida
		throw new UnsupportedOperationException();
	}

	public boolean verificarviva() {
		// TODO - implement Pelota.verificarviva
		throw new UnsupportedOperationException();
	}

	@Override
	public void colisionaCon(int Colisionable) {
		// TODO Auto-generated method stub
		
	}
	public void mover() {
		
	}

}