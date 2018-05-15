package modelo;

import java.io.File;

public class ProyectilRapido extends Proyectil{

	public static final int VELOCIDAD = 25;
	public static final int DANIO = 2;
	public static final String IMG = "img" + File.separator + "proyectil_r.png";

	public ProyectilRapido() {
		super();
		setDanio(DANIO);
		setVelocidad(VELOCIDAD);
		setImagen(IMG);
	}

}