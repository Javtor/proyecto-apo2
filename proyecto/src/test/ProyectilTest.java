package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Proyectil;

public class ProyectilTest {
	
	private Proyectil p;
	
	private void setupEscenario1() {
		p = new Proyectil(20, 20, null, 0, 0);
	}

	@Test
	public void testCalcularVelocidades() {
		setupEscenario1();
		p.calcularVelocidades(23, 24);
		System.out.println(p.getDX());
		System.out.println(p.getDY());
	}

}