package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.BonoProyFuerte;
import modelo.BonoProyNormal;
import modelo.BonoProyRapido;
import modelo.BonoVida;
import modelo.Nave;
import modelo.Pelota;
import modelo.ProyectilFuerte;
import modelo.ProyectilNormal;
import modelo.ProyectilRapido;

class NaveTest {
	
	private Nave nave;
	
	private void setupEscenario1() {
		nave = new Nave();
		nave.setX(30);
		nave.setDX(50);
	}
	
	private void setupEscenario2() {
		nave = new Nave();
		nave.setX(810);
	}
	
	private void setupEscenario3() {
		nave = new Nave();
		nave.setX(50);
		nave.setY(50);
		nave.setDY(-100);
	}
	
	private void setupEscenario4() {
		nave = new Nave();
		nave.setY(610);
	}

	@Test
	void testColisionaCon() {
		setupEscenario1();
		nave.colisionaCon(new Pelota(0));
		assertTrue(nave.getVidas() == 3);
		assertTrue(nave.esInvulnerable());
		
		setupEscenario1();
		nave.colisionaCon(new BonoVida());
		assertTrue(nave.getVidas() == 5);
		
		setupEscenario1();
		nave.colisionaCon(new BonoProyFuerte());
		assertTrue(nave.getProyectil() instanceof ProyectilFuerte);
		
		setupEscenario1();
		nave.colisionaCon(new BonoProyNormal());
		assertTrue(nave.getProyectil() instanceof ProyectilNormal);
		
		setupEscenario1();
		nave.colisionaCon(new BonoProyRapido());
		assertTrue(nave.getProyectil() instanceof ProyectilRapido);
	}
	
	@Test
	void testMover() {
		setupEscenario1();
		nave.mover();
		assertTrue(nave.getX()==80);
		
		setupEscenario2();
		nave.mover();
		assertTrue(nave.getX()==800-nave.getAncho());
		
		setupEscenario3();
		nave.mover();
		assertTrue(nave.getY()==0);
		
		setupEscenario4();
		nave.mover();
		assertTrue(nave.getY()==600-nave.getAlto());
	}
	
	@Test
	void testHayColision() {
		setupEscenario3();
		Pelota p = new Pelota(0);
		p.setX(50);
		p.setY(50);
		assertTrue(nave.hayColision(p));
		
		setupEscenario3();
		p = new Pelota(0);
		p.setX(200);
		p.setY(200);
		assertFalse(nave.hayColision(p));
	}

}
