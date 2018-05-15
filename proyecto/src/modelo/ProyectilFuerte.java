package modelo;

import java.io.File;

public class ProyectilFuerte extends Proyectil{

	public static final int VELOCIDAD = 15;
	public static final int DANIO = 5;
	public static final String IMG = "img" + File.separator + "proyectil_f.png";

	public ProyectilFuerte() {
		super();
		setDanio(DANIO);
		setVelocidad(VELOCIDAD);
		setImagen(IMG);
	}

}