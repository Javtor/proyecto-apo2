package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
	
	void setUpEscenario3() {
		setUpEscenario2();
		juego.agregarDecoracion(new Decoracion());
	}

	@Test
	void testAgregarDecoracion1() {
		juego.agregarDecoracion(deco);
		assertTrue (deco==juego.getPrimeradeco());
	}
	
	@Test
	void testAgregarDecoracion2() {
		setUpEscenario1();
		Decoracion d1 = new Decoracion();
		juego.agregarDecoracion(d1);
		assertTrue(d1==juego.getPrimeradeco().darSiguiente());
		assertTrue(d1==juego.getPrimeradeco().darAnterior());
	}
	
	@Test
	void testAgregarDecoracion3() {
		setUpEscenario2();
		Decoracion d1 = new Decoracion();
		juego.agregarDecoracion(d1);
		assertTrue(d1==juego.getPrimeradeco().darAnterior());
		assertTrue(d1==juego.getPrimeradeco().darSiguiente().darSiguiente());
	}
	
	@Test
	void darDecoraciones() {
		setUpEscenario3();
		ArrayList<Decoracion> array = juego.darDecoraciones();
		assertTrue(array.get(0)==juego.getPrimeradeco());
		assertTrue(array.get(1)==juego.getPrimeradeco().darSiguiente());
		assertTrue(array.get(2)==juego.getPrimeradeco().darAnterior());
		assertTrue(array.size()==3);	
	}
	
	@Test
	void crearDecoraciones() {
		juego.crearDecoraciones();
		ArrayList<Decoracion> array = juego.darDecoraciones();
		assertTrue(array.size()==Juego.NUMERO_DECORACIONES);
	}

}
