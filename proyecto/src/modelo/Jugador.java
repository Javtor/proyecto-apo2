package modelo;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Clase Jugador, que se encarga de almacenar los datos del juego de cada uno
 * de los jugadores, Esta clase permite que se visualize la tabla de puntajes
 * del juego
 * @author  Javier Andres Torres, Maria Camila Lenis, Juan Sebastian Palma
 * @version 1.0
 *
 */
public class Jugador implements Serializable, Comparable{
	// Atributos
	/**
	 * String que almacena el nombre del jugador
	 */
	private String nickname;
	/**
	 * Entero que se encarga de almacenar el puntaje
	 */
	private int puntaje;
	/**
	 * Entero que se encarga de almacenar el nivel
	 */
	private int nivel;
	// Asosiciones
	/**
	 *  Raiz izquierda del jugador
	 */
	private Jugador izq;
	/**
	 * Raiz derecha del jugador
	 */
	private Jugador der;
	//Constructor
	/**
	 * Constructor de la clase Jugador, establese el nombre del jugador e instancia las raices como
	 * nulas
	 * @param nickname
	 * 				Contiene el nickname del jugador
	 */
	public Jugador(String nickname) {
		super();
		this.nickname = nickname;
		
		izq=null;
		der=null;
	}
	/**
	 * Este metodo da el atributo nickname
	 * @return Retorna un String con el nombre del jugador
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Este metodo da el puntaje del jugador
	 * @return Retorna un entero con el puntaje del jugador
	 */
	public int getPuntaje() {
		return puntaje;
	}
	/**
	 * Este metodo retorna el nivel al que llego el jugador
	 * @return Reotrna un entero con el Nivel l que llego el Jugador
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * Este metodo modifica el atributo nickname
	 * @param nickname
	 * 			Este es un String que contiene el nombre del jugador
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * Modifica el valor del puntaje
	 * @param puntaje
	 * 			Este es un valor entero que contiene el puntaje
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	/**
	 * Este metodo modifica el nivel del jugador
	 * @param nivel
	 * 		Este es un valor entero que tiene el nivel 
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	/**
	 * Este metodo retorna una de las Raices
	 * @return Retorna la raiz Izquierda del jugador( Objeto tipo Jugador)
	 */
	public Jugador getIzq() {
		return izq;
	}
	/**
	 *  Este metodo retorna una de las Raices
	 * @return Retorna la raiz Derecha del jugador( Objeto tipo Jugador)
	 */
	public Jugador getDer() {
		return der;
	}
	/**
	 * Modifica la raiz izquierda del jugador
	 * @param izq
	 * 			Este es un objeto del tipo Jugador
	 */
	public void setIzq(Jugador izq) {
		this.izq = izq;
	}
	/**
	 * Este metodo modifica la raiz derecha del Jugador
	 * @param der
	 * 			Este es un objeto del tipo Jugador
	 */
	public void setDer(Jugador der) {
		this.der = der;
	}
	/**
	 * Metodo sobreescrito de la clase Comparator, Se encarga de comparar
	 * los puntajes entre jugadores.
	 * @param arg0
	 * 			Objeto que prefiriblemente debe ser del tipo jugador
	 */
	@Override
	public int compareTo(Object arg0) {
		Jugador j = (Jugador)arg0;
		return this.puntaje-j.getPuntaje();
	}
	/**
	 * Este metodo compara los nombres del Jugador
	 * @param nombre
	 * 			Este es el nombre al cual se debe de comparar
	 * @return Retorna un valor entero si el nombre es o no es igual; si no es igual denomina si se ubica abajo o encima de manera alfabetica
	 */
	public int compararNombre(String nombre) {
		return this.nickname.compareToIgnoreCase(nombre);
	}
	/**
	 * Este metodo se encarga de comparar el nivel del Jugador
	 * @param arg0
	 * 			Es un objeto de preferencia Jugador
	 * @return Retorna un valor Negativo si el nivel es menor al comparado y positivo si es mayor
	 */
	public int compararNivel(Object arg0) {
		Jugador j = (Jugador)arg0;
		return this.nivel-j.getNivel();
	}
	/**
	 * Metodo sobreescrito de la clase Comparable
	 * Este metodo se encarga de comparar los puntajes del jugador con el que se le manda
	 * @param arg0
	 * 			Objeto preferiblemente del tipo Jugador
	 * @return Retorn un valor booleano si es o no es igual el puntaje
	 */
	@Override
	public boolean equals(Object arg0) {
		Jugador j = (Jugador)arg0;
		return this.puntaje == j.getPuntaje() && this.nickname.equals(j.getNickname());
	}
	/**
	 * Este metodo se encarga de encontrar el nombre dentro del arbol
	 * @param nombre
	 * 			String que contiene el nombre que se desea buscar
	 * @return Retorna Un objeto del tipo jugador si encuentra el nombre deseado; Sino manda
	 * un objeto null
	 */
	public Jugador buscarNombre (String nombre) {
		Jugador j = this;
		while (j!=null) {
			int comp = j.compararNombre(nombre);
			
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
	/**
	 * Este metodo se encarga de insertar un jugador en una de la raices, si estan ocupadas
	 * lo manda al siguiente objeto a revisar si lo puede intsertar
	 * @param j
	 * 			Un objeto del ripo Jugador
	 * @throws JugadorRepetidoException
	 */
	public void insertar (Jugador j) throws JugadorRepetidoException{
		if (compararNombre(j.getNickname())==0) {
			throw new JugadorRepetidoException (this);
		}else if (compararNombre(j.getNickname())>0) {
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
	/**
	 * Este metodo se encarga de Crear un Arreglo con los objetos del arbol
	 * @param a
	 * 			Es el arreglo del tipo Jugador que va a contener todos los ugadores
	 */
	public void crearArreglo(ArrayList<Jugador> a) {
		if(izq != null) {
			izq.crearArreglo(a);
		}
		a.add(this);
		if(der != null) {
			der.crearArreglo(a);
		}
		
	}
	/**
	 * Metodo sobre escrito de la clase String, Este metodo se encarga de crear un String con
	 * el nombre, puntaje y nivel separados por guiones
	 * @return Retorna un String con todos los datos de los atributos
	 */
	@Override
	public String toString () {
		return nickname +" - "+puntaje+" - "+nivel;
	}
	
}
