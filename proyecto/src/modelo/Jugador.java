package modelo;

import java.io.Serializable;

public class Jugador implements Serializable, Comparable{
	
	private String nickname;
	private int puntaje;

	public Jugador(String nickname) {
		super();
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}
	
	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
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
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		Jugador j = (Jugador)arg0;
		return this.puntaje == j.getPuntaje() && this.nickname.equals(j.getNickname());
	}
	
}
