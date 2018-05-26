package test;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.ImageIcon;
import org.junit.jupiter.api.Test;

import modelo.Bonificacion;
import modelo.BonoProyFuerte;
import modelo.BonoProyNormal;
import modelo.BonoProyRapido;
import modelo.BonoPuntos;
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
	void testBonificacionTipo() {
		bono = new BonoProyFuerte();
		assertEquals(bono.getImagen(), new ImageIcon(BonoProyFuerte.DIREC).getImage());
		bono = new BonoProyNormal();
		assertEquals(bono.getImagen(), new ImageIcon(BonoProyNormal.DIREC).getImage());
		bono = new BonoProyRapido();
		assertEquals(bono.getImagen(), new ImageIcon(BonoProyRapido.DIREC).getImage());
		bono = new BonoPuntos();
		assertEquals(bono.getImagen(), new ImageIcon(BonoPuntos.DIREC).getImage());
		bono = new BonoVida();
		assertEquals(bono.getImagen(), new ImageIcon(BonoVida.DIREC).getImage());
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
