package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable, Comparable{
	
	private String nickname;
	private int puntaje;
	private int nivel;
	
	private Jugador izq;
	private Jugador der;

	public Jugador(String nickname) {
		super();
		this.nickname = nickname;
		
		izq=null;
		der=null;
	}

	public String getNickname() {
		return nickname;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Jugador j = (Jugador)arg0;
		return this.puntaje-j.getPuntaje();
	}
	
	public int compararNombre(Object arg0) {
		// TODO Auto-generated method stub
		Jugador j = (Jugador)arg0;
		return this.nickname.compareToIgnoreCase(j.getNickname());
	}
	
	public int compararNivel(Object arg0) {
		Jugador j = (Jugador)arg0;
		return this.nivel-j.getNivel();
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		Jugador j = (Jugador)arg0;
		return this.puntaje == j.getPuntaje() && this.nickname.equals(j.getNickname());
	}
	
	public Jugador buscarNombre (String nombre) {
		Jugador j = this;
		while (j!=null) {
			int comp = j.compareTo(nombre);
			
			if (comp == 0) {
				return j;
			}else if (comp>0) {
				j=j.izq;
			}else {
				j=j.der;
			}
		}
		return null;
	}
	
	
	public void insertar (Jugador j) {
		if (compararNombre(j)==0) {
			throw new JugadorRepetidoException ();
		}else if (compararNombre(j)>0) {
			if (izq == null)
				izq = j;
			else
				izq.insertar(j);
		}else {
			if (der==null)
				der=j;
			else
				der.insertar(j);
		}
	}
	
	public void crearArreglo(ArrayList<Jugador> a) {
		if(izq != null) {
			izq.crearArreglo(a);
		}
		a.add(this);
		if(der != null) {
			der.crearArreglo(a);
		}
		
	}
	
}
