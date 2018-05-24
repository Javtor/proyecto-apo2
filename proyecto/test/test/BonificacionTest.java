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

}
