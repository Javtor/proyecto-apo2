package modelo;

public abstract class SpriteMovimiento extends Sprite implements Movible{
	
	private int dX;
	private int dY;

	public SpriteMovimiento(int x, int y, String imagen) {
		super(x, y, imagen);
	}
	
	@Override
	public void mover() {
		 setX(getX()+dX);
		 setY(getY()+dY);
	}

	public int getDX() {
		return dX;
	}

	public void setDX(int dX) {
		this.dX = dX;
	}

	public int getDY() {
		return dY;
	}

	public void setDY(int dY) {
		this.dY = dY;
	}
	
	

}
