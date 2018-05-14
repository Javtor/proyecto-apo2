package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import modelo.Juego;
import modelo.Jugador;
import modelo.JugadorRepetidoException;
import modelo.NombreNoExisteException;
import modelo.PuntajeNoExisteException;

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
		try {
			juego.addJugador();
		} catch (JugadorRepetidoException e) {
			e.printStackTrace();
		}
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
	
	void setUpEscenario4() {
		try {
			jugador = juego.getJugador();
			jugador.setNickname("Julian");
			jugador.setNivel(3);
			jugador.setPuntaje(100);
			juego.addJugador();
			
			juego.setJugador(new Jugador ("Javier"));
			jugador = juego.getJugador();
			jugador.setNivel(4);
			jugador.setPuntaje(200);
			juego.addJugador();
			
			juego.setJugador(new Jugador ("Alejandro"));
			jugador = juego.getJugador();
			jugador.setNivel(2);
			jugador.setPuntaje(50);
			juego.addJugador();
			
			juego.setJugador(new Jugador ("Manyolml"));
			jugador = juego.getJugador();
			jugador.setNivel(7);
			jugador.setPuntaje(500);
			juego.addJugador();
			
			juego.setJugador(new Jugador ("JuanMa"));
			jugador = juego.getJugador();
			jugador.setNivel(6);
			jugador.setPuntaje(400);
			juego.addJugador();
		} catch (JugadorRepetidoException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	void testAddJugador1() {
		try {
			juego.addJugador();
		} catch (JugadorRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Jugador j = juego.getRaizJugador();
		assertTrue(j==jugador);
	}
	
	@Test
	void testAddJugador2() {
		setUpEscenario2();
		try {
			juego.addJugador();
		} catch (JugadorRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Jugador j = juego.getRaizJugador();
		assertTrue(j.getIzq()==jugador);
	}
	
	@Test
	void testAddJugador3() {
		setUpEscenario3();
		try {
			juego.addJugador();
		} catch (JugadorRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	@Test
	void testOrdenarPuntajeAscendente() {
		setUpEscenario4();
		
		ArrayList<Jugador> array = juego.ordenarPuntajeAscendente();
		
		assertEquals (array.get(0).getNickname(), "Alejandro");
		assertEquals (array.get(1).getNickname(), "Julian");
		assertEquals (array.get(2).getNickname(), "Javier");
		assertEquals (array.get(3).getNickname(), "JuanMa");
		assertEquals (array.get(4).getNickname(), "Manyolml");
	}
	
	@Test
	void testOrdenarPuntajeDescendente() {
		setUpEscenario4();
		
		ArrayList<Jugador> array = juego.ordenarPuntajeDescendente();
		
		assertEquals (array.get(0).getNickname(), "Manyolml");
		assertEquals (array.get(1).getNickname(), "JuanMa");
		assertEquals (array.get(2).getNickname(), "Javier");
		assertEquals (array.get(3).getNickname(), "Julian");
		assertEquals (array.get(4).getNickname(), "Alejandro");
	}
	
	@Test
	void testOrdenarNombreDescendente() {
		setUpEscenario4();
		
		ArrayList<Jugador> array = juego.ordenarNombreDescendente();
		
		assertEquals (array.get(0).getNickname(), "Manyolml");
		assertEquals (array.get(1).getNickname(), "Julian");
		assertEquals (array.get(2).getNickname(), "JuanMa");
		assertEquals (array.get(3).getNickname(), "Javier");
		assertEquals (array.get(4).getNickname(), "Alejandro");
	}
	
	@Test
	void testOrdenarNombreAscendente() {
		setUpEscenario4();
		
		ArrayList<Jugador> array = juego.ordenarNombreAscendente();
		
		assertEquals (array.get(0).getNickname(), "Alejandro");
		assertEquals (array.get(1).getNickname(), "Javier");
		assertEquals (array.get(2).getNickname(), "JuanMa");
		assertEquals (array.get(3).getNickname(), "Julian");
		assertEquals (array.get(4).getNickname(), "Manyolml");
	}
	
	@Test
	void testOrdenarNivelAscendente() {
		setUpEscenario4();
		ArrayList<Jugador> array = juego.ordenarNivelAscendente();
		
		assertEquals (array.get(0).getNickname(), "Alejandro");
		assertEquals (array.get(1).getNickname(), "Julian");
		assertEquals (array.get(2).getNickname(), "Javier");
		assertEquals (array.get(3).getNickname(), "JuanMa");
		assertEquals (array.get(4).getNickname(), "Manyolml");
	}
	
	@Test
	void testOrdenarNivelDescendente() {
		setUpEscenario4();
		ArrayList<Jugador> array = juego.ordenarNivelDescendente();
		
		assertEquals (array.get(0).getNickname(), "Manyolml");
		assertEquals (array.get(1).getNickname(), "JuanMa");
		assertEquals (array.get(2).getNickname(), "Javier");
		assertEquals (array.get(3).getNickname(), "Julian");
		assertEquals (array.get(4).getNickname(), "Alejandro");
	}
	
	@Test
	void testBuscarJugadorPuntaje1() {
		setUpEscenario4();
		Jugador jugador;
		try {
			jugador = juego.buscarJugadorPuntos(500);
			assertEquals (jugador.getNickname(), "Manyolml");
		} catch (PuntajeNoExisteException e) {
			fail("");
		}
		
	}
	
	@Test
	void testBuscarJugadorPuntaje2() {
		setUpEscenario4();
		try {
		Jugador jugador = juego.buscarJugadorPuntos(2);
		System.out.println(jugador.getNickname());
		fail ("Debe dar error");
		}catch (PuntajeNoExisteException e) {
			
		}
	}
	
	@Test
	void testBuscarJugadorNombre1() {
		setUpEscenario4();
		Jugador jugador;
		try {
			jugador = juego.buscarJugadorNombre("Javier");
			assertEquals (jugador.getNickname(), "Javier");
		} catch (NombreNoExisteException e) {
			fail("");
		}
		
	}
	
	@Test
	void testBuscarJugadorNombre2() {
		setUpEscenario4();
		try {
		Jugador jugador = juego.buscarJugadorNombre("Karol");
		fail ("Debe dar error");
		}catch (NombreNoExisteException e) {
			
		}
	}
	
	

}
