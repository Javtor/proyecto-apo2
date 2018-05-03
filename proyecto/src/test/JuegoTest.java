package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Juego;
import modelo.Jugador;
import modelo.JugadorRepetidoException;

class JuegoTest {
	
	private Juego juego;
	private Jugador jugador;
	
	JuegoTest(){
		juego = new Juego();
		jugador = juego.getJugador();
		jugador.setNickname("Joan");
		jugador.setNivel(1);
		jugador.setPuntaje(100);
	}

	void setUpEscenario1() {
		juego.addJugador();
	}
	
	void setUpEscenario2() {
		setUpEscenario1();
		juego.setJugador(new Jugador ("Camila"));
		jugador = juego.getJugador();
		jugador.setNivel(1);
		jugador.setPuntaje(50);
	}
	
	void setUpEscenario3() {
		setUpEscenario2();
		juego.setJugador(new Jugador ("Sariana"));
		jugador = juego.getJugador();
		jugador.setNivel(4);
		jugador.setPuntaje(500);
	}

	@Test
	void testAddJugador1() {
		juego.addJugador();
		Jugador j = juego.getRaizJugador();
		assertTrue(j==jugador);
	}
	
	@Test
	void testAddJugador2() {
		setUpEscenario2();
		juego.addJugador();
		Jugador j = juego.getRaizJugador();
		assertTrue(j.getIzq()==jugador);
	}
	
	@Test
	void testAddJugador3() {
		setUpEscenario3();
		juego.addJugador();
		Jugador j = juego.getRaizJugador();
		assertTrue(j.getDer()==jugador);
	}
	
	@Test
	void testAddJugador4() {
		setUpEscenario1();
		try {
			juego.addJugador();
			fail ("debia fallar");
		}catch (JugadorRepetidoException e) {
			
		}
	}
	
	

}
