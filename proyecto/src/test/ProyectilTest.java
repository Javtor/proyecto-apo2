package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import modelo.Proyectil;
import modelo.ProyectilNormal;

public class ProyectilTest {
	
	private Proyectil p;
	
	private void setupEscenario1() {
		p = new ProyectilNormal();
	}

	@Test
	public void testCalcularVelocidades() {
		setupEscenario1();
		p.disparar(20,20,25, 24);


	}

}
