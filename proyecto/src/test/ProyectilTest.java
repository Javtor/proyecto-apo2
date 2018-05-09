package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Proyectil;

public class ProyectilTest {
	
	private Proyectil p;
	
	private void setupEscenario1() {
		p = new Proyectil(20, 20,Proyectil.NORMAL);
		p = new Proyectil(Proyectil.NORMAL);
	}

	@Test
	public void testCalcularVelocidades() {
		setupEscenario1();
		p.disparar(20,20,25, 24);


	}

}
