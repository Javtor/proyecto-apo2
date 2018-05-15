package modelo;

import java.io.File;

public class ProyectilNormal extends Proyectil{

	public static final int VELOCIDAD = 20;
	public static final int DANIO = 3;
	public static final String IMG = "img" + File.separator + "proyectil_n.png";

	public ProyectilNormal() {
		super();
		setDanio(DANIO);
		setVelocidad(VELOCIDAD);
		setImagen(IMG);
	}

}