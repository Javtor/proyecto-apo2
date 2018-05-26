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
	
	private void setupEscenario1() {
		p = new ProyectilNormal();
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
	public void testCalcularVelocidades() {
		setupEscenario1();
		p.disparar(20,20,25, 24);
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
