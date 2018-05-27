package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import modelo.Bonificacion;
import modelo.Decoracion;
import modelo.Juego;
import modelo.Jugador;
import modelo.JugadorRepetidoException;
import modelo.Nave;
import modelo.NombreNoExisteException;
import modelo.Pelota;
import modelo.ProyectilRapido;
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
	
	void setUpEscenario5() {
		juego = new Juego();
	}
	
	void setUpEscenario6() {
		juego = new Juego();
		juego.crearBonus();
	}
	
	void setUpEscenario7() {
		juego = new Juego();
		juego.crearBonus();
		juego.crearBonus();
		juego.crearBonus();
	}
	
	void setUpEscenario8() {
		try {
			juego.iniciarJuego(false);
		} catch (JugadorRepetidoException e) {
			e.printStackTrace();
		}
		
		juego.getNave().disminuirVida();
		juego.getNave().disminuirVida();
		juego.getNave().setProyectil(new ProyectilRapido());	
		
		try {
			juego.guardarNave("./test/datatest/nave.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setUpEscenario9() {
		juego.insertarPelota(new Pelota(1));
		juego.insertarPelota(new Pelota(1));
		juego.insertarPelota(new Pelota(1));
		juego.insertarPelota(new Pelota(1));
		
		try {
			juego.guardarPelotas("./test/datatest/pelotas.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setUpEscenario10() {
		juego.crearBonus();
		juego.crearBonus();
		juego.crearBonus();
		juego.crearBonus();
		
		try {
			juego.guardarBonificaciones("./test/datatest/bonus.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setUpEscenario11() {
		juego.agregarDecoracion(new Decoracion());
		juego.agregarDecoracion(new Decoracion());
		juego.agregarDecoracion(new Decoracion());
		
		try {
			juego.guardarDeco("./test/datatest/deco.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setUpEscenario12() {
		try {
		juego.addJugador();
		
		Jugador j = new Jugador("Julian");
		j.setNivel(4);
		j.setPuntaje(200);
		juego.setJugador(j);
		juego.addJugador();
		
		juego.setJugador(new Jugador ("Javier"));
		jugador = juego.getJugador();
		jugador.setNivel(3);
		jugador.setPuntaje(100);
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
		}catch(JugadorRepetidoException e) {
			e.printStackTrace();
		}
		
		try {
			juego.guardarJugadores("./test/datatest/users.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void setUpEscenario13() {
		juego = new Juego();
		juego.setJugador(new Jugador("Javier"));
		try {
			juego.iniciarJuego(false);
		} catch (JugadorRepetidoException e) {
			e.printStackTrace();
		}
		juego.aumentarPuntaje();
		juego.aumentarPuntaje();
		juego.aumentarPuntaje();
		juego.subirNivel();
		juego.subirNivel();
		
		try {
			juego.guardarDatos("./test/datatest/data.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	void setUpEscenario14() {
		juego = new Juego();
		juego.setJugador(new Jugador("Camila"));
		try {
			juego.iniciarJuego(false);
		} catch (JugadorRepetidoException e) {
			e.printStackTrace();
		}
	}
	
	void setUpEscenario15() {
		juego = new Juego();
		juego.setJugador(new Jugador("Camila"));
		try {
			juego.iniciarJuego(false);
		} catch (JugadorRepetidoException e) {
			e.printStackTrace();
		}
		juego.subirNivel();
		juego.subirNivel();
		juego.subirNivel();
	}
	
	void setUpEscenario16() {
		juego = new Juego();
		juego.aumentarPuntaje();
		juego.aumentarPuntaje();
	}
	
	void setUpEscenario17() {
		juego = new Juego();
		juego.aumentarPuntaje();
		juego.aumentarPuntaje();
		juego.aumentarPuntaje();
		juego.aumentarPuntaje();
	}
	
	void setUpEscenario18() {
		juego = new Juego();
		juego.aumentarPuntaje();
	}
	
	void setUpEscenario19() {
		juego = new Juego();
		juego.setJugador(new Jugador("Armando"));
	}
	
	void setUpEscenario20() {
		setUpEscenario19();
		try {
			juego.iniciarJuego(false);
		} catch (JugadorRepetidoException e) {
			e.printStackTrace();
		}
		juego.subirNivel();
		juego.subirNivel();
		juego.subirNivel();
	}
	
	void setUpEscenario21() {
		setUpEscenario19();
		try {
			juego.iniciarJuego(false);
		} catch (JugadorRepetidoException e) {
			e.printStackTrace();
		}
		
		juego.getNave().disminuirVida();
		juego.getNave().disminuirVida();
		juego.getNave().setProyectil(new ProyectilRapido());	
		
		juego.subirNivel();
		juego.bonusPuntaje();
		juego.bonusPuntaje();
		
		try {
			juego.guardarNave("./test/datatest/nave.txt");
			juego.guardarPelotas("./test/datatest/pelotas.txt");
			juego.guardarBonificaciones("./test/datatest/bonus.txt");
			juego.guardarDeco("./test/datatest/deco.txt");
			juego.guardarDatos("./test/datatest/data.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//PRUEBAS

	@Test
	void testAddJugador1() {
		try {
			juego.addJugador();
		} catch (JugadorRepetidoException e) {
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
	
	@Test
	void testCrearBonus() {
		setUpEscenario5();
		juego.crearBonus();
		Bonificacion p = juego.getBonus().get(0);
		assertTrue(p != null);
		assertTrue(p.getSiguiente() == null);
		
		setUpEscenario6();
		juego.crearBonus();
		p = juego.getBonus().get(0);
		assertTrue(p.getSiguiente() != null);
		assertTrue(p.getSiguiente().getAnterior() == p);
	}
	
	@Test
	void testGetBonus() {
		setUpEscenario7();
		ArrayList<Bonificacion> b = juego.getBonus();
		assertTrue(b.get(0).getSiguiente() == b.get(1));
		assertTrue(b.get(1).getAnterior() == b.get(0));
		
		setUpEscenario5();
		b = juego.getBonus();
		assertTrue(b.isEmpty());
	}
	
	@Test
	void testRecuperarNave() {
		setUpEscenario8();
		try {
			juego.recuperarNave("./test/datatest/nave.txt");
		} catch (ClassNotFoundException | IOException e) {
			fail("No deberia fallar");
		}
		
		Nave nave = juego.getNave();
		assertTrue(nave.getVidas()==2);
		assertTrue(nave.getProyectil() instanceof ProyectilRapido);
	}
	
	@Test
	void testRecuperarNaveError() {
		setUpEscenario8();
		try {
			juego.recuperarNave("./datatest/nave.txt");
			fail("Deberia fallar");
		} catch (ClassNotFoundException | IOException e) {
			
		}
	}
	
	@Test
	void testRecuperarPelotas() {
		setUpEscenario9();
		try {
			juego.recuperarPelotas("./test/datatest/pelotas.txt");
		} catch (ClassNotFoundException | IOException e) {
			fail("No deberia fallar");
		}
		assertTrue(juego.getPelotas().size()==4);
	}
	
	@Test
	void testRecuperarPelotasError() {
		setUpEscenario9();
		try {
			juego.recuperarPelotas("./datatest/pelotas.txt");
			fail("Deberia fallar");
		} catch (ClassNotFoundException | IOException e) {
			
		}
	}
	
	@Test
	void testRecuperarBonus() {
		setUpEscenario10();
		try {
			juego.recuperarBonus("./test/datatest/bonus.txt");
		} catch (ClassNotFoundException | IOException e) {
			fail("No deberia fallar");
		}
		assertTrue(juego.getBonus().size()==4);
	}
	
	@Test
	void testRecuperarBonusError() {
		setUpEscenario10();
		try {
			juego.recuperarBonus("./datatest/bonus.txt");
			fail("Deberia fallar");
		} catch (ClassNotFoundException | IOException e) {
			
		}
	}
	
	@Test
	void testRecuperarDecoraciones() {
		setUpEscenario11();
		try {
			juego.recuperarDeco("./test/datatest/deco.txt");
		} catch (ClassNotFoundException | IOException e) {
			fail("No deberia fallar");
		}
		
		assertTrue(juego.darDecoraciones().size()==3);
	}
	
	@Test
	void testRecuperarDecoracionesError() {
		setUpEscenario11();
		try {
			juego.recuperarDeco("./datatest/deco.txt");
			fail("Deberia fallar");
		} catch (ClassNotFoundException | IOException e) {
			
		}
	}
	
	@Test
	void testRecuperarJugadores() {
		setUpEscenario12();
		try {
			juego.recuperarJugadores("./test/datatest/users.txt");
		} catch (ClassNotFoundException | IOException e) {
			fail("No deberia fallar");
		}
		assertTrue(juego.toArrayListJugador().size()==5);
		assertEquals(juego.getRaizJugador().getNickname(), "Joan");
		assertTrue(juego.getRaizJugador().getNivel()== 1);
		assertTrue(juego.getRaizJugador().getPuntaje()== 100);
		
	}
	
	@Test
	void testRecuperarJugadoresError() {
		setUpEscenario12();
		try {
			juego.recuperarJugadores("./datatest/users.txt");
			fail("Deberia fallar");
		} catch (ClassNotFoundException | IOException e) {
			
		}
	}
	
	@Test
	void testRecuperarDatos() {
		setUpEscenario13();
		try {
			juego.cargarDatos("./test/datatest/data.txt");
		} catch (IOException e) {
			fail("No deberia fallar");
		}
		
		assertEquals(juego.getJugador().getNickname(), "Javier");
		assertEquals(juego.getJugador().getPuntaje(), 15);
		assertEquals(juego.getJugador().getNivel(), 3);
	}
	
	@Test
	void testSubirNivel() {
		setUpEscenario14();
		assertTrue(juego.getPelotas().size()==3);
		setUpEscenario15();
		assertTrue(juego.getPelotas().size()==4);
	}
	
	@Test
	void testBonusPuntaje() {
		setUpEscenario16();
		juego.bonusPuntaje();
		assertTrue(juego.getPuntaje()==20);
		setUpEscenario17();
		juego.bonusPuntaje();
		assertTrue(juego.getPuntaje()==30);
	}
	
	@Test
	void testAumentarPuntaje() {
		setUpEscenario16();
		juego.aumentarPuntaje();
		assertTrue(juego.getPuntaje()==15);
		setUpEscenario18();
		juego.aumentarPuntaje();
		assertTrue(juego.getPuntaje()==10);
	}
	
	@Test
	void testIniciarJuego1() {
		setUpEscenario19();
		try {
			juego.iniciarJuego(false);
		} catch (JugadorRepetidoException e) {
			e.printStackTrace();
		}
		
		assertTrue(juego.getNave()!=null);
		assertTrue(juego.getPelotas().size()==3);
		assertTrue(juego.isJugando());
		assertTrue(juego.darDecoraciones().size()==5);
	}
	
	@Test
	void testIniciarJuego2() {
		setUpEscenario20();
		assertTrue(juego.getNave()!=null);
		assertTrue(juego.getPelotas().size()==4);
		assertTrue(juego.isJugando());
	}
	
	@Test
	void testIniciarJuego3() {
		setUpEscenario21();
		Nave nav = juego.getNave();
		juego = new Juego();
		try {
			juego.recuperarNave("./test/datatest/nave.txt");
			juego.recuperarPelotas("./test/datatest/pelotas.txt");
			juego.recuperarBonus("./test/datatest/bonus.txt");
			juego.recuperarDeco("./test/datatest/deco.txt");
			juego.cargarDatos("./test/datatest/data.txt");
		} catch (ClassNotFoundException | IOException e) {
			fail("No deberia fallar");
		}
		
		try {
			juego.iniciarJuego(true);
		} catch (JugadorRepetidoException e) {
			fail("No deberia fallar");
		}
		
		assertTrue(juego.getNave().getProyectil()==nav.getProyectil());
		assertTrue(juego.getNave().getVidas()==nav.getVidas());
		assertTrue(juego.getPelotas().size()==3);
		assertTrue(juego.getPuntaje()==20);
		assertTrue(juego.getJugador().getNivel()==2);
		
		
		
	}

}
