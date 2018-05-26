package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

import modelo.BonoVida;
import modelo.Pelota;
import modelo.Proyectil;
import modelo.ProyectilFuerte;
import modelo.ProyectilNormal;
import modelo.ProyectilRapido;

public class ProyectilTest {
	
	private Proyectil p;
	
	void setUpEscenario1() {
		p = new ProyectilNormal();
		p.setVisible(false);
	}
	
	void setUpEscenario2() {
		p = new ProyectilFuerte();
		p.setVisible(false);
	}
	
	void setUpEscenario3() {
		p = new ProyectilRapido();
		p.setVisible(false);
	}
	
	void setUpEscenario4() {
		p = new ProyectilNormal();
		p.setVisible(true);
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
	void testDispararProyectilNormal1() {
		setUpEscenario1();
		p.disparar(50,50,100,100);
		assertTrue(p.getDX()==10 && p.getDY()==10);
		setUpEscenario1();
		p.disparar(100,100,50,50);
		assertTrue(p.getDX()==-23 && p.getDY()==-23);
		setUpEscenario1();
		p.disparar(100,50,100,100);
		assertTrue(p.getDX()==0 && p.getDY()==13);
		setUpEscenario1();
		p.disparar(100,100,100,50);
		assertTrue(p.getDX()==0 && p.getDY()==-27);
	}
	
	@Test
	void testDispararProyectilNormal2() {
		setUpEscenario1();
		p.disparar(100,100,50,50);
		assertTrue(p.getDX()==-23 && p.getDY()==-23);
	}
	
	@Test
	void testDispararProyectilNormal3() {
		setUpEscenario1();
		p.disparar(100,50,100,100);
		assertTrue(p.getDX()==0 && p.getDY()==13);
	}
	
	@Test
	void testDispararProyectilNormal4() {
		setUpEscenario1();
		p.disparar(100,100,100,50);
		assertTrue(p.getDX()==0 && p.getDY()==-27);
	}
	
	@Test
	void testDispararProyectilFuerte1() {
		setUpEscenario2();
		p.disparar(50,50,100,100);
		assertTrue(p.getDX()==7 && p.getDY()==7);
	}
	
	@Test
	void testDispararProyectilFuerte2() {
		setUpEscenario2();
		p.disparar(100,100,50,50);
		assertTrue(p.getDX()==-17 && p.getDY()==-17);
	}
	
	@Test
	void testDispararProyectilFuerte3() {
		setUpEscenario2();
		p.disparar(100,50,100,100);
		assertTrue(p.getDX()==0 && p.getDY()==10);
	}
	
	@Test
	void testDispararProyectilFuerte4() {
		setUpEscenario2();
		p.disparar(100,100,100,50);
		assertTrue(p.getDX()==0 && p.getDY()==-20);
	}
	
	@Test
	void testDispararProyectilRapido1() {
		setUpEscenario3();
		p.disparar(50,50,100,100);
		assertTrue(p.getDX()==12 && p.getDY()==12);
	}
	
	@Test
	void testDispararProyectilRapido2() {
		setUpEscenario3();
		p.disparar(100,100,50,50);
		assertTrue(p.getDX()==-29 && p.getDY()==-29);
	}
	
	@Test
	void testDispararProyectilRapido3() {
		setUpEscenario3();
		p.disparar(100,50,100,100);
		assertTrue(p.getDX()==0 && p.getDY()==17);
	}
	
	@Test
	void testDispararProyectilRapido4() {
		setUpEscenario3();
		p.disparar(100,100,100,50);
		assertTrue(p.getDX()==0 && p.getDY()==-34);
	}
	
	@Test
	void testMover1() {
		setUpEscenario4();
		p.setX(-50);
		p.setY(10);
		p.mover();
		assertTrue(p.getDX()==0 && p.getDY()==0);
		assertTrue(!p.esVisible());
	}
	
	@Test
	void testMover2() {
		setUpEscenario4();
		p.setX(10);
		p.setY(-50);
		p.mover();
		assertTrue(p.getDX()==0 && p.getDY()==0);
		assertTrue(!p.esVisible());
	}
	
	@Test
	void testMover3() {
		setUpEscenario4();
		p.setX(810);
		p.setY(10);
		p.mover();
		assertTrue(p.getDX()==0 && p.getDY()==0);
		assertTrue(!p.esVisible());
	}
	
	@Test
	void testMover4() {
		setUpEscenario4();
		p.setX(30);
		p.setY(610);
		p.mover();
		assertTrue(p.getDX()==0 && p.getDY()==0);
		assertTrue(!p.esVisible());
	}
	
	@Test
	void testMover5() {
		setUpEscenario4();
		p.setX(50);
		p.setY(50);
		p.mover();
		assertTrue(p.esVisible());
	}
	
	@Test
	void testColisionaCon1() {
		setUpEscenario4();
		p.colisionaCon(new Pelota(1));
		assertTrue(!p.esVisible());
	}
	
	@Test
	void testColisionaCon2() {
		setUpEscenario4();
		p.colisionaCon(new BonoVida());
		assertTrue(p.esVisible());
	}

}