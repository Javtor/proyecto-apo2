package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

import modelo.BonoProyFuerte;
import modelo.BonoProyNormal;
import modelo.BonoProyRapido;
import modelo.BonoPuntos;
import modelo.BonoVida;
import modelo.Proyectil;
import modelo.ProyectilFuerte;
import modelo.ProyectilNormal;
import modelo.ProyectilRapido;

public class ProyectilTest {
	
	private Proyectil p;
	
	private void setupEscenario1() {
		p = new ProyectilNormal();
	}
	
	@Test
	void testProyectilTipo() {
		p = new ProyectilNormal();
		assertEquals(p.getImagen(), new ImageIcon(ProyectilNormal.IMG).getImage());
		assertEquals(p.getVelocidad(), ProyectilNormal.VELOCIDAD);
		assertEquals(p.getDanio(), ProyectilNormal.DANIO);

		p = new ProyectilFuerte();
		assertEquals(p.getImagen(), new ImageIcon(ProyectilFuerte.IMG).getImage());
		assertEquals(p.getVelocidad(), ProyectilFuerte.VELOCIDAD);
		assertEquals(p.getDanio(), ProyectilFuerte.DANIO);
		
		p = new ProyectilRapido();
		assertEquals(p.getImagen(), new ImageIcon(ProyectilRapido.IMG).getImage());
		assertEquals(p.getVelocidad(), ProyectilRapido.VELOCIDAD);
		assertEquals(p.getDanio(), ProyectilRapido.DANIO);
	}

	@Test
	public void testCalcularVelocidades() {
		setupEscenario1();
		p.disparar(20,20,25, 24);
	}

}
