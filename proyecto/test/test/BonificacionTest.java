package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Bonificacion;
import modelo.BonoVida;
import modelo.Nave;
import modelo.Pelota;
import modelo.ProyectilNormal;

class BonificacionTest {
	
	private Bonificacion bono;
	
	private void setupEscenario1() {
		bono = new BonoVida();
		bono.setX(50);
		bono.setY(50);
	}
	
	
	@Test
	void testColisionaCon() {
		setupEscenario1();
		assertTrue(bono.esVisible());
		bono.colisionaCon(new Nave());
		assertFalse(bono.esVisible());
		
		setupEscenario1();
		assertTrue(bono.esVisible());
		bono.colisionaCon(new ProyectilNormal());
		assertFalse(bono.esVisible());
		
		setupEscenario1();
		assertTrue(bono.esVisible());
		bono.colisionaCon(new Pelota(0));
		assertFalse(bono.esVisible());
	}
	
	@Test
	void testHayColision() {
		setupEscenario1();
		Nave n = new Nave();
		n.setX(50);
		n.setY(50);
		assertTrue(bono.hayColision(n));
		
		setupEscenario1();
		n = new Nave();
		n.setX(100);
		n.setY(50);
		assertFalse(bono.hayColision(n));
	}

}
