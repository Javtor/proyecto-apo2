package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Juego;
import modelo.Nave;
import modelo.Pelota;
import modelo.Proyectil;
import modelo.ProyectilFuerte;
import modelo.ProyectilNormal;
import modelo.ProyectilRapido;

 class PelotaTest {
	private Pelota pelota;
	private Pelota pelota1;
	private Pelota pelota2;
	private Juego juego;
	
	void setupEscenario1(){
		pelota= new Pelota(1);
		pelota.setX(50);
		pelota.setY(50);
	}
	void setupEscenario2() {
		setupEscenario1();
		pelota1=new Pelota(1);
		pelota1.setX(100);
		pelota1.setY(100);
		pelota.insertar(pelota1);
		
		pelota2=new Pelota(1);
		pelota2.setX(40);
		pelota2.setY(100);
		pelota.insertar(pelota2);
		
	}
	void setupEscenario3(){
		juego=new Juego();
	}
	void setupEscenario4() {
		setupEscenario3();
		setupEscenario2();
		juego.insertarPelota(pelota);
		juego.insertarPelota(pelota1);
		juego.insertarPelota(pelota2);
	}
	@Test
	void testHayColision() {
		setupEscenario1();
		ProyectilNormal p=new ProyectilNormal();
		p.setX(50);
		p.setY(50);
		assertTrue(pelota.hayColision(p));
		// Cambiar TestCase 2 del hay Colison
		p.setX(300);
		p.setY(100);
		assertFalse(pelota.hayColision(p));
	}
	@Test
	void testColisionCon() {
		setupEscenario1();
		ProyectilNormal p=new ProyectilNormal();
		p.setX(50);
		p.setY(50);
		pelota.colisionaCon(p);
		assertTrue(pelota.darVida()==2);
		
		ProyectilRapido o=new ProyectilRapido();
		o.setX(50);
		o.setY(50);
		pelota.setVida(5);
		pelota.colisionaCon(o);
		assertTrue(pelota.darVida()==3);
		
		ProyectilFuerte r=new ProyectilFuerte();
		r.setX(50);
		r.setY(50);
		pelota.setVida(5);
		pelota.colisionaCon(r);
		assertTrue(pelota.darVida()==0 && pelota.esVisible()==false);
		
		Nave n=new Nave();
		n.setX(50);
		n.setY(50);
		pelota.setVida(5);
		assertTrue(pelota.darVida()==5);
	}
	@Test
	void testExistenColisiones() {
		setupEscenario2();
		ProyectilNormal p=new ProyectilNormal();
		p.setX(50);
		p.setY(50);
		assertTrue(pelota.existenColisiones(p));
		
		p.setX(100);
		p.setY(100);
		assertTrue(pelota.existenColisiones(p));
		
		p.setX(40);
		p.setY(100);
		assertTrue(pelota.existenColisiones(p));
		
		p.setX(10);
		p.setY(20);
		assertFalse(pelota.existenColisiones(p));
	}
	@Test
	void testHayVivas() {
		setupEscenario2();
		assertTrue(pelota.hayVivas());
		
		pelota1.setVisible(false);
		assertTrue(pelota.hayVivas());
		
		pelota2.setVisible(false);
		pelota1.setVisible(true);
		assertTrue(pelota.hayVivas());
		
		pelota.setVisible(false);
		pelota1.setVisible(false);
		assertFalse(pelota.hayVivas());
	}
	void testDisminuirVida() {
		setupEscenario1();
		ProyectilNormal p=new ProyectilNormal();
		p.setX(50);
		p.setY(50);
		pelota.disminuirVida(p);
		assertTrue(pelota.darVida()==2);
		
		ProyectilRapido o=new ProyectilRapido();
		o.setX(50);
		o.setY(50);
		pelota.setVida(5);
		pelota.disminuirVida(o);
		assertTrue(pelota.darVida()==3);
		
		ProyectilFuerte r=new ProyectilFuerte();
		r.setX(50);
		r.setY(50);
		pelota.setVida(5);
		pelota.disminuirVida(r);
		assertTrue(pelota.darVida()==0 && pelota.esVisible()==false);
	}
	@Test
	void testInsertar() {
		setupEscenario3();
		pelota= new Pelota(1);
		pelota.setX(50);
		pelota.setY(500);
		juego.insertarPelota(pelota);
		assertTrue(juego.darRaizPelota()==pelota);
		
		pelota1=new Pelota(1);
		juego.insertarPelota(pelota1);
		assertTrue(juego.darRaizPelota().darPeso()==2);
		
		pelota2=new Pelota(1);
		pelota.setX(600);
		juego.insertarPelota(pelota2);
		assertTrue(juego.darRaizPelota().darPeso()==3);
	}
	@Test
	void testGetPelotas() {
		setupEscenario4();
		ArrayList<Pelota> a=juego.getPelotas();
		assertTrue(a.size()==3);
	}
	@Test
	void testMover() {
		setupEscenario1();
		pelota.setX(10);pelota.setY(10);
		pelota.setDX(10);
		pelota.setDY(10);
		pelota.mover();
		assertTrue(pelota.getX()==20 && pelota.getY()==20);
		
		pelota.setX(0);pelota.setY(0);
		pelota.setDX(20);
		pelota.setDY(20);
		pelota.mover();
		assertTrue(pelota.getDY()==20 && pelota.getDX()==20);
		
		pelota.setX(10);pelota.setY(0);
		pelota.setDX(0);
		pelota.setDY(10);
		pelota.mover();
		assertTrue(pelota.getDY()==10);
		
		pelota.setX(800);pelota.setY(10);
		pelota.setDX(10);
		pelota.setDY(10);
		pelota.mover();
		assertTrue(pelota.getDX()==10);
	}
}
