package modelo;

public class Pelota extends SpriteMovimiento implements Colisionable {

	private boolean viva;
	private int vidas;
	private Pelota izq;
	private Pelota der;

	public Pelota(int x, int y, String imagen) {
		super(x, y, imagen);
		// TODO Auto-generated constructor stub
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

}