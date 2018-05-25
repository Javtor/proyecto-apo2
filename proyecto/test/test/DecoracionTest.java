package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Decoracion;
import modelo.Juego;

class DecoracionTest {

	private Decoracion deco;
	private Juego juego;
	
	DecoracionTest(){
		juego = new Juego();
		deco = new Decoracion();
	}
	
	void setUpEscenario1(){
		juego.agregarDecoracion(deco);
	}
	
	void setUpEscenario2() {
		setUpEscenario1();
		juego.agregarDecoracion(new Decoracion());
	}

	@Test
	void testAgregarDecoracion1() {
		juego.agregarDecoracion(deco);
		assertTrue (deco==juego.getPrimeradeco());
		
		/*setUpEscenario1();
		
		setUpEscenario2();
		juego.agregarDecoracion(d1);
		assertTrue(d1==juego.getPrimeradeco().darAnterior());
		assertTrue(d1==juego.getPrimeradeco().darSiguiente().darSiguiente());
		*/
	}
	
	@Test
	void testAgregarDecoracion2() {
		setUpEscenario1();
		Decoracion d1 = new Decoracion();
		juego.agregarDecoracion(d1);
		assertTrue(d1==juego.getPrimeradeco().darSiguiente());
		assertTrue(d1==juego.getPrimeradeco().darAnterior());
	}

}
